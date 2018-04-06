package requete;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modele.type.Conferencier;
import modele.type.Salle;
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
        int rs = stmt.executeUpdate("insert into Seminaires values ("+seminaire.getNumSeminaire()+","
					  + " '"+seminaire.getLibelle()+ "',"
					  + " "+seminaire.getDureeSemi()+ ","
					  + " '"+seminaire.getEtatSemi()+"',"
					  + " "+seminaire.getPrixUnePlace()+ ","
					  + " '"+seminaire.getDateToString()+ "',"
					  + " "+seminaire.getNombrePlace()+ ","
					  + " "+seminaire.getNumTheme()+ ","
					  + " "+seminaire.getNumPerstataire()+","
					  + "  NULL,"// Il reste la salle
					  + " "+seminaire.getNumRepas()+","
					  + " "+seminaire.getRecettePrevuMin()+","
					  + " "+seminaire.getDepencePrevuMax()+","
					  + " "+seminaire.getDepencePrevuMin()+","
					  + " "+seminaire.getDepencePrevuMax()+")");
					  
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
        int rs = stmt.executeUpdate("insert into FaitUneConf values ("+conferencier.getNumConferencier()
        				+ ","+idSemi
						+ "," +conferencier.getPrixDePrestation()
						+ ",'"+conferencier.getTransparents()
						+ ",'"+conferencier.getTitre()+"')");
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
        int rs = stmt.executeUpdate("insert into Organise values ("+seminaire.getNumAnimateur()+","
        		+ seminaire.getNumSeminaire()+")");
        // Close the result set, statement and the connection 
        stmt.close();
    }
	
	/**
	 * Ajouter une Action dans la base de données
	 * @param conn permet d'utiliser la connexion
	 * @param seminaire
	 * @throws SQLException
	 */
	public static void insertUneActiviter(Connection conn, int idSemi,int idAction) throws SQLException {      
        Statement stmt = conn.createStatement();
        int rs = stmt.executeUpdate("insert into Prevue values ("+idSemi+","+idAction+")");
        // Close the result set, statement and the connection 
        stmt.close();
    }
	
	/**
	 * selcetion les repas qui sont présent dans la base de données
	 * @param conn permet d'utiliser la connexion
	 * @throws SQLException
	 */
	public static void afficheRepasSelect(Connection conn)throws SQLException {
		Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from Repas");
        while (rs.next()) {
            System.out.println(" • " + rs.getInt("idRepa") + " - "+ rs.getString("libelleRepa"));
        }
        rs.close();
        stmt.close();
	}
	
	/**
	 * selcetion des personnes qui sont des animateurs/animatrices
	 * @param conn permet d'utiliser la connexion
	 * @throws SQLException
	 */
	public static void affichePersonneSelect(Connection conn, String typeP)throws SQLException {
		Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from Personnes where typePers='"+typeP+"'");
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
	 * mise à jour de la Salles
	 * @param conn permet d'utiliser la connexion
	 * @throws SQLException
	 */
	public static void updateSalle(Connection conn,int numSalle, int numSeminaire)throws SQLException {
		Statement stmt = conn.createStatement();
        int rs = stmt.executeUpdate("UPDATE Seminaire SET idSalle="+numSalle+" WHERE idSemi="+numSeminaire+"");
        // Close the result set, statement and the connection 
        stmt.close();
	}
	
	/**
	 * Affiche les prestataires disponibles à une date donnée
	 * @param conn permet d'utiliser la connexion
	 * @param dateDuJour
	 * @throws SQLException
	 */
	public static void affichePrestataireSelect(Connection conn, String dateDuJour)throws SQLException {
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
	 * Selectionne le numéro du séminaire maximum +1
	 * @param conn permet d'utiliser la connexion
	 * @throws SQLException
	 * 
	 * @info On concidère qu'il aura toujours au moins 
	 * 		 un séminaire dans la base de donnée
	 * 
	 * @return Un entier
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
	
	/**
	 * Selectionne tous les séminaires en attente
	 * @param conn permet d'utiliser la connexion
	 * @throws SQLException
	 * 
	 * @return Une list de Séminaire
	 */
	public static List<Seminaire> selectLesSeminaire(Connection conn)throws SQLException {
		List<Seminaire> seminaires = new ArrayList<>();
		Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Seminaire where  etatSemi =='ATT' AND"
        							+ " sysdate >= dateSemi");
        while (rs.next()) {
        	Seminaire seminaire = new Seminaire();
        	seminaire.setDate(rs.getDate("dateSemi"));
        	//Si jamais il y a un problème avec la date utilise : seminaire.setDate(rs.getString("dateSemi"));
        	seminaire.setLibelle(rs.getString("libelleSemi"));
        	seminaire.setNombrePlace(rs.getInt("nbPers"));
        	seminaire.setNumPerstataire(rs.getInt("idPres"));
        	seminaire.setNumSeminaire(rs.getInt("idSemi"));
        	seminaires.add(seminaire);
        }
        rs.close();
        stmt.close();
        return seminaires;
	} 

	/**
	 * Selection tous les salles qui on un nombre de place 
	 * sufisante avec un perstataire
	 * 
	 * @param conn
	 * @param nbPlace
	 * @param numPerstataire
	 * @return Une Liste de salles
	 * @throws SQLException
	 */
	public static List<Salle> selectLesSalles(Connection conn, int nbPlace, int numPerstataire)throws SQLException {
		List<Salle>  salles = new ArrayList<>();
		Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT S.idSalle, S.libelleSalle, S.nbPlace, P.prix, "
        					+ "FROM PrixSalles P natural join Salles S "
        					+ "WHERE S.nbPlace >= "+ nbPlace
        					+ "AND P.idPres == " + numPerstataire);
        while (rs.next()) {
        	Salle sal = new Salle();
        	sal.setNumSalle(rs.getInt("idSalle"));
        	sal.setNombrePlace(rs.getInt("nbPlace"));
        	sal.setNumPrestataire(numPerstataire);
        	sal.setLibelle(rs.getString("libelleSalle"));
        	sal.setPrix(rs.getFloat("prix"));
        	salles.add(sal);
        }
        rs.close();
        stmt.close();
        return salles;
	}
	
	/**
	 * 
	 * @param conn
	 * @param NumSemi
	 * @return
	 * @throws SQLException
	 */
	public static float selectLesTotal(Connection conn, int NumSemi) throws SQLException{
		float toto = 0;
		Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT F.prix"
        							+ "FROM Seminaires Semi natural join FaitUneConf  F");
        while(rs.next()){
        	toto +=  rs.getFloat("prix");
        }
        rs.close();
        stmt.close();
		return toto;
	}
}
