package application;

import connexion.ConnexionBdd;
import modele.*;

public class Start {

	public static void main(String[] args) {
		try{
			boolean continuer = true;
			do{
				ActionSeminaire semi = null;
				
		        int choix = InsertionScanner.saisirEntier(1,5,"Choisir une commende :\n"
		        		+ " - 1 : Création d'un séminaire\n"
		        		+ " - 2 : Incription à un séminaire\n"
		        		+ " - 3 : Confimation à un séminaire\n"
		        		+ " - 4 : Le planning des Séminaire\n"
		        		+ " - 5 : Quitter");
		        
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
		            
		        case 5 :
		        	continuer = false;
		        }
		        
		        if(continuer){
		        	semi.action(ConnexionBdd.getConnexion());
		        }
			}while(continuer);
		}finally{
			ConnexionBdd.closeConnexion();
		}
	}

}
