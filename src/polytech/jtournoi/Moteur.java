package polytech.jtournoi;

import java.util.ArrayList;
import java.util.HashMap;

import polytech.exception.EpreuveDejaExistanteException;
import polytech.exception.NombreDeParticipantInsufisantException;
import polytech.personnes.Arbitre;
import polytech.personnes.Joueur;

public abstract class Moteur {

	public static ArrayList<Tournoi> listeTournoi;
	
	public static void init(){
		listeTournoi = new ArrayList<Tournoi>();
	}
	
	/**
	 * Crée un tournoi avec son nom et l'ensemble des types d'épreuve
	 * @param nom
	 * @param epreuves
	 * @return
	 */
	public static Tournoi creerTournoi(String nom, ArrayList<TypeEpreuve> epreuves) {
		Tournoi t = new Tournoi(nom,epreuves);
		listeTournoi.add(t);
		return t;
	}
	
	/**
	 * Méthode permettant de lancer le tournoi
	 * @param t
	 * @throws Exception
	 */
	public static void startTournoi(Tournoi t) throws Exception{
		if(t.verificationTournoi()){
			t.startTournoi();
		}
		else{
			throw new Exception("Echec de la vérification du tournoi");
		}
	}
	
	/**
	 * Ajouter l'épreuve au tournoi
	 * @param t
	 * @param te
	 * @param equipes
	 * @throws NombreDeParticipantInsufisantException
	 * @throws EpreuveDejaExistanteException
	 */
	public static void setEpreuve(Tournoi t, TypeEpreuve te,
			HashMap<Equipe, Joueur> equipes)
			throws NombreDeParticipantInsufisantException,
			EpreuveDejaExistanteException {
		if (t.getTypeEpreuves().contains(te)) {
			t.setEpreuve(te, equipes);
		}
	}
	
	/**
	 * Renvoie tout les matchs d'un tournoi
	 * @param t
	 * @return une hashmap de type d'épreuve et de match
	 */
	public HashMap<TypeEpreuve,ArrayList<Match>> getCurrentsMatchByEpreuve(Tournoi t){
		ArrayList<Epreuve> epreuves = t.getEpreuves();
		HashMap<TypeEpreuve,ArrayList<Match>> map = new HashMap<TypeEpreuve,ArrayList<Match>>();
		for(Epreuve e : epreuves){
			map.put(e.getTypeEpreuve(), e.getCurrentMatch());
		}
		return map;
	}
	
	/**
	 * Renvoie l'ensemble des matchs d'un tournoi
	 * @param t
	 * @return
	 */
	public static ArrayList<Match> getCurrentMatch(Tournoi t){
		ArrayList<Match> match = new ArrayList<Match>();
		ArrayList<Epreuve> epreuves = t.getEpreuves();
		for(Epreuve e : epreuves){
			for(Match m : e.getCurrentMatch()){
				match.add(m);
			}
		}
		return match;
	}
	/**
	 * Renvoie le match arbitré par l'arbitre dans le Tournoi en question
	 * @param a
	 * @param t
	 * @return
	 */
	public static Match getMatch(Arbitre a, Tournoi t){
		for(Match m : getCurrentMatch(t)){
			if(m.getArbitre()==a){
				int i = getCurrentMatch(t).indexOf(m);
				return getCurrentMatch(t).get(i);
			}
		}
		return null;
	}

	public static ArrayList<Tournoi> getTournois(){
		return listeTournoi;
	}
	
	
}
