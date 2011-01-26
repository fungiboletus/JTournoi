package polytech.stock.SQL;

import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.List;

import polytech.personnes.Arbitre;

public class ArbitreSQL extends PersonneCompetenteSQL
{
	protected List<Arbitre> arbitres;
	
	protected String nomTable()
	{
		return "arbitres";
	}

	@Override
	public void chargerStock()
	{
		GestionSQL.seConnecterSiNecessaire();
		arbitres = chargerDepuisBase();
	}

	@Override
	public void sauvegarderStock()
	{
		GestionSQL.seConnecterSiNecessaire();
		sauvegarderDansBase(arbitres);
	}
	
	@Override
	public Object construirePourStock(Object element)
	{
		Arbitre a = (Arbitre) element;

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
		return (List<CLASS_TYPE>) arbitres;
	}

	@Override
	public Object construireDepuisStock(Object element)
	{
		ResultSet rs = (ResultSet) element;

		Arbitre a = new Arbitre();
	
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
