package polytech.jtournoi;

import java.util.ArrayList;
import java.util.HashMap;

import polytech.exception.EpreuveDejaExistanteException;
import polytech.exception.NombreDeParticipantInsufisantException;
import polytech.exception.TournoiDejaLanceException;
import polytech.exception.TournoiNonLanceException;
import polytech.exception.nbrArbitreInsufisantException;
import polytech.personnes.Joueur;
import polytech.stock.TupleAvecID;

/**
 * Gestion d'un tournoi
 * 
 * @author Muller Stéphane
 * 
 * 
 */
public class Tournoi extends TupleAvecID {

	Boolean tournoilance = false;
	String nom;
	ArrayList<Equipe> equipes = new ArrayList<Equipe>();
	ArrayList<TypeEpreuve> typeEpreuves = new ArrayList<TypeEpreuve>();
	HashMap<TypeEpreuve, HashMap<Equipe, Joueur>> map = new HashMap<TypeEpreuve, HashMap<Equipe, Joueur>>();
	ArrayList<Epreuve> epreuves = new ArrayList<Epreuve>();

	public Tournoi(String nom, ArrayList<TypeEpreuve> te) {
		typeEpreuves = te;
		this.nom = nom;
	}
	
	/**
	 * Méthode associant la liste des equipes au type d'epreuve
	 * @param te
	 * @param equipes
	 * @throws NombreDeParticipantInsufisantException
	 * @throws EpreuveDejaExistanteException
	 * @throws TournoiNonLanceException
	 */
	public void setEpreuve(TypeEpreuve te, HashMap<Equipe, Joueur> equipes)
			throws NombreDeParticipantInsufisantException,
			EpreuveDejaExistanteException, TournoiNonLanceException {
		int inc = 0;
		if (tournoilance == false) {
			throw new TournoiNonLanceException();
		}
		for (Equipe e : equipes.keySet()) {
			if (e.getEpreuves().contains(te)) {
				inc++;
			}
		}
		if (inc < 1) {
			throw new NombreDeParticipantInsufisantException();
		}
		if (map.containsKey(te)) {
			throw new EpreuveDejaExistanteException();
		}
		map.put(te, equipes);
	}

	/**
	 * Méthode permettant de verifier si on peut lancer le tournoi
	 * @throws NombreDeParticipantInsufisantException
	 */
	public void verificationTournoi()
			throws NombreDeParticipantInsufisantException {
		if (typeEpreuves.size() != 0) {
			// System.out.println(map);
			for (TypeEpreuve te : typeEpreuves) {
				// on vérifie que pour chaque type d'épreuve on ait au moins
				// deux équipes pour la joueur
				if (!map.containsKey(te) || map.get(te).size() < 2) {
					throw new NombreDeParticipantInsufisantException();
				}
			}
		}
	}

	/**
	 * Ajoute une équipe pour le type d'épreuve donné Si le type est individuel
	 * alors on spécifie aussi le nom du joueur Sinon on passe null
	 * 
	 * @param e
	 * @param j
	 * @param te
	 * @return
	 * @throws TournoiDejaLanceException
	 */
	public boolean addEquipe(Equipe e, Joueur j, TypeEpreuve te)
			throws TournoiDejaLanceException {
		if (tournoilance == true) {
			throw new TournoiDejaLanceException();
		}
		if (map.containsKey(te)) {
			if (!map.get(te).containsKey(e)) {
				map.get(te).put(e, j);
				return true;
			}
		}
		return false;
	}

	/**
	 * On supprime l'equipe dans l'epreuve du type d'epreuve donne
	 * 
	 * @param te
	 * @return
	 * @throws TournoiNonLanceException
	 * @throws TournoiDejaLanceException
	 */
	public boolean supprimerEpreuve(TypeEpreuve te)
			throws TournoiNonLanceException {
		if (tournoilance == false) {
			throw new TournoiNonLanceException();
		}
		if (typeEpreuves.contains(te)) {
			for (Epreuve e : epreuves) {
				if (e.getTypeEpreuve().equals(e)) {
					epreuves.remove(e);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Ajoute un type d'epreuve dans la liste du tournoi
	 * 
	 * @param te
	 * @return
	 * @throws TournoiNonLanceException
	 * @throws TournoiDejaLanceException
	 */
	public boolean addTypeEpreuve(TypeEpreuve te)
			throws TournoiNonLanceException, TournoiDejaLanceException {
		if (tournoilance == true) {
			throw new TournoiDejaLanceException();
		}
		if (!typeEpreuves.contains(te)) {
			typeEpreuves.add(te);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Renvoie l'epreuve par rapport au au type d'epreuve donne
	 * 
	 * @param te
	 * @return
	 * @throws TournoiNonLanceException
	 */
	public Epreuve getEpreuve(TypeEpreuve te) throws TournoiNonLanceException {
		if (tournoilance == false) {
			throw new TournoiNonLanceException();
		}
		for (Epreuve e : epreuves) {
			if (e.getTypeEpreuve().equals(te)) {
				return e;
			}
		}
		return null;
	}

	/**
	 * Methode renvoyant les epreuves
	 * @return
	 * @throws TournoiNonLanceException
	 */
	public ArrayList<Epreuve> getEpreuves() throws TournoiNonLanceException {
		//on vérifie que le tournoi a été lancé pour pouvoir recuperer les epreuves
		if (tournoilance == false) {
			throw new TournoiNonLanceException();
		}
		return epreuves;
	}

	public ArrayList<Equipe> getEquipes() {
		return equipes;
	}

	public ArrayList<TypeEpreuve> getTypeEpreuves() {
		return typeEpreuves;
	}

	/**
	 * Méthode de lancement du tournoi
	 * 
	 * @throws nbrArbitreInsufisantException
	 */
	public void startTournoi() throws nbrArbitreInsufisantException {
		try {
			verificationTournoi();
		} catch (NombreDeParticipantInsufisantException e1) {
			throw new RuntimeException("t'as pas lancé la vérification ...");
		}
		tournoilance = true;
		for (TypeEpreuve te : typeEpreuves) {
			Epreuve e = new Epreuve(map.get(te), te);
			epreuves.add(e);
		}
	}
	/**
	 * Méthode d'affichage
	 */
	public String toString() {
		String s = "";
		if (tournoilance == true) {
			s = "Bienvenue dans le tournoi : " + nom + "\n";
			for (Epreuve e : epreuves) {
				s += e.toString();
			}
			s += "\n";
		} else {
			s += "µLe tournoi n'a pas encore été lancé";
		}
		return s;
	}

	public boolean isLance() {
		return tournoilance;
	}

}
