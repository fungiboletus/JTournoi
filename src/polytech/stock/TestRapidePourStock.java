package polytech.stock;

import java.util.List;

import polytech.jtournoi.Equipe;
import polytech.personnes.Joueur;

public class TestRapidePourStock
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Stock.chargerStock(TypeChargement.XML);
		//Stock.chargerStock(TypeChargement.SQL);
		
		List<Joueur> joueurs = Stock.getJoueurs();

		//joueurs.add(new Joueur());

		if (joueurs != null)
		{
			for (Joueur j : joueurs)
			{
				System.out.println(j);
			}
		}
		
		System.out.println("===============================");
		
		List<Equipe> equipes = Stock.getEquipes();

		//joueurs.add(new Joueur());

		if (equipes != null)
		{
			for (Equipe e : equipes)
			{
				System.out.println(e);
			}
		}

		Stock.enregistrerStock();
	}

}
