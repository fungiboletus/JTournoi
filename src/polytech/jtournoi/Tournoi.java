package polytech.jtournoi;

import java.util.ArrayList;

import polytech.tools.Tools;

public class Tournoi {

	ArrayList<Equipe> equipes = new ArrayList<Equipe>();
	ArrayList<TypeEpreuve> typeEpreuves = new ArrayList<TypeEpreuve>();
	ArrayList<Epreuve> epreuves = new ArrayList<Epreuve>();
	
	/**
	 * Constructeur prenant en paramètre juste une liste d'équipe et réalisant toute les épreuves possibles
	 * @param equipe
	 */
	public Tournoi(ArrayList<Equipe> equipe, ArrayList<TypeEpreuve> epreuves){
		equipes = equipe;
		typeEpreuves = epreuves;
	}
	
	

	
	public ArrayList<Epreuve> getEpreuves(){
		return epreuves;
	}




	public void startTournoi() {
		System.out.println("on start o/");
		
	}
	
}
