package polytech.stock;

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
}
