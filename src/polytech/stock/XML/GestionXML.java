package polytech.stock.XML;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import polytech.stock.GestionnaireDeStock;

/**
 * @author Antoine Pultier Classe commune à toutes les autres pour la gestion du
 *         XML.
 */
public abstract class GestionXML implements GestionnaireDeStock {

	/**
	 * Méthode renvoyant le nom de la relation XML, utilisé nottamment par le
	 * fichier.
	 * 
	 * @return Nom de la relation
	 */
	protected abstract String nomRelation();

	@Override
	public <CLASS_TYPE> List<CLASS_TYPE> recupererStock() {
		return GestionXML.chargerFichierXml(nomRelation(), this);
	}

	@Override
	public <CLASS_TYPE> void enregistrerStock(List<CLASS_TYPE> liste) {
		GestionXML.sauvegarderFichierXml(liste, nomRelation(), this);
	}

	/**
	 * Demande polimentà jdom d'écrire le xml sous forme de texte lisible.
	 * 
	 * @param flux
	 *            Fichier dans lequel écrire le document.
	 * @param document
	 *            Document à écrire
	 * @throws Exception
	 *             Exception Problème lors de l'écriture
	 */
	protected static void ecrireXML(OutputStream flux, Document document)
			throws Exception {
		// On affiche de façon à ce que ça soit lisible
		XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
		sortie.output(document, flux);
	}

	/**
	 * Création d'un document jdom à partir d'un flux.
	 * 
	 * @param flux
	 *            . Flux dans lequel il faut lire le document xml.
	 * @return Document xml construit à partir du flux.
	 * @throws Exception
	 *             Problème de lecture ou de construction du document.
	 */
	protected static Document creerDocument(InputStream flux) throws Exception {
		return new SAXBuilder().build(flux);
	}

	/**
	 * Création d'un document jdom pour écrire dedans.
	 * 
	 * @param nom
	 *            Nom du document à créer.
	 * @return Nouveau document créé.
	 */
	protected static Document creerDocument(String nom) {
		Document doc = new Document();
		doc.setRootElement(new Element(nom));
		return doc;
	}

	/**
	 * Récupère le noeud parent d'un document.
	 * 
	 * @param document
	 *            Document dans lequel il faut charger les noeuds xml.
	 * @return Noeud parent.
	 * @throws Exception
	 *             Le document est vide.
	 */
	protected static Element chargerElementFlux(Document document)
			throws Exception {
		Element e = document.getRootElement();

		if (e == null) {
			throw new Exception("Il n'y a pas d'éléments");
		}

		return e;
	}

	/**
	 * Charge un fichier xml, et créé les objets à partir des noeuds, à l'aide
	 * du gestionnaire de stock.
	 * 
	 * @param <CLASS_TYPE>
	 * @param nomClasse
	 *            Nom du fichier xml à charger.
	 * @param gestionnaire
	 *            Gestionnaire servant à créer les éléments à partir des noeuds
	 *            trouvés dans le document.
	 * @return Liste des objets créés à partir du gestionnaire.
	 */
	@SuppressWarnings("unchecked")
	protected static <CLASS_TYPE> List<CLASS_TYPE> chargerFichierXml(
			String nomClasse, GestionnaireDeStock gestionnaire) {
		List<CLASS_TYPE> liste = new ArrayList<CLASS_TYPE>();

		File fichier = new File(nomClasse + ".xml");

		if (fichier.exists()) {
			try {
				FileInputStream lecteurFichier = new FileInputStream(fichier);

				// Chargement du document
				for (Object e : GestionXML.chargerElementFlux(
						GestionXML.creerDocument(lecteurFichier)).getChildren()) {
					// Pour chaque noeud rencontré, on appelle le gestionnaire
					// de stock pour qu'il s'en occupe.
					liste.add((CLASS_TYPE) gestionnaire
							.construireDepuisStock(e));
				}

			} catch (Exception e) {
				System.out
						.println("Une erreur horrible est arrivée pendant le chargement du fichier : ");
				e.printStackTrace();
			}
		}

		return liste;
	}

	/**
	 * Enregistre une liste d'objets dans un document xml à l'aide du
	 * gestionnaire de stock.
	 * 
	 * @param <CLASS_TYPE>
	 * @param liste
	 *            Liste des objets à enregistrer.
	 * @param nomClasse
	 *            Nom du document XML à enregistrer.
	 * @param gestionnaire
	 *            Gestionnaire permettant de transformer les objets en noeuds
	 *            XML.
	 */
	protected static <CLASS_TYPE> void sauvegarderFichierXml(
			List<CLASS_TYPE> liste, String nomClasse,
			GestionnaireDeStock gestionnaire) {
		Document doc = creerDocument(nomClasse);
		Element root = doc.getRootElement();

		for (CLASS_TYPE e : liste) {
			root.addContent((Element) gestionnaire.construirePourStock(e));
		}

		try {
			ecrireXML(new FileOutputStream(nomClasse + ".xml"), doc);
		} catch (Exception e) {
			System.out
					.println("Impossible de sauvegarder la base de données : "
							+ e.getMessage());
		}
	}
}
