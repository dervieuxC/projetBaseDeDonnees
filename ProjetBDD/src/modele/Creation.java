package modele;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import requete.Requetes;

/**
 * 
 * @author Corentin Dervieux
 * @author Thibaut Masselin
 *
 */
public class Creation extends ActionSeminaire {


	@Override
	public void action(Connection conn) {
		//Cr�er un s�minaire avec :
		try {
			//� animateur
			
			Requetes.afficheAnimateurSelect(conn);
			Scanner sc = new Scanner(System.in);
	        int numAnimateur;
	        System.out.println("Choisir un num�ro d'animateur :");
	        numAnimateur = sc.nextInt();
	        
			//� programme initial (activit�s)
			
			//� le cas �ch�ant, le ou les conf�renciers, avec titre, transparents (dans les d�lais pr�vus),tarif de la prestation
			
			//� date
			
			//� nombre de places
			
			//� tarif de l'inscription
			
			//� prestataire
			
			//� total des recettes pr�vus (min, max)
			
			//� total des d�penses pr�vus (min, max)
		 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
