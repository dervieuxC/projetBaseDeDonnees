package modele.type;
/**
 * 
 * @author Thibaut Masselin
 *
 */
public class Salle {
	private int numPrestataire;
	private String libelle;
	private int numSalle;
	private float prix;
	private int nombrePlace;
	
	public Salle(){}
	
	public String toString(){
		return "- "+ numSalle+ " :" + libelle + " | prix:"+prix+" nbPlace:" + nombrePlace;
	}
	
	public int getNumPrestataire() {
		return numPrestataire;
	}
	public void setNumPrestataire(int numPrestataire) {
		this.numPrestataire = numPrestataire;
	}
	public int getNumSalle() {
		return numSalle;
	}
	public void setNumSalle(int numSalle) {
		this.numSalle = numSalle;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	public int getNombrePlace() {
		return nombrePlace;
	}
	public void setNombrePlace(int nombrePlace) {
		this.nombrePlace = nombrePlace;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
}
