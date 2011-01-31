package polytech.stock.XML;

import org.jdom.Element;

import polytech.jtournoi.Match;
import polytech.stock.Stock;

/**
 * @author Antoine Pultier Gestion XML d'un match.
 */
public class MatchXML extends GestionXML {

	@Override
	public Object construireDepuisStock(Object element) {
		Element noeud = (Element) element;

		Element equipe_1 = noeud.getChild("equipe_1");
		Element equipe_2 = noeud.getChild("equipe_2");

		Element arbitre = noeud.getChild("arbitre");

		Match a = new Match(Stock.getEquipeParId(Integer.parseInt(equipe_1
				.getAttributeValue("id"))), Stock.getEquipeParId(Integer
				.parseInt(equipe_2.getAttributeValue("id"))),
				Stock.getArbitreParId(Integer.parseInt(arbitre
						.getAttributeValue("id"))), Integer.parseInt(noeud
						.getAttributeValue("numero")), Integer.parseInt(noeud
						.getAttributeValue("tour")));

		a.setId(Integer.parseInt(noeud.getAttributeValue("id")));
		a.setScE1(Integer.parseInt(equipe_1.getAttributeValue("score")));
		a.setScE2(Integer.parseInt(equipe_2.getAttributeValue("score")));

		return a;
	}

	@Override
	public Object construirePourStock(Object element) {
		Match a = (Match) element;

		Element noeud = new Element("match");

		noeud.setAttribute("id", "" + a.getId());
		noeud.setAttribute("numero", "" + a.getNumero());
		noeud.setAttribute("tour", "" + a.getTour());

		Element equipe_1 = new Element("equipe_1");
		equipe_1.setAttribute("id", "" + a.getE1().getId());
		equipe_1.setAttribute("score", "" + a.getScE1());

		Element equipe_2 = new Element("equipe_2");
		equipe_2.setAttribute("id", "" + a.getE2().getId());
		equipe_2.setAttribute("score", "" + a.getScE2());

		Element arbitre = new Element("arbitre");
		arbitre.setAttribute("id", "" + a.getArbitre().getId());

		noeud.addContent(equipe_1);
		noeud.addContent(equipe_2);
		noeud.addContent(arbitre);

		return noeud;
	}

	@Override
	protected String nomRelation() {
		return "matchs";
	}

}
