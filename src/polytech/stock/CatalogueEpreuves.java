package polytech.stock;

import java.io.FileReader;

import java.util.ArrayList;
import java.util.List;

import polytech.jtournoi.TypeEpreuve;

import au.com.bytecode.opencsv.CSVReader;

public abstract class CatalogueEpreuves
{
	public static List<TypeEpreuve> recupererTypesEpreuves()
	{
		List<TypeEpreuve> epreuves = new ArrayList<TypeEpreuve>();
	
		try
		{
			CSVReader reader = new CSVReader(new FileReader("Catalogue.csv"));
			
			String [] ligne;
			
			while ((ligne = reader.readNext()) != null) {
				if (ligne.length < 5)
				{
					System.out.println("Une ligne a été ignorée par manque d'informations");
				}
				else
				{
					epreuves.add(new TypeEpreuve(
							Integer.parseInt(ligne[0]),
							ligne[1],
							ligne[2],
							Integer.parseInt(ligne[3]),
							Integer.parseInt(ligne[4])
						));
				}
			}
		} catch (Exception e)
		{
			System.out.println("Erreur de chargement du catalogue d'épreves : "+e.getMessage());
		}

		return epreuves;
	}

}
