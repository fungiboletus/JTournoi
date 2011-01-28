package polytech.stock.SQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class GestionREL extends GestionSQL
{
	protected int idRelation;

	public void setIdRelation(int id)
	{
		idRelation = id;
	}

	protected void gererClauseWhere(PreparedStatement p)
	{
		try
		{
			p.setInt(1, idRelation);
		} catch (SQLException e)
		{
			System.out.println("Impossible de spécifier la clause where : "+e.getMessage());
		}
	}
	@Override
	public Object construireDepuisStock(Object element)
	{
		ResultSet rs = (ResultSet) element;

		Integer[] a = new Integer[2];
	
		try
		{
			a[0] = rs.getInt(1);
			a[1] = rs.getInt(2);
		} catch (SQLException e)
		{
			System.out.println("Impossible de charger un élément : " + e.getMessage());
		}
		
		return a;
	}

	@Override
	public Object construirePourStock(Object element)
	{
		Integer[] a = (Integer[]) element;

		try
		{
			declarationPreparee.setInt(1, a[0]);
			declarationPreparee.setInt(2, a[1]); 
		} catch (SQLException e)
		{
			System.out.println("Impossible de créer un élement du stock : "+e.getMessage());
			return null;
		}

		return declarationPreparee;
	}
}
