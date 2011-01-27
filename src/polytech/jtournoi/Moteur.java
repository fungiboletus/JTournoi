package polytech.jtournoi;

import java.util.ArrayList;
import java.util.HashMap;

import polytech.tools.Tools;

public abstract class Moteur {

	static Tournoi t;
	
	public static void creerTournoi(ArrayList<Equipe> equipes, ArrayList<TypeEpreuve> epreuves) throws Exception{
		if(Tools.epreuveSansEquipe(epreuves,equipes)){
			throw new EpreuveSansEquipeException();
		}
		else{
			t = new Tournoi(equipes,epreuves);
		}
	}
	
	public void startTournoi(Tournoi t){
		t.startTournoi();
	}
	
	public HashMap<TypeEpreuve,ArrayList<Match>> getCurrentsMatch(Tournoi t){
		ArrayList<Epreuve> epreuves = t.getEpreuves();
		HashMap<TypeEpreuve,ArrayList<Match>> map = new HashMap<TypeEpreuve,ArrayList<Match>>();
		for(Epreuve e : epreuves){
			map.put(e.getTypeEpreuve(), e.getCurentMatch());
		}
		return map;
	}
	
	public void getMatch (){
		HashMap<TypeEpreuve,ArrayList<Match>> map = getCurrentsMatch(t);
		for(TypeEpreuve te : map.keySet()){
			System.out.println(map.get(te+"\n"));
		}
	}
}
