package polytech.stock;

import java.util.ArrayList;
import java.util.List;

import polytech.personnes.Arbitre;
import polytech.personnes.Joueur;
import polytech.personnes.Organisateur;

import polytech.jtournoi.Equipe;
import polytech.jtournoi.TypeEpreuve;

import polytech.stock.SQL.ArbitreSQL;
import polytech.stock.XML.ArbitreXML;
import polytech.stock.SQL.JoueurSQL;
import polytech.stock.XML.JoueurXML;
import polytech.stock.SQL.OrganisateurSQL;
import polytech.stock.XML.OrganisateurXML;
import polytech.stock.SQL.EquipeSQL;
import polytech.stock.XML.EquipeXML;

public abstract class Stock
{
	protected static List<Arbitre> arbitres;
	protected static List<Joueur> joueurs;
	protected static List<Organisateur> organisateurs;
	protected static List<TypeEpreuve> typesEpreuves;
	protected static List<Equipe> equipes;

	public static void initialiserStockVide()
	{
		arbitres = new ArrayList<Arbitre>();
		joueurs = new ArrayList<Joueur>();
		organisateurs = new ArrayList<Organisateur>();
		equipes = new ArrayList<Equipe>();
		
		typesEpreuves = CatalogueEpreuves.recupererTypesEpreuves();
	}

	public static void chargerStock(TypeChargement mode)
	{
		GestionnaireDeStock gestionArbitres = null;
		GestionnaireDeStock gestionJoueurs = null;
		GestionnaireDeStock gestionOrganisateurs = null;
		GestionnaireDeStock gestionEquipes = null;

		switch (mode)
		{
			case SQL:
				gestionArbitres = new ArbitreSQL(); 
				gestionJoueurs = new JoueurSQL(); 
				gestionOrganisateurs = new OrganisateurSQL(); 
				gestionEquipes = new EquipeSQL();
				break;
			case XML:
				gestionArbitres = new ArbitreXML(); 
				gestionJoueurs = new JoueurXML(); 
				gestionOrganisateurs = new OrganisateurXML(); 
				gestionEquipes = new EquipeXML();
				break;
		}

		typesEpreuves = CatalogueEpreuves.recupererTypesEpreuves();
		
		arbitres = gestionArbitres.recupererStock();
		joueurs = gestionJoueurs.recupererStock();
		organisateurs = gestionOrganisateurs.recupererStock();
		equipes = gestionEquipes.recupererStock();
	}

	public static void enregistrerStock()
	{
		new ArbitreXML().enregistrerStock(arbitres);
		new JoueurXML().enregistrerStock(joueurs);
		new OrganisateurXML().enregistrerStock(organisateurs);
		new EquipeXML().enregistrerStock(equipes);
		
		/*new ArbitreSQL().enregistrerStock(arbitres);
		new JoueurSQL().enregistrerStock(joueurs);
		new OrganisateurSQL().enregistrerStock(organisateurs);
		new EquipeSQL().enregistrerStock(equipes);*/
	}

	public static List<Arbitre> getArbitres()
	{
		return arbitres;
	}
	public static void setArbitres(List<Arbitre> arbitres)
	{
		Stock.arbitres = arbitres;
	}
	public static List<Joueur> getJoueurs()
	{
		return joueurs;
	}
	public static void setJoueurs(List<Joueur> joueurs)
	{
		Stock.joueurs = joueurs;
	}

	public static void addJoueur(Joueur j)
	{
		if (!joueurs.contains(j))
		{
			joueurs.add(j);
		}
	}

	public static List<Organisateur> getOrganisateurs()
	{
		return organisateurs;
	}
	public static void setOrganisateurs(List<Organisateur> organisateurs)
	{
		Stock.organisateurs = organisateurs;
	}
	
	public static List<Arbitre> getArbitresLibres()
	{
		List<Arbitre> arbitresLibres = new ArrayList<Arbitre>();
		
		for (Arbitre a : arbitres)
		{
			if (a.getBusy())
			{
				arbitresLibres.add(a);
			}
		}
		
		return arbitresLibres;
	}
	
	public static Arbitre getRandomArbitreLibre(){
		List<Arbitre> arbitresLibres = getArbitresLibres();
		int i = (int)Math.random()*(arbitresLibres.size()-1);
		return arbitresLibres.get(i);
	}

	public static List<TypeEpreuve> getTypesEpreuves()
	{
		return typesEpreuves;
	}

	public static void setTypesEpreuves(List<TypeEpreuve> typesEpreuves)
	{
		Stock.typesEpreuves = typesEpreuves;
	}

	public static List<Equipe> getEquipes()
	{
		return equipes;
	}

	public static void setEquipes(List<Equipe> equipes)
	{
		Stock.equipes = equipes;
	}
	
	public static void addEquipe(Equipe e)
	{
		if (!equipes.contains(e))
		{
			equipes.add(e);
		}
	}

	protected static <CLASS_TYPE> CLASS_TYPE getById(List<CLASS_TYPE> liste, int id)
	{
		if (liste != null)
		{
			for (CLASS_TYPE t : liste)
			{
				if (((TupleAvecID) t).getId() == id)
				{
					return t;
				}
			}
		}
		return null;
	}

	public static TypeEpreuve getTypeEpreuveParId(int id)
	{
		return getById(typesEpreuves, id);
	}
	
	public static Joueur getJoueurParId(int id)
	{
		return getById(joueurs, id);
	}
}
