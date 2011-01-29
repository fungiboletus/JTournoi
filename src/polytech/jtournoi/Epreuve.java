package polytech.jtournoi;

import java.util.ArrayList;

import polytech.stock.Stock;
import polytech.stock.TupleAvecID;
import polytech.tools.Tools;

import polytech.personnes.*;
/**
 * 
 * @author Pinku
 * @ param : type d'epreuve, liste d'équipe, vainqueur, heure de l'épreuve
 * 
 */
public class Epreuve extends TupleAvecID {
	
	 
	private Equipe vainqueur;
	public TypeEpreuve type;
	private ArrayList<ArrayList<Equipe>> tableau;
	private ArrayList<Match> currentMatch;
	private ArrayList<Equipe> vainqueurEquipe;
	private int tour=1;
	
	public Epreuve()
	{
	}
	
	public Epreuve(ArrayList<Equipe> equipe,TypeEpreuve te){
		super();
		type=te;
		tableau = new ArrayList<ArrayList<Equipe>>();
		
		tableau.add(Tools.melangerEquipe(equipe));
		if(equipe.size()==1){
			vainqueur=equipe.get(0);
		}
		else{
			vainqueur =null;
			vainqueurEquipe = new ArrayList<Equipe>();
			currentMatch = new ArrayList<Match>();
			tour();
		}
	}
	
	public void tour(){
		ArrayList<Equipe> current = tableau.get(tableau.size()-1);
		vainqueurEquipe.clear();
		currentMatch.clear();
		int indice = tableau.get(tableau.size()-1).size();
		int indicePuissance = Tools.puissance(indice);
		
		//nombre d'équipe qui ne joue pas le premier tour
		int indiceStandBy = indice-(indice-indicePuissance)*2;
		//boucle uniquement pour le premier tour pour ramener le nombre d'équipe a une puissance de 2
		for(int i=0;i<indiceStandBy;i++){
			System.out.println("ça boucle ? ");
			vainqueurEquipe.add(current.get(i));
		}
		//on réalise autant de match qu'il faut pour ramener le nombre d'équipe a une puissance de deux
		int j = indiceStandBy;
		for(int i = indiceStandBy;i<indiceStandBy+(indice-indicePuissance);i++){
			Arbitre a = Stock.getRandomArbitreLibre();
			Match m = new Match(current.get(j),current.get(j+1),a,i,tour);
			j=j+2;
			currentMatch.add(m);
			vainqueurEquipe.add(null);
		}
		tour ++;
	}
	
	public Match getMatch(Arbitre ar){
		for (Match m : currentMatch){
			if(ar==m.getArbitre()){
				return m;
			}
		}
		return null;
	}
	
	private boolean isMatch(Match m){
		int i=-1;
		for(Match ma : currentMatch){
			if(m.compareTo(ma)==0){
				i=0;
			}
		}
		return i==0;
	}
	
	private void remove(Match m){
		currentMatch.remove(m);
		if(currentMatch.size()==0){
			ArrayList<Equipe> tmp = new ArrayList<Equipe>();
			tmp.addAll(vainqueurEquipe);
			tableau.add(tmp);
			if(vainqueurEquipe.size()==1){
				vainqueur=vainqueurEquipe.get(0);
				
			}
			else{
				tour();
			}
		}
	}
	
	public Equipe getVainqueur(){
		return vainqueur;
	}
	
	public void setVainqueur(Equipe vainqueur)
	{
		this.vainqueur = vainqueur;
	}

	public void setScore(Match m, int se1, int se2){
		if(isMatch(m)){
			m.setScore(se1,se2);
			vainqueurEquipe.set(m.getNumero(),m.getVainqueur());
			remove(m);
		}
	}
	
	public String toString(){
		String s="";
		for (ArrayList<Equipe> list : tableau){
			System.out.println(list.size());
			for(Equipe e : list){
				s+=e.getNom()+"  ";
			}
			s+="\n";
		}
		return s;
	}

	public TypeEpreuve getTypeEpreuve() {
		return type;
	}

	public void setTypeEpreuve(TypeEpreuve type)
	{
		this.type = type;
	}

	public int getTour()
	{
		return tour;
	}

	public void setTour(int tour)
	{
		this.tour = tour;
	}

	public ArrayList<ArrayList<Equipe>> getTableau()
	{
		return tableau;
	}

	public void setTableau(ArrayList<ArrayList<Equipe>> tableau)
	{
		this.tableau = tableau;
	}

	public ArrayList<Match> getCurrentMatch()
	{
		return currentMatch;
	}

	public void setCurrentMatch(ArrayList<Match> currentMatch)
	{
		this.currentMatch = currentMatch;
	}

	public ArrayList<Equipe> getVainqueurEquipe()
	{
		return vainqueurEquipe;
	}

	public void setVainqueurEquipe(ArrayList<Equipe> vainqueurEquipe)
	{
		this.vainqueurEquipe = vainqueurEquipe;
	}
}
