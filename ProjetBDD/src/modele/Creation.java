package modele;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.InsertionScanner;
import exception.SQLWarningsExceptions;
import modele.type.Conferencier;
import modele.type.Salle;
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
			Requetes.affichePersonneSelect(conn,"ACT");
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
			
	        this.ajouterDesConferencier(conn,seminaire);

			
			//-------------------- nombre de places --------------------
			seminaire.setNombrePlace(InsertionScanner.saisirEntier("Définir le nombre de personne maximum qui vous semble adapter au séminaire:"));
	        
			//-------------------- tarif de l'inscription --------------------
			seminaire.setPrixUnePlace(InsertionScanner.saisirDecimal("Définir un prix pour une place :"));
	        
			//-------------------- prestataire --------------------
	        Requetes.affichePrestataireSelect(conn, seminaire.getDateToString());
	        seminaire.setNumPerstataire(InsertionScanner.saisirEntier("Choisir un prestataire :"));  
	        
	        //-------------------- Salle ------------------------
	        List<Salle> lesSalles = Requetes.selectLesSalles(conn, seminaire);
			List<Integer> lesIdSalle = null;
			System.out.println("Les Salles disponible :");
			for(Salle s : lesSalles){
				System.out.println(s.toString());
				lesIdSalle.add(s.getNumSalle());
			}
			seminaire.setNumSeminaire(InsertionScanner.saisirEntier(lesIdSalle, true, "Saisir le numéro d'une Salle :"));

			//-------------------- Pause ------------------------
			Requetes.afficheLesPause(conn, seminaire.getNumPerstataire());
			List<Integer> lesPauses = ajouterDesPauses(seminaire.getDureeSemi());
			
			
			//-------------------- total des recettes prévus (min, max) --------------------
	        seminaire.setRecettePrevuMin(InsertionScanner.saisirDecimal("Définir une recette minimal :"));
	        seminaire.setRecettePrevuMax(InsertionScanner.saisirDecimal("Définir une recette maximal :"));
	        
	        //-------------------- total des dépenses prévus (min, max)--------------------
	        seminaire.setDepencePrevuMin(InsertionScanner.saisirDecimal("Définir une dépence minimal :"));
	        seminaire.setDepencePrevuMax(InsertionScanner.saisirDecimal("Définir une dépence maximal :"));
	        
	        // INSERT INTO
	        
	        
	        Requetes.insertSeminaire(conn,seminaire);
	        Requetes.insertOrganise(conn, seminaire);
	        
	        if(seminaire.getLesConferenciers().size() > 0){
	        	this.insertionDesConférencier(conn,seminaire);
	        }
	        
	        this.insertionPause(conn, lesPauses, seminaire.getNumSeminaire());
	        
	        //Prevue
	        this.insertionActiviter(conn, seminaire);
	        
		} catch (SQLException e) {
			System.err.println("Erreur base de données : "+ e.getMessage());
			SQLWarningsExceptions.printExceptions(e);
		}
	}
	
	/**
	 * Permet de créer des conférenciers
	 * @param conn
	 * @param seminaire
	 * @throws SQLException
	 */
	private void ajouterDesConferencier(Connection conn,Seminaire seminaire) throws SQLException{
		List<Conferencier> lesConferenciers =  new ArrayList<>();
		while(InsertionScanner.saisirEntier(0,1," - 0 = Arrêter d'ajouter des conférenciers \n"
											  + " - 1 = Ajouter un nouveau conférencier ") == 0){
			System.out.println(" -- Ajoute nouveau conférencier : -- ");
			Requetes.affichePersonneSelect(conn,"CON");
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
	
	/**
	 *  liste des pause
	 * @param conn
	 * @param seminaire
	 * @throws SQLException
	 */
	private void insertionPause(Connection conn, List<Integer> lesPauses,int numSeminaire) throws SQLException{
		for(Integer i : lesPauses){
			Requetes.insertUnePause(conn, i, numSeminaire);
		}
	}
	
	/**
	 * Permet de saisir une ou plusieurs pauses
	 * @param typeJour
	 * @return
	 */
	private List<Integer> ajouterDesPauses(int typeJour){
		List<Integer> inte = new ArrayList<>();
		int nbPauseMax = 1;
		if(typeJour != 2){nbPauseMax = 2;}
		for(int i=0;i<nbPauseMax;i++){
			inte.add(InsertionScanner.saisirEntier("Saisir l'id d'une pause :"));
		}
		return inte;
	}
	
}
