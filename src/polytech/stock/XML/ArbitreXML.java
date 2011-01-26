package polytech.stock.XML;

import java.util.List;

import org.jdom.Element;

import polytech.personnes.Arbitre;

public class ArbitreXML extends PersonneCompetenteXML
{

	protected List<Arbitre> arbitres;

	@Override
	public void chargerStock()
	{
		arbitres = GestionXML.chargerFichierXml("arbitres", this);
	}

	@Override
	public void sauvegarderStock()
	{
		GestionXML.sauvegarderFichierXml(arbitres, "arbitres", this);
	}

	@Override
	public void ajouterReference(Object reference)
	{
		
	}

	@Override
	public void supprimerReference(Object reference)
	{
		// TODO Auto-generated method stub

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
	
	@SuppressWarnings("unchecked")
	@Override
	public <CLASS_TYPE> List<CLASS_TYPE> recupererStock()
	{
		return (List<CLASS_TYPE>) arbitres;
	}

	@SuppressWarnings("unchecked")
	public <CLASS_TYPE> void enregistrerStock(List<CLASS_TYPE> liste)
	{
		arbitres = (List<Arbitre>) liste;
	}
}
