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
		ArrayList<Arbitre> arbitres = new ArrayList<Arbitre>();
		for(int i=0;i<5;i++){
			arbitres.add(new Arbitre(""+i,""+i,""+i));
		}
		Stock.setArbitres(arbitres);
		System.out.println(Stock.getEquipes().size());
		for(TypeEpreuve te : Tools.getTypeEpreuve((ArrayList<Equipe>)Stock.getEquipes())){
			
			System.out.println(te.getNom());
		}
		try {
			Moteur.creerTournoi((ArrayList<Equipe>) Stock.getEquipes(), (ArrayList<TypeEpreuve>)Stock.getTypesEpreuves());
		} catch (Exception e) {
			System.out.println("fail la création du tournoi");
		}
	}

}
