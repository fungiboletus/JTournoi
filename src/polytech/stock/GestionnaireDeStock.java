package polytech.stock;

import java.util.List;

/**
 * @author Antoine Pultier
 * Le gestionnaire de stock est une interface qui va permettre d'utiliser du polymorphisme.
 * 
 * Les méthodes de gestion du stock sont identiques que l'on ai du XML, du SQL, du Json, de la sérialisation, ou n'importe quoi d'autre.
 */
public interface GestionnaireDeStock
{
	/**
	 * Récupère le stock qui a été préalablement chargé.
	 * 
	 * @param <CLASS_TYPE>
	 * @return Liste des objets en stock.
	 */
	public <CLASS_TYPE> List<CLASS_TYPE> recupererStock();

	/**
	 * Enregistre le stock pour ensuite le sauvegarder.
	 * 
	 * @param <CLASS_TYPE>
	 * @param liste Liste des objets à enregistrer dans le stock.
	 */
	public <CLASS_TYPE> void enregistrerStock(List<CLASS_TYPE> liste);
	
	/**
	 * Construit un objet à partir de sa représentation du stock.
	 * 
	 * @param element Informations permettant de construire l'objet.
	 * @return Objet construit à partir du stock.
	 */
	public Object construireDepuisStock(Object element);

	/**
	 * Transforme un objet pour sa représentation dans le stock.
	 * 
	 * @param element Objet à transformer pour qu'il puisse être inséré dans le stock.
	 * @return Informations permettant d'insérer l'objet dans le stock.
	 */
	public Object construirePourStock(Object element);
}
