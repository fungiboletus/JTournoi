package polytech.jtournoi;

import java.util.ArrayList;
import java.util.HashMap;

import polytech.personnes.Joueur;
import polytech.stock.TupleAvecID;

public class Tournoi extends TupleAvecID {

	String nom;
	ArrayList<Equipe> equipes = new ArrayList<Equipe>();
	ArrayList<TypeEpreuve> typeEpreuves = new ArrayList<TypeEpreuve>();
	HashMap<TypeEpreuve,HashMap<Equipe,Joueur>> map = new HashMap<TypeEpreuve,HashMap<Equipe,Joueur>>();
	ArrayList<Epreuve> epreuves = new ArrayList<Epreuve>();
	
	
	public Tournoi(String nom, ArrayList<TypeEpreuve> te){
		typeEpreuves =te;
		this.nom=nom;
	}
	
	public boolean setEpreuve(TypeEpreuve te, HashMap<Equipe,Joueur> equipes){
	    //System.out.println(map);
		if(!map.containsKey(te)){
			map.put(te, equipes);
			return true;
		}
		return false;
	}
	
	public boolean verificationTournoi() {
		if (typeEpreuves.size() != 0) {
		    //System.out.println(map);
			for (TypeEpreuve te : typeEpreuves) {
				// on vérifie que pour chaque type d'épreuve on ait au moins
				// deux équipes pour la joueur
				if (!map.containsKey(te) || map.get(te).size() < 2) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean addEquipe(Equipe e, Joueur j, TypeEpreuve te){
		if(map.containsKey(te)){
			if(!map.get(te).containsKey(e)){
				map.get(te).put(e, j);
				return true;
			}
		}
		return false;
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
	
	public boolean addTypeEpreuve(TypeEpreuve te){
		if(!typeEpreuves.contains(te)){
			typeEpreuves.add(te);
			return true;
		}
		else{
			return false;
		}
	}
	
	public ArrayList<Epreuve> getEpreuves(){
		return epreuves;
	}
	
	
	public ArrayList<TypeEpreuve> getTypeEpreuves(){
        return typeEpreuves;
    }


	public void startTournoi() {
		if(!verificationTournoi()){
			throw new RuntimeException("t'as pas lancé la vérification ...");
		}
		for(TypeEpreuve te : typeEpreuves){
			Epreuve e = new Epreuve(map.get(te),te);
			epreuves.add(e);
		}
		
	}
	
}
