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
			int numAnimateur = InsertionScanner.saisirEntier("Choisir un numéro d'animateur :");
	        
	        //-------------------- date --------------------
	        String dateString = InsertionScanner.DateString("Déterminer une date :");
	        
	        //-------------------- journée --------------------
	        int dureeSemi = InsertionScanner.choixBorner(0,2,"Choisir la durée du seminaire :(0 = matin | 1=après-midi | 2=journée)");
	        		
			//-------------------- programme initial (activités) --------------------
	        Requetes.afficheActiviteSelect(conn);
	        List<Integer> idActivite = InsertionScanner.activiteSelected(dureeSemi,"Choisir les avtivitées vouluent");
	        
			//• le cas échéant, le ou les conférenciers, avec titre, transparents (dans les délais prévus),tarif de la prestation
			
			
			//-------------------- nombre de places --------------------
	        int nombrePlace = InsertionScanner.saisirEntier("Définir le nombre de personne maximum qui vous semble adapter au séminaire:");
	        
			//-------------------- tarif de l'inscription --------------------
	        float prixUnePlace = InsertionScanner.saisirDecimal("Définir un prix pour une place :");
	        
			//-------------------- prestataire --------------------
	        Requetes.affichePrestaterSelect(conn, dateString);
	        int numPerstataire = InsertionScanner.saisirEntier("Choisir un prestataire :");
	        
			//-------------------- total des recettes prévus (min, max) --------------------
	        int recettePrevuMin = InsertionScanner.saisirEntier("Définir une recette minimal :");
	        int recettePrevuMax = InsertionScanner.saisirEntier("Définir une recette maximal :");
			
	        //-------------------- total des dépenses prévus (min, max)--------------------
	        int depencePrevuMin = InsertionScanner.saisirEntier("Définir une dépence minimal :");
	        int depencePrevuMax = InsertionScanner.saisirEntier("Définir une dépence maximal :");
		 
	        // INSERT INTO
	        
	        
		} catch (SQLException e) {
			System.err.println("Erreur base de données : "+ e.getMessage());
			e.printStackTrace();
		}
	}
	
}
