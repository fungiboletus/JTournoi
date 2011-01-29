package polytech.stock.SQL;

import polytech.jtournoi.TypeEpreuve;
import polytech.personnes.PersonneCompetente;
import polytech.stock.Stock;

import java.util.List;
import java.util.ArrayList;

/**
 * @author Antoine Pultier
 * Gestion commune de toutes les personnes compétentes.
 */
public abstract class PersonneCompetenteSQL extends PersonneSQL
{
	/**
	 * Enregistre les compétences d'une personne compétente.
	 * @param p Personne compétente
	 */
	protected void genererCompetences(PersonneCompetente p)
	{
		List<Integer[]> competences = new ArrayList<Integer[]>();
		
		for (TypeEpreuve competence : p.getCompetences())
		{
			Integer[] couple = new Integer[2];

			couple[0] = p.getId();
			couple[1] = competence.getId();

			competences.add(couple);
		}

		new PersCompCompetencesREL().enregistrerStock(competences);
	}
	
	/**
	 * Récupére les compétences d'une personne compétente.
	 * @param p Personne compétente.
	 */
	protected void recupererCompetences(PersonneCompetente p)
	{
		PersCompCompetencesREL pcc = new PersCompCompetencesREL();
		pcc.setIdRelation(p.getId());
		List<Integer[]> competences =  pcc.recupererStock();
		
		if (competences != null)
		{
			for (Integer[] e : competences)
			{
				TypeEpreuve t = Stock.getTypeEpreuveParId(e[1]);

				if (t != null)
				{
					p.addCompetence(t);
				}
			}
		}
	}
}
