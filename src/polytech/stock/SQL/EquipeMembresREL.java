package polytech.stock.SQL;

public class EquipeMembresREL extends GestionREL
{
	
	@Override
	protected String structureTable()
	{
		return "ID_EQUIPE, ID_JOUEUR";
	}

	@Override
	protected String structureTableTypee()
	{
		return "ID_EQUIPE INTEGER, ID_JOUEUR INTEGER";
	}

	@Override
	protected String nomTable()
	{
		return "equipe_membres";
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
