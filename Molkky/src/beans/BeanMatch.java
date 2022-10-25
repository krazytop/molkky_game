package beans;

public class BeanMatch {
	BeanEquipe equipe1;
	BeanEquipe equipe2;
	int scoreEquipe1 = 0;
	int scoreEquipe2 = 0;
	
	public BeanMatch(BeanEquipe equipe1, BeanEquipe equipe2) {
		this.equipe1 = equipe1;
		this.equipe2 = equipe2;
	}
	public BeanEquipe getEquipe1() {
		return equipe1;
	}
	public void setEquipe1(BeanEquipe equipe1) {
		this.equipe1 = equipe1;
	}
	public BeanEquipe getEquipe2() {
		return equipe2;
	}
	public void setEquipe2(BeanEquipe equipe2) {
		this.equipe2 = equipe2;
	}
	public int getScoreEquipe1() {
		return scoreEquipe1;
	}
	public void setScoreEquipe1(int scoreEquipe1) {
		this.scoreEquipe1 = scoreEquipe1 > 50 ? 25 : scoreEquipe1;
	}
	public int getScoreEquipe2() {
		return scoreEquipe2;
	}
	public void setScoreEquipe2(int scoreEquipe2) {
		this.scoreEquipe2 = scoreEquipe2 > 50 ? 25 : scoreEquipe2;
	}
	
	

}
