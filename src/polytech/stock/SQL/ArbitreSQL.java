package polytech.stock.SQL;

import java.sql.SQLException;
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
			declarationPreparee.setString(1, a.getNom()); 
			declarationPreparee.setString(2, a.getPrenom()); 
			declarationPreparee.setString(3, a.getPassword()); 
		} catch (SQLException e)
		{
			System.out.println("Impossible de créer un élement du stock : "+e.getMessage());
			return null;
		}

		return declarationPreparee;
	}

	public List<Arbitre> getArbitres()
	{
		return arbitres;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <CLASS_TYPE> List<CLASS_TYPE> recupererStock()
	{
		return (List<CLASS_TYPE>) arbitres;
	}

}
