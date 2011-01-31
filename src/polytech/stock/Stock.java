package polytech.stock;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import polytech.personnes.Arbitre;
import polytech.personnes.Joueur;
import polytech.personnes.Organisateur;

import polytech.exception.ArbitreDejaExistant;
import polytech.jtournoi.Epreuve;
import polytech.jtournoi.Equipe;
import polytech.jtournoi.TypeEpreuve;
import polytech.jtournoi.Match;

import polytech.stock.SQL.ArbitreSQL;
import polytech.stock.SQL.EpreuveSQL;
import polytech.stock.SQL.GestionSQL;
import polytech.stock.XML.ArbitreXML;
import polytech.stock.XML.EpreuveXML;
import polytech.stock.SQL.JoueurSQL;
import polytech.stock.XML.JoueurXML;
import polytech.stock.SQL.OrganisateurSQL;
import polytech.stock.XML.OrganisateurXML;
import polytech.stock.SQL.EquipeSQL;
import polytech.stock.XML.EquipeXML;
import polytech.stock.SQL.MatchSQL;
import polytech.stock.XML.MatchXML;

/**
 * @author Antoine Pultier Le stock sert à contenir les références de tout les
 *         objets utilisés au cours de l'exécution du programme.
 * 
 *         Il permet d'enregistrer ces objets à l'aide de différentes méthodes,
 *         pour qu'ils soient rechargés plus tard.
 * 
 *         Il convient de déclarer au stock les objets du programmes qui doivent
 *         être sauvegardés. Sans cela, le programme risque de ne pas
 *         fonctionner lors d'un nouveau lancement.
 */
public abstract class Stock {
	protected static List<Arbitre> arbitres;
	protected static List<Joueur> joueurs;
	protected static List<Organisateur> organisateurs;
	protected static List<TypeEpreuve> typesEpreuves;
	protected static List<Equipe> equipes;
	protected static List<Match> matchs;
	protected static List<Epreuve> epreuves;

	/**
	 * Fonction permettant d'obtenir un nouveau stock vide, qui ne contient pas
	 * de références aux anciens objets.
	 * 
	 * Cette fonction est dangereuse, car si le stock est ensuite sauvegardé,
	 * les anciennes données sont perdues.
	 */
	public static void initialiserStockVide() {
		arbitres = new ArrayList<Arbitre>();
		joueurs = new ArrayList<Joueur>();
		organisateurs = new ArrayList<Organisateur>();
		equipes = new ArrayList<Equipe>();
		matchs = new ArrayList<Match>();
		epreuves = new ArrayList<Epreuve>();
		typesEpreuves = CatalogueEpreuves.recupererTypesEpreuves();

		gererOrganisateurRacine();
	}

	/**
	 * Charge dans le stock les données sauvegardées.
	 * 
	 * @param mode
	 *            Mode de chargement du stock.
	 */
	public static void chargerStock(TypeChargement mode) {
		GestionnaireDeStock gestionArbitres = null;
		GestionnaireDeStock gestionJoueurs = null;
		GestionnaireDeStock gestionOrganisateurs = null;
		GestionnaireDeStock gestionEquipes = null;
		GestionnaireDeStock gestionMatchs = null;
		GestionnaireDeStock gestionEpreuves = null;

		switch (mode) {
		case SQL:
			// Vérification anti noobs
			if (!(new File("canard.db").exists())) {
				System.out
						.println("La base de données n'existe pas…\nLe stock est donc vide.");
				initialiserStockVide();
				return;
			}
			gestionArbitres = new ArbitreSQL();
			gestionJoueurs = new JoueurSQL();
			gestionOrganisateurs = new OrganisateurSQL();
			gestionEquipes = new EquipeSQL();
			gestionMatchs = new MatchSQL();
			gestionEpreuves = new EpreuveSQL();
			break;
		case XML:
			// Vérification anti noobs
			if (!(new File("organisateurs.xml").exists())) {
				System.out
						.println("Un fichier xml de base est introuvable…\nLe stock est donc vide.");
				initialiserStockVide();
				return;
			}
			gestionArbitres = new ArbitreXML();
			gestionJoueurs = new JoueurXML();
			gestionOrganisateurs = new OrganisateurXML();
			gestionEquipes = new EquipeXML();
			gestionMatchs = new MatchXML();
			gestionEpreuves = new EpreuveXML();
			break;
		}

		typesEpreuves = CatalogueEpreuves.recupererTypesEpreuves();

		arbitres = gestionArbitres.recupererStock();
		joueurs = gestionJoueurs.recupererStock();
		organisateurs = gestionOrganisateurs.recupererStock();

		equipes = gestionEquipes.recupererStock();

		matchs = gestionMatchs.recupererStock();

		epreuves = gestionEpreuves.recupererStock();

		gererOrganisateurRacine();
	}

