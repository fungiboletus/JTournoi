package polytech.tools;

import java.util.ArrayList;

import polytech.jtournoi.Equipe;
import polytech.jtournoi.TypeEpreuve;

public abstract class Tools {
	
	public static int puissance(int n){
		int i=1;
		while(i<n){
			i=i*2;
		}
		return i/2;
	}
	
	public static ArrayList<Equipe> melangerEquipe(ArrayList<Equipe> list){
		Equipe tmp;
		ArrayList<Equipe> liste = new ArrayList<Equipe>();
		liste.addAll(list);
		int max=liste.size()-1;
		int i=0;
		int random;
		while(max>0){
			random=(int)(Math.random()*max);
			tmp=liste.get(random);
			liste.remove(random);
			liste.add(random,liste.get(max-1));
			liste.remove(max);
			liste.add(max-1,tmp);
			max--;
			i++;
		}
		return liste;
	}

	public static ArrayList<TypeEpreuve> getTypeEpreuve(ArrayList<Equipe> equipes){
		ArrayList<TypeEpreuve> epreuve = new ArrayList<TypeEpreuve>();
		for(Equipe e : equipes){
			System.out.println("equipe");
			for(TypeEpreuve te : e.getEpreuves()){
				System.out.println("equipes epruve");
				if(!containsTypeEpreuve(epreuve,te)){
					epreuve.add(te);
				}
			}
		}
		return epreuve;
	}
	

	private static boolean containsTypeEpreuve(ArrayList<TypeEpreuve> epreuves, TypeEpreuve te){
		for(TypeEpreuve e : epreuves){
			if (e.getId()==te.getId()){
				return true;
			}
		}
		return false;
	}
	
	public static boolean epreuveSansEquipe(ArrayList<TypeEpreuve> epreuves, ArrayList<Equipe> equipes){
		ArrayList<TypeEpreuve> tmp = getTypeEpreuve(equipes);
		for(TypeEpreuve e : epreuves){
			if(!containsTypeEpreuve(tmp,e)){
				return true;
			}
		}
		return false;
	}

	public static ArrayList<Equipe> equipeParEpreuve(TypeEpreuve te, ArrayList<Equipe> equipes){
		ArrayList<Equipe> list = new ArrayList<Equipe>();
		for(Equipe e : equipes){
			if(containsTypeEpreuve(e.getEpreuves(),te)){
				list.add(e);
			}
		}
		return list;
	}
	
}
