package modele;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

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

		//Confirmer un s�minaire 1 semaine avant sa date planifi�e :
		try {
			cal.add(Calendar.DATE, 7);
			List<Seminaire> seminaires = new ArrayList<>();
		
			seminaires.addAll(Requetes.selectLesSeminaire(conn));
			
			annulationDeSeminaire(conn,seminaires);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	/**
	 * Mette les s�minaires � annul� dans l'�tat ANN
	 * et les supprime de la liste
	 * @param conn
	 * @param seminaires
	 * @throws SQLException
	 */
	private void annulationDeSeminaire(Connection conn, List<Seminaire> seminaires) throws SQLException{
		for(Seminaire semi : seminaires){
			Requetes.selectNbInscrit(conn,semi);
			
			//� annulation si le nombre d'inscriptions est insuffisant
			if(Math.round(semi.getNombrePlace()/2) <= semi.getNombrePersonneActuelle() 
					&& cal.getTime().compareTo(semi.getDate()) == -1){
				semi.setEtatSemi("ANN");
				System.out.println("Le nombre d'inscriptions est insuffisant pour :"+ semi.getLibelle() );
				seminaires.remove(semi);
			}
		}
	}
		//� pr�venir le prestataire (soit annulation, soit confirmation avec le nombre de personnes)
		//for(Seminaire semi : seminaires){
		
		
		//� bilan budg�taire si le s�minaire a lieu
		
		
		
        //nombre d'inscriptions est insuffisant
		/*
		if(nbInscrit < nbpersonneMin){
			//anulation
		}
		*/
		
		//pr�venir le prestataire
		
		//Si le s�minaire � lieu 
		/*
		if(seminaire == ok){
			//prix
		}
		 */
	
}
