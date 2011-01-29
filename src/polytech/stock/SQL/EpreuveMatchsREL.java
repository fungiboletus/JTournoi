package polytech.stock.SQL;

/**
 * @author Antoine Pultier
 * Relation entre une épreuve et ces matchs.
 */
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
	protected int nbChamps()
	{
		return 2;
	}
	
	protected String clauseWhere()
	{
		return "WHERE ID_EPREUVE = ?";
	}

}
