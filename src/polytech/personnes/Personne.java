package polytech.personnes;

/**
 *Gestion d'une personne dans le cadre d'un tournoi sportif.
 *@author Sylvestre Genevier
 *@version 1.0
 */

public class Personne
{

    /**
     *Le nom de la personne.
     */

    private String nom;

    /**
     *Le prenom de la personne.
     */

    private String prenom;

     /**
     *Constructeur d'une personne.
     *@param nom Le nom de la personne.
     *@param prenom Le prenom de la personne.
     */

    public Personne (String nom, String prenom)
    {
	this.nom = nom;
	this.prenom = prenom;
    }

    /**
     *Constructeur par defaut.
     *Le nom du joueur est "nom inconnu" et son prenom "prenom inconnu".
     *La liste de sports de ce joueur est egalement vide.
     */

    public Personne()
    {
	this("nom inconnu","prenom inconnu");
    }

    /**
     *Accesseur en lecture.
     *@return Le nom de la personne.
     */

    public String getNom()
    {
	return nom;
    }

    /**
     *Accesseur en lecture.
     *@return Le prenom de la personne.
     */

    public String getPrenom()
    {
	return prenom;
    }

    /**Mutateur
     *Modifie le nom de la personne par le nom donne.
     *@param nom Nouveau nom de la personne.
     */

    public void setNom(String nom)
    {
	this.nom = nom;
    }

    /**Mutateur
     *Modifie le prenom de la personne par le nom donne.
     *@param prenom Nouveau prenom de la personne.
     */

    public void setPrenom(String prenom)
    {
	this.prenom = prenom;
    }

    /**
     *Methode toString.
     *@return Nom, prenom.
     */

    public String toString()
    {
	return nom+ " " +prenom;
    }
}
