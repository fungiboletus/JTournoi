package polytech.tools;

import java.util.ArrayList;

import polytech.jtournoi.Equipe;
import polytech.jtournoi.TypeEpreuve;

public abstract class Tools
{

	public static int puissance(int n)
	{
		int i = 1;
		while (i < n)
		{
			i = i * 2;
		}
		return i / 2;
	}

	public static ArrayList<Equipe> melangerEquipe(ArrayList<Equipe> list)
	{
		Equipe tmp;
		ArrayList<Equipe> liste = new ArrayList<Equipe>();
		liste.addAll(list);
		int max = liste.size() - 1;
		int i = 0;
		int random;
		while (max > 0)
		{
			random = (int) (Math.random() * max);
			tmp = liste.get(random);
			liste.remove(random);
			liste.add(random, liste.get(max - 1));
			liste.remove(max);
			liste.add(max - 1, tmp);
			max--;
			i++;
		}
		return liste;
	}

	public static ArrayList<TypeEpreuve> getTypeEpreuve(
			ArrayList<Equipe> equipes)
	{
		ArrayList<TypeEpreuve> epreuve = new ArrayList<TypeEpreuve>();
		for (Equipe e : equipes)
		{
			for (TypeEpreuve te : e.getEpreuves())
			{
				if (!containsTypeEpreuve(epreuve, te))
				{
					epreuve.add(te);
				}
			}
		}
		return epreuve;
	}

	public static boolean containsTypeEpreuve(ArrayList<TypeEpreuve> epreuves,
			TypeEpreuve te)
	{
		for (TypeEpreuve e : epreuves)
		{
			if (e.getId() == te.getId())
			{
				return true;
			}
		}
		return false;
	}


	public static boolean epreuveSansEquipes(TypeEpreuve te, ArrayList<Equipe> equipes){
		int inc =0;
		for(Equipe e : equipes){
			if(e.getEpreuves().contains(te)){
				inc++;
			}
		}
		return inc < 2;
	}

	public static boolean epreuvesSansEquipes(ArrayList<TypeEpreuve> epreuves,
			ArrayList<Equipe> equipes)
	{
		for (TypeEpreuve te : epreuves)
		{
			if (epreuveSansEquipes(te, equipes))
			{
				return true;
			}
		}
		return false;
	}

	public static ArrayList<Equipe> equipeParEpreuve(TypeEpreuve te,
			ArrayList<Equipe> equipes)
	{
		ArrayList<Equipe> list = new ArrayList<Equipe>();
		for (Equipe e : equipes)
		{
			if (containsTypeEpreuve(e.getEpreuves(), te))
			{
				list.add(e);
			}
		}
		return list;
	}

	public static String bytesToHex(byte[] b)
	{
		char hexDigit[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		StringBuffer buf = new StringBuffer();
		for (int j = 0; j < b.length; j++)
		{
			buf.append(hexDigit[(b[j] >> 4) & 0x0f]);
			buf.append(hexDigit[b[j] & 0x0f]);
		}
		return buf.toString();
	}

}
