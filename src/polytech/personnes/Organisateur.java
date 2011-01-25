package polytech.personnes;

/**
 *Gestion d'un organisateur dans le cadre d'un tournoi sportif.
 *@author Sylvestre Genevier
 *@version 1.0
 */

public class Organisateur extends Personne

{
	/**
    *Constructeur d'un organisateur.
    *@param nom Le nom de l'organisateur.
    *@param prenom Le prenom de l'organisateur.
    *@param password Le mot de passe de l'organisateur.
    */
	
	public Organisateur (String nom, String prenom, String password)
	{
		super(nom, prenom, password);
	}
	
	 /**
    *Constructeur par defaut.
    *Le nom de l'organisateur est "nom inconnu", son prenom "prenom inconnu" et son mot de passe "mot de passe inconnu".
	*/

   public Organisateur ()
   {
   		super();
   }
}
