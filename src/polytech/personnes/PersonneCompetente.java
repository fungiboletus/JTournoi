package polytech.personnes;

import java.util.ArrayList;

/**
 *Gestion d'une personne compétente dans le cadre d'un tournoi sportif.
 *@author Sylvestre Genevier
 *@version 1.0
 */

public class PersonneCompetente extends Personne

{
	protected ArrayList<String> competences;	
	
	/**
     *Constructeur d'une personne compétente.
     *@param nom Le nom de la personne.
     *@param prenom Le prenom de la personne.
     *@param password Le mot de passe de la personne.
     *De plus, sa liste de compétences est alors vide.
     */
	
	public PersonneCompetente (String nom, String prenom, String password)
	{
		super(nom, prenom, password);
		this.competences = new ArrayList<String>();
	}
	
	 /**
     *Constructeur par defaut.
     *Le nom de la personne est "nom inconnu", son prenom "prenom inconnu" et son mot de passe "mot de passe inconnu".
     *De plus, sa liste de compétence est aussi vide.
	 */

    public PersonneCompetente ()
    {
    	super();
    	this.competences = new ArrayList<String>();
    }
	
	/**Accesseur en lecture.  
     *@param i Le numero de la competence demandee.
     *@return Le i-ieme sport de la personne dans la liste des competences, s'il existe. Dans le cas ou il n'existe pas, la methode renvoie une chaine vide de caracteres.      
     */
	
	public String getCompetence(int i)
    {
	if((i<0)||(i>=competences.size())) return "";
	else return competences.get(i);
    }

    /**
     *Accesseur en lecture.
     *@return Le nombre de competences de la personne.
     */
	
    public int getNombreDeCompetences()
    {
	return competences.size();
    }

     /**
     *Methode toString.
     *@return Nom, prenom et sport(s) de la personne.
     */

    public String toString()
    {
	String c = ""; 
	if (competences.size() == 0)
	    {
		c = " La personne n'a actuellement aucun sport";
	    }
	else
	    {
		for (int i = 0; i< competences.size(); i++)
		    {
			c =  c+ " " +competences.get(i);
		    }
	    }
	return nom+ " " +prenom+ ": Compétence(s) :"+c;
    }


    /**
     *Methode afficheCompetences.
     *Affiche dans le terminal une ligne contenant le(s) sport(s) de la personne. Quand aucun sport n'a ete rentre pour la personne, un signalement automatique est envoye.
     */

    public void afficheCompetences()
    { 
	if (competences.size() == 0) System.out.println("La personne n'a actuellement aucun sport");
	else
	    {
		for (int i = 0; i< competences.size(); i++)
		    {
			System.out.print(" "+competences.get(i));
		    }
	    }
    }    
    
    /**
     *Methode add
     *Ajoute une competence (un sport) à la personne.
     *@param comp Competence a ajouter.
     */

    public void add(String comp)
    {
	competences.add(comp);
    }

    /**
     *Methode erase
     *Efface une competence donnee.
     *@param comp Competence a supprimer.
     */

    public void erase(String comp)
    {
	competences.remove(comp);
    }

	public ArrayList<String> getCompetences()
	{
		return competences;
	}

	public void setCompetences(ArrayList<String> competences)
	{
		this.competences = competences;
	}
}
