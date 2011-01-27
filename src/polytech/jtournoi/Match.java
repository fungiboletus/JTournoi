package polytech.jtournoi;

import polytech.personnes.Arbitre;

public class Match implements Comparable{
	Equipe e1;
	Equipe e2;
	int scE1;
	int scE2;
	Arbitre a;
	int id;
	int tour;
	
	public Match(Equipe e1, Equipe e2, Arbitre a, int id, int tour) {
		this.id=id;
		this.e1=e1;
		this.e2=e2;
		if(a.getBusy()==true){
			throw new RuntimeException();
		}
		else{
			this.a=a;
			a.setBusy(true);
		}
		this.tour=tour;
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
	
	public int getTour(){
		return tour;
	}
	
	public Arbitre getARbitre(){
		return a;
	}
	
	public String toString(){
		String s = "";
		s+="Bienvenue dans le match numéro : "+id+" ou s'affronte "+e1.getNom()+" et "+e2.getNom();
		return s;
	}

	@Override
	public int compareTo(Object arg0) {
		Match m = (Match) arg0;
		if(m.getId()==this.id && m.getTour()==this.tour){
			return 0;
		}
		return -1;
	}
}
