package polytech.stock.SQL;

import polytech.stock.GestionnaireDeStock;

import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

public abstract class GestionSQL implements GestionnaireDeStock
{
	@Override
	public <CLASS_TYPE> List<CLASS_TYPE> recupererStock()
	{
		GestionSQL.seConnecterSiNecessaire();
		return chargerDepuisBase();
	}

	@Override
	public <CLASS_TYPE> void enregistrerStock(List<CLASS_TYPE> liste)
	{
		GestionSQL.seConnecterSiNecessaire();
		sauvegarderDansBase(liste);
	}

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
		
			// Amélioration astronomique des performances
			Statement s = connexion.createStatement();
			s.execute("PRAGMA synchronous = OFF");

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
	protected abstract String structureTableTypee();
	protected abstract String nomTable();
	protected abstract int nbInfosTable();

	protected static List<String> semaphoresSuppressions = null;
	
	protected boolean suppressionAutomatique()
	{
		if (semaphoresSuppressions == null)
		{
			semaphoresSuppressions = new ArrayList<String>();
		}
		
		String nomClasse = getClass().getSimpleName();
		
		if (!semaphoresSuppressions.contains(nomClasse))
		{
			semaphoresSuppressions.add(nomClasse);
			return true;
		}
		return false;
	}

	protected String clauseWhere()
	{
		return null;
	}

	protected void gererClauseWhere(PreparedStatement p)
	{
	}

	@SuppressWarnings("unchecked")
	protected <CLASS_TYPE> List<CLASS_TYPE> chargerDepuisBase()
	{
		List<CLASS_TYPE> liste = new ArrayList<CLASS_TYPE>();

		String nom = nomTable();
		String structure = structureTable();

		try{
			String waire = clauseWhere();
			
			if (waire != null)
			{
				declarationPreparee = connexion.prepareStatement(" SELECT "+structure+" FROM "+nom+" "+waire+";");
				gererClauseWhere(declarationPreparee);	
			}
			else
			{
				declarationPreparee = connexion.prepareStatement(" SELECT "+structure+" FROM "+nom+";");
			}

			ResultSet rs = declarationPreparee.executeQuery();

			while (rs.next())
			{
				liste.add((CLASS_TYPE) construireDepuisStock(rs));
			}

			rs.close();

		} catch (SQLException e)
		{
			System.out.println("Impossible de récupérer les données : "+e.getMessage());
		}

		return liste;
	}

	protected void creerTable(String nom, String structureTypee)
	{
		try{
			declaration = connexion.createStatement();
			declaration.executeUpdate("CREATE TABLE IF NOT EXISTS "+nom+" ("+structureTypee+");");
		} catch (SQLException e)
		{
			System.out.println("Impossible de créer la table : "+e.getMessage());
		}
	}

	protected void supprimerTable(String nom)
	{
		try{
			declaration = connexion.createStatement();
			declaration.executeUpdate("DROP TABLE IF EXISTS "+nom+";");
		} catch (SQLException e)
		{
			System.out.println("Impossible de supprimer la table : "+e.getMessage());
		}
	}

	protected <CLASS_TYPE> void sauvegarderDansBase(List<CLASS_TYPE> liste)
	{
			String nom = nomTable();
			String structure = structureTable();
			String structureTypee = structureTableTypee();

			if (suppressionAutomatique())
			{
				supprimerTable(nom);
			}

			creerTable(nom, structureTypee);

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

			sb.deleteCharAt(sb.length()-1);

			sb.append(");");

		try{
			declarationPreparee = connexion.prepareStatement(sb.toString());

			for (CLASS_TYPE e : liste)
			{
				if (construirePourStock(e) != null)
				{
					declarationPreparee.addBatch();
				}
			}
		
			connexion.setAutoCommit(false);
			declarationPreparee.executeBatch();
			connexion.commit();

		} catch (SQLException e)
		{
			System.out.println("Impossible de sauvegarder : "+e.getMessage());
		}
	}
}
