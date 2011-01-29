package polytech.stock.SQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import polytech.jtournoi.Epreuve;
import polytech.jtournoi.Equipe;
import polytech.jtournoi.Match;
import polytech.stock.Stock;

/**
 * @author Antoine Pultier
 * Gestion SQL d'une épreuve.
 */
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
		
		// Récupération de la liste des vainqueurs
		EpreuveVainqueursREL evr = new EpreuveVainqueursREL();
		evr.setIdRelation(a.getId());
		
		List<Integer[]> vainqueurs =  evr.recupererStock();
		ArrayList<Equipe> listeVainqueurEquipe = new ArrayList<Equipe>();
		
		if (vainqueurs != null)
		{
			for (Integer[] e : vainqueurs)
			{
				// Si l'id vaut 0, c'est que l'on a affaire à un null dans la liste
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
		
		
		// Récupération de la liste des matchs courants
		EpreuveMatchsREL epr = new EpreuveMatchsREL();
		epr.setIdRelation(a.getId());
		
		List<Integer[]> currentMatch =  epr.recupererStock();
		ArrayList<Match> listeCurrentMatch = new ArrayList<Match>();	
		
		if (currentMatch != null)
		{
			for (Integer[] e : currentMatch)
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
		a.setCurrentMatch(listeCurrentMatch);
		
		// Récupération de la matrice des matchs
		EpreuveTableauxREL etr = new EpreuveTableauxREL();
		etr.setIdRelation(a.getId());
		
		List<Integer[]> tableau = etr.recupererStock();
		ArrayList<ArrayList<Equipe>> listeTableau = new ArrayList<ArrayList<Equipe>>();
		
		if (tableau != null)
		{
			for (Integer[] e : tableau)
			{
				if (e[1] == 0)
				{
					listeTableau.add(null);
				}
				else
				{
					EpreuveSousTableauxREL estr = new EpreuveSousTableauxREL();
					estr.setIdRelation(e[0]);
					
					List<Integer[]> sousTableau = estr.recupererStock();
					ArrayList<Equipe> listeSousTableau = new ArrayList<Equipe>();
					
					for (Integer[] ee : sousTableau)
					{
						if (ee[1] == 0)
						{
							listeSousTableau.add(null);
						}
						else
						{
							listeSousTableau.add(Stock.getEquipeParId(ee[1]));
						}
					}
					
					listeTableau.add(listeSousTableau);
				}
			}
		}
		a.setTableau(listeTableau);
		
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
		
		// Création de la liste des vainqueurs
		List<Integer[]> listeVainqueurEquipe = new ArrayList<Integer[]>();
		
		for (Equipe j : a.getVainqueurEquipe())
		{
			Integer[] couple = new Integer[2];

			couple[0] = a.getId();
			
			if (j != null)
			{
				couple[1] = j.getId();
			}
			else
			{
				couple[1] = 0;
			}
			listeVainqueurEquipe.add(couple);
		}

		new EpreuveVainqueursREL().enregistrerStock(listeVainqueurEquipe);
		
		// Création de la liste des matchs en cours
		List<Integer[]> listeCurrentMatch = new ArrayList<Integer[]>();
		
		for (Match m : a.getCurrentMatch())
		{
			Integer[] couple = new Integer[2];

			couple[0] = a.getId();
			
			if (m != null)
			{
				couple[1] = m.getId();
			}
			else
			{
				couple[1] = 0;
			}

			listeCurrentMatch.add(couple);
		}

		new EpreuveMatchsREL().enregistrerStock(listeCurrentMatch);
		
		// Création de la matrice des matchs
		List<Integer[]> listeTableaux = new ArrayList<Integer[]>();
		for (ArrayList<Equipe> t : a.getTableau())
		{
			Integer[] couple = new Integer[2];
			couple[0] = a.getId();
			
			if (t != null)
			{
				// Création d'un identifiant supposé unique.
				// Si on dépasse 100000 références, d'autres problèmes se feront sentir bien avant
				couple[1] = a.getId() * 100000 + listeTableaux.size();
				List<Integer[]> listeSousTableaux = new ArrayList<Integer[]>();
				for (Equipe e : t)
				{
					Integer[] couple_bis = new Integer[2];

					// Les tableaux de la matrice sont liés par un identifiant calculé plus haut
					couple_bis[0] = couple[1];
					
					if (e != null)
					{
						couple_bis[1] = e.getId();
					}
					else
					{
						couple_bis[1] = 0;
					}
					listeSousTableaux.add(couple_bis);
				}
				new EpreuveSousTableauxREL().enregistrerStock(listeSousTableaux);
			}
			else
			{
				couple[1] = 0;
			}
			listeTableaux.add(couple);
		}
		new EpreuveTableauxREL().enregistrerStock(listeTableaux);
		
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
	protected int nbChamps()
	{
		return 4;
	}

}
