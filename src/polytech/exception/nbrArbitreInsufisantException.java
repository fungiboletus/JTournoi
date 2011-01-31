package polytech.exception;

import polytech.jtournoi.TypeEpreuve;

public class nbrArbitreInsufisantException extends Exception {

	public nbrArbitreInsufisantException(int i, TypeEpreuve te) {
		System.out.println("Il manque "+i+" arbitres ayant la compétence "+te.getNom());
		System.out.println("Veuillez relancer méthode tour() sur l'épreuve correspondate une fois que le nombre d'arbitre sera suffisant");
	}
	
}
