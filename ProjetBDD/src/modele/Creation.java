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
		
		//Cr�er un s�minaire avec :
		try {
			//� animateur			
			Requetes.afficheAnimateurSelect(conn);
			int numAnimateur = InsertionScanner.animateurNum();
	        
	        //� date
	        
	        String dateString = InsertionScanner.DateString();
	        
	        //� journ�e
	        
	        int dureeSemi = InsertionScanner.dureeSeminaire();
	        
	        		
			//� programme initial (activit�s)
			
	        Requetes.afficheActiviteSelect(conn);
	        List<Integer> idActivite = InsertionScanner.activiteSelected(dureeSemi);
	        
			//� le cas �ch�ant, le ou les conf�renciers, avec titre, transparents (dans les d�lais pr�vus),tarif de la prestation
			
			
			//� nombre de places
			
			//� tarif de l'inscription
			
			//� prestataire
			
			//� total des recettes pr�vus (min, max)
			
			//� total des d�penses pr�vus (min, max)
		 
	        // INSERT INTO
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
