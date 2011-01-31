package polytech.stock.XML;

import java.util.ArrayList;

import org.jdom.Element;

import polytech.jtournoi.Epreuve;
import polytech.jtournoi.Equipe;
import polytech.jtournoi.Match;
import polytech.stock.Stock;

/**
 * @author Antoine Pultier
 * 
 *         Gestion du xml d'une épreuve.
 */
public class EpreuveXML extends GestionXML {

	@Override
	public Object construireDepuisStock(Object element) {
		Element noeud = (Element) element;

		Epreuve a = new Epreuve();

		// Récupération des informations de base de l'épreuve
		a.setId(Integer.parseInt(noeud.getAttributeValue("id")));
		a.setTour(Integer.parseInt(noeud.getAttributeValue("id")));
		a.setTypeEpreuve(Stock.getTypeEpreuveParId(Integer.parseInt(noeud
				.getChild("type").getAttributeValue("id"))));

		a.setVainqueur(Stock.getEquipeParId(Integer.parseInt(noeud.getChild(
				"vainqueur").getAttributeValue("id"))));

		// Récupération de la liste des vainqueurs
		Element vainqueurEquipe = noeud.getChild("vainqueurEquipe");
		ArrayList<Equipe> listeVainqueurEquipe = new ArrayList<Equipe>();
		if (vainqueurEquipe != null) {
			for (Object e : vainqueurEquipe.getChildren()) {
				Element ne = (Element) e;

				// C'est un cas particulier, il peut avoir des noeuds xml
				// signifiant qu'il y a un null dans la liste
				// C'est le cas dans la classe épreuve, pour des raisons qui
				// sont ignorées içi
				if (ne.getName().equals("null")) {
					listeVainqueurEquipe.add(null);
				} else {
					listeVainqueurEquipe.add(Stock.getEquipeParId(Integer
							.parseInt(ne.getAttributeValue("id"))));
				}
			}
		}
		a.setVainqueurEquipe(listeVainqueurEquipe);

		// Récupération de la liste des matchs courants, sur le même modèle que
		// la liste des vainqueurs
		Element currentMatch = noeud.getChild("currentMatch");
		ArrayList<Match> listeCurrentMatch = new ArrayList<Match>();
		if (currentMatch != null) {
			for (Object e : currentMatch.getChildren()) {
				Element ne = (Element) e;

				if (ne.getName().equals("null")) {
					listeCurrentMatch.add(null);
				} else {
					listeCurrentMatch.add(Stock.getMatchParId(Integer
							.parseInt(ne.getAttributeValue("id"))));
				}
			}
		}
		a.setCurrentMatch(listeCurrentMatch);

		// Récupération de la matrice des matchs
		// C'est le même principe que précédemment, sauf qu'il y a deux boucles
		// imbriquées.
		Element tableau = noeud.getChild("tableau");
		ArrayList<ArrayList<Equipe>> listeTableau = new ArrayList<ArrayList<Equipe>>();
		if (tableau != null) {
			for (Object e : tableau.getChildren()) {
				Element ne = (Element) e;

				if (ne.getName().equals("null")) {
					listeTableau.add(null);
				} else {
					ArrayList<Equipe> listeSousTableau = new ArrayList<Equipe>();
					for (Object ee : ne.getChildren()) {
						Element nee = (Element) ee;

						if (nee.getName().equals("null")) {
							listeSousTableau.add(null);
						} else {
							listeSousTableau.add(Stock.getEquipeParId(Integer
									.parseInt(ne.getAttributeValue("id"))));
						}
					}
					listeTableau.add(listeSousTableau);
				}
			}
		}
		a.setTableau(listeTableau);

		return a;
	}

	@Override
	public Object construirePourStock(Object element) {
		Epreuve a = (Epreuve) element;

		Element noeud = new Element("epreuve");

		// Construction des informations de base de l'épreuve
		noeud.setAttribute("id", "" + a.getId());
		noeud.setAttribute("tour", "" + a.getTour());

		noeud.addContent(new Element("vainqueur").setAttribute("id", ""
				+ a.getVainqueur().getId()));
		noeud.addContent(new Element("type").setAttribute("id", ""
				+ a.getTypeEpreuve().getId()));

		// Construction de la liste des vainqueurs
		Element vainqueurEquipe = new Element("vainqueurEquipe");
		for (Equipe j : a.getVainqueurEquipe()) {
			if (j != null) {
				vainqueurEquipe.addContent(new Element("equipe").setAttribute(
						"id", "" + j.getId()));
			} else {
				// Le tableau de la classe équipe contient des null. Il convient
				// de respecter cette particularité.
				vainqueurEquipe.addContent(new Element("null"));
			}
		}
		noeud.addContent(vainqueurEquipe);

		// Construction de la liste des matchs en cours, sur le même principe
		// que la liste des vainqueurs.
		Element currentMatch = new Element("currentMatch");
		for (Match te : a.getCurrentMatch()) {
			if (te != null) {
				currentMatch.addContent(new Element("match").setAttribute("id",
						"" + te.getId()));
			} else {
				vainqueurEquipe.addContent(new Element("null"));
			}
		}
		noeud.addContent(currentMatch);

		// Construction de la matrice des matchs.
		Element tableau = new Element("tableau");
		for (ArrayList<Equipe> t : a.getTableau()) {
			if (t != null) {
				Element sousTableau = new Element("sousTableau");
				for (Equipe e : t) {
					if (e != null) {
						sousTableau.addContent(new Element("equipe")
								.setAttribute("id", "" + e.getId()));
					} else {
						sousTableau.addContent(new Element("null"));
					}
				}
				tableau.addContent(sousTableau);
			} else {
				tableau.addContent(new Element("null"));
			}
		}
		noeud.addContent(tableau);

		return noeud;
	}

	@Override
	protected String nomRelation() {
		return "epreuves";
	}

}
