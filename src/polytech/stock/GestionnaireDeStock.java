package polytech.stock;

import java.util.List;

/**
 * @author Antoine Pultier
 * 
 */
public interface GestionnaireDeStock
{
	/**
	 * Récupère le stock qui a été préalablement chargé.
	 */
	public <CLASS_TYPE> List<CLASS_TYPE> recupererStock();

	/**
	 * Enregistre le stock pour ensuite le sauvegarder.
	 */
	public <CLASS_TYPE> void enregistrerStock(List<CLASS_TYPE> liste);
	
	/**
	 * Construit un objet à partir d'un élément du stock.
	 */
	public Object construireDepuisStock(Object element);

	/**
	 * Produit un objet à destination du stock.
	 */
	public Object construirePourStock(Object element);
}
