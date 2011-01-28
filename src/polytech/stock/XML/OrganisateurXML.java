package polytech.stock.XML;

import org.jdom.Element;

import polytech.personnes.Organisateur;

public class OrganisateurXML extends GestionXML
{
	@Override
	protected String nomRelation()
	{
		return "organisateurs";
	}

	@Override
	public Object construireDepuisStock(Object element)
	{
		Element noeud = (Element) element;

		Organisateur a = new Organisateur(
				noeud.getChildText("nom"),
				noeud.getChildText("prenom"),
				noeud.getChildText("motdepasse")
			);

		a.setId(Integer.parseInt(noeud.getAttributeValue("id")));
		
		return a;
	}

	@Override
	public Object construirePourStock(Object element)
	{
		Organisateur a = (Organisateur) element;

		Element noeud = new Element("organisateur");
		
		noeud.setAttribute("id", ""+a.getId());
		
		noeud.addContent(new Element("nom").setText(a.getNom()));
		noeud.addContent(new Element("prenom").setText(a.getPrenom()));
		noeud.addContent(new Element("motdepasse").setText(a.getPassword()));

		return noeud;
	}
}
