package polytech.jtournoi;

import java.util.ArrayList;

import polytech.stock.TupleAvecID;
import polytech.tools.Tools;

public class Tournoi extends TupleAvecID {

	String nom;
	ArrayList<Equipe> equipes = new ArrayList<Equipe>();
	ArrayList<TypeEpreuve> typeEpreuves = new ArrayList<TypeEpreuve>();
	ArrayList<Epreuve> epreuves = new ArrayList<Epreuve>();
	
	/**
	 * Constructeur prenant en paramètre juste une liste d'équipe et réalisant toute les épreuves possibles
	 * @param equipe
	 */
	public Tournoi(String nom,ArrayList<Equipe> equipe, ArrayList<TypeEpreuve> epreuves){
		super();
		this.nom=nom;
		equipes = equipe;
		typeEpreuves = epreuves;
	}
	
	public Tournoi(String nom){
		this.nom=nom;
	}

	public void lancerTournoi(){
		
	}
	
	public boolean verificationTournoi(){
		if(equipes.size()<2 || Tools.epreuvesSansEquipes(typeEpreuves, equipes)){
			return false;
		}
		return true;
	}
	
	public boolean addEquipe(Equipe e){
		if(!equipes.contains(e)){
			equipes.add(e);
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean supprimerEpreuve(TypeEpreuve te){
		if(typeEpreuves.contains(te)){
			for(Epreuve e : epreuves){
				if(e.getTypeEpreuve().equals(e)){
					epreuves.remove(e);
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean addTypeEpreuve(TypeEpreuve te) throws EpreuveSansEquipeException{
		if(!typeEpreuves.contains(te)){
			typeEpreuves.add(te);
			if(Tools.epreuveSansEquipes(te, equipes)){
				throw new EpreuveSansEquipeException();
			}
			return true;
		}
		else{
			return false;
		}
	}
	
	public ArrayList<Epreuve> getEpreuves(){
		return epreuves;
	}




	public void startTournoi() {
		System.out.println("on start o/");
		
	}
	
}
