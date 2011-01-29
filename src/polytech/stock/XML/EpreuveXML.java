package polytech.stock.XML;

import java.util.ArrayList;

import org.jdom.Element;

import polytech.jtournoi.Epreuve;
import polytech.jtournoi.Equipe;
import polytech.jtournoi.Match;
import polytech.stock.Stock;

public class EpreuveXML extends GestionXML
{

	@Override
	public Object construireDepuisStock(Object element)
	{
		Element noeud = (Element) element;

		Epreuve a = new Epreuve();
				
		a.setId(Integer.parseInt(noeud.getAttributeValue("id")));
		a.setTour(Integer.parseInt(noeud.getAttributeValue("id")));
		a.setTypeEpreuve(Stock.getTypeEpreuveParId(Integer.parseInt(noeud.getChild("type").getAttributeValue("id"))));

		a.setVainqueur(Stock.getEquipeParId(Integer.parseInt(noeud.getChild("vainqueur").getAttributeValue("id"))));
		
		Element vainqueurEquipe = noeud.getChild("vainqueurEquipe");
		ArrayList<Equipe> listeVainqueurEquipe = new ArrayList<Equipe>();	
		if (vainqueurEquipe != null)
		{
			for (Object e : vainqueurEquipe.getChildren())
			{
				Element ne = (Element) e;
				
				if (ne.getName().equals("null"))
				{
					listeVainqueurEquipe.add(null);
				}
				else
				{
					listeVainqueurEquipe.add(Stock.getEquipeParId(Integer.parseInt(ne.getAttributeValue("id"))));
				}
			}
		}
		a.setVainqueurEquipe(listeVainqueurEquipe);
		
		Element currentMatch = noeud.getChild("currentMatch");
		ArrayList<Match> listeCurrentMatch = new ArrayList<Match>();	
		if (currentMatch != null)
		{
			for (Object e : currentMatch.getChildren())
			{
				Element ne = (Element) e;
				
				if (ne.getName().equals("null"))
				{
					listeCurrentMatch.add(null);
				}
				else
				{
					listeCurrentMatch.add(Stock.getMatchParId(Integer.parseInt(ne.getAttributeValue("id"))));
				}
			}
		}
		a.setCurrentMatch(listeCurrentMatch);
		
		Element tableau = noeud.getChild("tableau");
		ArrayList<ArrayList<Equipe>> listeTableau = new ArrayList<ArrayList<Equipe>>();
		if (tableau != null)
		{
			for (Object e : tableau.getChildren())
			{
				Element ne = (Element) e;
				
				if (ne.getName().equals("null"))
				{
					listeTableau.add(null);
				}
				else
				{
					ArrayList<Equipe> listeSousTableau = new ArrayList<Equipe>();
					for (Object ee : ne.getChildren())
					{
						Element nee = (Element) ee;
						
						if (nee.getName().equals("null"))
						{
							listeSousTableau.add(null);
						}
						else
						{
							listeSousTableau.add(Stock.getEquipeParId(Integer.parseInt(ne.getAttributeValue("id"))));					
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

		Element noeud = new Element("epreuve");
		
		noeud.setAttribute("id", ""+a.getId());
		noeud.setAttribute("tour", ""+a.getTour());

		noeud.addContent(new Element("vainqueur").setAttribute("id", ""+a.getVainqueur().getId()));
		noeud.addContent(new Element("type").setAttribute("id", ""+a.getTypeEpreuve().getId()));

		Element vainqueurEquipe = new Element("vainqueurEquipe");
		for (Equipe j : a.getVainqueurEquipe())
		{			
			if (j != null)
			{
				vainqueurEquipe.addContent(new Element("equipe").setAttribute("id", ""+j.getId()));
			}
			else
			{
				vainqueurEquipe.addContent(new Element("null"));
			}
		}
		noeud.addContent(vainqueurEquipe);

		Element currentMatch = new Element("currentMatch");
		for (Match te : a.getCurrentMatch())
		{
			if (te != null)
			{
				currentMatch.addContent(new Element("match").setAttribute("id", ""+te.getId()));
			}
			else
			{
				vainqueurEquipe.addContent(new Element("null"));
			}
		}
		noeud.addContent(currentMatch);
		
		Element tableau = new Element("tableau");
		for (ArrayList<Equipe> t : a.getTableau())
		{
			if (t != null)
			{
				Element sousTableau = new Element("sousTableau");
				for (Equipe e : t)
				{
					if (e != null)
					{
						sousTableau.addContent(new Element("equipe").setAttribute("id", ""+e.getId()));
					}
					else
					{
						sousTableau.addContent(new Element("null"));
					}
				}
				tableau.addContent(sousTableau);				
			}
			else
			{
				tableau.addContent(new Element("null"));
			}
		}
		noeud.addContent(tableau);

		return noeud;
	}

	@Override
	protected String nomRelation()
	{
		return "epreuves";
	}

}
