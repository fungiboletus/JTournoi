package polytech.jtournoi;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import polytech.personnes.Joueur;
import polytech.stock.Stock;
import polytech.stock.TypeChargement;

public class EquipeTest {

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
	public void test3(){
		Equipe e = creerEquipe(0);
		e.setEpreuves(getCompetence(0));
		assertTrue(e.getEpreuves().size()==5);
	}
	
	@Test
	public void test4(){
		Equipe e = creerEquipe(0);
		e.setEpreuves(getCompetence(5));
		assertTrue(e.getEpreuves().size()==0);
	}
	
	
}
