package polytech.stock;

import java.util.ArrayList;
import java.util.List;

import polytech.personnes.Arbitre;
import polytech.personnes.Joueur;
import polytech.personnes.Organisateur;

import polytech.jtournoi.TypeEpreuve;

import polytech.stock.SQL.ArbitreSQL;
import polytech.stock.XML.ArbitreXML;
import polytech.stock.SQL.JoueurSQL;
import polytech.stock.XML.JoueurXML;
import polytech.stock.SQL.OrganisateurSQL;
import polytech.stock.XML.OrganisateurXML;

public abstract class Stock
{
	protected static List<Arbitre> arbitres;
	protected static List<Joueur> joueurs;
	protected static List<Organisateur> organisateurs;
	protected static List<TypeEpreuve> typesEpreuves;

	public static void chargerStock(TypeChargement mode)
	{
		typesEpreuves = CatalogueEpreuves.recupererTypesEpreuves();

		GestionnaireDeStock gestionArbitres = null;
		GestionnaireDeStock gestionJoueurs = null;
		GestionnaireDeStock gestionOrganisateurs = null;

		switch (mode)
		{
			case SQL:
				gestionArbitres = new ArbitreSQL(); 
				gestionJoueurs = new JoueurSQL(); 
				gestionOrganisateurs = new OrganisateurSQL(); 
				break;
			case XML:
				gestionArbitres = new ArbitreXML(); 
				gestionJoueurs = new JoueurXML(); 
				gestionOrganisateurs = new OrganisateurXML(); 
				break;
		}

		arbitres = gestionArbitres.recupererStock();
		joueurs = gestionJoueurs.recupererStock();
		organisateurs = gestionOrganisateurs.recupererStock();
	}

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
	
	public static List<Arbitre> getArbitresLibres()
	{
		List<Arbitre> arbitresLibres = new ArrayList<Arbitre>();
		
		for (Arbitre a : arbitres)
		{
			if (a.getBusy())
			{
				arbitresLibres.add(a);
			}
		}
		
		return arbitresLibres;
	}
}
