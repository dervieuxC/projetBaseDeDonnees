package modele;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.InsertionScanner;
import modele.type.Conferencier;
import modele.type.Seminaire;
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
			
			Seminaire seminaire = new Seminaire();
			
			//-------------------- animateur --------------------		
			Requetes.afficheAnimateurSelect(conn);
			seminaire.setNumAnimateur(InsertionScanner.saisirEntier("Choisir un numéro d'animateur :"));
	        
			//-------------------- Titre Séminaire --------------------		
			seminaire.setLibelle(InsertionScanner.saisirString("Choisir un numéro d'animateur :"));
	        
			
			//-------------------- Thème --------------------
			Requetes.afficheThemeSelect(conn);
			seminaire.setNumTheme(InsertionScanner.saisirEntier("Entrer le thème selcetionner :"));
			
	        //-------------------- date --------------------
			seminaire.setDateString(InsertionScanner.DateStringOracle("Déterminer une date :"));
	        
	        //-------------------- journée --------------------
			seminaire.setDureeSemi(InsertionScanner.saisirEntier(0,2,"Choisir la durée du seminaire :(0 = matin | 1=après-midi | 2=journée)"));
	        		
			//-------------------- programme initial (activités) --------------------
	        Requetes.afficheActiviteSelect(conn);
	        seminaire.setLesActivites(InsertionScanner.activiteSelected(seminaire.getDureeSemi(),"Choisir les avtivitées souhaiter :"));
	        
			//• le cas échéant, le ou les conférenciers, avec titre, transparents (dans les délais prévus),tarif de la prestation
			
	        this.ajouterDesConferencier(seminaire);

			
			//-------------------- nombre de places --------------------
			seminaire.setNombrePlace(InsertionScanner.saisirEntier("Définir le nombre de personne maximum qui vous semble adapter au séminaire:"));
	        
			//-------------------- tarif de l'inscription --------------------
			seminaire.setPrixUnePlace(InsertionScanner.saisirDecimal("Définir un prix pour une place :"));
	        
			//-------------------- prestataire --------------------
	        Requetes.affichePrestaterSelect(conn, seminaire.getDateString());
	        seminaire.setNumPerstataire(InsertionScanner.saisirEntier("Choisir un prestataire :"));  
	        
			//-------------------- total des recettes prévus (min, max) --------------------
	        seminaire.setRecettePrevuMin(InsertionScanner.saisirDecimal("Définir une recette minimal :"));
	        seminaire.setRecettePrevuMax(InsertionScanner.saisirDecimal("Définir une recette maximal :"));
			
	        // valeur calculer
	        // max +20%
	        // min moitierDesParticipant
	        
	        //-------------------- total des dépenses prévus (min, max)--------------------
	        seminaire.setDepencePrevuMin(InsertionScanner.saisirDecimal("Définir une dépence minimal :"));
	        seminaire.setDepencePrevuMax(InsertionScanner.saisirDecimal("Définir une dépence maximal :"));
	        
	        // INSERT INTO
	       
	        // TODO: A voire pour les conférenciers
	        // TODO: Discuter de comment mettre les 4 dèrnière varible dans la base de données
	        Requetes.insertSeminaire(conn,seminaire);
	        Requetes.insertOrganise(conn, seminaire);
	        
	        if(seminaire.getLesConferenciers().size() > 0){
	        	insertionDesConférencier(conn,seminaire);
	        }
	        //FaitUneConf
	        //Prevue
	        
		} catch (SQLException e) {
			System.err.println("Erreur base de données : "+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void ajouterDesConferencier(Seminaire seminaire){
		List<Conferencier> lesConferenciers =  new ArrayList<>();
		while(InsertionScanner.saisirEntier(0,1," - 0 = Arrêter d'ajouter des conférenciers \n"
											  + " - 1 = Ajouter un nouveau conférencier ") == 0){
			System.out.println(" -- Ajoute nouveau conférencier : -- ");
			
			int numConferencier = InsertionScanner.saisirEntier("Entrer le numéro du conférencier :");
			String titre = InsertionScanner.saisirString("Entrer le titre de la conférence :");
			String  transparents = InsertionScanner.saisirString("Entrer les transparents :");
			float prixDePrestation = InsertionScanner.saisirDecimal("Le prix de la prestation :");
			
			lesConferenciers.add(new Conferencier(numConferencier,titre,transparents,prixDePrestation));
		}
		seminaire.setLesConferenciers(lesConferenciers);
		// Pour savoir si il y a quelque chose à ajouter
	}
	
	private void insertionDesConférencier(Connection conn, Seminaire seminaire) throws SQLException{
		for(Conferencier c : seminaire.getLesConferenciers()){
			Requetes.insertFaitUneConf(conn, seminaire.getNumSeminaire(), c);
		}
		
	}
	
}
