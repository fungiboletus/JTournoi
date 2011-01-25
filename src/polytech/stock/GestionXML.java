package polytech.stock;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * @author Antoine Pultier
 *
 */
public abstract class GestionXML implements GestionnaireDeStock
{
	
	/** Demande polimentà jdom d'écrire le xml sous forme de texte lisible.*/
	public static void ecrireXML(OutputStream flux, Document document) throws Exception
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
	protected static Document creerDocument() throws Exception
	{
		Document doc = new Document();
		doc.setRootElement(new Element("data"));
		return doc;
	}
	
	/** Récupère un noeud direct présent dans le document.*/
	protected static Element chargerElementFlux(Document flux, String nom) throws Exception
	{
		Element e = flux.getRootElement().getChild(nom);

		if (e == null)
		{
			throw new Exception("Il n'y a pas d'éléments "+nom);
		}

		return e;
	}
	
	/** Fonction polymorphique à la C++.
	 * 
	 * Le langage java est compliqué par rapport au C++ pour ce genre d'utilisation.
	 * Cela s'explique par le fait que le type générique utilisé n'est connu qu'à l'exécution.
	 * 
	 * Cela reste pénible, mais ce n'est pas l'homme qui doit se plier à la machine.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" }) // je fais ce que je veux, avec mon IDE
	public static <CLASS_TYPE> List<CLASS_TYPE> tokamakGenerique(Element noeud, Class hackJava) throws Exception
	{
		List<Element> liste = noeud.getChildren();

		List<CLASS_TYPE> noeuds = new ArrayList<CLASS_TYPE>(liste.size());
		
		for (Element e : liste)
		{
			//noeuds.add(new CANARD(e)); // C++ is better
			noeuds.add((CLASS_TYPE) hackJava.getDeclaredConstructor(Element.class).newInstance(e));
		}
		
		return noeuds;
	}

}
