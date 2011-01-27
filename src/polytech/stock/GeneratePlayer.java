package polytech.stock;
import java.util.List;

import polytech.jtournoi.Equipe;
import polytech.jtournoi.TypeEpreuve;
import polytech.personnes.*;

public class GeneratePlayer {
	
	public static void main(String[] args){
		
		//Création de 20 joueurs.
	Joueur j1 = new Joueur("Stinson","Barney","awesome");
	Joueur j2 = new Joueur("Mosby","Ted","12345");
	Joueur j3 = new Joueur("Eriksen","Marshall","azerty");
	Joueur j4 = new Joueur("Scherbatsky","Robin","a");
	Joueur j5 = new Joueur("Aldrin","Lily","b");
	Joueur j6 = new Joueur("Mosby","The daughter","c");
	Joueur j7 = new Joueur("Mosby","The son","d");
	Joueur j8 = new Joueur("Driver","Ranjit","e");
	Joueur j9 = new Joueur("Hofstadter","Leonard","f");
	Joueur j10 = new Joueur("Cooper","Sheldon","g");
	Joueur j11 = new Joueur("Penny","Penny","h");
	Joueur j12 = new Joueur("Wolowitz ","Howard","i");
	Joueur j13 = new Joueur("Koothrappali","Raj","j");
	Joueur j14 = new Joueur("Wolowitz","The Mother","k");
	Joueur j15 = new Joueur("Wheaton","Wil","l");
	Joueur j16 = new Joueur("Who","Bernadette","m");
	Joueur j17 = new Joueur("Harper","Charly","n");
	Joueur j18 = new Joueur("Harper","Alan","o");
	Joueur j19 = new Joueur("Harper","Jack","p");
	Joueur j20 = new Joueur("Housekeeper","Berta","q");
	
		//Ajout de compétences (sports) aux joueurs.
	List<TypeEpreuve> epreuves = CatalogueEpreuves.recupererTypesEpreuves();
	j1.add(epreuves.get(1));
	j2.add(epreuves.get(2));
	j2.add(epreuves.get(3));
	j2.add(epreuves.get(4));
	j3.add(epreuves.get(5));
	j3.add(epreuves.get(6));
	j3.add(epreuves.get(7));
	j4.add(epreuves.get(8));
	j5.add(epreuves.get(9));
	j6.add(epreuves.get(10));
	j6.add(epreuves.get(1));
	j7.add(epreuves.get(1));
	j7.add(epreuves.get(2));
	j7.add(epreuves.get(7));
	j7.add(epreuves.get(8));
	j7.add(epreuves.get(3));
	j8.add(epreuves.get(1));
	j8.add(epreuves.get(3));
	j9.add(epreuves.get(1));
	j9.add(epreuves.get(2));
	j9.add(epreuves.get(3));
	j9.add(epreuves.get(4));
	j10.add(epreuves.get(3));
	j11.add(epreuves.get(4));
	j12.add(epreuves.get(5));
	j13.add(epreuves.get(6));
	j14.add(epreuves.get(7));
	j15.add(epreuves.get(8));
	j16.add(epreuves.get(9));
	j17.add(epreuves.get(10));
	j18.add(epreuves.get(1));
	j18.add(epreuves.get(2));
	j18.add(epreuves.get(3));
	j18.add(epreuves.get(4));
	j19.add(epreuves.get(5));
	j19.add(epreuves.get(6));
	j19.add(epreuves.get(7));
	j19.add(epreuves.get(8));
	j20.add(epreuves.get(2));
	j20.add(epreuves.get(3));
	j20.add(epreuves.get(4));
	j20.add(epreuves.get(5));
	
	  // Création de 4 équipes de 5 joueurs.
	Equipe e1 = new Equipe("HIMYM");
	Equipe e2 = new Equipe("TBBT");
	Equipe e3 = new Equipe("TAAHM");
	Equipe e4 = new Equipe("Mess");
	e1.ajouterParticipant(j1);
	e1.ajouterParticipant(j2);
	e1.ajouterParticipant(j3);
	e1.ajouterParticipant(j4);
	e1.ajouterParticipant(j5);
	e2.ajouterParticipant(j9);
	e2.ajouterParticipant(j10);
	e2.ajouterParticipant(j11);
	e2.ajouterParticipant(j12);
	e2.ajouterParticipant(j13);
	e3.ajouterParticipant(j17);
	e3.ajouterParticipant(j18);
	e3.ajouterParticipant(j19);
	e3.ajouterParticipant(j20);
	e3.ajouterParticipant(j6);
	e4.ajouterParticipant(j7);
	e4.ajouterParticipant(j8);
	e4.ajouterParticipant(j14);
	e4.ajouterParticipant(j15);
	e4.ajouterParticipant(j16);
	}
	
}
