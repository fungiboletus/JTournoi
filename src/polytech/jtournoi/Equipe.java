package polytech.jtournoi;

import java.util.ArrayList;
import polytech.personnes.*;
import polytech.stock.TupleAvecID;

/**
 *Gestion d'une equipe dans un tournoi sportif.
 * 
 * @author Sylvestre Genevier
 *@version 1.0
 */

public class Equipe extends TupleAvecID
{
    /**
     * Le nom de l'equipe.
     */

    private String nom;

    /**
     * La liste des participants dans l'equipe.
     */

    private ArrayList<Joueur> membres;
    
    /**
     * La liste des types d'epreuve de l'equipe.
     */

    private ArrayList<TypeEpreuve> epreuves;
    
    /**
     * Le nombre de joueur maximum d'une équipe.
     */
    
    private static final int MAX = 5;
     
    /**
     * Le nombre nécessaire de compétence
     */
    
    private static final int NBCOMP = 5;

     /**
     *Constructeur d'une equipe. 
     *@param nom Le nom de l'equipe.
     *@param membres La liste de membres de l'equipe.
     *@param epreuves La liste des cinq epreuves de l'équipe.
     */

    public Equipe(String nom, ArrayList<Joueur> membres, ArrayList<TypeEpreuve> epreuves)
    {
		super();
    	this.nom = nom;
    	if (membres.size() <= MAX)
    	{   		
    		this.membres = membres;
    	}
    	else
    	{
    		this.membres = new ArrayList<Joueur>();
    		System.out.println("La liste de joueur est trop grande, l'équipe a été créée sans joueur.");
    	}
    	this.setEpreuves(epreuves);
    }

    /**Constructeur d'une equipe. 
    *@param nom Le nom de l'equipe.
    */

   public Equipe(String nom)
   {
	    super();
	    this.nom = nom;
	    this.membres = new ArrayList<Joueur>();
	    this.epreuves = new ArrayList<TypeEpreuve>();
   }
    
    /**
     *Constructeur par défaut.
     */

    public Equipe()
    {
    	this("pas de nom");
    }

     /**
     *Accesseur en lecture.
     *@return Le nom de l'equipe.
     */

    public String getNom()
    {
    	return nom;
    }
    
    /**
     *Mutateur.
     *Change le nom de l'equipe.
     *@param nouveauNom Le nouveau nom a donner a l'equipe.
     */

    public void setNom(String nouveauNom)
    {
    	nom = nouveauNom;
    }

    /**
     *Accesseur en lecture.
     *@return La liste d'epreuves de l'equipe.
     */

    public ArrayList<TypeEpreuve> getEpreuves()
    {
    	return epreuves;
    }
    
    /**
     * Méthode qui vérifie si la liste d'épreuve comporte des doublons.
     * @return true si la liste est valide, c'est-à-dire ne comporte pas de doublons.
     */
    
    public boolean checkDoublons(ArrayList<TypeEpreuve> epreuves)
    {
    	System.out.println("check doublons rofl"+epreuves.size());
    	if(epreuves.isEmpty()) 
    	{
    		return true;
    	}
    	else
    	{
    		TypeEpreuve first = epreuves.get(0);
    		ArrayList<TypeEpreuve> reste = epreuves;
    		reste.remove(0);
    		if (epreuves.contains(first))
    		{
    			System.out.println("La liste d'épreuve est invalide car elle contient au moins un doublon.");
    			System.out.println("En effet, l'épreuve "+first.getNom()+" a au moins deux occurences.");
    			System.out.println("La liste d'épreuve est par conséquent vide.");
    			return false;
    		}
    		else
    		{
    			return checkDoublons(reste);
    		}
    	}
    }
    
    /**
     *Mutateur.
     *Change la liste d'epreuve de l'equipe.
     *@param epreuves La nouvelle liste d'epreuve de l'equipe.
     */

    public void setEpreuves2(ArrayList<TypeEpreuve> epreuves)
    {
    	if (epreuves.size() == NBCOMP || epreuves.isEmpty())
    	{
    		int needFive = 0;
    		// On donne la liste d'épreuve donnée à l'équipe et on vérifie si elle est valide. Si elle ne l'est pas, on la remplace par une liste vide.
    		this.epreuves = epreuves;
    		if (membres.isEmpty()){
    			System.out.println("L'équipe doit comporter au moins un joueur pour ajouter des épreuves");
    			this.epreuves = new ArrayList<TypeEpreuve>();
    		}
    		else{
    			for (int i = 0 ; i < NBCOMP ; i++)
        		{
    				for (int j = 0 ; j<membres.size() ; j++)
    				{
    					if (membres.get(j).getCompetences().contains(epreuves.get(i)))
    					{
    						needFive++;
    						break;
    					}
    				}
    				if (needFive == i)
    				{
    					System.out.println("Au moins un joueur de l'équipe "+nom+" doit pratiquer l'épreuve suivante pour que cette liste d'épreuve soit valide :");
    					System.out.println(epreuves.get(i).getNom());
    					System.out.println("L'équipe a maintenant une liste de compétences vide.");
    					this.epreuves = new ArrayList<TypeEpreuve>();
    					break;
    				}
        		}
    			
    		}
    	}
    	else {
    		this.epreuves = new ArrayList<TypeEpreuve>();
    		System.out.println("Veuillez donner une liste de "+NBCOMP+" epreuves uniques exactement.");
    		System.out.println("L'équipe a maintenant une liste de compétences vide.");
    	}
    }
    
