package polytech.stock.XML;

import org.jdom.Element;
import java.util.ArrayList;

import polytech.jtournoi.Equipe;
import polytech.jtournoi.TypeEpreuve;
import polytech.personnes.Joueur;
import polytech.stock.Stock;

public class EquipeXML extends GestionXML
{
	protected String nomRelation()
	{
		return "equipes";
	}

	@Override
	public Object construireDepuisStock(Object element)
	{
		Element noeud = (Element) element;

		Equipe a = new Equipe(noeud.getChildText("nom"));
		
		a.setId(Integer.parseInt(noeud.getAttributeValue("id")));

		Element membres = noeud.getChild("membres");
		if (membres != null)
		{
			for (Object e : membres.getChildren())
			{
				Joueur j = Stock.getJoueurParId(Integer.parseInt(((Element) e).getAttributeValue("id")));

				if (j != null)
				{
					a.ajouterParticipant(j);
				}
			}
		}
		
		Element competences = noeud.getChild("epreuves");

		if (competences != null)
		{
			ArrayList<TypeEpreuve> tabCompetences = new ArrayList<TypeEpreuve>();
			for (Object e : competences.getChildren())
			{
				TypeEpreuve t = Stock.getTypeEpreuveParId(Integer.parseInt(((Element) e).getAttributeValue("id")));

				if (t != null)
				{
					tabCompetences.add(t);
				}
			}
			// TODO Dès que la méthode ne fait plus d'exceptions, c'est à décommenter
			//a.setEpreuves(tabCompetences);
		}

		return a;
	}

	@Override
	public Object construirePourStock(Object element)
	{
		Equipe a = (Equipe) element;

		Element noeud = new Element("equipe");
		
		noeud.setAttribute("id", ""+a.getId());

		noeud.addContent(new Element("nom").setText(a.getNom()));

		Element membres = new Element("membres");
		for (Joueur j : a.getMembres())
		{
			membres.addContent(new Element("membre").setAttribute("id", ""+j.getId()));
		}
		noeud.addContent(membres);

		Element epreuves = new Element("epreuves");
		for (TypeEpreuve te : a.getEpreuves())
		{
			epreuves.addContent(new Element("epreuve").setAttribute("id", ""+te.getId()));
		}
		noeud.addContent(epreuves);

		return noeud;
	}
}
