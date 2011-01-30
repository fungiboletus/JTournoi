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
		Main m =new Main(2);
		Stock.chargerStock(TypeChargement.XML);

		HashMap<String,Integer> map = new HashMap<String,Integer>();
		map.put("a", 0);
		map.put("b", 1);
		
	}

	public Main(int i){
		this();
		System.out.println(i);
	}
	
	public Main(){
		System.out.println("graou");
	}
}
