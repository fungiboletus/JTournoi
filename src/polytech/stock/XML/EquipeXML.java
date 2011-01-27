package polytech.stock.XML;

import org.jdom.Element;
import polytech.jtournoi.Equipe;

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

		return a;
	}

	@Override
	public Object construirePourStock(Object element)
	{
		Equipe a = (Equipe) element;

		Element noeud = new Element("equipe");
		
		noeud.setAttribute("id", ""+a.getId());

		noeud.addContent(new Element("nom").setText(a.getNom()));

		return noeud;
	}
}
