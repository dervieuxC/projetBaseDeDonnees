package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Création d'un singleton
 * @autor Thibaut MASSELIN
 */

public class ConnexionBdd {
	private static Connection connection = null;
	private final static String URL = "jdbc:oracle:thin:@im2ag-oracle.e.ujf-grenoble.fr:1521:im2ag";
	private final static String USER = "dervieco";
	private final static String PASSWRD = "newpasswd42";
	
	/**
	 *  Constructor
	 */
	private ConnexionBdd(){
		try {
			
			Class.forName("oracle.jdbc.OracleDriver");
			connection = (Connection) DriverManager.getConnection(URL, USER, PASSWRD);
			
		} catch (ClassNotFoundException |SQLException e) {
			throw new IllegalStateException("Imposible de se connecter à la BDD!");
		}
	}

	/**
	 *  Retourne la connection
	 * @return Connection
	 */
	public static Connection getConnexion(){
		// si la connexion est null connexion par défaut
		if (connection == null) {
			ConnexionBdd.getInstance();
		}		
		return connection;
	}

	/**
	 *  Instance de connexion
	 */
	private static void getInstance(){
		if (connection == null) {
			new ConnexionBdd();
		}
	}
	
	/**
	 *  Ferme la connection
	 */
	
	public static void closeConnexion() {
		try {
			connection.close();
			connection = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}