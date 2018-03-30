package application;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InsertionScanner {
	
	private static Scanner sc = new Scanner(System.in);
	
	private InsertionScanner(){}
	
	public static String DateString(){
		Map<Integer,String> lesMois = new HashMap<>();
		lesMois.put(1, "JAN");
		lesMois.put(2, "FEB");
		lesMois.put(3, "MAR");
		lesMois.put(4, "APR");
		lesMois.put(5, "MAY");
		lesMois.put(6, "JUN");
		lesMois.put(7, "JUL");
		lesMois.put(8, "AUG");
		lesMois.put(9, "SEP");
		lesMois.put(10, "OCT");
		lesMois.put(11, "NOV");
		lesMois.put(12, "DEC");
		
		int mois, jour,annee, maxJour = 31;
		
        // Selectionne le jour
        System.out.println("entrez le mois de la representation (1 - 12): ");
        while(true){
	        mois = sc.nextInt();
	        if(mois > 0 && mois <=12){break;}
	        else{System.err.println("Erreur de saisie !");
	        System.out.println("Merci de saisir entre les bornes (1 - 12)");}
        }
        if(mois == 2){
        	maxJour = 28;
        }else if(mois == 4 || mois == 6 || mois == 9 || mois == 11){
        	maxJour = 30;
        }
        // Selectionne le mois
        System.out.println("entrez le jour de la representation (1 - "+maxJour+"): ");
        while(true){  
	        jour = sc.nextInt();
	        if(jour > 0 && jour <= maxJour){break;}
        }
        // Selectionne l'année
        while(true){
	        System.out.println("entrez l'annee de la representation (2000 - 2015): ");
	        annee = sc.nextInt();
	        if(annee > 2000){break;}
        }
        String str = jour+"-"+lesMois.get(mois)+"-"+String.valueOf(annee).substring(2, 4);
		return str;	
	}
	public static int animateurNum(){
		int numAnimateur;
	    System.out.println("Choisir un numéro d'animateur :");
	    numAnimateur = sc.nextInt();
	    return numAnimateur;
	}
}