	/**
	 * Enregistre avec tout les moyens possible le stock.
	 */
	public static void enregistrerStock() {
		new ArbitreXML().enregistrerStock(arbitres);
		new JoueurXML().enregistrerStock(joueurs);
		new OrganisateurXML().enregistrerStock(organisateurs);
		new EquipeXML().enregistrerStock(equipes);
		new MatchXML().enregistrerStock(matchs);
		new EpreuveXML().enregistrerStock(epreuves);

		// Pour les gens qui jugent utile de sauvegarder plusieurs fois par
		// exécution
		GestionSQL.resetSuppressionAutomatique();

		new ArbitreSQL().enregistrerStock(arbitres);
		new JoueurSQL().enregistrerStock(joueurs);
		new OrganisateurSQL().enregistrerStock(organisateurs);
		new EquipeSQL().enregistrerStock(equipes);
		new MatchSQL().enregistrerStock(matchs);
		new EpreuveSQL().enregistrerStock(epreuves);

		gererOrganisateurRacine();
	}

	/**
	 * Gestion de l'organisateur racine.
	 * 
	 * Il faut s'assurer qu'il y ai au moins un organisateur racine.
	 * 
	 * Cet utilisateur est enregistré dans la base de données, ce qui permet de
	 * modifier ses informations comme son mot de passe.
	 * 
	 * Mais il doit être créé automatiquement lors du premier lancement.
	 */
	public static void gererOrganisateurRacine() {
		// no comment
		boolean contains = false;

		for (Organisateur o : organisateurs) {
			if (o.getNom().equals("root")) {
				contains = true;
			}
		}

		if (!contains) {
			addOrganisateur(new Organisateur("root", "root", "password"));
		}
	}

	public static List<Arbitre> getArbitres() {
		return arbitres;
	}

	public static void setArbitres(List<Arbitre> arbitres) {
		Stock.arbitres = arbitres;
	}

	public static List<Joueur> getJoueurs() {
		return joueurs;
	}

	public static void setJoueurs(List<Joueur> joueurs) {
		Stock.joueurs = joueurs;
	}

	public static void addJoueur(Joueur j) {
		if (!joueurs.contains(j)) {
			joueurs.add(j);
		}
	}

	public static List<Organisateur> getOrganisateurs() {
		return organisateurs;
	}

	public static void setOrganisateurs(List<Organisateur> organisateurs) {
		Stock.organisateurs = organisateurs;
	}

	public static void addOrganisateur(Organisateur o) {
		if (!organisateurs.contains(o)) {
			organisateurs.add(o);
		}
	}

	/**
	 * Récupère une liste d'arbitres libres.
	 * 
	 * @return Liste d'arbitres libres.
	 */
	public static List<Arbitre> getArbitresLibres(TypeEpreuve te) {
		List<Arbitre> arbitresLibres = new ArrayList<Arbitre>();

		for (Arbitre a : arbitres) {
			if (!a.getBusy() && a.getCompetences().contains(te)) {
				arbitresLibres.add(a);
			}
		}

		return arbitresLibres;
	}

