package polytech.stock.XML;

import org.jdom.Element;

import polytech.personnes.Joueur;

/**
 * @author Antoine Pultier
 * Gestion XML d'un joueur.
 */
public class JoueurXML extends PersonneCompetenteXML
{
	@Override
	protected String nomRelation()
	{
		return "joueurs";
	}

	@Override
	public Object construireDepuisStock(Object element)
	{
		Element noeud = (Element) element;

		Joueur j = new Joueur();
		
		j.setNom(noeud.getChildText("nom"));
		j.setPrenom(noeud.getChildText("prenom"));
		j.setPasswordHash(noeud.getChildText("motdepasse"));
		
		j.setId(Integer.parseInt(noeud.getAttributeValue("id")));
		j.setScore(Integer.parseInt(noeud.getAttributeValue("score")));

		recupererCompetences(j, noeud);

		return j;
	}

	@Override
	public Object construirePourStock(Object element)
	{
		Joueur j = (Joueur) element;

		Element noeud = new Element("joueur");
		
		noeud.setAttribute("id", ""+j.getId());
		noeud.setAttribute("score", ""+j.getScore());
		
		noeud.addContent(new Element("nom").setText(j.getNom()));
		noeud.addContent(new Element("prenom").setText(j.getPrenom()));
		noeud.addContent(new Element("motdepasse").setText(j.getPasswordHash()));

		noeud.addContent(genererCompetences(j));

		return noeud;
	}
}
