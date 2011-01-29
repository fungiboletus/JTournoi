package polytech.jtournoi;

import polytech.stock.TupleAvecID;

/**
 *Gestion du type d'épreuve dans le cadre d'un tournoi sportif.
 *@author Sylvestre Genevier
 *@version 1.0
 */

public class TypeEpreuve extends TupleAvecID
{
	/**
	 * Nom de l'épreuve.
	 */
	private String nom;
	
	/**
	 * Type de l'épreuve. 
	 */
	private String type;
	
	/**
	 * Durée de l'épreuve.
	 */
	private int duree;
	
	/**
	 * Nombre de points gagnés par le vainqueur de l'épreuve.
	 */
	private int points;
	
	/**
	 * Est-ce que l'épreuve est individuelle ?
	 */
	private boolean individuel;
	
	/**
    *Constructeur d'un type d'epreuve.
    *@param nom Le nom de l'epreuve.
    *@param type Le type de l'epreuve.
    *@param duree La duree de l'epreuve.
    *@param points Le nombre de points de l'epreuve.
    */
	
	public TypeEpreuve(int id, String nom, String type, int duree, int points, boolean indi) {
		individuel = indi;
		this.id = id;
		this.nom = nom;
		this.type = type;
		this.duree = duree;
		this.points = points;
	}
	
	/**
     *Constructeur par defaut.
     */
	
	public TypeEpreuve() {
		this(0, "Pas de nom","Pas de type",0,0,false);
	}

	public String getNom() {
		return nom;
	}

	public boolean isIndivideul(){
		return individuel;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("["+id+"]");
		builder.append(" ");
		if (nom != null)
		{
			builder.append(nom+", ");
		}
		if (type != null)
		{
			builder.append("type : "+type+", ");
		}
		builder.append(duree+" min, ");
		builder.append(points+" points");
		return builder.toString();
	}

	
	
}
