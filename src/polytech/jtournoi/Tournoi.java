package polytech.jtournoi;

import java.util.ArrayList;

public class Tournoi {

	ArrayList<Equipe> equipes = new ArrayList<Equipe>();
	
	/**
	 * Constructeur prenant en paramètre juste une liste d'équipe et réalisant toute les épreuves possibles
	 * @param equipe
	 */
	public Tournoi(ArrayList<Equipe> equipe){
		equipes = equipe;
	}
	
}
