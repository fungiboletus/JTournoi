package polytech.stock.SQL;

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
