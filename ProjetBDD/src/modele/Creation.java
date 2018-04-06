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
		creatSeminaire(conn);
	}
	
	/**
	 * Création d'un séminaire
	 * @param conn
	 */
	private void creatSeminaire(Connection conn){
		//Créer un séminaire avec :
		try {
			
			Seminaire seminaire = new Seminaire();
			
			//-------------------- Numéro du Séminaire --------------------
			seminaire.setNumSeminaire(Requetes.selectMaxSeminaire(conn));
			seminaire.setEtatSemi("ATT");
			
			//-------------------- animateur --------------------		
			Requetes.afficheAnimateurSelect(conn);
			seminaire.setNumAnimateur(InsertionScanner.saisirEntier("Choisir un numéro d'animateur :"));
	        
			//-------------------- Titre Séminaire --------------------		
			seminaire.setLibelle(InsertionScanner.saisirString("Choisir un titre pour le Séminaire :"));
	        
			//-------------------- Thème --------------------
			Requetes.afficheThemeSelect(conn);
			seminaire.setNumTheme(InsertionScanner.saisirEntier("Entrer le thème selcetionner :"));
			//Proposé de créer un nouveau theme
			
	        //-------------------- date --------------------
			seminaire.setDate(InsertionScanner.DateStringOracle("Déterminer une date :"));
	        
	        //-------------------- journée --------------------
			seminaire.setDureeSemi(InsertionScanner.saisirEntier(0,2,"Choisir la durée du seminaire :(0 = matin | 1 = après-midi | 2 = journée)"));
	        
			//-------------------- repat --------------------
			if(seminaire.getDureeSemi() == 2 || InsertionScanner.saisirEntier(0,1,"Voulez vous un repas :(0 = non | 1 = oui)")== 1){
				Requetes.afficheRepasSelect(conn);
				seminaire.setNumRepas(InsertionScanner.saisirEntier("Choisir un repas :"));
			}
			
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
	        Requetes.affichePrestaterSelect(conn, seminaire.getDateToString());
	        seminaire.setNumPerstataire(InsertionScanner.saisirEntier("Choisir un prestataire :"));  
	        
			//-------------------- total des recettes prévus (min, max) --------------------
	        seminaire.setRecettePrevuMin(InsertionScanner.saisirDecimal("Définir une recette minimal :"));
	        seminaire.setRecettePrevuMax(InsertionScanner.saisirDecimal("Définir une recette maximal :"));
	        
	        //-------------------- total des dépenses prévus (min, max)--------------------
	        seminaire.setDepencePrevuMin(InsertionScanner.saisirDecimal("Définir une dépence minimal :"));
	        seminaire.setDepencePrevuMax(InsertionScanner.saisirDecimal("Définir une dépence maximal :"));
	        
	        // INSERT INTO
	       
	        // TODO: A voire pour les conférenciers
	        // TODO: Discuter de comment mettre les 4 dèrnière varible dans la base de données
	        Requetes.insertSeminaire(conn,seminaire);
	        Requetes.insertOrganise(conn, seminaire);
	        
	        if(seminaire.getLesConferenciers().size() > 0){
	        	this.insertionDesConférencier(conn,seminaire);
	        }
	        
	        //Prevue
	        this.insertionActiviter(conn, seminaire);
	        
		} catch (SQLException e) {
			System.err.println("Erreur base de données : "+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Permet de créer des conférenciers
	 */
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
		seminaire.addAllLesConferenciers(lesConferenciers);
		// Pour savoir si il y a quelque chose à ajouter
	}
	
	/**
	 * Ajouter une liste de conférencier
	 * @param conn
	 * @param seminaire
	 * @throws SQLException
	 */
	private void insertionDesConférencier(Connection conn, Seminaire seminaire) throws SQLException{
		for(Conferencier conferencier : seminaire.getLesConferenciers()){
			Requetes.insertFaitUneConf(conn, seminaire.getNumSeminaire(), conferencier);
		}
	}
	
	/**
	 * Ajouter une liste d'activiter
	 * @param conn
	 * @param seminaire
	 * @throws SQLException
	 */
	private void insertionActiviter(Connection conn, Seminaire seminaire) throws SQLException{
		for(Integer i : seminaire.getLesActivites()){
			Requetes.insertUneActiviter(conn,seminaire.getNumSeminaire(),i);
		}
	}
	
}
