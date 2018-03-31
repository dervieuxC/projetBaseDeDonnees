package application;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


/**
 * 
 * @author Thibaut Masselin
 *
 * @info Class qui gère toutes les saisies de l'utilisateur dans l'application
 * 
 */
public class InsertionScanner {
	/**
	 * 
	 */
	private static Scanner sc;
	/**
	 * Constructeur private pour évité toute intanciation de l'objet
	 */
	private InsertionScanner(){}
	
	/**
	 * @info Permet de selectionner la fonctionnalitée voulut par
	 * 		l'utilisateur.
	 * 
	 * @request garentie la validitée de l'entier saisie
	 * 
	 * @param min est la born inférieur
	 * @param max est la born supérieur
	 * @param message est à destination de l'utilisateur
	 * @return Un entier entre les bornes min et max.
	 */
	public static int choixBorner(int min,int max,String message){
		try{
			sc = new Scanner(System.in);
			int value;
			System.out.println(message);
			while(true){
				try{
					System.out.print(">");
					String str = sc.nextLine();
					value = Integer.parseInt(str.trim());
					if( value >= min && value <= max ){
						return value;
					}
				}catch(Exception e){
					System.err.println("Exception lever :" + e.getMessage());
				}
				System.err.print("Erreur de saisie! ");
				System.out.println("| Saisir entre ("+min+" - "+max+")");
			}
		}finally{
			sc.close();
		}
    }
	
	/**
	 * @info Permet de saisir un décimal dans la console
	 * 
	 * @request garentie la validitée du décimal saisie
	 * 
	 * @param message est à destination de l'utilisateur
	 * @return Un décimal saisie
	 */
	public static float saisirDecimal(String message) {
		try{
			sc = new Scanner(System.in);
			float value;
			System.out.println(message);
			while(true){
				try{
					System.out.print(">");
					String str = sc.nextLine();
					value = Float.parseFloat(str.trim());
					return value;
				}catch(Exception e){
					System.err.println("Exception lever :" + e.getMessage());
				}
				System.err.print("Erreur de saisie! ");
			}
		}finally{
			sc.close();
		}
	}
	
	/**
	 * @info C'est une demande faite à l'utilisteur pour rentrer un 
	 * 		 entier.
	 * 
	 * !!!!!! A revoir au niveau du traitement en créer une autre 
	 * 		  méthode avec plus de paramètre pour plus de spécification !!!!!
	 * 
	 * @param message est à destination de l'utilisateur
	 * @return Le choix de l'utilisteur
	 */
	public static int saisirEntier(String message){
		try{
			sc = new Scanner(System.in);
			System.out.println(message);
			while(true){
				try{
					System.out.print(">");
				    String str = sc.nextLine();
					int value = Integer.parseInt(str.trim());
				    return value;
				}catch(Exception e){
					System.err.println("Exception lever :" + e.getMessage());
				}
				System.err.println("Erreur de saisie !");
			}
		}finally{
			sc.close();
		}
	}
	
	/**
	 * @info Permet à l'utilisateur de ne saisir que des date dans le futur
	 * 		 au momment où il saisie le formulaire
	 * 
	 * @param message est à destination de l'utilisateur
	 * @return la date sous form d'un string correctement fomé
	 */
	public static String DateString(String message){
		try{
			//recupérer les information sur la date actuelle
			Calendar calendar =new GregorianCalendar();
			calendar.setTime(new Date());
			int anneAct = calendar.get(Calendar.YEAR);
			int moisAct = calendar.get(Calendar.MONTH);
			int jourAct = calendar.get(Calendar.DAY_OF_MONTH);
			
			sc = new Scanner(System.in);
			
			System.out.println(message);
			int mois , jour,annee;
			
			// Selectionne l'année
	        System.out.println("entrez une année supérieur ou égal à "+anneAct+": ");
	        while(true){ 
		        try{
		        	System.out.print(">");
					String str = sc.nextLine();
					annee = Integer.parseInt(str.trim());
					if(annee >= anneAct){break;}
		        }catch(Exception e){
					System.err.println("Exception lever :" + e.getMessage());
				}
		        System.err.println("Erreur de saisie !");
	        }
			
	        // Selectionne le mois
	        int minMois = 1;
	        if(annee == anneAct){
	        	minMois = moisAct;
	        }
	        mois = InsertionScanner.choixBorner(minMois,12,"entrez le mois (1 - 12): ");
	        
	        // pour conaître le nombre de jour du mois selectionner
	        int maxJour = 31;
	        if(mois == 2){
	        	maxJour = 28;
	        }else if(mois == 4 || mois == 6 || mois == 9 || mois == 11){
	        	maxJour = 30;
	        }
	        
	        // Selectionne le jour
	        int minJour = 1;
	        if(annee == anneAct && mois == moisAct){
	        	minJour = jourAct;
	        }
	        jour = InsertionScanner.choixBorner(minJour,maxJour,"entrez le jour (1 - "+maxJour+"): ");

	        //pour formé la strcture correct de la date final
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
	        
	        // assemblage de la date
	        String str = jour+"-"+lesMois.get(mois)+"-"+String.valueOf(annee).substring(2, 4);
	        
			return str;
		}finally{
			sc.close();
		}
	}
	
	
	
	/**
	 * @info Demmande a l'utilisateur de sélectionner des activitées en fonction
	 * 		du paramètre dureeJour
	 * 
	 * @param dureeJour définit la période de temps
	 * @param message est à destination de l'utilisateur
	 * @return retourne une liste entier
	 */
	
	public static List<Integer> activiteSelected(int dureeJour, String message){
		List<Integer> lesActivitees = new ArrayList<>();
		int nbIter = 3;
		if(dureeJour == 2){
			nbIter = 6;
		}
		System.out.println(message); 
		for(int i = 0; i < nbIter;i++){
			lesActivitees.add(InsertionScanner.saisirEntier("- Activité n°" +i+" : "));
		}
        return lesActivitees;
	}

	
}
