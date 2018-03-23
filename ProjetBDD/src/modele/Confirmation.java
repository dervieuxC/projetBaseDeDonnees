package modele;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 
 * @author Corentin Dervieux
 * @author Thibaut Masselin
 *
 */
public class Confirmation extends ActionSeminaire {

	@Override
	public void action(Connection conn) {
		// Code de la Confirmation
		/*
		Confirmer un séminaire 1 semaine avant sa date planifiée :
		• annulation si le nombre d'inscriptions est insuffisant
		• prévenir le prestataire (soit annulation, soit confirmation avec le nombre de personnes)
		• bilan budgétaire si le séminaire a lieu
		 */
		
		try {
			req.creatSeminaire(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        //nombre d'inscriptions est insuffisant
		/*
		if(nbInscrit < nbpersonneMin){
			//anulation
		}
		*/
		
		//prévenir le prestataire
		
		//Si le séminaire à lieu 
		/*
		if(seminaire == ok){
			//prix
		}
		 */
	}
	
}
