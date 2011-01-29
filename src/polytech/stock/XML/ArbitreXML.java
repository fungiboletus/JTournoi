package polytech.stock.XML;

import org.jdom.Element;

import polytech.personnes.Arbitre;

/**
 * @author Antoine Pultier
 * Gestion du XML d'un arbitre.
 */
public class ArbitreXML extends PersonneCompetenteXML
{
	@Override
	protected String nomRelation()
	{
		return "arbitres";
	}

	@Override
	public Object construireDepuisStock(Object element)
	{
		Element noeud = (Element) element;

		Arbitre a = new Arbitre(
				noeud.getChildText("nom"),
				noeud.getChildText("prenom"),
				noeud.getChildText("motdepasse")
			);

		a.setId(Integer.parseInt(noeud.getAttributeValue("id")));
		
		recupererCompetences(a, noeud);

		return a;
	}

	@Override
	public Object construirePourStock(Object element)
	{
		Arbitre a = (Arbitre) element;

		Element noeud = new Element("arbitre");
		
		noeud.setAttribute("id", ""+a.getId());

		noeud.addContent(new Element("nom").setText(a.getNom()));
		noeud.addContent(new Element("prenom").setText(a.getPrenom()));
		noeud.addContent(new Element("motdepasse").setText(a.getPassword()));

		noeud.addContent(genererCompetences(a));

		return noeud;
	}
	
}
