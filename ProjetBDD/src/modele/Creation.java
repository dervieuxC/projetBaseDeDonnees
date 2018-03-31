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
			int numAnimateur = InsertionScanner.saisirEntier("Choisir un num�ro d'animateur :");
	        
	        //-------------------- date --------------------
	        String dateString = InsertionScanner.DateString("D�terminer une date :");
	        
	        //-------------------- journ�e --------------------
	        int dureeSemi = InsertionScanner.choixBorner(0,2,"Choisir la dur�e du seminaire :(0 = matin | 1=apr�s-midi | 2=journ�e)");
	        		
			//-------------------- programme initial (activit�s) --------------------
	        Requetes.afficheActiviteSelect(conn);
	        List<Integer> idActivite = InsertionScanner.activiteSelected(dureeSemi,"Choisir les avtivit�es vouluent");
	        
			//� le cas �ch�ant, le ou les conf�renciers, avec titre, transparents (dans les d�lais pr�vus),tarif de la prestation
			
			
			//-------------------- nombre de places --------------------
	        int nombrePlace = InsertionScanner.saisirEntier("D�finir le nombre de personne maximum qui vous semble adapter au s�minaire:");
	        
			//-------------------- tarif de l'inscription --------------------
	        float prixUnePlace = InsertionScanner.saisirDecimal("D�finir un prix pour une place :");
	        
			//-------------------- prestataire --------------------
	        Requetes.affichePrestaterSelect(conn, dateString);
	        int numPerstataire = InsertionScanner.saisirEntier("Choisir un prestataire :");
	        
			//-------------------- total des recettes pr�vus (min, max) --------------------
	        int recettePrevuMin = InsertionScanner.saisirEntier("D�finir une recette minimal :");
	        int recettePrevuMax = InsertionScanner.saisirEntier("D�finir une recette maximal :");
			
	        //-------------------- total des d�penses pr�vus (min, max)--------------------
	        int depencePrevuMin = InsertionScanner.saisirEntier("D�finir une d�pence minimal :");
	        int depencePrevuMax = InsertionScanner.saisirEntier("D�finir une d�pence maximal :");
		 
	        // INSERT INTO
	        
	        
		} catch (SQLException e) {
			System.err.println("Erreur base de donn�es : "+ e.getMessage());
			e.printStackTrace();
		}
	}
	
}
