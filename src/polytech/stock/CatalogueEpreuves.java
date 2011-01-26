package polytech.stock;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import polytech.jtournoi.TypeEpreuve;

import au.com.bytecode.opencsv.CSVReader;

public class CatalogueEpreuves
{

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException
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
				TypeEpreuve te = new TypeEpreuve(
						Integer.parseInt(ligne[0]),
						ligne[1],
						ligne[2],
						Integer.parseInt(ligne[3]),
						Integer.parseInt(ligne[4])
					);
				
				System.out.println(te);
			}
	    }
	}

}
