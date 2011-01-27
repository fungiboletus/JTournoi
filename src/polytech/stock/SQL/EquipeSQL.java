package polytech.stock.SQL;

import java.sql.ResultSet;
import java.sql.SQLException;

import polytech.jtournoi.Equipe;

public class EquipeSQL extends GestionSQL
{

	@Override
	protected String structureTable()
	{
		return "ID, NOM";
	}

	@Override
	protected String structureTableTypee()
	{
		return "ID INTEGER PRIMARY KEY, NOM TEXT";
	}

	@Override
	protected String nomTable()
	{
		return "equipes";
	}

	@Override
	protected int nbInfosTable()
	{
		return 2;
	}

	@Override
	public Object construireDepuisStock(Object element)
	{
		ResultSet rs = (ResultSet) element;

		Equipe a = new Equipe();
	
		try
		{
			a.setId(rs.getInt(1));
			a.setNom(rs.getString(2));
		} catch (SQLException e)
		{
			System.out.println("Impossible de charger un élément : " + e.getMessage());
		}
		
		return a;
	}

	@Override
	public Object construirePourStock(Object element)
	{
		Equipe a = (Equipe) element;

		try
		{
			declarationPreparee.setInt(1, a.getId());
			declarationPreparee.setString(2, a.getNom()); 
		} catch (SQLException e)
		{
			System.out.println("Impossible de créer un élement du stock : "+e.getMessage());
			return null;
		}

		return declarationPreparee;
	}

}