    public boolean setEpreuves(ArrayList<TypeEpreuve> epreuves){
    	//System.out.println("ok les gens on setEpreuve");
    	//System.out.println(epreuves.size());
    	if(epreuves.size()!=5){
    		return false;
    	}
    	for(TypeEpreuve te : epreuves){
    		if(!haveCompetence(te)){
    			return false;
    		}
    	}
    	this.epreuves=epreuves;
    	return true;
    }
    
    private boolean haveCompetence(TypeEpreuve te){
    	int nbrRequis;
    	if(te.isIndivideul()){
    		nbrRequis =1;
    	}
    	else{
    		nbrRequis =2;
    	}
    	int joueur =0;
    	for(Joueur j : membres){
    		if(j.getCompetences().contains(te)){
    			joueur++;
    		}
    	}
    	if(joueur>=nbrRequis){
    		return true;
    	}
    	return false;
    }
    
    public ArrayList<Joueur> getMembres()
	{
		return membres;
	}

	public void setMembres(ArrayList<Joueur> membres)
	{
		this.membres = membres;
	}

	/**
     *Accesseur en lecture.
     *@return Le nombre de participants dans l'equipe.
     */

    public int getNombreDeJoueurs()
    {
	return membres.size();
    }

    /**
     *Accesseur en lecture.
     *@param i Le numero correspondant a la position du participant demande.
     *@return Le ieme participant dans l'equipe, s'il existe. Renvoie un participant anonyme dans tous les autres cas.
     */

    public Joueur getJoueur(int i)
    {
	if((i<0)||(i>=membres.size())) return new Joueur();
	else return membres.get(i);
    }

    /**
     * Methode getNumeroEquipe.
     *Complexite lineaire en fonction du nombre d'equipes.
     *@param nom Nom du joueur dont on veut le numero.
     *@param prenom Prenom du joueur dont on veut le numero.
     *@return Le numero d'un joueur dans la liste de l'équipe. Si le joueur n'existe pas, la methode renvoie -1.
     */

    public int getNumeroJoueur(String nom, String prenom)
    {
	for (int i = 0 ; i< membres.size() ; i++)
	    {
		if ((membres.get(i).getNom().equals(nom)) && (membres.get(i).getPrenom().equals(prenom))) return i;
	    }
	return -1;
    }

    /**
     *Ajout d'un joueur a l'equipe..
     *Complexite lineaire par rapport au nombre de joueur.
     *@param nouveauJoueur Le nouveau joueur de l'equipe.
     */

    public void ajouterParticipant(Joueur nouveauMembre)
    {
    	if(membres.size() > MAX)
    	{
    		System.out.println("L'équipe est déjà complète");
		}
		else if (membres.contains(nouveauMembre))
		{
			System.out.println("Ce joueur avait deja ete rentre :"+nouveauMembre.getId());
		}
		else
		{
    		membres.add(nouveauMembre);
		}
    }

    /**
     *Suppression d'un joueur.
     *@param joueurSupprime Le participant a retirer.
     */

    public void supprimerJoueur(Joueur joueurSupprime)
    {
	membres.remove(joueurSupprime);
    }

    /**
     *Suppression d'un joueur d'apres son nom et son prenom.
     *@param nom Le nom du joueur a retirer.
     *@param prenom Le prenom du joueur a retirer.
     */

    public void supprimerJoueur(String nom, String prenom)
    {
	this.supprimerJoueur(getJoueur(getNumeroJoueur(nom, prenom)));
    }


    /**
     *Listing des joueurs de l'equipe.
     */

    public void afficherJoueurs()
    {
	for(Joueur joueur : membres)
	    {
		System.out.println("\n"+joueur);
	    }
    }

    /**
     *Methode toString.
     *@return Le nom de l'equipe, et le nombre de participants qui la compose.
     */

    public String toString()
    {
	return "["+id+"] " +nom+ " : " +membres.size()+" participants.";
    }
}
