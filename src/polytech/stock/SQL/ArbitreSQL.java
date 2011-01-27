package polytech.stock.SQL;

import java.sql.SQLException;
import java.sql.ResultSet;

import polytech.personnes.Arbitre;

public class ArbitreSQL extends PersonneCompetenteSQL
{
	protected String nomTable()
	{
		return "arbitres";
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
		
		recupererCompetences(a);
		
		return a;
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

		genererCompetences(a);
		
		return declarationPreparee;
	}
}
