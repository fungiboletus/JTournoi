package polytech.stock.SQL;

public class EpreuveMatchsREL extends GestionREL
{

	@Override
	protected String structureTable()
	{
		return "ID_EPREUVE, ID_MATCH";
	}

	@Override
	protected String structureTableTypee()
	{
		return "ID_EPREUVE INTEGER, ID_MATCH INTEGER";
	}

	@Override
	protected String nomTable()
	{
		return "epreuve_matchs";
	}

	@Override
	protected int nbInfosTable()
	{
		return 2;
	}
	
	protected String clauseWhere()
	{
		return "WHERE ID_EPREUVE = ?";
	}

}
