package polytech.stock;

import java.io.FileReader;

import java.util.ArrayList;
import java.util.List;

import polytech.jtournoi.TypeEpreuve;

import au.com.bytecode.opencsv.CSVReader;

/**
 * @author Antoine Pultier
 * Le catalogue d'épreuves est un fichier texte au format CSV.
 * 
 * Il est de la forme ID, Nom, Categorie, Durée, Points.
 * 
 * Cette classe utilise la librairie OpenCSV pour fonctionner.
 */
public abstract class CatalogueEpreuves
{
	/**
	 * Charge le fichier Catalogue.csv dans une liste de TypeEpreuve
	 * @return Une liste de TypeEpreuve
	 */
	public static List<TypeEpreuve> recupererTypesEpreuves()
	{
		List<TypeEpreuve> epreuves = new ArrayList<TypeEpreuve>();
	
		try
		{
			// Chargement du fichier
			CSVReader reader = new CSVReader(new FileReader("Catalogue.csv"));
			
			String [] ligne;
			
			// Lecture du fichier
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
			System.out.println("Erreur de chargement du catalogue d'épreuves : "+e.getMessage());
		}

		return epreuves;
	}

}
