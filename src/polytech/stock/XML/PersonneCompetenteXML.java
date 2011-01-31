package polytech.stock.XML;

import org.jdom.Element;

import polytech.jtournoi.TypeEpreuve;
import polytech.personnes.PersonneCompetente;
import polytech.stock.Stock;

/**
 * @author Antoine Pultier Gestion XML des compétences pour les personnes
 *         compétentes.
 */
public abstract class PersonneCompetenteXML extends GestionXML {

	/**
	 * Génération du xml des compétences d'une personne.
	 * 
	 * @param p
	 *            Personne compéptente
	 * @return Noeud xml représentant les compétences.
	 */
	protected Element genererCompetences(PersonneCompetente p) {
		Element competences = new Element("competences");

		for (TypeEpreuve competence : p.getCompetences()) {
			competences.addContent(new Element("competence").setAttribute("id",
					"" + competence.getId()));
		}

		return competences;
	}

	/**
	 * Récupération des compétences d'une personne.
	 * 
	 * @param p
	 *            Personne auquel il faut ajouter les compétences.
	 * @param noeud
	 *            Noeud xml qui contient les compétences.
	 */
	protected void recupererCompetences(PersonneCompetente p, Element noeud) {
		Element competences = noeud.getChild("competences");

		if (competences != null) {
			for (Object e : competences.getChildren()) {
				TypeEpreuve t = Stock.getTypeEpreuveParId(Integer
						.parseInt(((Element) e).getAttributeValue("id")));

				if (t != null) {
					p.addCompetence(t);
				}
			}
		}
	}

}
