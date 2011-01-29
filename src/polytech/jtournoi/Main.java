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
		for(Equipe e : Stock.getEquipe()){
			for(TypeEpreuve te : e.getEpreuves()){
				
			}
		}
		try {
			Moteur.creerTournoi("viande",(ArrayList<Equipe>) Stock.getEquipe(), (ArrayList<TypeEpreuve>)Stock.getTypesEpreuves());
		} catch (Exception e) {
			System.out.println("fail la création du tournoi");
		}
	}

}
