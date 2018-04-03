package requete;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Corentin Dervieux
 * @author Thibaut Masselin
 * 
 */
public class Requetes {
	/**
	 * 
	 * @param conn
	 * @throws SQLException
	 */
	public static void creatSeminaire(Connection conn) throws SQLException {      
        Statement stmt = conn.createStatement();
        //int rs = stmt.executeUpdate("insert into  values ('"+id+"', '"+ jour +"-"+ mois +"-"+annee + "')");
        // Close the result set, statement and the connection 
        stmt.close();
    }
	
	/**
	 * selcetion des personnes qui sont des animateurs/animatrices
	 * @param conn
	 * @throws SQLException
	 */
	public static void afficheAnimateurSelect(Connection conn)throws SQLException {
		Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from Personnes where typePers='ACT'");
        while (rs.next()) {
            System.out.println(rs.getInt("idPers") + " - "+ rs.getString("nomPers") + " " +rs.getString("prenomPers"));
        }
        rs.close();
        stmt.close();
	}
	
	/**
	 * selcetion toutes activitées qui sont dans la bdd
	 * @param conn
	 * @throws SQLException
	 */
	public static void afficheActiviteSelect(Connection conn)throws SQLException {
		Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from Activite");
        while (rs.next()) {
            System.out.println(rs.getInt("idAct") + " - "+ rs.getString("libelleAct"));
        }
        rs.close();
        stmt.close();
	}
	
	/**
	 * Affiche les prestataires disponibles à une date donnée
	 * @param conn
	 * @param dateDuJour
	 * @throws SQLException
	 */
	public static void affichePrestaterSelect(Connection conn, String dateDuJour)throws SQLException {
		Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT P.idPres, P.libellePres"
        							   + "FROM Prestataires P NATURAL JOIN Seminaire "
        							   + "WHERE NOT(dateSemi = '"+ dateDuJour +"')");
        while (rs.next()) {
            System.out.println(rs.getInt("idPres") + " - "+ rs.getString("libellePres"));
        }
        rs.close();
        stmt.close();
	} 
	
	/**
	 * Affiche les Thèmes présent dans la base de données
	 * @param conn
	 * @throws SQLException
	 */
	public static void afficheThemeSelect(Connection conn)throws SQLException {
		Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Themes");
        while (rs.next()) {
            System.out.println(rs.getInt("idTheme") + " - "+ rs.getString("libelleTheme"));
        }
        rs.close();
        stmt.close();
	} 
	
	/*
    public static void requeteNumToNom(Connection conn) throws SQLException {
        String id;
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrer un numero de Spectacle dont vous souhaitez avoir le nom :");
        id = sc.nextLine();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select nomS from LesSpectacles where numS=" + id);
        if (rs.next()) {
            System.out.println("nom Spectale : " + rs.getString(1) + " ");
        } else {
            System.out.println("numero spectacle incorrecte");
        }
        // Close the result set, statement and theconnection 
        rs.close();
        stmt.close();
    }
    
     public static void requeteNewRep(Connection conn) throws SQLException {
        String id, mois, annee, jour;
        Scanner sc = new Scanner(System.in);
        Date date;
        System.out.println("entrez le numero du Spectacle dans le quel vous voulez ajoutez une representation : ");
        id = sc.nextLine();
        System.out.println("entrez le jour de la representation : ");
        jour = sc.nextLine();
        System.out.println("entrez le mois de la representation : ");
        mois = sc.nextLine();
        System.out.println("entrez l'annee de la representation : ");
        annee = sc.nextLine();
        
        Statement stmt = conn.createStatement();
        int rs = stmt.executeUpdate("insert into LESREPRESENTATIONS values ('"+id+"', '"+ jour +"-"+ mois +"-"+annee + "')");
        System.out.println("nombre de ligne creer :"+rs);
        // Close the result set, statement and theconnection 
        stmt.close();
    }
    */

}
