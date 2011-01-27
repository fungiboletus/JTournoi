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
 * @author Antoine Pultier
 *
 */
public abstract class GestionXML implements GestionnaireDeStock
{

	protected abstract String nomRelation();

	@Override
	public <CLASS_TYPE> List<CLASS_TYPE> recupererStock()
	{
		return GestionXML.chargerFichierXml(nomRelation(), this);
	}

	@Override
	public <CLASS_TYPE> void enregistrerStock(List<CLASS_TYPE> liste)
	{
		GestionXML.sauvegarderFichierXml(liste, nomRelation(), this);
	}

	/** Demande polimentà jdom d'écrire le xml sous forme de texte lisible.*/
	protected static void ecrireXML(OutputStream flux, Document document) throws Exception
	{
		// On affiche de façon à ce que ça soit lisible
		XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
		sortie.output(document, flux);
	}

	/** Création d'un document jdom à partir d'un flux.*/
	protected static Document creerDocument(InputStream flux) throws Exception
	{
		return new SAXBuilder().build(flux);
	}
	
	/** Création d'un document jdom pour écrire dedans.*/
	protected static Document creerDocument(String nom) 
	{
		Document doc = new Document();
		doc.setRootElement(new Element(nom));
		return doc;
	}
	
	/** Récupère un noeud direct présent dans le document.*/
	protected static Element chargerElementFlux(Document flux) throws Exception
	{
		Element e = flux.getRootElement();

		if (e == null)
		{
			throw new Exception("Il n'y a pas d'éléments");
		}

		return e;
	}

	/** Charge un fichier xml à la mode de chez nous.*/
	@SuppressWarnings("unchecked")
	protected static <CLASS_TYPE> List<CLASS_TYPE> chargerFichierXml(String nomClasse, GestionnaireDeStock gestionnaire)
	{
		List<CLASS_TYPE> liste = new ArrayList<CLASS_TYPE>();
		
		File fichier = new File(nomClasse + ".xml");

		if (fichier.exists())
		{
			try
			{
				FileInputStream lecteurFichier = new FileInputStream(fichier);

				for (Object e : GestionXML.chargerElementFlux(GestionXML.creerDocument(lecteurFichier)).getChildren())
				{
					liste.add((CLASS_TYPE) gestionnaire.construireDepuisStock(e));
				}

			} catch (Exception e)
			{
				System.out.println("Une erreur horrible est arrivée pendant le chargement du fichier : ");
				e.printStackTrace();
			}
		}

		return liste;
	}

	/** Enregistre un fichier xml.*/
	protected static <CLASS_TYPE> void sauvegarderFichierXml(List<CLASS_TYPE> liste, String nomClasse, GestionnaireDeStock gestionnaire)
	{
		Document doc = creerDocument(nomClasse);
		Element root = doc.getRootElement();

		for (CLASS_TYPE e : liste)
		{
			root.addContent((Element) gestionnaire.construirePourStock(e));
		}

		try
		{
			ecrireXML(new FileOutputStream(nomClasse + ".xml"), doc);
		} catch (Exception e)
		{
			System.out.println("Impossible de sauvegarder la base de données : "+e.getMessage());
		}
	}
}
