package polytech.jtournoi;

import java.util.ArrayList;
import java.util.HashMap;

import polytech.personnes.Arbitre;
import polytech.tools.Tools;

public abstract class Moteur {

	static Tournoi t;
	
	public static Tournoi creerTournoi(ArrayList<Equipe> equipes, ArrayList<TypeEpreuve> epreuves) throws Exception{
		if(Tools.epreuveSansEquipe(epreuves,equipes)){
			throw new EpreuveSansEquipeException();
		}
		else{
			t = new Tournoi(equipes,epreuves);
		}
		return t;
	}
	
	public void startTournoi(Tournoi t){
		t.startTournoi();
	}
	
	public HashMap<TypeEpreuve,ArrayList<Match>> getCurrentsMatchByEpreuve(Tournoi t){
		ArrayList<Epreuve> epreuves = t.getEpreuves();
		HashMap<TypeEpreuve,ArrayList<Match>> map = new HashMap<TypeEpreuve,ArrayList<Match>>();
		for(Epreuve e : epreuves){
			map.put(e.getTypeEpreuve(), e.getCurentMatch());
		}
		return map;
	}
	
	public ArrayList<Match> getCurrentMatch(Tournoi t){
		ArrayList<Match> match = new ArrayList<Match>();
		ArrayList<Epreuve> epreuves = t.getEpreuves();
		for(Epreuve e : epreuves){
			for(Match m : e.getCurentMatch()){
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
	
	public void getAllMatch (){
		HashMap<TypeEpreuve,ArrayList<Match>> map = getCurrentsMatchByEpreuve(t);
		for(TypeEpreuve te : map.keySet()){
			System.out.println(map.get(te+"\n"));
		}
	}
	
	
	
}
