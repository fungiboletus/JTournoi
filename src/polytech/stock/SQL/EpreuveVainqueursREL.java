package polytech.stock.SQL;

public class EpreuveVainqueursREL extends GestionREL
{

	@Override
	protected String structureTable()
	{
		return "ID_EPREUVE, ID_EQUIPE";
	}

	@Override
	protected String structureTableTypee()
	{
		return "ID_EPREUVE INTEGER, ID_EQUIPE INTEGER";
	}

	@Override
	protected String nomTable()
	{
		return "epreuve_vainqueurs";
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
