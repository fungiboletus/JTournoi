package polytech.stock.SQL;

import java.util.List;

import java.sql.SQLException;
import java.sql.PreparedStatement;

import polytech.personnes.Organisateur;

public class OrganisateurSQL extends GestionSQL
{

	protected List<Organisateur> organisateurs;

	protected String structureTable()
	{
		return "NOM TEXT, PRENOM TEXT, MOTDEPASSE TEXT";
	}

	protected String nomTable()
	{
		return "organisateurs";
	}

	protected int nbInfosTable()
	{
		return 3;
	}

	@Override
	public void chargerStock()
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void sauvegarderStock()
	{
		GestionSQL.seConnecterSiNecessaire();
		sauvegarderDansBase(organisateurs);
	}

	@Override
	public void ajouterReference(Object reference)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void supprimerReference(Object reference)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public Object construireDepuisStock(Object element)
	{
		// TODO Auto-generated method stub
		return null;
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

}
