package polytech.jtournoi;

import java.util.ArrayList;

import polytech.personnes.Arbitre;
import polytech.tools.Tools;


/**
 * 
 * @author Pinku
 * @ param : type d'epreuve, liste d'équipe, vainqueur, heure de l'épreuve
 * 
 */
public class Epreuve {
	
	private Equipe vainqueur;
	private ArrayList<ArrayList<Equipe>> tableau;
	private ArrayList<Match> currentMatch;
	private ArrayList<Equipe> vainqueurEquipe;
	Arbitre a = new Arbitre();
	
	public Epreuve(ArrayList<Equipe> equipe){
		vainqueur =null;
		tableau.add(equipe);
		vainqueurEquipe = new ArrayList<Equipe>();
		currentMatch = new ArrayList<Match>();
		tour();
	}
	
	public void tour(){
		ArrayList<Equipe> current = tableau.get(tableau.size()-1);
		vainqueurEquipe.clear();
		currentMatch.clear();
		int indice = tableau.get(tableau.size()-1).size();
		int indicePuissance = Tools.puissance(indice);
		//on réalise autant de match qu'il faut pour ramener le nombre d'équipe a une puissance de deux
		
		//nombre d'équipe qui ne joue pas le premier tour
		int indiceStandBy = indice-(indice-indicePuissance)*2;
		//boucle uniquement pour le premier tour pour ramener le nombre d'équipe a une puissance de 2
		for(int i=0;i<indiceStandBy;i++){
			vainqueurEquipe.add(current.get(i));
		}
		int j = indiceStandBy;
		for(int i = indiceStandBy;i<indiceStandBy+indicePuissance;i=i+2){
			Match m = new Match(current.get(i),current.get(i+1),a,j);
			j++;
			currentMatch.add(m);
			vainqueurEquipe.add(null);
		}
	}
	
	public ArrayList<Match> getCurentMatch(){
		return currentMatch;
	}
	
	public Match getMatch(Arbitre ar){
		for (Match m : currentMatch){
			if(ar==m.getARbitre()){
				return m;
			}
		}
		return null;
	}
	
	private boolean isMatch(Match m){
		for(Match ma : currentMatch){
			return ma==m;
		}
		return false;
	}
	
	private void remove(Match m){
		currentMatch.remove(m);
		if(currentMatch.size()==0){
			tableau.add(vainqueurEquipe);
			if(vainqueurEquipe.size()==1){
				vainqueur=vainqueurEquipe.get(0);
			}
			else{
				tour();
			}
		}
	}
	
	public void setScore(Match m, int se1, int se2){
		if(isMatch(m)){
			m.setScore(se1,se2);
			vainqueurEquipe.set(m.getId(),m.getVainqueur());
			remove(m);
		}
	}
	
	public String toString(){
		String s="";
		for (ArrayList<Equipe> list : tableau){
			for(Equipe e : list){
				s=e.getNom()+"  ";
			}
			s+="/n";
		}
		return s;
	}
}
