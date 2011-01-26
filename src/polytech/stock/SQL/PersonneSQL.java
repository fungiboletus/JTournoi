package polytech.stock.SQL;

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

	protected int nbInfosTable()
	{
		return 4;
	}


	@Override
	public void ajouterReference(Object reference)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void supprimerReference(Object reference)
	{
		// TODO Auto-generated method stub

	}


}
