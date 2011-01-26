package polytech.stock.SQL;

public abstract class PersonneSQL extends GestionSQL
{

	protected String structureTable()
	{
		return "NOM TEXT, PRENOM TEXT, MOTDEPASSE TEXT";
	}

	protected int nbInfosTable()
	{
		return 3;
	}

	@Override
	public void chargerStock()
	{
		// TODO Auto-generated method stub
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

	@Override
	public Object construireDepuisStock(Object element)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
