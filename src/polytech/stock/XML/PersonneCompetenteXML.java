package polytech.stock.XML;

import org.jdom.Element;

import polytech.jtournoi.TypeEpreuve;
import polytech.personnes.PersonneCompetente;
import polytech.stock.Stock;

public abstract class PersonneCompetenteXML extends GestionXML
{

	protected Element genererCompetences(PersonneCompetente p)
	{
		Element competences = new Element("competences");

		for (TypeEpreuve competence : p.getCompetences())
		{
			competences.addContent(new Element("competence").setAttribute("id", ""+competence.getId()));
		}

		return competences;
	}

	protected void recupererCompetences(PersonneCompetente p, Element noeud)
	{
		Element competences = noeud.getChild("competences");

		if (competences != null)
		{
			for (Object e : competences.getChildren())
			{
				TypeEpreuve t = Stock.getTypeEpreuveParId(Integer.parseInt(((Element) e).getAttributeValue("id")));

				if (t != null)
				{
					p.addCompetence(t);
				}
			}
		}
	}

}
