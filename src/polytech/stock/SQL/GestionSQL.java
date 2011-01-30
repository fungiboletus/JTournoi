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

/**
 * @author Antoine Pultier
 * Gestion commune à toutes les classes de gestion du SQL.
 */
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

	/**
	 * Référence de la connexion à la base de données.
	 */
	protected static Connection connexion = null;
	
	/**
	 * Déclaration d'une requête simple.
	 */
	protected Statement declaration = null;
	
	/**
	 * Déclaration d'une requête préparée.
	 */
	protected PreparedStatement declarationPreparee;

	/**
	 *  Si la connexion à la base de données n'est pas encore réalisée, 
	 *  connexion à la base de données.
	 */
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

			// Chargement de la classe sqlite.
			Class.forName("org.sqlite.JDBC");
		
			// Connexion
			connexion = DriverManager.getConnection("jdbc:sqlite:canard.db");
		
			// Amélioration astronomique des performances
			Statement s = connexion.createStatement();
			s.execute("PRAGMA synchronous = OFF");
			
			
			// Des personnes dignes de confiances m'on dit que les autocommit, c'était mauvais pour les performances
			connexion.setAutoCommit(false);

		} catch (Exception e)
		{
			System.out.println("Problème d'ouverture de la base de données : "+e.getMessage());
		}
	}

	/**
	 * Déconnexion de la base de données.
	 */
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

	/**
	 * Renvoit la structure de la table, sans les types.
	 * C'est utile pour les requêtes d'insertion, et de sélections.
	 * @return Structure
	 */
	protected abstract String structureTable();
	
	/**
	 * Renvoit la structure typée de la table.
	 * C'est utile pour la création de la table.
	 * Sqlite ne prête pas beaucoup d'importance aux types, dans le sens
	 * où l'on peut insérer n'importe quel type dans un champ d'un type donné.
	 * Mais une bonne pratique est quand même de spécifier les bons types.
	 * @return Structure typée
	 */
	protected abstract String structureTableTypee();
	
	/**
	 * Renvoit le nom de la table.
	 * @return Nom de la table.
	 */
	protected abstract String nomTable();
	
	/**
	 * Renvoit le nombre de champs de la table.
	 * Cette fonction au profil simple permet de simplifier les classes résultantes, en 
	 * générant automatiquement certaines requêtes.
	 * @return nombre de champs.
	 */
	protected abstract int nbChamps();

	/**
	 * Liste de sémaphores servant aux suppressions automatiques.
	 * Pour plus d'informations, référez vous à la documentation de la fonction
	 * suppressionAutomatique.
	 */
	protected static List<String> semaphoresSuppressions = null;
	
	/**
	 * Lorsque l'on enregistre les données du programme,
	 * la table doit préalablement être vidée, ou supprimmée.
	 * 
	 * Sinon les informations déjà présentes lors d'une ancienne exécution du programme sont en double.
	 * Pour plus de simplicité entre le XML et le SQL, il a été décidé de ne pas gérer les mises à jour (UPDATE).
	 * Uniquement des insertions et des suppressions sont réalisées.
	 * 
	 * Cependant, la table doit être vidée une seule fois par sauvegarde. C'est nottamment visible pour les relations,
	 * car à chaque sauvegarde d'une relation, la table ne doit pas être vidée.
	 * 
	 * La solution technique retenue est de sauvegarder dans un tableau les noms des tables qui ont déjà étés vidées
	 * lors de la sauvegarde.
	 * 
	 * @return Vrai la table doit être supprimée.
	 */
	protected boolean suppressionAutomatique()
	{
		// Si le tableau n'existe pas encore, création du tableau
		if (semaphoresSuppressions == null)
		{
			semaphoresSuppressions = new ArrayList<String>();
		}
		
		String nomClasse = nomTable();
		
		// Si la tableau n'est pas dans la liste
		if (!semaphoresSuppressions.contains(nomClasse))
		{
			// Ajout du tableau dans la liste
			semaphoresSuppressions.add(nomClasse);
			// Il faut vider la table
			return true;
		}
		return false;
	}
	
	/**
	 * Certaines personnes sauvegardent plusieurs fois par exécution, il faut permettre
	 * cette possibilité.
	 */
	public static void resetSuppressionAutomatique()
	{
		semaphoresSuppressions = null;
	}
	
	/**
	 * Renvoit la clause where de la condition si il y en a une.
	 * @return clause where en String, ou null si il n'y en a par
	 */
	protected String clauseWhere()
	{
		// De base, il n'y a pas de clause where (d'où le null)
		return null;
	}

	/**
	 * Si il y a une clause where, cette fonction est appelée pour remplir
	 * la requête préparée.
	 * @param p Requête préparée à remplir
	 */
	protected void gererClauseWhere(PreparedStatement p)
	{
		// De base, il n'y a rien à faire puisque qu'il n'y a pas de clause where
	}

	/**
	 * Charge dans une liste les objets stockés dans la base
	 * @param <CLASS_TYPE>
	 * @return Liste des objets récupérés depuis la base
	 */
	@SuppressWarnings("unchecked")
	protected <CLASS_TYPE> List<CLASS_TYPE> chargerDepuisBase()
	{
		List<CLASS_TYPE> liste = new ArrayList<CLASS_TYPE>();

		String nom = nomTable();
		String structure = structureTable();

		try{
			String waire = clauseWhere();
			
			// Si il y a une clause where,
			if (waire != null)
			{
				// Création de la requête avec le where
				declarationPreparee = connexion.prepareStatement(" SELECT "+structure+" FROM "+nom+" "+waire+";");
				gererClauseWhere(declarationPreparee);	
			}
			else
			{
				// Sinon, création d'une requête simple
				declarationPreparee = connexion.prepareStatement(" SELECT "+structure+" FROM "+nom+";");
			}

			// Exécution de la requête
			ResultSet rs = declarationPreparee.executeQuery();

			while (rs.next())
			{
				// Pour chaque résultat, appel de la méthode permettant de construire l'objet à partir du stock
				liste.add((CLASS_TYPE) construireDepuisStock(rs));
			}

			rs.close();

		} catch (SQLException e)
		{
			System.out.println("Impossible de récupérer les données de "+nom+": "+e.getMessage());
		}

		return liste;
	}

	/**
	 * Création d'une table à partir de sa structure, et de son nom
	 * @param nom Nom de la table à créer
	 * @param structureTypee Structure de la table à créer
	 */
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

	/**
	 * Supprime une table à partir de son nom
	 * @param nom Nom de la table à supprimer
	 */
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

	/**
	 * Sauvegarde dans la table les objets de la liste.
	 * @param <CLASS_TYPE>
	 * @param liste Liste à sauvegarder dans la base
	 */
	protected <CLASS_TYPE> void sauvegarderDansBase(List<CLASS_TYPE> liste)
	{
			String nom = nomTable();
			String structure = structureTable();
			String structureTypee = structureTableTypee();

			// Si la table doit être supprimée, il faut la supprimer…
			if (suppressionAutomatique())
			{
				supprimerTable(nom);
			}

			creerTable(nom, structureTypee);
			
			// Création de la requête d'insertion des valeurs
			StringBuilder sb = new StringBuilder("INSERT INTO ");
			sb.append(nom);
			sb.append(" (");
			sb.append(structure);
			sb.append(") VALUES (");

			// Création des champs paramétrables
			int nbInfos = nbChamps();
			for (int i = 0; i < nbInfos; ++i)
			{
				sb.append("?,");
			}

			sb.deleteCharAt(sb.length()-1);

			sb.append(");");

		try{
			// Commit de la suppression et la création de la table
			connexion.commit();
			
			// Excecution de la requête préparée
			declarationPreparee = connexion.prepareStatement(sb.toString());

			// Pour chaque objet à sauvegarder
			for (CLASS_TYPE e : liste)
			{
				// Remplissage de la requête préparée à partir de l'objet à sauvegarder 
				if (construirePourStock(e) != null)
				{
					// Enregistrement de la requête à exécuter
					declarationPreparee.addBatch();
				}
			}

			// Exécution de toutes les insertions d'un coup
			declarationPreparee.executeBatch();
			
			// Puisque l'on a plus l'autocommit, il faut bien faire un commit…
			connexion.commit();

		} catch (SQLException e)
		{
			System.out.println("Impossible de sauvegarder "+nom+": "+e.getMessage());
		}
	}
}
