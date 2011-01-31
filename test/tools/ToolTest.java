package tools;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import polytech.jtournoi.Equipe;
import polytech.jtournoi.TypeEpreuve;
import polytech.personnes.Joueur;
import polytech.stock.Stock;
import polytech.stock.TypeChargement;
import polytech.tools.Tools;

public class ToolTest {

	@Before
	public void setUp() {
		Stock.chargerStock(TypeChargement.XML);
	}

	public Joueur creerJoueurCompetent(int d) {
		Joueur j = new Joueur("nom", "prenom", "psswd");
		j.setCompetences(getCompetence(d));
		return j;
	}

	public Equipe creerEquipe(int d) {
		Equipe e = new Equipe("equipe 1");
		for (int i = 0; i < 5; i++) {
			e.ajouterParticipant(creerJoueurCompetent(d));
		}
		e.setEpreuves(getCompetence(d));
		return e;
	}

	public ArrayList<TypeEpreuve> getCompetence(int d) {
		ArrayList<TypeEpreuve> epreuves = new ArrayList<TypeEpreuve>();
		for (int i = d; i < d + 5; i++) {
			epreuves.add(Stock.getTypesEpreuves().get(i));
		}
		return epreuves;
	}

	@Test
	public void getTypeEpreuveTest() {
		ArrayList<Equipe> equipes = new ArrayList<Equipe>();
		equipes.add(creerEquipe(0));
		equipes.add(creerEquipe(5));
		assertTrue(Tools.getTypeEpreuve(equipes).size() == 10);
	}

	@Test
	public void getTypeEpreuveTest2() {
		int a = 3;
		int b = 5;
		ArrayList<Equipe> equipes = new ArrayList<Equipe>();
		equipes.add(creerEquipe(a));
		equipes.add(creerEquipe(b));
		assertTrue(Tools.getTypeEpreuve(equipes).size() == 5 + b - a);
	}

	@Test
	public void epreuveSansEquipe() {
		ArrayList<Equipe> equipes = new ArrayList<Equipe>();
		equipes.add(creerEquipe(0));
		equipes.add(creerEquipe(0));
		assertFalse(Tools.epreuveSansEquipes(getCompetence(0).get(0), equipes));
	}

	@Test
	public void epreuveSansEquipe2() {
		ArrayList<Equipe> equipes = new ArrayList<Equipe>();
		equipes.add(creerEquipe(1));
		equipes.add(creerEquipe(1));
		equipes.add(creerEquipe(0));
		equipes.add(creerEquipe(2));
		assertTrue(Tools.epreuveSansEquipes(getCompetence(0).get(0), equipes));
	}

	@Test
	public void epreuvesSansEquipe2() {
		ArrayList<Equipe> equipes = new ArrayList<Equipe>();
		equipes.add(creerEquipe(1));
		equipes.add(creerEquipe(1));
		equipes.add(creerEquipe(0));
		equipes.add(creerEquipe(2));
		assertTrue(Tools.epreuvesSansEquipes(getCompetence(0), equipes));
	}

}
