package modele;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import application.InsertionScanner;
import exception.SQLWarningsExceptions;
import modele.type.Salle;
import modele.type.Seminaire;
import requete.Requetes;

/**
 * 
 * @author Corentin Dervieux
 * @author Thibaut Masselin
 *
 */
public class Confirmation extends ActionSeminaire {

	private Calendar cal = Calendar.getInstance();
	
	@Override
	public void action(Connection conn) {
		// Code de la Confirmation

		//Confirmer un séminaire 1 semaine avant sa date planifiée :
		try {
			cal.add(Calendar.DATE, 7);
			
			// Les séminaires en attente
			List<Seminaire> seminaires = new ArrayList<>();		
			seminaires.addAll(Requetes.selectLesSeminaireEnATT(conn));
			annulationDeSeminaire(conn,seminaires);
			decisionSeminaire(conn,seminaires);
			
			// Les séminaires en confirmer
			List<Seminaire> semi = new ArrayList<>();
			semi.addAll(Requetes.selectLesSeminaireCON(conn));
			blilanSeminaire(conn, semi);
		} catch (SQLException e) {
			SQLWarningsExceptions.printExceptions(e);
		}	
	}
	
	/**
	 * Mette les séminaires à annulé dans l'état ANN
	 * et les supprime de la liste
	 * @param conn
	 * @param seminaires
	 * @throws SQLException
	 */
	private void annulationDeSeminaire(Connection conn, List<Seminaire> seminaires) throws SQLException{
		for(Seminaire semi : seminaires){
			Requetes.selectNbInscrit(conn,semi);
			
			//• annulation si le nombre d'inscriptions est insuffisant
			if(Math.round(semi.getNombrePlace()/2) <= semi.getNombrePersonneActuelle() 
					//cal.getTime() > semi.getDate()
					&& cal.getTime().compareTo(semi.getDate()) == 1){
				semi.setEtatSemi("ANN");
				System.out.println("Le nombre d'inscriptions est insuffisant pour :"+ semi.getLibelle() );
				Requetes.updateSeminaireEtat(conn, semi);
				
			}
		}
	}

	/**
	 * @param conn
	 * @param seminaires
	 * @throws SQLException
	 */
	private void decisionSeminaire(Connection conn,List<Seminaire> seminaires) throws SQLException{
		for(Seminaire semi : seminaires){
			if(!semi.getEtatSemi().equals("ANN")){
				System.out.println("id:" +semi.getNumSeminaire() +" Titre:'"+semi.getLibelle()+"' Nombre De Personne:"+semi.getNombrePersonneActuelle());
				switch(InsertionScanner.saisirEntier(0,2," 0 = annulation | 1 = confirmation | 2 = Passé au suivant ") ){
					case 0:
						semi.setEtatSemi("ANN");
						Requetes.updateSeminaireEtat(conn, semi);
						break;
					case 1:
						semi.setEtatSemi("CON");
						Requetes.updateSeminaireEtat(conn, semi);
						break;
					case 2:
						
				}
			}
		}
	}
		
	//• bilan budgétaire si le séminaire a lieu
	/**
	 * Présente le bilan de tous les séminaire confirmés
	 * @param conn
	 * @param seminaires
	 * @throws SQLException
	 */
	private void blilanSeminaire(Connection conn, List<Seminaire> seminaires) throws SQLException{
		for(Seminaire semi : seminaires){
			float totalConf = Requetes.selectLeTotalConf(conn, semi);
			float totalPause = Requetes.selectLeTotalPause(conn, semi);
			float totalRepat = Requetes.selectLeTotalRepat(conn, semi);
			float prixSalle = Requetes.selectPrixSalle(conn, semi);
			System.out.println("-------------------");
			System.out.println("Titre : " + semi.getLibelle());
			System.out.println("Le prix des repas : "+ totalRepat);
			System.out.println("Le prix de la salle : "+ prixSalle);
			System.out.println("Le prix des pauses : "+ totalPause);
			System.out.println("Le prix des conférencier : "+ totalConf);
			float totalDepence = totalRepat+prixSalle+totalPause+totalConf;
			System.out.println("\nTotal Dépence : "+ totalDepence);
			float totalRectte = semi.getNombrePersonneActuelle()*semi.getPrixUnePlace();
			System.out.println("\nTotal Recette : "+ totalRectte);
			System.out.println("\nRésultat : "+ (totalRectte - totalDepence));
			System.out.println("-------------------");
		}
	}

}
