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
 * @info Class qui g�re toutes les saisies de l'utilisateur dans l'application
 * 
 */
public class InsertionScanner {
	
	/**
	 * Attribut
	 */
	private static Scanner sc = new Scanner(System.in);
	
	private static Map<Integer,String> lesMois = null;
	
	/**
	 * Constructeur private pour �viter toute intanciation de la class
	 */
	private InsertionScanner(){}
	
	/**
	 * C'est une demande faite � l'utilisteur pour rentrer un 
	 * entier par rapport a une liste de integer donn�e.
	 *  
	 * @param ints est une liste de ints
	 * @param appartient permet de savoir comment traiter la list 
	 * 		  TRUE == doit appartenier � la list
	 * 		  FALSE == ne doit pas appartenir � la list
	 * @param message affiche dans la console une indication 
	 * 		  � destination de l'utilisateur
	 * @return Un entier
	 */
	public static int saisirEntier(List<Integer> ints,boolean appartient,String message){
		System.out.println(message);
		int value;
		while(true){
				value = saisirEntier("");
				if(appartient){
					//TRUE
					if(ints.contains(value)){
						return value;
					}else{
						System.out.println("Cette valeur est inconnu");
					}
				}else{
					//FALSE
					if(ints.contains(value)){
						System.out.println("Cette valeur est d�j� connu");
					}else{
						return value;
					}
				}
			System.err.println("Erreur de saisie !");
		}
	}

	/**
	 * C'est une demande faite � l'utilisteur pour rentrer un 
	 * entier sans contrainte.
	 *  
	 * @param message affiche dans la console une indication 
	 * 		  � destination de l'utilisateur
	 * @return Un entier
	 */
	public static int saisirEntier(String message){
		if(!message.equals("")){System.out.println(message);}
		while(true){
			try{
			    String str = sc.nextLine();
				int value = Integer.parseInt(str.trim());
			    return value;
			}catch(Exception e){
				System.err.println("Exception lever :" + e.getMessage());
			}
			System.err.println("Erreur de saisie !");
		}
	}
	
	/**
	 * Permet de selectionner la fonctionnalit�e voulut par
	 * l'utilisateur.
	 * 
	 * @request garentie la validit�e de l'entier saisie
	 * 
	 * @param min est la born inf�rieur
	 * @param max est la born sup�rieur
	 * @param message affiche dans la console une indication 
	 * 		  � destination de l'utilisateur
	 * @return Un entier entre les bornes min et max.
	 */
	public static int saisirEntier(int min,int max,String message){
		int value;
		System.out.println(message);
		while(true){
				value = saisirEntier("");
				if( value >= min && value <= max ){
					return value;
				}
			System.err.println(" Saisir entre ("+min+" - "+max+")");
		}
    }
	
	/**
	 * C'est une demande faite � l'utilisteur pour rentrer un 
	 * entier �gale ou supp�rieur � suppEgal.
	 * 
	 * @param min est une borne minimum ou �gale
	 * @param message affiche dans la console une indication 
	 * 		  � destination de l'utilisateur
	 * @return Un entier supp�rieur � la borne min
	 */
	public static int saisirEntier(int min,String message){
		System.out.println(message);
		while(true){
				int value = saisirEntier("");
				if(value >= min){
					return value;
				}
			System.err.println(" Saisir une valeur supp�rieur � "+min);
		}
	}
	
	/**
	 * Permet de saisir un d�cimal dans la console sans contrainte
	 * 
	 * @request garentie la validit�e du d�cimal saisie
	 * 
	 * @param message affiche dans la console une indication 
	 * 		  � destination de l'utilisateur
	 * @return Un d�cimal saisie
	 */
	public static float saisirDecimal(String message) {
		float value;
		System.out.println(message);
		while(true){
			try{
				String str = sc.nextLine();
				value = Float.parseFloat(str.trim());
				return value;
			}catch(Exception e){
				System.err.println("Exception lever :" + e.getMessage());
			}
			System.err.print("Erreur de saisie! ");
		}
	}
	
	/**
	 * Permet � l'utilisateur de ne saisir que des date dans le futur
	 * au momment o� il saisie le formulaire
	 * 
	 * @param message affiche dans la console une indication 
	 * 		  � destination de l'utilisateur
	 * 
	 * @return Une cha�ne de caract�re qui repr�sente une
	 * 			date sous le format ORACLE `dd-MMM-yy`
	 */
	@SuppressWarnings("deprecation")
	public static Date DateStringOracle(String message){
		//recup�rer les information sur la date actuelle
		Calendar calendar =new GregorianCalendar();
		calendar.setTime(new Date());
		int anneAct = calendar.get(Calendar.YEAR);
		int moisAct = calendar.get(Calendar.MONTH)+1; // +1 pour le mois car Janvier == 0
		int jourAct = calendar.get(Calendar.DATE);
		
		System.out.println(message);
		int mois , jour,annee;
		
		// Selectionne l'ann�e
		annee = saisirEntier(anneAct,"Entrer une ann�e sup�rieur ou �gal � "+anneAct+": ");
        
		// pour connaitre le mois minimum possible
        int minMois = 1;
        if(annee == anneAct){
        	minMois = moisAct;
        }
        // Selectionne le mois
        mois = saisirEntier(minMois,12,"entrez le mois ("+minMois+" - 12): ");
        
        // pour cona�tre le nombre de jour du mois selectionner
        int maxJour = 31;
        if(mois == 2){
        	maxJour = 28;
        }else if(mois == 4 || mois == 6 || mois == 9 || mois == 11){
        	maxJour = 30;
        }
        
        // pour connaitre le jour minimum possible
        int minJour = 1;
        if(annee == anneAct && mois == moisAct){
        	minJour = jourAct;
        }
        
        // Selectionne le jour
        jour = saisirEntier(minJour,maxJour,"entrez le jour ("+minJour+" - "+maxJour+"): ");

        return new Date(annee,mois,jour); 
        
	}
	
	/**
	 * Permet de mettre la mois au format MMM
	 * @param i est un nombre entre (1 - 12)
	 * @return Une Chaine
	 */
	public static String findMois(int i) {
		getInitialisationMois();
		return lesMois.get(i);
	}
	
	/**
	 * Permet de cr�er la liste si elle n'a pas �t� cr�er avant
	 */
	private static void getInitialisationMois(){
		if(lesMois == null){
        	lesMois = new HashMap<>();    
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
        }
	}
	
	/**
	 * Permet de saisir une cha�ne de carat�re dans 
	 * la console
	 * 
	 * @param message affiche dans la console une indication 
	 * 		  � destination de l'utilisateur
	 * @return Une cha�ne de carat�re
	 */
	public static String saisirString(String message){
		System.out.println(message);
		String str = sc.nextLine();
		return str.trim();	
	}
	
	/**
	 * Demmande a l'utilisateur de s�lectionner des activit�es en fonction
	 * du param�tre dureeJour
	 * 
	 * @param dureeJour d�finit la p�riode de temps
	 * @param message affiche dans la console une indication 
	 * 		  � destination de l'utilisateur
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
			lesActivitees.add(InsertionScanner.saisirEntier("- Activit� n�" +i+" : "));
		}
        return lesActivitees;
	}

	
}
