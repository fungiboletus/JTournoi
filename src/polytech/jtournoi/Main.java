package polytech.jtournoi;

import java.util.ArrayList;

import polytech.personnes.Arbitre;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<Equipe> equipes = new ArrayList<Equipe>();
		ArrayList<Match> listMatch = new ArrayList<Match>();
		ArrayList<Match> listMatch2 = new ArrayList<Match>();
		Arbitre a;
		Match ma=null;
		for(int i =0; i<840;i++){
			equipes.add(new Equipe("equipe "+(i+1)));
		}
		Epreuve e = new Epreuve(equipes);
		listMatch.addAll(e.getCurentMatch());
		for(Match m : listMatch){
			e.setScore(m, 10, 20);
		}
//		listMatch2.addAll(e.getCurentMatch());
//		for(Match m : listMatch2){
//			e.setScore(m, 10, 20);
//		}
		System.out.println(e);
	}

}
