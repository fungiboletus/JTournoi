package polytech.stock.SQL;

/**
 * @author Antoine Pultier
 * Relation entre la ligne de la matrices des matchs d'une épreuve, et ses matchs.
 */
public class EpreuveSousTableauxREL extends GestionREL
{

	@Override
	protected String structureTable()
	{
		return "ID_SOUS_TABLEAU, ID_MATCH";
	}

	@Override
	protected String structureTableTypee()
	{
		return "ID_SOUS_TABLEAU INTEGER, ID_MATCH INTEGER";
	}

	@Override
	protected String nomTable()
	{
		return "epreuve_sous_tableaux";
	}

	@Override
	protected int nbChamps()
	{
		return 2;
	}

}
