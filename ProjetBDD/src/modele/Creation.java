package modele;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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
	        
	        int dureeSemi = InsertionScanner.dureeSeminaire();
	        
	        		
			//• programme initial (activités)
			
	        Requetes.afficheActiviteSelect(conn);
	        List<Integer> idActivite = InsertionScanner.activiteSelected(dureeSemi);
	        
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
