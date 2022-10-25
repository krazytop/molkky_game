package beans;

public class BeanTir {
	BeanEquipe equipe;
	int valeur;
	
	public BeanTir(BeanEquipe equipe, int valeur) {
		super();
		this.equipe = equipe;
		this.valeur = valeur;
	}
	
	public BeanEquipe getEquipe() {
		return equipe;
	}
	
	public void setEquipe(BeanEquipe equipe) {
		this.equipe = equipe;
	}
	
	public int getValeur() {
		return valeur;
	}
	
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}
	
	

}
