package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import exception.SQLWarningsExceptions;

/**
 * Creation d'un singleton pour la connexion
 * @autor Thibaut MASSELIN
 */

public class ConnexionBdd {
	/**
	 *  Attribut de classe
	 */
	private static final String configurationFile = "BD.properties.txt";
	private static Connection connection = null;	
	private static String  JDBC_DRIVER, DB_URL, USER, PASSWRD;

	/**
	 *  Constructor private
	 */
	private ConnexionBdd(){
		try {
			
			DatabaseAccessProperties dap = new DatabaseAccessProperties(configurationFile);
			JDBC_DRIVER = dap.getJdbcDriver();
			DB_URL = dap.getDatabaseUrl();
			USER = dap.getUsername();
			PASSWRD = dap.getPassword();
		    
			// Load the database driver
            Class.forName(JDBC_DRIVER);
            
			connection = (Connection) DriverManager.getConnection(DB_URL, USER, PASSWRD);
			
		} catch (SQLException se) {
            // Print information about SQL exceptions
            SQLWarningsExceptions.printExceptions(se);
            
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
            e.printStackTrace();
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
	 *@info Instance de connexion
	 */
	private static void getInstance(){
		if (connection == null) {
			new ConnexionBdd();
		}
	}
	
	/**
	 * @info Ferme la connection
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
