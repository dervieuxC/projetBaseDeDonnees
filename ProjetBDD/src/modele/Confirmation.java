package modele;

import java.sql.Connection;

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
		Confirmer un s�minaire 1 semaine avant sa date planifi�e :
		� annulation si le nombre d'inscriptions est insuffisant
		� pr�venir le prestataire (soit annulation, soit confirmation avec le nombre de personnes)
		� bilan budg�taire si le s�minaire a lieu
		*/
		
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
	
}
