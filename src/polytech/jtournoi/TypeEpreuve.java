package polytech.jtournoi;

/**
 *Gestion du type d'épreuve dans le cadre d'un tournoi sportif.
 *@author Sylvestre Genevier
 *@version 1.0
 */

public class TypeEpreuve 
{
	private int id;
	private String nom;
	private String type;
	private int duree;
	private int points;
	
	/**
    *Constructeur d'un type d'epreuve.
    *@param nom Le nom de l'epreuve.
    *@param type Le type de l'epreuve.
    *@param duree La duree de l'epreuve.
    *@param points Le nombre de points de l'epreuve.
    */
	
	public TypeEpreuve(int id, String nom, String type, int duree, int points) {
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
		this(0, "Pas de nom","Pas de type",0,0);
	}

	public String getNom() {
		return nom;
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

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("TypeEpreuve [id=");
		builder.append(id);
		builder.append(", ");
		if (nom != null)
		{
			builder.append("nom=");
			builder.append(nom);
			builder.append(", ");
		}
		if (type != null)
		{
			builder.append("type=");
			builder.append(type);
			builder.append(", ");
		}
		builder.append("duree=");
		builder.append(duree);
		builder.append(", points=");
		builder.append(points);
		builder.append("]");
		return builder.toString();
	}

	
	
}
