package polytech.stock;

import java.util.List;

import polytech.personnes.Arbitre;
import polytech.personnes.Joueur;
import polytech.personnes.Organisateur;

public abstract class Stock
{
	protected static List<Arbitre> arbitres;
	protected static List<Joueur> joueurs;
	protected static List<Organisateur> organisateurs;
	
	public static List<Arbitre> getArbitres()
	{
		return arbitres;
	}
	public static void setArbitres(List<Arbitre> arbitres)
	{
		Stock.arbitres = arbitres;
	}
	public static List<Joueur> getJoueurs()
	{
		return joueurs;
	}
	public static void setJoueurs(List<Joueur> joueurs)
	{
		Stock.joueurs = joueurs;
	}
	public static List<Organisateur> getOrganisateurs()
	{
		return organisateurs;
	}
	public static void setOrganisateurs(List<Organisateur> organisateurs)
	{
		Stock.organisateurs = organisateurs;
	}
		
}
