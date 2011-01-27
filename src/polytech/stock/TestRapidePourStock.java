package polytech.stock;

import java.util.List;

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

		Stock.enregistrerStock();
	}

}
