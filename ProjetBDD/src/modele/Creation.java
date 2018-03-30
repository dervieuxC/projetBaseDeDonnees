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
			int numAnimateur = InsertionScanner.animateurNum("Choisir un num�ro d'animateur :");
	        
	        //� date
	        
	        String dateString = InsertionScanner.DateString("D�terminer une date :");
	        
	        //� journ�e
	        
	        int dureeSemi = InsertionScanner.dureeSeminaire("Choisir la dur�e du seminaire");
	        		
			//� programme initial (activit�s)
			
	        Requetes.afficheActiviteSelect(conn);
	        List<Integer> idActivite = InsertionScanner.activiteSelected(dureeSemi,"Choisir les avtivit�es vouluent");
	        
			//� le cas �ch�ant, le ou les conf�renciers, avec titre, transparents (dans les d�lais pr�vus),tarif de la prestation
			
			
			//� nombre de places
			
	        /*affiche les salles qui sont disponoble ce jour
	        int nbPlace = InsertionScanner.;*/
	        
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
