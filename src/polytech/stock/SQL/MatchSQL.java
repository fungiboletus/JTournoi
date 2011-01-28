package polytech.stock.SQL;

import java.sql.ResultSet;
import java.sql.SQLException;

import polytech.jtournoi.Match;
import polytech.stock.Stock;

public class MatchSQL extends GestionSQL
{

	@Override
	public Object construireDepuisStock(Object element)
	{
		ResultSet rs = (ResultSet) element;
		
		Match a = null;
		try
		{
			a = new Match(
					Stock.getEquipeParId(rs.getInt(2)),
					Stock.getEquipeParId(rs.getInt(3)),
					Stock.getArbitreParId(rs.getInt(6)),
					rs.getInt(7),
					rs.getInt(8)
				);
			
			a.setId(rs.getInt(1));
			a.setScE1(rs.getInt(4));
			a.setScE2(rs.getInt(5));
			
		} catch (SQLException e)
		{
			System.out.println("Impossible de charger un match : " + e.getMessage());
		}

		return a;
	}

	@Override
	public Object construirePourStock(Object element)
	{
		Match a = (Match) element;
		
		try
		{
			declarationPreparee.setInt(1, a.getId());
			declarationPreparee.setInt(2, a.getE1().getId());
			declarationPreparee.setInt(3, a.getE2().getId());
			declarationPreparee.setInt(4, a.getScE1());
			declarationPreparee.setInt(5, a.getScE2());
			declarationPreparee.setInt(6, a.getArbitre().getId());
			declarationPreparee.setInt(7, a.getNumero());
			declarationPreparee.setInt(8, a.getTour());
		} catch (SQLException e)
		{
			System.out.println("Impossible de créer un élement un match : "+e.getMessage());
			return null;
		}
		
		return declarationPreparee;
	}

	@Override
	protected String structureTable()
	{
		return "ID, ID_E1, ID_E2, SC_E1, SC_E2, ID_ARBITRE, NUMERO, TOUR";
	}

	@Override
	protected String structureTableTypee()
	{
		return "ID INTEGER PRIMARY KEY, ID_E1 INTEGER, ID_E2 INTEGER, SC_E1 INTEGER, SC_E2 INTEGER, ID_ARBITRE INTEGER, NUMERO INTEGER, TOUR INTEGER";
	}

	@Override
	protected String nomTable()
	{
		return "matchs";
	}

	@Override
	protected int nbInfosTable()
	{
		return 8;
	}

}
