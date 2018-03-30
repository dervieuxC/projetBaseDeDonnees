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
		//Créer un séminaire avec :
		try {
			//• animateur
			
			Requetes.afficheAnimateurSelect(conn);
			Scanner sc = new Scanner(System.in);
	        int numAnimateur;
	        System.out.println("Choisir un numéro d'animateur :");
	        numAnimateur = sc.nextInt();
	        
			//• programme initial (activités)
			
			//• le cas échéant, le ou les conférenciers, avec titre, transparents (dans les délais prévus),tarif de la prestation
			
			//• date
			
			//• nombre de places
			
			//• tarif de l'inscription
			
			//• prestataire
			
			//• total des recettes prévus (min, max)
			
			//• total des dépenses prévus (min, max)
		 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
