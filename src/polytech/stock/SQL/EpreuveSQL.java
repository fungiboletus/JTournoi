package polytech.stock.SQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import polytech.jtournoi.Epreuve;
import polytech.jtournoi.Equipe;
import polytech.jtournoi.Match;
import polytech.stock.Stock;

public class EpreuveSQL extends GestionSQL
{

	@Override
	public Object construireDepuisStock(Object element)
	{
		ResultSet rs = (ResultSet) element;

		Epreuve a = new Epreuve();
	
		try
		{
			a.setId(rs.getInt(1));
			a.setTour(rs.getInt(2));
			a.setTypeEpreuve(Stock.getTypeEpreuveParId(rs.getInt(3)));
			a.setVainqueur(Stock.getEquipeParId(rs.getInt(4)));
		} catch (SQLException e)
		{
			System.out.println("Impossible de charger un élément : " + e.getMessage());
		}
		
		EpreuveVainqueursREL evr = new EpreuveVainqueursREL();
		evr.setIdRelation(a.getId());
		
		List<Integer[]> vainqueurs =  evr.recupererStock();
		ArrayList<Equipe> listeVainqueurEquipe = new ArrayList<Equipe>();
		
		if (vainqueurs != null)
		{
			for (Integer[] e : vainqueurs)
			{
				if (e[1] == 0)
				{
					listeVainqueurEquipe.add(null);
				}
				else
				{
					listeVainqueurEquipe.add(Stock.getEquipeParId(e[1]));
				}
			}
		}
		
		a.setVainqueurEquipe(listeVainqueurEquipe);
		
		EpreuveMatchsREL epr = new EpreuveMatchsREL();
		epr.setIdRelation(a.getId());
		
		List<Integer[]> currentMatch =  epr.recupererStock();
		ArrayList<Match> listeCurrentMatch = new ArrayList<Match>();	
		
		if (currentMatch != null)
		{
			for (Integer[] e : vainqueurs)
			{
				if (e[1] == 0)
				{
					listeVainqueurEquipe.add(null);
				}
				else
				{
					listeCurrentMatch.add(Stock.getMatchParId(e[1]));
				}
			}
		}
		
		return a;
	}

	@Override
	public Object construirePourStock(Object element)
	{
		Epreuve a = (Epreuve) element;
		
		try
		{
			declarationPreparee.setInt(1, a.getId());
			declarationPreparee.setInt(2, a.getTour());
			declarationPreparee.setInt(3, a.getTypeEpreuve().getId());
			declarationPreparee.setInt(4, a.getVainqueur().getId());
			
		} catch (SQLException e)
		{
			System.out.println("Impossible de créer un élement épreuve : "+e.getMessage());
			return null;
		}
		
		List<Integer[]> listeVainqueurEquipe = new ArrayList<Integer[]>();
		
		for (Equipe j : a.getVainqueurEquipe())
		{
			Integer[] couple = new Integer[2];

			couple[0] = a.getId();
			couple[1] = j.getId();

			listeVainqueurEquipe.add(couple);
		}

		new EpreuveVainqueursREL().enregistrerStock(listeVainqueurEquipe);
		
		List<Integer[]> listeCurrentMatch = new ArrayList<Integer[]>();
		
		for (Match m : a.getCurrentMatch())
		{
			Integer[] couple = new Integer[2];

			couple[0] = a.getId();
			couple[1] = m.getId();

			listeCurrentMatch.add(couple);
		}

		new EpreuveMatchsREL().enregistrerStock(listeCurrentMatch);
		
		return declarationPreparee;
	}

	@Override
	protected String structureTable()
	{
		return "ID, TOUR, ID_TYPE, ID_VAINQUEUR";
	}

	@Override
	protected String structureTableTypee()
	{
		return "ID INTEGER PRIMARY KEY, TOUR INTEGER, ID_TYPE INTEGER, ID_VAINQUEUR INTEGER";
	}

	@Override
	protected String nomTable()
	{
		return "epreuves";
	}

	@Override
	protected int nbInfosTable()
	{
		return 4;
	}

}
