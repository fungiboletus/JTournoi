package polytech.jtournoi;

import java.util.ArrayList;
import java.util.HashMap;

import polytech.personnes.Arbitre;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HashMap<String, Integer> map = new HashMap<String,Integer>();
		for(int i =0;i<10;i++){
			if(i%2==0){
				map.put("a", i);
			}
			else{
				map.put("b", i);
			}
		}
		for(String s : map.keySet()){
			System.out.println(map.get(s));
		}
//		ArrayList<Equipe> equipes = new ArrayList<Equipe>();
//		ArrayList<Match> listMatch = new ArrayList<Match>();
//		Arbitre a;
//		Match ma = null;
//		for (int i = 0; i < 15; i++) {
//			equipes.add(new Equipe("equipe " + (i + 1)));
//		}
//		Epreuve e = new Epreuve(equipes,new TypeEpreuve());
//
//		while (e.getVainqueur() == null) {
//			listMatch.clear();
//			listMatch.addAll(e.getCurentMatch());
//			for (Match m : listMatch) {
//				e.setScore(m, 10, 20);
//			}
//		}
//		System.out.println(e);
	}

}
