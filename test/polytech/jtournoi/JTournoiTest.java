package polytech.jtournoi;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import polytech.personnes.Joueur;
import polytech.stock.Stock;
import polytech.stock.TypeChargement;

public class JTournoiTest {

	@Before
	public void setUp(){
		Stock.chargerStock(TypeChargement.XML);
	}
	
	public Joueur creerJoueurCompetent(int d){
		Joueur j = new Joueur("nom","prenom","psswd");
		j.setCompetences(getCompetence(d));
		return j; 
	}
	
	public Equipe creerEquipe(int d){
		Equipe e = new Equipe("equipe 1");
		for(int i =0;i<5;i++){
			e.ajouterParticipant(creerJoueurCompetent(d));
		}
		return e;
	}
	
	public ArrayList<TypeEpreuve> getCompetence(int d){
		ArrayList<TypeEpreuve> epreuves = new ArrayList<TypeEpreuve>();
		for(int i =d;i<d+5;i++){
			epreuves.add(Stock.getTypesEpreuves().get(i));
		}
		return epreuves;
	}
	
	@Test
	public void test1(){
		Joueur j = creerJoueurCompetent(0);
		assertTrue(j.getNombreDeCompetences()==5);
	}
	
	@Test
	public void haveCompetenceTest(){
		boolean b = true;
		Equipe e = creerEquipe(0);
		for(TypeEpreuve te : getCompetence(0)){
			if(!e.haveCompetence(te)){
				b=false;
			}
		}
		assertTrue(b);
	}
		
	@Test
	public void setEpreuveTest(){
		Equipe e = creerEquipe(0);
		e.setEpreuves(getCompetence(0));
		assertTrue(e.getEpreuves().size()==5);
	}
	
	@Test
	public void setEpreuveTest2(){
		Equipe e = creerEquipe(0);
		e.setEpreuves(getCompetence(5));
		assertTrue(e.getEpreuves().size()==0);
	}
	
	@Test
	public void cstrTournoiTest(){
		Tournoi t= new Tournoi("test",getCompetence(0));
		assertFalse(t.verificationTournoi());
	}
	
//	@Test
//	public void tourTest(){
//		ArrayList<Equipe> equipes = new ArrayList<Equipe>();
//		for(int i=0;i<5;i++){
//			equipes.add(creerEquipe(0));
//		}
//		Epreuve e = new Epreuve(equipes, getCompetence(0).get(0));
//	}
}
