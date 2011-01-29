package polytech.personnes;

/**
 * Gestion d'un joueur dans le cadre d'un tournoi sportif.
 * 
 * @author Sylvestre Genevier
 * @version 1.0
 */
public class Joueur extends PersonneCompetente

{
	/**
	 * Constructeur d'un Joueur.
	 * 
	 * @param nom
	 *            Le nom du joueur.
	 * @param prenom
	 *            Le prenom du joueur.
	 * @param password
	 *            Le mot de passe du joueur. De plus, sa liste de compétences
	 *            est alors vide.
	 */
	public Joueur(String nom, String prenom, String password)
	{
		super(nom, prenom, password);
	}

	int score;
	
	/**
	 * @param score the score to add
	 */
	public void incScore(int inc) {
		score+=inc;
	}

	public int getScore(){
		return score;
	}
	
	/**
	 * Constructeur par defaut. Le nom du joueur est "nom inconnu", son prenom
	 * "prenom inconnu" et son mot de passe "mot de passe inconnu". De plus, sa
	 * liste de compétence est aussi vide.
	 */
	public Joueur()
	{
		super();
	}
}