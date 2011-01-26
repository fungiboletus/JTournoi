package polytech.stock.XML;

import org.jdom.Element;

import polytech.personnes.PersonneCompetente;

public abstract class PersonneCompetenteXML extends GestionXML
{

	protected Element genererCompetences(PersonneCompetente p)
	{
		Element competences = new Element("competences");

		for (String competence : p.getCompetences())
		{
			competences.addContent(new Element("competence").setText(competence));
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
				p.add(((Element) e).getText());
			}
		}
	}

}
