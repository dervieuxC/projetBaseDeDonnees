package modele;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import application.InsertionScanner;
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
			List<Seminaire> seminaires = new ArrayList<>();
		
			seminaires.addAll(Requetes.selectLesSeminaire(conn));
			
			annulationDeSeminaire(conn,seminaires);
			decisionSeminaire(conn,seminaires);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			}
		}
	}

	/**
	 * prévenir le prestataire (soit annulation, soit confirmation avec le nombre de personnes)
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
						break;
					case 1:
						semi.setEtatSemi("CON");
						List<Salle> lesSalles = Requetes.selectLesSalles(conn, semi.getNombrePersonneActuelle(), semi.getNumPerstataire());
						List<Integer> lesIdSalle = null;
						for(Salle s : lesSalles){
							System.out.println(s.toString());
							lesIdSalle.add(s.getNumSalle());
						}
						int UneSalle = InsertionScanner.saisirEntier(lesIdSalle, true, "Saisir le numéro d'une Salle :");
						Requetes.updateSalle(conn, UneSalle, semi.getNumPerstataire());
						break;
					case 2:
				}
			}
		}
	}
		
	//• bilan budgétaire si le séminaire a lieu
	private void blilanSeminaire(List<Seminaire> seminaires){
		
	}

}
