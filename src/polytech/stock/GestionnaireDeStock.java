package polytech.stock;

import java.util.List;

/**
 * @author Antoine Pultier
 * 
 */
public interface GestionnaireDeStock
{
	/**
	 * Charge un stock
	 */
	public void chargerStock();

	/**
	 * Sauvegarde un stock
	 */
	public void sauvegarderStock();
	
	/**
	 * Ajoute une référence dans le stock
	 * @param reference Référence à ajouter
	 */
	public void ajouterReference(Object reference);

	/**
	 * Supprime une référence du stock
	 * @param reference Référence à supprimer
	 */
	public void supprimerReference(Object reference);

	/**
	 * Construit un objet à partir d'un élément du stock.
	 */
	public Object construireDepuisStock(Object element);

	/**
	 * Produit un objet à destination du stock.
	 */
	public Object construirePourStock(Object element);

	public <CLASS_TYPE> List<CLASS_TYPE> recupererStock();
}
