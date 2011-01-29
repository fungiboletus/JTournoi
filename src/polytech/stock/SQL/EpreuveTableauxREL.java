package polytech.stock.SQL;

/**
 * @author Antoine Pultier
 * Relation entre la colonne et la ligne de la matrice des matchs d'une épreuve.
 */
public class EpreuveTableauxREL extends GestionREL
{

	@Override
	protected String structureTable()
	{
		return "ID_EPREUVE, ID_SOUS_TABLEAU";
	}

	@Override
	protected String structureTableTypee()
	{
		return "ID_EPREUVE INTEGER, ID_SOUS_TABLEAU INTEGER";
	}

	@Override
	protected String nomTable()
	{
		return "epreuve_tableaux";
	}

	@Override
	protected int nbChamps()
	{
		return 2;
	}

}
