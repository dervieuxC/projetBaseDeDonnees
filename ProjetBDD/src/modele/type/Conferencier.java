package modele.type;

public class Conferencier {
	private int numConferencier ;
	private String titre ;
	private String  transparents ;
	private float prixDePrestation ;
	
	public Conferencier(int numConferencier,String titre,String  transparents,float prixDePrestation){
		this.setNumConferencier(numConferencier);
		this.setTitre(titre);
		this.setTransparents(transparents);
		this.setPrixDePrestation(prixDePrestation);
	}

	//getter
	public int getNumConferencier() {
		return numConferencier;
	}
	public String getTitre() {
		return titre;
	}
	public float getPrixDePrestation() {
		return prixDePrestation;
	}
	public String getTransparents() {
		return transparents;
	}
	
	
	//setter
	private void setNumConferencier(int numConferencier) {
		this.numConferencier = numConferencier;
	}
	private void setTitre(String titre) {
		this.titre = titre;
	}
	private void setTransparents(String transparents) {
		this.transparents = transparents;
	}
	private void setPrixDePrestation(float prixDePrestation) {
		this.prixDePrestation = prixDePrestation;
	}
	
}
