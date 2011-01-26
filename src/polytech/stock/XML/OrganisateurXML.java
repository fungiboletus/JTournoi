package polytech.stock.XML;

import java.util.List;

import org.jdom.Element;

import polytech.personnes.Organisateur;

public class OrganisateurXML extends GestionXML
{

	protected List<Organisateur> organisateurs;

	@Override
	public void chargerStock()
	{
		organisateurs = GestionXML.chargerFichierXml("organisateurs", this);
	}

	@Override
	public void sauvegarderStock()
	{
		GestionXML.sauvegarderFichierXml(organisateurs, "organisateurs", this);
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

		Organisateur a = new Organisateur(
				noeud.getChildText("nom"),
				noeud.getChildText("prenom"),
				noeud.getChildText("motdepasse")
			);

		return a;
	}

	@Override
	public Object construirePourStock(Object element)
	{
		Organisateur a = (Organisateur) element;

		Element noeud = new Element("organisateur");

		noeud.addContent(new Element("nom").setText(a.getNom()));
		noeud.addContent(new Element("prenom").setText(a.getPrenom()));
		noeud.addContent(new Element("motdepasse").setText(a.getPassword()));

		return noeud;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <CLASS_TYPE> List<CLASS_TYPE> recupererStock()
	{
		return (List<CLASS_TYPE>) organisateurs;
	}

}
