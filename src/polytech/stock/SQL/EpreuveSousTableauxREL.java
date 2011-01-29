package polytech.stock.SQL;

public class EpreuveSousTableauxREL extends GestionREL
{

	@Override
	protected String structureTable()
	{
		return "ID_TABLEAU, ID_SOUS_TABLEAU";
	}

	@Override
	protected String structureTableTypee()
	{
		return "ID_TABLEAU INTEGER, ID_SOUS_TABLEAU INTEGER";
	}

	@Override
	protected String nomTable()
	{
		return "epreuve_sous_tableaux";
	}

	@Override
	protected int nbInfosTable()
	{
		return 2;
	}

}
