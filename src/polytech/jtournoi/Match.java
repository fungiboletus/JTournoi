package polytech.jtournoi;

import polytech.personnes.Arbitre;

public class Match {
	Equipe e1;
	Equipe e2;
	int scE1;
	int scE2;
	Arbitre a;
	int id;
	
	public Match(Equipe e1, Equipe e2, Arbitre a, int id) {
		this.id=id;
		this.e1=e1;
		this.e2=e2;
		this.a=a;
	}
	
	public void setScore(int sc1, int sc2){
		scE1=sc1;
		scE2=sc2;
	}
	
	public Equipe getVainqueur(){
		if(scE1<scE2){
			return e2;
		}
		else 
			return e1;
	}
	
	public int getId(){
		return id;
	}
	
	public Arbitre getARbitre(){
		return a;
	}
}
