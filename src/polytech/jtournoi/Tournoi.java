package polytech.jtournoi;

import java.util.ArrayList;

import polytech.stock.TupleAvecID;
import polytech.tools.Tools;

public class Tournoi extends TupleAvecID {

	ArrayList<Equipe> equipes = new ArrayList<Equipe>();
	ArrayList<TypeEpreuve> typeEpreuves = new ArrayList<TypeEpreuve>();
	ArrayList<Epreuve> epreuves = new ArrayList<Epreuve>();
	
	/**
	 * Constructeur prenant en paramètre juste une liste d'équipe et réalisant toute les épreuves possibles
	 * @param equipe
	 */
	public Tournoi(ArrayList<Equipe> equipe, ArrayList<TypeEpreuve> epreuves){
		super();
		equipes = equipe;
		typeEpreuves = epreuves;
	}
	
	public void startTournoi(){
		for(TypeEpreuve te : Tools.getTypeEpreuve(equipes)){
			Epreuve e = new Epreuve(Tools.equipeParEpreuve(te,equipes),te);
			epreuves.add(e);
		}
	}
	
	public ArrayList<Epreuve> getEpreuves(){
		return epreuves;
	}
	
}
