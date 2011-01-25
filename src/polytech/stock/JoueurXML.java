package polytech.stock;

import java.util.List;

import org.jdom.Element;

import polytech.personnes.Joueur;

public class JoueurXML extends GestionXML
{

	protected List<Joueur> joueurs;

	@Override
	public void chargerStock()
	{
		joueurs = GestionXML.chargerFichierXml("joueurs", this);
	}

	@Override
	public void sauvegarderStock()
	{
		GestionXML.sauvegarderFichierXml(joueurs, "joueurs", this);
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

		Joueur j = new Joueur(
				noeud.getChildText("nom"),
				noeud.getChildText("prenom"),
				noeud.getChildText("motdepasse")
			);

		Element competences = noeud.getChild("competences");

		if (competences != null)
		{
			for (Object e : competences.getChildren())
			{
				j.add(((Element) e).getText());
			}
		}

		return j;
	}

	@Override
	public Object construirePourStock(Object element)
	{
		Joueur j = (Joueur) element;

		Element noeud = new Element("joueur");

		noeud.addContent(new Element("nom").setText(j.getNom()));
		noeud.addContent(new Element("prenom").setText(j.getPrenom()));
		noeud.addContent(new Element("motdepasse").setText(j.getPassword()));

		Element competences = new Element("competences");

		for (String competence : j.getCompetences())
		{
			competences.addContent(new Element("competence").setText(competence));
		}

		noeud.addContent(competences);

		return noeud;
	}
}
