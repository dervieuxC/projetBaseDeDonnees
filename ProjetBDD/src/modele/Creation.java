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
			//-------------------- animateur --------------------		
			Requetes.afficheAnimateurSelect(conn);
			int numAnimateur = InsertionScanner.choixPoposition("Choisir un numéro d'animateur :");
	        
	        //-------------------- date --------------------
	        
	        String dateString = InsertionScanner.DateString("Déterminer une date :");
	        
	        //-------------------- journée --------------------
	        
	        int dureeSemi = InsertionScanner.choixBorner(0,2,"Choisir la durée du seminaire :(0 = matin | 1=après-midi | 2=journée)");
	        		
			//-------------------- programme initial (activités) --------------------
			
	        Requetes.afficheActiviteSelect(conn);
	        List<Integer> idActivite = InsertionScanner.activiteSelected(dureeSemi,"Choisir les avtivitées vouluent");
	        
			//• le cas échéant, le ou les conférenciers, avec titre, transparents (dans les délais prévus),tarif de la prestation
			
			
			//-------------------- nombre de places --------------------
	        /*Il est plus intéréssent de séléctionnée une salle qui à un nombre
	          de place défini plutôt que demander un nombre dépourvue de sens.
	         */
	        Requetes.afficheSalleDispoSelect(conn, dateString);
	        int numSalle = InsertionScanner.choixPoposition("Sélectionné parmi les propositions la salle qui vous semble adapter :");
	        
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
