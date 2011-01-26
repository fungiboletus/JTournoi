package polytech.stock.SQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import polytech.personnes.Joueur;

public class JoueurSQL extends PersonneCompetenteSQL
{

protected List<Joueur> joueurs;
	
	protected String nomTable()
	{
		return "joueurs";
	}
	
	@Override
	public void chargerStock()
	{
		GestionSQL.seConnecterSiNecessaire();
		joueurs = chargerDepuisBase();
	}
	
	@Override
	public void sauvegarderStock()
	{
		GestionSQL.seConnecterSiNecessaire();
		sauvegarderDansBase(joueurs);
	}
	
	@Override
	public Object construirePourStock(Object element)
	{
		Joueur a = (Joueur) element;

		try
		{
			declarationPreparee.setInt(1, a.getId());
			declarationPreparee.setString(2, a.getNom()); 
			declarationPreparee.setString(3, a.getPrenom()); 
			declarationPreparee.setString(4, a.getPassword()); 
		} catch (SQLException e)
		{
			System.out.println("Impossible de créer un élement du stock : "+e.getMessage());
			return null;
		}

		return declarationPreparee;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <CLASS_TYPE> List<CLASS_TYPE> recupererStock()
	{
		return (List<CLASS_TYPE>) joueurs;
	}
	
	@SuppressWarnings("unchecked")
	public <CLASS_TYPE> void enregistrerStock(List<CLASS_TYPE> liste)
	{
		joueurs = (List<Joueur>) liste;
	}
	
	@Override
	public Object construireDepuisStock(Object element)
	{
		ResultSet rs = (ResultSet) element;

		Joueur a = new Joueur();
	
		try
		{
			a.setId(rs.getInt(1));
			a.setNom(rs.getString(2));
			a.setPrenom(rs.getString(3));
			a.setPassword(rs.getString(4));

		} catch (SQLException e)
		{
			System.out.println("Impossible de charger un élément : " + e.getMessage());
		}
		
		return a;
	}

}
