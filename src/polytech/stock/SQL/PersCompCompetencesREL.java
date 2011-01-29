package polytech.stock.SQL;

/**
 * @author Antoine Pultier
 * Relation entre une personne compétente et ses compétences.
 */
public class PersCompCompetencesREL extends GestionREL
{
	@Override
	protected String structureTable()
	{
		return "ID_PERSONNE, ID_COMPETENCE";
	}

	@Override
	protected String structureTableTypee()
	{
		return "ID_PERSONNE INTEGER, ID_COMPETENCE INTEGER";
	}

	@Override
	protected String nomTable()
	{
		return "perscomp_competences";
	}

	@Override
	protected int nbChamps()
	{
		return 2;
	}

	protected String clauseWhere()
	{
		return "WHERE ID_PERSONNE = ?";
	}
}
