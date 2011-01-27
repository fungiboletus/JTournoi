package polytech.stock.SQL;

import polytech.jtournoi.TypeEpreuve;
import polytech.personnes.PersonneCompetente;
import polytech.stock.Stock;

import java.util.List;
import java.util.ArrayList;

public abstract class PersonneCompetenteSQL extends PersonneSQL
{
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
