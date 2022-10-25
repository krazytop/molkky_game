package beans;

public class BeanEquipe {
	private String nom;
	private boolean tour = false;

	public BeanEquipe(String nom) {
		this.nom = nom;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public boolean isTour() {
		return tour;
	}
	public void setTour(boolean tour) {
		this.tour = tour;
	}
	
}
