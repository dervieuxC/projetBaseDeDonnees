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
	
	//***************************************************************************************//
	//Les Inserts
	//***************************************************************************************//
	
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
					  + " "+seminaire.getNumSalle()+","
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
						+ ",'"+conferencier.getTransparents()+"'"
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
	 * Ajouter Une pause
	 * @param conn permet d'utiliser la connexion
	 * @param idSemi
	 * @param idPause
	 * @throws SQLException
	 */
	public static void insertUnePause(Connection conn, int idSemi,int idPause) throws SQLException {      
        Statement stmt = conn.createStatement();
        int rs = stmt.executeUpdate("insert into PauseSemi values ("+idSemi+","+idPause+")");
        // Close the result set, statement and the connection 
        stmt.close();
    }
	

	//***************************************************************************************//
	//Les Affiches
	//***************************************************************************************//
	
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
            System.out.println(" • " + rs.getInt("idPers") + " - "+ rs.getString("nomPers")
            		+ " " +rs.getString("prenomPers"));
        }
        rs.close();
        stmt.close();
	}
	
	/**
	 * affiche toutes les pauses
	 * 
	 * @param conn
	 * @param nbPlace
	 * @param numPerstataire
	 * @return Une Liste de salles
	 * @throws SQLException
	 */
	public static void afficheLesPause(Connection conn,int numPerstataire)throws SQLException {

		Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Pauses natural join PrixPauses natural join Prestataires "
        								+ "where idPres="+numPerstataire);
        while (rs.next()) {
        	System.out.println(rs.getInt("idPause") + " - " + rs.getString("libellePause")+" prix:"+ rs.getFloat("prix"));
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
        ResultSet rs = stmt.executeQuery("select * from Activites");
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
	public static void affichePrestataireSelect(Connection conn, String dateDuJour)throws SQLException {
		Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT P.idPres, P.libellePres"
        							   + "FROM Prestataires P NATURAL JOIN Seminaires "
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

	///Les Selects
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
        ResultSet rs = stmt.executeQuery("SELECT MAX(idSemi) FROM Seminaires");
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
	public static List<Seminaire> selectLesSeminaireEnATT(Connection conn)throws SQLException {
		List<Seminaire> seminaires = new ArrayList<>();
		Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Seminaires where  etatSemi ='ATT' AND"
        							+ " sysdate <= dateSemi");
        while (rs.next()) {
        	Seminaire seminaire = new Seminaire();
        	seminaire.setDate(rs.getDate("dateSemi"));
        	//Si jamais il y a un problème avec la date utilise : seminaire.setDate(rs.getString("dateSemi"));
        	seminaire.setLibelle(rs.getString("libelleSemi"));
        	seminaire.setNombrePlace(rs.getInt("nbPers"));
        	seminaire.setNumPerstataire(rs.getInt("idPres"));
        	seminaire.setNumSeminaire(rs.getInt("idSemi"));
        	seminaire.setNumSalle(rs.getInt("idSalle"));
        	seminaire.setNumSalle(rs.getInt("idRepa"));
        	seminaires.add(seminaire);
        }
        rs.close();
        stmt.close();
        return seminaires;
	} 
	
	/**
	 * Selectionne tous les séminaires en confirmé
	 * @param conn permet d'utiliser la connexion
	 * @throws SQLException
	 * 
	 * @return Une list de Séminaire
	 */
	public static List<Seminaire> selectLesSeminaireCON(Connection conn)throws SQLException {
		List<Seminaire> seminaires = new ArrayList<>();
		Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Seminaires where  etatSemi ='CON' AND sysdate <= dateSemi");
        while (rs.next()) {
        	Seminaire seminaire = new Seminaire();
        	seminaire.setDate(rs.getDate("dateSemi"));
        	//Si jamais il y a un problème avec la date utilise : seminaire.setDate(rs.getString("dateSemi"));
        	seminaire.setLibelle(rs.getString("libelleSemi"));
        	seminaire.setNombrePlace(rs.getInt("nbPers"));
        	seminaire.setNumPerstataire(rs.getInt("idPres"));
        	seminaire.setNumSeminaire(rs.getInt("idSemi"));
        	seminaire.setNumSalle(rs.getInt("idSalle"));
        	seminaire.setNumSalle(rs.getInt("idRepa"));
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
	 * @param semi
	 * @return Une Liste de salles
	 * @throws SQLException
	 */
	public static List<Salle> selectLesSalles(Connection conn,Seminaire semi)throws SQLException {
		List<Salle>  salles = new ArrayList<>();
		Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT S.idSalle, S.libelleSalle, S.nbPlace, P.prix, "
        					+ "FROM PrixSalles P natural join Salles S "
        					+ "WHERE S.nbPlace >= "+ semi.getNombrePlace()
        					+ "AND P.idPres = " + semi.getNumPerstataire());
        while (rs.next()) {
        	Salle sal = new Salle();
        	sal.setNumSalle(rs.getInt("idSalle"));
        	sal.setNombrePlace(rs.getInt("nbPlace"));
        	sal.setNumPrestataire(semi.getNumPerstataire());
        	sal.setLibelle(rs.getString("libelleSalle"));
        	sal.setPrix(rs.getFloat("prix"));
        	salles.add(sal);
        }
        rs.close();
        stmt.close();
        return salles;
	}
	
	/**
	 * Total du prix des conférenciers pour un Séminaire
	 * @param conn
	 * @param semi
	 * @return Un Décimal
	 * @throws SQLException
	 */
	public static float selectLeTotalConf(Connection conn, Seminaire semi) throws SQLException{
		float toto = 0;
		Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT prix FROM Seminaires natural join FaitUneConf"
        			+ "Where idSemi="+ semi.getNumSeminaire());
        while(rs.next()){
        	toto +=  rs.getFloat("prix");
        }
        rs.close();
        stmt.close();
		return toto;
	}
	
	/**
	 * Total du prix des repas pour un séminaire
	 * @param conn
	 * @param semi
	 * @return Un Décimal
	 * @throws SQLException
	 */
	public static float selectLeTotalRepat(Connection conn, Seminaire semi) throws SQLException{
		float toto = 0;
		Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT prix"
        							+ "FROM Seminaires natural join PrixRepas Where idSemi="+ semi.getNumSeminaire());
        while(rs.next()){
        	toto +=  rs.getFloat("prix");
        }
        rs.close();
        stmt.close();
		return toto;
	}
	
	/**
	 * Total du prix des pauses pour un séminaire
	 * @param conn
	 * @param semi
	 * @return
	 * @throws SQLException
	 */
	public static float selectLeTotalPause(Connection conn, Seminaire semi) throws SQLException{
		float toto = 0;
		Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select prix from PrixPauses natural join PauseSemi Where idSemi="+ semi.getNumSeminaire());
        while(rs.next()){
        	toto +=  rs.getFloat("prix");
        }
        rs.close();
        stmt.close();
		return toto;
	}
	
	/**
	 * Total du prix de la salle
	 * @param conn
	 * @param semi
	 * @return
	 * @throws SQLException
	 */
	public static float selectPrixSalle(Connection conn, Seminaire semi) throws SQLException{
		float toto = 0;
		Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select prix from PrixSalles Where idSemi="+ semi.getNumSeminaire());
        while(rs.next()){
        	toto +=  rs.getFloat("prix");
        }
        rs.close();
        stmt.close();
		return toto;
	}
	//***************************************************************************************//
	//Les Updates
	//***************************************************************************************//
	/**
	 * mise à jour de l'etat du séminaire
	 * @param semi
	 * @throws SQLException
	 */
	public static void updateSeminaireEtat(Connection conn,Seminaire semi) throws SQLException{
		Statement stmt = conn.createStatement();
        int rs = stmt.executeUpdate("UPDATE Seminaire SET etatSemi="+semi.getEtatSemi()+"  Where idSemi="+ semi.getNumSeminaire());
        // Close the result set, statement and the connection 
        stmt.close();
	}
	
	
}
