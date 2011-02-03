package polytech.jtournoi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import polytech.exception.EpreuveDejaExistanteException;
import polytech.exception.NombreDeParticipantInsufisantException;
import polytech.exception.nbrArbitreInsufisantException;
import polytech.personnes.Arbitre;
import polytech.personnes.Joueur;
import polytech.stock.Stock;
import polytech.stock.TypeChargement;

public class JTournoiTest {

	@Before
	public void setUp() {
		Stock.chargerStock(TypeChargement.XML);
	}

	public Joueur creerJoueurCompetent(int d) {
		Joueur j = new Joueur("nom", "prenom", "psswd");
		j.setCompetences(getCompetence(d));
		return j;
	}

	public Equipe creerEquipe(int d, int k) {
		Equipe e = new Equipe("equipe " + k);
		for (int i = 0; i < 5; i++) {
			e.ajouterParticipant(creerJoueurCompetent(d));
			e.setEpreuves(getCompetence(d));
		}
		return e;
	}

	public ArrayList<TypeEpreuve> getCompetence(int d) {
		ArrayList<TypeEpreuve> epreuves = new ArrayList<TypeEpreuve>();
		for (int i = d; i < d + 5; i++) {
			epreuves.add(Stock.getTypesEpreuves().get(i));
		}
		return epreuves;
	}

	public ArrayList<Equipe> creeEquipe(int k) {
		ArrayList<Equipe> equipes = new ArrayList<Equipe>();
		for (int i = 0; i < k; i++) {
			equipes.add(creerEquipe(0, i));
		}
		return equipes;
	}

	@Test
	public void test1() {
		Joueur j = creerJoueurCompetent(0);
		assertTrue(j.getNombreDeCompetences() == 5);
	}

	@Test
	public void haveCompetenceTest() {
		boolean b = true;
		Equipe e = creerEquipe(0, 0);
		for (TypeEpreuve te : getCompetence(0)) {
			if (!e.haveCompetence(te)) {
				b = false;
			}
		}
		assertTrue(b);
	}

	@Test
	public void setEpreuveTest() {
		Equipe e = creerEquipe(0, 0);
		e.setEpreuves(getCompetence(0));
		assertTrue(e.getEpreuves().size() == 5);
	}

	@Test
	public void tourTest() {
		try {
			Epreuve e = new Epreuve(creeEquipe(4), getCompetence(0).get(0));
			assertTrue(e.getCurrentMatch().size() == 2);
		} catch (nbrArbitreInsufisantException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void isMatchTest() {
		try {
			ArrayList<Equipe> equipes = new ArrayList<Equipe>();
			equipes = creeEquipe(4);
			Epreuve e = new Epreuve(equipes, getCompetence(0).get(0));
			Match m = e.getCurrentMatch().get(0);
			Arbitre a = m.getArbitre();
			e.setScore(a, m, 0, 10);
			assertEquals(m.getE2(), m.getVainqueur());
		} catch (nbrArbitreInsufisantException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(expected = NombreDeParticipantInsufisantException.class)
	public void tournoiTest() throws NombreDeParticipantInsufisantException {
		Tournoi t = new Tournoi("test", getCompetence(5));
		t.verificationTournoi();
	}

	@Test(expected = EpreuveDejaExistanteException.class)
	public void supprimerepreuveTest() throws EpreuveDejaExistanteException {
		Tournoi t = new Tournoi("test", getCompetence(5));
		ArrayList<Equipe> equipes = creeEquipe(5);
		HashMap<Equipe, Joueur> map = new HashMap<Equipe, Joueur>();
		for (Equipe e : equipes) {
			map.put(e, null);
		}
		try {
			t.setEpreuve(getCompetence(0).get(0),map);
		} catch (NombreDeParticipantInsufisantException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (EpreuveDejaExistanteException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		for (TypeEpreuve te : getCompetence(0)) {
			try {
				t.setEpreuve(te, map);
			} catch (NombreDeParticipantInsufisantException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
	@Test
	public void isMatchTest2() {
		try {
			ArrayList<Equipe> equipes = new ArrayList<Equipe>();
			equipes = creeEquipe(2);
			Epreuve e = new Epreuve(equipes, getCompetence(0).get(0));
			Match m = e.getCurrentMatch().get(0);
			Arbitre a = m.getArbitre();
			e.setScore(a, m, 0, 10);
			assertEquals(e.getVainqueur().getId(),m.getVainqueur().getId());
			System.out.println(e.getVainqueur().getId());
			System.out.println(m.getVainqueur().getId());
			assertFalse(e.getVainqueur().getId()!=m.getVainqueur().getId());
		} catch (nbrArbitreInsufisantException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
