package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


/**
 * 
 * @author Thibaut Masselin
 *
 */
public class InsertionScanner {
	
	private static Scanner sc;
	
	private InsertionScanner(){}
	
	/**
	 * @info Permet de selectionner la fonctionnalit�e voulut par
	 * 		l'utilisateur.
	 * 
	 * @param min est la born inf�rieur
	 * @param max est la born sup�rieur
	 * @param message est � destination de l'utilisateur
	 * @return Un entier entre les bornes min et max.
	 */
	public static int choixBorner(int min,int max,String message){
		try{
			sc = new Scanner(System.in);
			int value;
			System.out.println(message);
			while(true){
				try{
					String str = sc.nextLine();
					value = Integer.parseInt(str.trim());
					if( value >= min && value <= max ){
						return value;
					}
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
	 * @info La m�thode ne g�re pas la validit� des date dans le temps
	 * 		c'est-�-dire le faite de pouvoir rentrer une date ant�rieur
	 * 		� la date actuelle 
	 * 
	 * @param message est � destination de l'utilisateur
	 * @return la date sous form d'un string correctement fom�
	 */
	public static String DateString(String message){
		try{
			sc = new Scanner(System.in);
			
			System.out.println(message);
			
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
			
			int mois , jour,annee;
			
	        // Selectionne le mois
	        mois = InsertionScanner.choixBorner(1,12,"entrez le mois (1 - 12): ");
	        // pour cona�tre le nombre de jour du mois selectionner
	        int maxJour = 31;
	        if(mois == 2){
	        	maxJour = 28;
	        }else if(mois == 4 || mois == 6 || mois == 9 || mois == 11){
	        	maxJour = 30;
	        }
	        
	        // Selectionne le jour
	        jour = InsertionScanner.choixBorner(1,maxJour,"entrez le jour (1 - "+maxJour+"): ");
	        
	        // Selectionne l'ann�e
	        System.out.println("entrez une ann�e sup�rieur ou �gal � 2018: ");
	        while(true){ 
		        try{
					String str = sc.nextLine();
					annee = Integer.parseInt(str.trim());
					if(annee >= 2018){break;}
		        }catch(Exception e){
					System.err.println("Exception lever :" + e.getMessage());
				}
		        System.err.println("Erreur de saisie !");
	        }
	        
	        // assemblage de la date
	        String str = jour+"-"+lesMois.get(mois)+"-"+String.valueOf(annee).substring(2, 4);
	        
			return str;
		}finally{
			sc.close();
		}
	}
	
	/**
	 * @info C'est une demande faite � l'utilisteur pour rentrer un 
	 * 		 entier.
	 * 
	 * !!!!!! A revoir au niveau du traitement en cr�er une autre 
	 * 		  m�thode avec plus de param�tre pour plus de sp�cification !!!!!
	 * 
	 * @param message est � destination de l'utilisateur
	 * @return Le choix de l'utilisteur
	 */
	public static int choixPoposition(String message){
		try{
			sc = new Scanner(System.in);
			System.out.println(message);
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
		}finally{
			sc.close();
		}
	}
	
	/**
	 * @info Demmande a l'utilisateur de s�lectionner des activit�es en fonction
	 * 		du param�tre dureeJour
	 * 
	 * @param dureeJour 
	 * @param message est � destination de l'utilisateur
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
			lesActivitees.add(InsertionScanner.choixPoposition("- Activit� n�" +i+" : "));
		}
        return lesActivitees;
	}

	
}
