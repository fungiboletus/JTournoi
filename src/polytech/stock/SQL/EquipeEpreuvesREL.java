package polytech.stock.SQL;

public class EquipeEpreuvesREL extends GestionREL
{

	@Override
	protected String structureTable()
	{
		return "ID_EQUIPE, ID_EPREUVE";
	}

	@Override
	protected String structureTableTypee()
	{
		return "ID_EQUIPE INTEGER, ID_EPREUVE INTEGER";
	}

	@Override
	protected String nomTable()
	{
		return "equipe_epreuves";
	}

	@Override
	protected int nbInfosTable()
	{
		return 2;
	}

	protected String clauseWhere()
	{
		return "WHERE ID_EQUIPE = ?";
	}

}
