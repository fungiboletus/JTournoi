package polytech.personnes;

/**
 *Gestion d'un arbitre dans le cadre d'un tournoi sportif.
 *@author Sylvestre Genevier
 *@version 1.0
 */

public class Arbitre extends PersonneCompetente

{
	
	/**
	 * L'état de l'arbitre. Sera vrai si l'arbitre est occupé et faux sinon.	
	 */
	
	private boolean busy;
	
	/**
    *Constructeur d'un arbitre.
    *@param nom Le nom de l'arbitre.
    *@param prenom Le prenom de l'arbitre.
    *@param password Le mot de passe de l'arbitre.
    *De plus, sa liste de compétences est alors vide.
    */
	
	public Arbitre (String nom, String prenom, String password)
	{
		super(nom, prenom, password);
		busy = false;
	}
	
	 /**
    *Constructeur par defaut.
    *Le nom de l'arbitre est "nom inconnu", son prenom "prenom inconnu" et son mot de passe "mot de passe inconnu".
    *De plus, sa liste de compétence est aussi vide.
	 */

   public Arbitre ()
   {
   		super();
   		busy = false;
   }
   
   /**
    *Accesseur en lecture.
    *@return L'état d'occupation de l'arbitre.
    */

   public boolean getBusy()
   {
	return busy;
   }
   
   /**Mutateur
    *Modifie l'etat de la personne par l'etat donne.
    *@param busy Nouvel etat de la personne.
    */

   public void setBusy(boolean busy)
   {
	this.busy = busy;
   }
}
