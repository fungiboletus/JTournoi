package polytech.jtournoi;

import java.util.ArrayList;
import java.util.HashMap;

import polytech.stock.Stock;
import polytech.stock.TupleAvecID;
import polytech.tools.Tools;

import polytech.exception.nbrArbitreInsufisantException;
import polytech.personnes.*;
/**
 * 
 * @author Muller Stéphane
 * @ param : type d'epreuve, liste d'équipe
 * Création et réalisation d'une épreuve avec les équipes passée en paramètre 
 * 
 */
public class Epreuve extends TupleAvecID {
	
	 
	private Equipe vainqueur;
	public TypeEpreuve type;
	private ArrayList<ArrayList<Equipe>> tableau;
	private ArrayList<Match> currentMatch;
	private ArrayList<Equipe> vainqueurEquipe;
	private int tour=1;
	HashMap<Equipe,Joueur> map;
	
	public Epreuve()
	{
	}
	/**
	 * Constructor dans le cas d'une équipe collective
	 * @param Hashmap des équipes et des joueurs défini pour chaque équipe
	 * @param te Type de l'épreuve
	 * @throws nbrArbitreInsufisantException 
	 */
	public Epreuve(HashMap<Equipe,Joueur> eqj, TypeEpreuve te) throws nbrArbitreInsufisantException{
		this(Tools.getEquipe(eqj),te);
		map=eqj;
	}
	
	/**
	 * Constructeur dans le cas d'une épreuve collective
	 * @param ArrayList d'équipe
	 * @param Type de l'épreuve
	 * @throws nbrArbitreInsufisantException 
	 */
	public Epreuve(ArrayList<Equipe> equipe,TypeEpreuve te) throws nbrArbitreInsufisantException{
		super();
		map=null;
		type=te;
		tableau = new ArrayList<ArrayList<Equipe>>();
		
		tableau.add(Tools.melangerEquipe(equipe));
		//si il n'y a qu'eune seul équipe elle est vainqueur (ne devraiit pas se présenter)
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
	/**
	 * Méthode de réalisation d'un tour 
	 * @throws nbrArbitreInsufisantException 
	 */
	public void tour() throws nbrArbitreInsufisantException{
		
		//on récupère la liste des équipes du tour précédent
		ArrayList<Equipe> current = tableau.get(tableau.size()-1);
		
		//on vide les listes temporaires
		vainqueurEquipe.clear();
		currentMatch.clear();
		
		int indice = tableau.get(tableau.size()-1).size();
		int indicePuissance = Tools.puissance(indice);
		
		//nombre d'équipe qui ne joue pas le premier tour
		int indiceStandBy = indice-(indice-indicePuissance)*2;
		
		//boucle uniquement pour le premier tour pour ramener le nombre d'équipe a une puissance de 2
		for(int i=0;i<indiceStandBy;i++){
			vainqueurEquipe.add(current.get(i));
		}
		//on réalise autant de match qu'il faut pour ramener le nombre d'équipe a une puissance de deux
		int j = indiceStandBy;
		//si le nombre est une puissance de deux alors tout les matchs sont réalisé
		int inc=0;
		for (int i = indiceStandBy; i < indiceStandBy
				+ (indice - indicePuissance); i++) {
			//on vérifie que l'on a un arbitre de disponible
			if (Stock.getArbitresLibres(type).size() != 0) {
				Arbitre a = Stock.getRandomArbitreLibre(type);
				Match m = new Match(current.get(j), current.get(j + 1), a, i,
						tour);
				j = j + 2;
				currentMatch.add(m);
				vainqueurEquipe.add(null);
			}
			else{
				inc++;
			}
		}
		if(inc!=0){
			throw new nbrArbitreInsufisantException(inc,type);
		}
		tour ++;
	}
	
	/**
	 * Renvoie le match pour l'arbitre
	 * @param un arbitre
	 * @return le match que l'arbitre est etrain d'arbitrer
	 */
	public Match getMatch(Arbitre ar){
		for (Match m : currentMatch){
			if(ar==m.getArbitre()){
				return m;
			}
		}
		return null;
	}
	
	/**
	 * Vérifie si le match est bien dans la liste des match courant
	 * @param m
	 * @return
	 */
	private boolean isMatch(Match m){
		int i=-1;
		for(Match ma : currentMatch){
			if(m.compareTo(ma)==0){
				i=0;
			}
		}
		return i==0;
	}
	
	/**
	 * Enlève un match de la liste des match courant
	 * @param m
	 * @throws nbrArbitreInsufisantException 
	 */
	private void remove(Match m) throws nbrArbitreInsufisantException{
		currentMatch.remove(m);
		//si la liste des match courant est nul alors tout les matchs du tour ont été réalisé
		if(currentMatch.size()==0){
			ArrayList<Equipe> tmp = new ArrayList<Equipe>();
			//on rajoute la liste des vainqueur a l'arbre de l'épreuve
			tmp.addAll(vainqueurEquipe);
			tableau.add(tmp);
			//si il n'y a qu'un seul vainqueur alors c'était le dernier match de l'épreuve
			if(vainqueurEquipe.size()==1){
				//on set le score et le vainqueur qui va bien
				setScore(vainqueurEquipe.get(0));
			}
			else{
				tour();
			}
		}
	}
	/**
	 * Incrémente le score de l'équipe et du joueur dans le cadre d'une équipe individuelle
	 * @param vainqueur
	 */
	private void setScore(Equipe vainqueur){
			//on est dans une épreuve individuelle
			int inc =type.getPoints();
			if(map!=null){
				map.get(vainqueur).incScore(inc);
				vainqueur.incScore(inc);
			}
			else{
				vainqueur.incAllScore(inc);
			}
	}
	
	/**
	 * Renvoie le vainqueur de l'épreuve (null si c'est pas terminé)
	 * @return
	 */
	public Equipe getVainqueur(){
		return vainqueur;
	}
	
	public void setVainqueur(Equipe vainqueur)
	{
		this.vainqueur = vainqueur;
	}

	/**
	 * Méthode permettant de rentrer le score d'un match en vérifiant que c'est le bon arbitre
	 * @param m
	 * @param se1
	 * @param se2
	 * @throws nbrArbitreInsufisantException 
	 */
	public void setScore(Arbitre a,Match m, int se1, int se2) throws nbrArbitreInsufisantException{
		if(isMatch(m)&&m.getArbitre().equals(a)){
			m.setScore(se1,se2);
			vainqueurEquipe.set(m.getNumero(),m.getVainqueur());
			remove(m);
		}
	}
	/**
	 * Méthode d'affiche de l'épreuve (non optimise)
	 */
	public String toString(){
		String s="Epreuve de type : "+type+"\n";
		for (ArrayList<Equipe> list : tableau){
			System.out.println(list.size());
			for(Equipe e : list){
				s+=e.getNom()+"  ";
			}
			s+="\n";
		}
		return s;
	}

	/**
	 * Retourne le type de l'épreuve
	 * @return
	 */
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
