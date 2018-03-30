package application;

import java.util.Scanner;

import connexion.ConnexionBdd;
import modele.*;

public class Start {

	public static void main(String[] args) {
		try{
			ActionSeminaire semi;
			
			
			Scanner sc = new Scanner(System.in);
	        int choix;
	        System.out.println("Choisir ");
	        choix = sc.nextInt();
	        
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
