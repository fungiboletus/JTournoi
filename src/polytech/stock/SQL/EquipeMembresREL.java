package polytech.stock.SQL;

/**
 * @author Antoine Pultier
 * Relation entre une équipe et ses membres.
 */
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
	protected int nbChamps()
	{
		return 2;
	}

	protected String clauseWhere()
	{
		return "WHERE ID_EQUIPE = ?";
	}

}
