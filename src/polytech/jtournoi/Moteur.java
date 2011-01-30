package polytech.jtournoi;

import java.util.ArrayList;
import java.util.HashMap;

import polytech.personnes.Arbitre;
import polytech.personnes.Joueur;

public abstract class Moteur {

	

	public static Tournoi creerTournoi(String nom, ArrayList<TypeEpreuve> epreuves) {
		Tournoi t = new Tournoi(nom,epreuves);
		return t;
	}
	
	public static void startTournoi(Tournoi t) throws Exception{
		if(t.verificationTournoi()){
			t.startTournoi();
		}
		else{
			throw new Exception("Echec de la vérification du tournoi");
		}
	}
	
	public static boolean setEpreuve(Tournoi t, TypeEpreuve te,HashMap<Equipe,Joueur> equipes){
	    if(t.getTypeEpreuves().contains(te)){
			if(t.setEpreuve(te,equipes)){
				return true;
			}
		}
		return false;
	}
	
	public HashMap<TypeEpreuve,ArrayList<Match>> getCurrentsMatchByEpreuve(Tournoi t){
		ArrayList<Epreuve> epreuves = t.getEpreuves();
		HashMap<TypeEpreuve,ArrayList<Match>> map = new HashMap<TypeEpreuve,ArrayList<Match>>();
		for(Epreuve e : epreuves){
			map.put(e.getTypeEpreuve(), e.getCurrentMatch());
		}
		return map;
	}
	
	public ArrayList<Match> getCurrentMatch(Tournoi t){
		ArrayList<Match> match = new ArrayList<Match>();
		ArrayList<Epreuve> epreuves = t.getEpreuves();
		for(Epreuve e : epreuves){
			for(Match m : e.getCurrentMatch()){
				match.add(m);
			}
		}
		return match;
	}
	
	public Match getMatch(Arbitre a, Tournoi t){
		for(Match m : getCurrentMatch(t)){
			if(m.getArbitre()==a){
				int i = getCurrentMatch(t).indexOf(m);
				return getCurrentMatch(t).get(i);
			}
		}
		return null;
	}
	
	public void getAllMatch (Tournoi t){
		HashMap<TypeEpreuve,ArrayList<Match>> map = getCurrentsMatchByEpreuve(t);
		for(TypeEpreuve te : map.keySet()){
			System.out.println(map.get(te+"\n"));
		}
	}
	
	
	
}
