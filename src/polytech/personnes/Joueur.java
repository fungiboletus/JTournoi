package polytech.personnes;

import java.util.ArrayList;


public class Joueur extends Personne

{
	private ArrayList<String> competences;
	
	public Joueur (String nom, String prenom)
	{
		super(nom, prenom);
		this.competences = new ArrayList<String>();
	}
	
	
}
