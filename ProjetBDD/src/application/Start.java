package application;

import connexion.ConnexionBdd;
import modele.*;

public class Start {

	public static void main(String[] args) {
		try{
			ActionSeminaire semi;
			
	        int choix = InsertionScanner.choixBorner(1,4,"Choisir une commende :\n"
	        		+ " - 1 : cr�ation d'un s�minaire\n"
	        		+ " - 2 : Incription � un s�minaire\n"
	        		+ " - 3 : Confimation � un s�minaire\n"
	        		+ " - 4 : Le planning des S�minaire");
	        
	        switch(choix) {
	        case 1 :
	           semi = new Creation();
	           break;
	        
	        case 2 :
	        	semi = new Inscription();
	           break;
	        
	        case 3 :
	        	semi = new Confirmation();
	            break;
	        
	        case 4 :
	        	semi = new Planning();
	            break;
	        default :
	        	semi = null;
	        	// Exception
	        }
	        
	        semi.action(ConnexionBdd.getConnexion());
		}finally{
			ConnexionBdd.closeConnexion();
		}
	}

}
