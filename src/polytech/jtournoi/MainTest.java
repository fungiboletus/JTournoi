package polytech.jtournoi;

import java.util.ArrayList;
import java.util.HashMap;

import polytech.exception.EpreuveDejaExistanteException;
import polytech.exception.NombreDeParticipantInsufisantException;
import polytech.exception.TournoiNonLanceException;
import polytech.exception.TournoiNonValideExcetion;
import polytech.personnes.Joueur;
import polytech.stock.Stock;
import polytech.stock.TypeChargement;

public class MainTest {
	
	public static Joueur creerJoueurCompetent(int d) {
		Joueur j = new Joueur("nom", "prenom", "psswd");
		j.setCompetences(getCompetence(d));
		return j;
	}

	public static Equipe creerEquipe(int d, int k) {
		Equipe e = new Equipe("equipe " + k);
		for (int i = 0; i < 5; i++) {
			e.ajouterParticipant(creerJoueurCompetent(d));
			e.setEpreuves(getCompetence(d));
		}
		return e;
	}

	public static ArrayList<TypeEpreuve> getCompetence(int d) {
		ArrayList<TypeEpreuve> epreuves = new ArrayList<TypeEpreuve>();
		for (int i = d; i < d + 5; i++) {
			epreuves.add(Stock.getTypesEpreuves().get(i));
		}
		return epreuves;
	}

	public static ArrayList<Equipe> creeEquipe(int k) {
		ArrayList<Equipe> equipes = new ArrayList<Equipe>();
		for (int i = 0; i < k; i++) {
			equipes.add(creerEquipe(0, i));
		}
		return equipes;
	}
	
	/**
	 * @param args 
	 */
	public static void main(String[] args)  {
		Stock.chargerStock(TypeChargement.SQL);
		Moteur.init();
		ArrayList<Equipe> listeEquipe = creeEquipe(3);
		ArrayList<TypeEpreuve> listeEpreuve = getCompetence(0);
		Tournoi t = Moteur.creerTournoi("test", listeEpreuve );
		HashMap<Equipe,Joueur> map = new HashMap<Equipe,Joueur>();
		for(Equipe e : listeEquipe){
			map.put(e, e.getJoueur(0));
		}

		for(TypeEpreuve te : listeEpreuve){
			try {
				Moteur.setEpreuve(t, te, map);
			} catch (NombreDeParticipantInsufisantException e1) {
				System.out.println("fail");
			} catch (EpreuveDejaExistanteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (TournoiNonLanceException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		try {
			Moteur.startTournoi(t);
		} catch (TournoiNonValideExcetion e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Epreuve e = (t.getEpreuves().get(0));
			System.out.println(e.getCurrentMatch());
		} catch (TournoiNonLanceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
