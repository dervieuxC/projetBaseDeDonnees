package requete;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modele.type.Conferencier;
import modele.type.Seminaire;

/**
 *
 * @author Corentin Dervieux
 * @author Thibaut Masselin
 * 
 */

public class Requetes {
	
	/**
	 * Ajouter un Séminaire dans la base de données
	 * @param conn permet d'utiliser la connexion
	 * @param seminaire
	 * @throws SQLException
	 */
	public static void insertSeminaire(Connection conn, Seminaire seminaire) throws SQLException {      
        Statement stmt = conn.createStatement();
        int rs = stmt.executeUpdate("insert into Seminaires values ('"+seminaire.getNumSeminaire()+"',"
					  + " '"+seminaire.getLibelle()+ "',"
					  + " '"+seminaire.getDureeSemi()+ "',"
					  + " 'ATT',"
					  + " '"+seminaire.getPrixUnePlace()+ "',"
					  + " '"+seminaire.getDateString()+ "',"
					  + " '"+seminaire.getNombrePlace()+ "',"
					  + " '"+seminaire.getNumTheme()+ "',"
					  + " ,"+seminaire.getNumPerstataire()+"', NULL,NULL)");
					  // Il reste la salle et le repat
        // Close the result set, statement and the connection 
        stmt.close();
    }
	
	/**
	 * Ajouter un Conférencier dans la base de données
	 * @param conn permet d'utiliser la connexion
	 * @param seminaire
	 * @throws SQLException
	 */
	public static void insertFaitUneConf(Connection conn, int idSemi,Conferencier conferencier) throws SQLException {      
        Statement stmt = conn.createStatement();
        int rs = stmt.executeUpdate("insert into FaitUneConf values ('"+conferencier.getNumConferencier()+"',"
						+ "'"+idSemi+"',"
						+ "'"+conferencier.getPrixDePrestation()+"',"
						+ "'"+conferencier.getTransparents()+"',"
						+ "'"+conferencier.getTitre()+"')");
        // Close the result set, statement and the connection 
        stmt.close();
    }
	
	/**
	 * Ajouter un Animateur dans la base de données
	 * @param conn permet d'utiliser la connexion
	 * @param seminaire
	 * @throws SQLException
	 */
	public static void insertOrganise(Connection conn, Seminaire seminaire) throws SQLException {      
        Statement stmt = conn.createStatement();
        int rs = stmt.executeUpdate("insert into Organise values ('"+seminaire.getNumAnimateur()+"',"
        		+ "'"+seminaire.getNumSeminaire()+"')");
        // Close the result set, statement and the connection 
        stmt.close();
    }
	
	/**
	 * Ajouter une Action dans la base de données
	 * @param conn permet d'utiliser la connexion
	 * @param seminaire
	 * @throws SQLException
	 */
	public static void insertAction(Connection conn, int idSemi,int idAction) throws SQLException {      
        Statement stmt = conn.createStatement();
        int rs = stmt.executeUpdate("insert into Prevue values ('"+idSemi+"','"+idAction+"')");
        // Close the result set, statement and the connection 
        stmt.close();
    }
	
	/**
	 * selcetion des personnes qui sont des animateurs/animatrices
	 * @param conn permet d'utiliser la connexion
	 * @throws SQLException
	 */
	public static void afficheAnimateurSelect(Connection conn)throws SQLException {
		Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from Personnes where typePers='ACT'");
        while (rs.next()) {
            System.out.println(" • " + rs.getInt("idPers") + " - "+ rs.getString("nomPers") + ""
            		+ " " +rs.getString("prenomPers"));
        }
        rs.close();
        stmt.close();
	}
	
	/**
	 * selcetion toutes activitées qui sont dans la bdd
	 * @param conn permet d'utiliser la connexion
	 * @throws SQLException
	 */
	public static void afficheActiviteSelect(Connection conn)throws SQLException {
		Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from Activite");
        while (rs.next()) {
            System.out.println(" • " + rs.getInt("idAct") + " - "+ rs.getString("libelleAct"));
        }
        rs.close();
        stmt.close();
	}
	
	/**
	 * Affiche les prestataires disponibles à une date donnée
	 * @param conn permet d'utiliser la connexion
	 * @param dateDuJour
	 * @throws SQLException
	 */
	public static void affichePrestaterSelect(Connection conn, String dateDuJour)throws SQLException {
		Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT P.idPres, P.libellePres"
        							   + "FROM Prestataires P NATURAL JOIN Seminaire "
        							   + "WHERE NOT(dateSemi = '"+ dateDuJour +"')");
        while (rs.next()) {
            System.out.println(" • " + rs.getInt("idPres") + " - "+ rs.getString("libellePres"));
        }
        rs.close();
        stmt.close();
	} 
	
	/**
	 * Affiche les Thèmes présent dans la base de données
	 * @param conn permet d'utiliser la connexion
	 * @throws SQLException
	 */
	public static void afficheThemeSelect(Connection conn)throws SQLException {
		Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Themes");
        while (rs.next()) {
            System.out.println(" • " + rs.getInt("idTheme") + " - "+ rs.getString("libelleTheme"));
        }
        rs.close();
        stmt.close();
	} 

	/**
	 * Affiche les Thèmes présent dans la base de données
	 * @param conn permet d'utiliser la connexion
	 * @throws SQLException
	 */
	public static int selectMaxSeminaire(Connection conn)throws SQLException {
		int idSeminaire = 1;
		Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT MAX(idSemi) FROM Seminaire");
        if (rs.next()) {
        	idSeminaire = rs.getInt("idSemi") + 1;
        }
        rs.close();
        stmt.close();
        return idSeminaire;
	} 
	
}
