package polytech.jtournoi;

import java.util.ArrayList;

public abstract class Moteur {

	public static void creerTournoi(ArrayList<Equipe> equipes, ArrayList<TypeEpreuve> epreuves) throws Exception{
		if(epreuveSansEquipe(epreuves,equipes)){
			throw new EpreuveSansEquipeException();
		}
	}
	
	private static ArrayList<TypeEpreuve> getTypeEpreuve(ArrayList<Equipe> equipes){
		ArrayList<TypeEpreuve> epreuve = new ArrayList<TypeEpreuve>();
		for(Equipe e : equipes){
			for(TypeEpreuve te : e.getEpreuves()){
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
	
	private static boolean epreuveSansEquipe(ArrayList<TypeEpreuve> epreuves, ArrayList<Equipe> equipes){
		ArrayList<TypeEpreuve> tmp = getTypeEpreuve(equipes);
		for(TypeEpreuve e : epreuves){
			if(!containsTypeEpreuve(tmp,e)){
				return true;
			}
		}
		return false;
	}
	
}
