package polytech.jtournoi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestSqlite
{

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
		// Chargement du driver JDBC bourrin (traduction de sqlite en java,
		// et bibliothèques natives pour les architectures répandues)
		Class.forName("org.sqlite.JDBC");
		
		// Connexion
		Connection connexion = DriverManager.getConnection("jdbc:sqlite:canard.db");
		Statement declaration = connexion.createStatement();
		
		// Truc super utile
		System.out.println(declaration.executeQuery("select 42;").getInt(1));
		
		// De la politesse !
		connexion.close();
	}

}
