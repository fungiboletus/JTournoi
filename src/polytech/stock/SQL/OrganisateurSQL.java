package polytech.stock.SQL;

import java.sql.ResultSet;
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
	public void chargerStock()
	{
		GestionSQL.seConnecterSiNecessaire();
		organisateurs = chargerDepuisBase();
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
		return (List<CLASS_TYPE>) organisateurs;
	}
	
	@SuppressWarnings("unchecked")
	public <CLASS_TYPE> void enregistrerStock(List<CLASS_TYPE> liste)
	{
		organisateurs = (List<Organisateur>) liste;
	}
	
	@Override
	public Object construireDepuisStock(Object element)
	{
		ResultSet rs = (ResultSet) element;

		Organisateur a = new Organisateur();
	
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
