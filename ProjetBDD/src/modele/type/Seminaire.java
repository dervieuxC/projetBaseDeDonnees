package modele.type;

import java.util.List;

/**
 * 
 * @author Thibaut Masselin
 *
 */
public class Seminaire {
	
	//Attributs
	private int numAnimateur;
	private int numTheme;
	private String dateString;
	private int dureeSemi;
	private List<Integer> lesActivites;
	private List<Conferencier> lesConferenciers;
	private int nombrePlace;
	private float prixUnePlace;
	private int numPerstataire;
    
	private float recettePrevuMin;
	private float recettePrevuMax;
	private float depencePrevuMin;
	private float depencePrevuMax;
	
	//Constructeur
	public Seminaire(){}

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

	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
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

	public void setLesConferenciers(List<Conferencier> lesConferenciers) {
		this.lesConferenciers = lesConferenciers;
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
	
	
}
