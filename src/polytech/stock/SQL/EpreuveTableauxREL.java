package polytech.stock.SQL;

public class EpreuveTableauxREL extends GestionREL
{

	@Override
	protected String structureTable()
	{
		return "ID_EPREUVE, ID_TABLEAU";
	}

	@Override
	protected String structureTableTypee()
	{
		return "ID_EPREUVE INTEGER, ID_TABLEAU INTEGER";
	}

	@Override
	protected String nomTable()
	{
		return "epreuve_tableaux";
	}

	@Override
	protected int nbInfosTable()
	{
		return 2;
	}

}
