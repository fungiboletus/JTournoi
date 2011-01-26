package polytech.stock.SQL;

import java.sql.SQLException;
import java.util.List;

import polytech.personnes.Organisateur;

public class OrganisateurSQL extends PersonneSQL
{
	protected List<Organisateur> organisateurs;
	
	protected String nomTable()
	{
		return "organisateurs";
	}

	@Override
	public void sauvegarderStock()
	{
		GestionSQL.seConnecterSiNecessaire();
		sauvegarderDansBase(organisateurs);
	}
	
	@Override
	public Object construirePourStock(Object element)
	{
		Organisateur a = (Organisateur) element;

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
	
	@SuppressWarnings("unchecked")
	@Override
	public <CLASS_TYPE> List<CLASS_TYPE> recupererStock()
	{
		return (List<CLASS_TYPE>) organisateurs;
	}

}
