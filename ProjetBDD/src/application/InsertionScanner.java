package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;



public class InsertionScanner {
	
	private static Scanner sc = new Scanner(System.in);
	
	private InsertionScanner(){}
	
	
	public static int choixFonctionnalite(int min,int max,String question){
		int t;
		System.out.println(question);
		while(true){
			t = sc.nextInt();
			if( t >= 0 && t <= 5 ){
				return t;
			}
			System.err.println("Erreur de saisie !");
		}
		 
        
    }
	
	public static String DateString(String info){
		
		System.out.println(info);
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
		
		int mois, jour,annee;
		
        // Selectionne le mois
        System.out.println("entrez le mois (1 - 12): ");
        while(true){
	        mois = sc.nextInt();
	        if(mois > 0 && mois <=12){break;}
	        else{System.err.println("Erreur de saisie !");
	        System.out.println("Merci de saisir entre les bornes (1 - 12)");}
        }
        
        // pour conaître le nombre de jour du mois selectionner
        int maxJour = 31;
        if(mois == 2){
        	maxJour = 28;
        }else if(mois == 4 || mois == 6 || mois == 9 || mois == 11){
        	maxJour = 30;
        }
        
        // Selectionne le jour
        System.out.println("entrez le jour (1 - "+maxJour+"): ");
        while(true){  
	        jour = sc.nextInt();
	        if(jour > 0 && jour <= maxJour){break;}
        }
        // Selectionne l'année
        while(true){
	        System.out.println("entrez l'année (2000 - 2015): ");
	        annee = sc.nextInt();
	        if(annee > 2000){break;}
        }
        
        // assemblage de la date
        String str = jour+"-"+lesMois.get(mois)+"-"+String.valueOf(annee).substring(2, 4);
		return str;	
	}
	
	public static int animateurNum(String question){
	    System.out.println(question);
	    return sc.nextInt();
	}

	public static int dureeSeminaire(String question){
		System.out.println(question + " (0 = matin | 1=après-midi | 2=journée) ");
		while(true){ 
			switch(sc.nextInt()) {
	        case 0 :
	        	return 0;	        
	        case 1 :
	        	return 1;	        
	        case 2 :
	        	return 2;
	        default:
	        	System.err.println("Mauvaise saisie !");
	        	System.out.println("0 = matin | 1=après-midi | 2=journée");
	        }
		}
	}
	
	public static List<Integer> activiteSelected(int dureeJour, String question){
		// 0 = matin
		// 1 = après-midi
		// 2 = journée entière
		
		List<Integer> lesActivitees = new ArrayList<>();
		int nbIter = 3;
		if(dureeJour == 2){
			nbIter = 6;
		}
		System.out.println(question); 
		for(int i = 0; i < nbIter;i++){
			lesActivitees.add(sc.nextInt());
		}
        return lesActivitees;
	}
}
