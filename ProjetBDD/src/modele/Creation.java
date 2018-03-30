package modele;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import application.InsertionScanner;
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
			int numAnimateur = InsertionScanner.animateurNum();
	        
	        //• date
	        
	        String dateString = InsertionScanner.DateString();
	        
	        //• journée
	        
	        
	        
			//• programme initial (activités)
			
	        
	        
			//• le cas échéant, le ou les conférenciers, avec titre, transparents (dans les délais prévus),tarif de la prestation
			
			
			//• nombre de places
			
			//• tarif de l'inscription
			
			//• prestataire
			
			//• total des recettes prévus (min, max)
			
			//• total des dépenses prévus (min, max)
		 
	        // INSERT INTO
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
