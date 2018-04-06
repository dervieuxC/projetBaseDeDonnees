package modele.type;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import application.InsertionScanner;

/**
 * 
 * @author Thibaut Masselin
 *
 */
public class Seminaire {
	
	//Attributs
	private int numSeminaire;
	private int numAnimateur;
	private int numTheme;
	private String libelle;
	private int NumSalle;
	private Date dateString;
	
	private String etatSemi; 
	
	private int dureeSemi;
	private List<Integer> lesActivites;
	private List<Conferencier> lesConferenciers;
	
	private int nombrePlace;
	private int nombrePersonneActuelle;
	
	private float prixUnePlace;
	private int numPerstataire;
	private int numRepas;
    
	private float recettePrevuMin;
	private float recettePrevuMax;
	private float depencePrevuMin;
	private float depencePrevuMax;
	
	//Constructeur
	public Seminaire(){
		this.lesConferenciers = new ArrayList<>();
		// Valeurs par défaut
		this.recettePrevuMin = 0.0f;
		this.recettePrevuMax = 0.0f;
		this.depencePrevuMin = 0.0f;
		this.depencePrevuMax = 0.0f;
	}
	
	//GETTER - SETTER
	
	
	public int getNumAnimateur() {
		return numAnimateur;
	}

	public void setNumAnimateur(int numAnimateur) {
		this.numAnimateur = numAnimateur;
	}

	public int getNumTheme() {
		return numTheme;
	}

	public void setNumTheme(int numTheme) {
		this.numTheme = numTheme;
	}

	public Date getDate() {
		return dateString;
	}

	@SuppressWarnings("deprecation")
	public String getDateToString(){
		return dateString.getDate()+"-"+InsertionScanner.findMois(dateString.getMonth())+"-"+String.valueOf(dateString.getYear()).substring(2, 4);
	}
	
	public void setDate(Date dateString) {
		
		this.dateString = dateString;
	}
	
	@SuppressWarnings("deprecation")
	public void setDate(String dateString) {		
		this.dateString = new Date(dateString);
	}

	public int getDureeSemi() {
		return dureeSemi;
	}

	public void setDureeSemi(int dureeSemi) {
		this.dureeSemi = dureeSemi;
	}

	public List<Integer> getLesActivites() {
		return lesActivites;
	}

	public void setLesActivites(List<Integer> lesActivites) {
		this.lesActivites = lesActivites;
	}

	public List<Conferencier> getLesConferenciers() {
		return lesConferenciers;
	}

	public void addAllLesConferenciers(List<Conferencier> lesConferenciers) {
		this.lesConferenciers.addAll(lesConferenciers);
	}

	public int getNombrePlace() {
		return nombrePlace;
	}

	public void setNombrePlace(int nombrePlace) {
		this.nombrePlace = nombrePlace;
	}

	public float getPrixUnePlace() {
		return prixUnePlace;
	}

	public void setPrixUnePlace(float prixUnePlace) {
		this.prixUnePlace = prixUnePlace;
	}

	public int getNumPerstataire() {
		return numPerstataire;
	}

	public void setNumPerstataire(int numPerstataire) {
		this.numPerstataire = numPerstataire;
	}

	public float getRecettePrevuMin() {
		return recettePrevuMin;
	}

	public void setRecettePrevuMin(float recettePrevuMin) {
		this.recettePrevuMin = recettePrevuMin;
	}

	public float getRecettePrevuMax() {
		return recettePrevuMax;
	}

	public void setRecettePrevuMax(float recettePrevuMax) {
		this.recettePrevuMax = recettePrevuMax;
	}

	public float getDepencePrevuMin() {
		return depencePrevuMin;
	}

	public void setDepencePrevuMin(float depencePrevuMin) {
		this.depencePrevuMin = depencePrevuMin;
	}

	public float getDepencePrevuMax() {
		return depencePrevuMax;
	}

	public void setDepencePrevuMax(float depencePrevuMax) {
		this.depencePrevuMax = depencePrevuMax;
	}

	public int getNumSeminaire() {
		return numSeminaire;
	}

	public void setNumSeminaire(int numSeminaire) {
		this.numSeminaire = numSeminaire;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public int getNumRepas() {
		return numRepas;
	}

	public void setNumRepas(int numRepas) {
		this.numRepas = numRepas;
	}

	public int getNombrePersonneActuelle() {
		return nombrePersonneActuelle;
	}

	public void setNombrePersonneActuelle(int nombrePersonneActuelle) {
		this.nombrePersonneActuelle = nombrePersonneActuelle;
	}

	public String getEtatSemi() {
		return etatSemi;
	}

	public void setEtatSemi(String etatSemi) {
		this.etatSemi = etatSemi;
	}

	public int getNumSalle() {
		return NumSalle;
	}

	public void setNumSalle(int numSalle) {
		NumSalle = numSalle;
	}
	
}
