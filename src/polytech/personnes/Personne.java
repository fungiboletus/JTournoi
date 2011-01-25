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

    protected String nom;

    /**
     *Le prenom de la personne.
     */

    protected String prenom;

    
    /**
     *Le mot de passe de la personne.
     */
    
    protected String password;
    
     /**
     *Constructeur d'une personne.
     *@param nom Le nom de la personne.
     *@param prenom Le prenom de la personne.
     *@param password Le mot de passe de la personne.
     */

    public Personne (String nom, String prenom, String password)
    {
	this.nom = nom;
	this.prenom = prenom;
	this.password = password;
    }

    /**
     *Constructeur par defaut.
     *Le nom de la personne est "nom inconnu", son prenom "prenom inconnu" et son mot de passe "mot de passe inconnu".
     */

    public Personne()
    {
	this("nom inconnu","prenom inconnu","mot de passe inconnu");
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
    
    /**
     *Accesseur en lecture.
     *@return Le mot de passe de la personne.
     */

    public String getPassword()
    {
	return password;
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
    
    /**Mutateur
     *Modifie le prenom de la personne par le nom donne.
     *@param prenom Nouveau prenom de la personne.
     */

    public void setPassword(String password)
    {
	this.password = password;
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
