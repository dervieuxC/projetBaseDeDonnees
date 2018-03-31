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
			//-------------------- animateur --------------------		
			Requetes.afficheAnimateurSelect(conn);
			int numAnimateur = InsertionScanner.choixPoposition("Choisir un num�ro d'animateur :");
	        
	        //-------------------- date --------------------
	        
	        String dateString = InsertionScanner.DateString("D�terminer une date :");
	        
	        //-------------------- journ�e --------------------
	        
	        int dureeSemi = InsertionScanner.choixBorner(0,2,"Choisir la dur�e du seminaire :(0 = matin | 1=apr�s-midi | 2=journ�e)");
	        		
			//-------------------- programme initial (activit�s) --------------------
			
	        Requetes.afficheActiviteSelect(conn);
	        List<Integer> idActivite = InsertionScanner.activiteSelected(dureeSemi,"Choisir les avtivit�es vouluent");
	        
			//� le cas �ch�ant, le ou les conf�renciers, avec titre, transparents (dans les d�lais pr�vus),tarif de la prestation
			
			
			//-------------------- nombre de places --------------------
	        /*Il est plus int�r�ssent de s�l�ctionn�e une salle qui � un nombre
	          de place d�fini plut�t que demander un nombre d�pourvue de sens.
	         */
	        Requetes.afficheSalleDispoSelect(conn, dateString);
	        int numSalle = InsertionScanner.choixPoposition("S�lectionn� parmi les propositions la salle qui vous semble adapter :");
	        
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
