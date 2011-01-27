package polytech.jtournoi;

import java.util.ArrayList;
import java.util.HashMap;

import polytech.personnes.Arbitre;
import polytech.stock.Stock;
import polytech.stock.TypeChargement;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Stock.chargerStock(TypeChargement.XML);
		System.out.println(Stock.getJoueurs().size());
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