	/**
	 * Récupérère un arbitre libre sélectionné aléatoirement.
	 * 
	 * @return Arbitre libre sélectionné aléatoirement.
	 */
	public static Arbitre getRandomArbitreLibre(TypeEpreuve te) {
		List<Arbitre> arbitresLibres = getArbitresLibres(te);
		int i = (int) Math.random() * (arbitresLibres.size() - 1);
		return arbitresLibres.get(i);
	}

	public static List<TypeEpreuve> getTypesEpreuves() {
		return typesEpreuves;
	}

	
	public static void addTypesEpreuve(TypeEpreuve type)
    {
        typesEpreuves.add(type);
    }
	
	public static List<Equipe> getEquipe(){
		return equipes;
	}

	public static void setTypesEpreuves(List<TypeEpreuve> typesEpreuves) {
		Stock.typesEpreuves = typesEpreuves;
	}

	public static void setEquipes(List<Equipe> equipes) {
		Stock.equipes = equipes;
	}

	public static List<Match> getMatchs() {
		return matchs;
	}

	public static void setMatchs(List<Match> matchs) {
		Stock.matchs = matchs;
	}

	public static List<Epreuve> getEpreuves() {
		return epreuves;
	}

	public static void setEpreuves(List<Epreuve> epreuves) {
		Stock.epreuves = epreuves;
	}

	public static void addEquipe(Equipe e) {
		if (!equipes.contains(e)) {
			equipes.add(e);
		}
	}
	
	public static void addArbitre(Arbitre a) throws ArbitreDejaExistant{
        if (!arbitres.contains(a))
        {
            arbitres.add(a);
        }
        else{
            throw new ArbitreDejaExistant();
        }
    }

	/**
	 * Recherche dans une liste donnée, un TupleAvecID à partir de son ID.
	 * 
	 * @param <CLASS_TYPE>
	 * @param liste
	 *            Liste dans lequel cherché la référence du tuple.
	 * @param id
	 *            Identifiant du tuple recherché
	 * @return Tuple correspondant à l'identifiant recherché
	 */
	protected static <CLASS_TYPE> CLASS_TYPE getById(List<CLASS_TYPE> liste,
			int id) {
		if (liste != null) {
			for (CLASS_TYPE t : liste) {
				if (((TupleAvecID) t).getId() == id) {
					return t;
				}
			}
		}
		return null;
	}

	/**
	 * Recherche le typeepreuve du stock correspondant à un identifiant.
	 * 
	 * @param id
	 *            Identifiant de l'objet
	 * @return TypeEpreuve
	 */
	public static TypeEpreuve getTypeEpreuveParId(int id) {
		return getById(typesEpreuves, id);
	}

	/**
	 * Recherche le joueur du stock correspondant à un identifiant.
	 * 
	 * @param id
	 *            Identifiant de l'objet
	 * @return Joueur
	 */
	public static Joueur getJoueurParId(int id) {
		return getById(joueurs, id);
	}

	/**
	 * Recherche l'équipe du stock correspondant à un identifiant.
	 * 
	 * @param id
	 *            Identifiant de l'objet
	 * @return Equipe
	 */
	public static Equipe getEquipeParId(int id) {
		return getById(equipes, id);
	}

	/**
	 * Recherche l'arbitre du stock correspondant à un identifiant.
	 * 
	 * @param id
	 *            Identifiant de l'objet
	 * @return Arbitre
	 */
	public static Arbitre getArbitreParId(int id) {
		return getById(arbitres, id);
	}

	/**
	 * Recherche le match du stock correspondant à un identifiant.
	 * 
	 * @param id
	 *            Identifiant de l'objet
	 * @return Match
	 */
	public static Match getMatchParId(int id) {
		return getById(matchs, id);
	}
}
