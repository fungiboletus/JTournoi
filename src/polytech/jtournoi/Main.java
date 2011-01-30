package polytech.jtournoi;

import java.util.ArrayList;
import java.util.HashMap;

import polytech.personnes.Arbitre;
import polytech.stock.Stock;
import polytech.stock.TypeChargement;
import polytech.tools.Tools;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Stock.chargerStock(TypeChargement.XML);
		System.out.println("equipe"+Stock.getEquipe().size());
		System.out.println("arbitres"+Stock.getArbitres().size());
		for(Equipe e : Stock.getEquipe()){
			System.out.println(e.getEpreuves().size());
			for(TypeEpreuve te : e.getEpreuves()){
				System.out.println(te.getNom());
			}
		}
		try {
			Moteur.creerTournoi("",(ArrayList<Equipe>) Stock.getEquipe(), (ArrayList<TypeEpreuve>)Stock.getTypesEpreuves());
		} catch (Exception e) {
			System.out.println("fail la création du tournoi");
		}
	}

}
