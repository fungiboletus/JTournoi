package polytech.stock.SQL;

/**
 * @author Antoine Pultier
 * Gestion SQL de la classe abstraite Personne.
 * 
 * La structure de la table est identique pour toutes les tables qui en héritent.
 */
public abstract class PersonneSQL extends GestionSQL
{
	protected String structureTable()
	{
		return "ID, NOM, PRENOM, MOTDEPASSE";
	}
	
	protected String structureTableTypee()
	{
		return "ID INTEGER PRIMARY KEY, NOM TEXT, PRENOM TEXT, MOTDEPASSE TEXT";
	}

	protected int nbChamps()
	{
		return 4;
	}
}
