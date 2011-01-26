package polytech.stock.SQL;

import polytech.stock.GestionnaireDeStock;

import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;

public abstract class GestionSQL implements GestionnaireDeStock
{

	protected static Connection connexion = null;
	protected Statement declaration = null;
	protected PreparedStatement declarationPreparee;

	protected static void seConnecterSiNecessaire()
	{
		
		// Chargement du driver JDBC bourrin (traduction de sqlite en java,
		// et bibliothèques natives pour les architectures répandues)
		try
		{
			// Si il y a déjà une connexion, inutile d'aller plus loin
			if (connexion != null && !connexion.isClosed())
			{
				return;
			}

			Class.forName("org.sqlite.JDBC");
		
			// Connexion
			connexion = DriverManager.getConnection("jdbc:sqlite:canard.db");

		} catch (Exception e)
		{
			System.out.println("Problème d'ouverture de la base de données : "+e.getMessage());
		}
	}

	protected static void seDeconnecter()
	{
		try{
			if (connexion != null && !connexion.isClosed())
			{
				connexion.close();
			}
		} catch (Exception e)
		{
			System.out.println("Problème de fermeture de la base de données : "+e.getMessage());
		}
	}

	// Informations sur la table
	protected abstract String structureTable();
	protected abstract String nomTable();
	protected abstract int nbInfosTable();

	protected <CLASS_TYPE> void sauvegarderDansBase(List<CLASS_TYPE> liste)
	{
		try{
			String nom = nomTable();
			String structure = structureTable();

			declaration = connexion.createStatement();
			declaration.executeUpdate("DROP TABLE IF EXISTS "+nom+";");
			declaration.executeUpdate("CREATE TABLE "+nom+" ("+structure+");");

			StringBuilder sb = new StringBuilder("INSERT INTO ");
			sb.append(nom);
			sb.append(" (");
			sb.append(structure);
			sb.append(") VALUES (");

			int nbInfos = nbInfosTable();
			for (int i = 0; i < nbInfos; ++i)
			{
				sb.append("?,");
			}

			sb.deleteCharAt(sb.length());

			sb.append(");");

			declarationPreparee = connexion.prepareStatement(sb.toString());

			for (CLASS_TYPE e : liste)
			{
				if (construirePourStock(e) != null)
				{
					declarationPreparee.executeUpdate();
				}
			}

		} catch (SQLException e)
		{
			System.out.println("Impossible de sauvegarder : "+e.getMessage());
		}
	}
}
