package imagein.entities;

public class Affectation {

	private int annee;
	private int semaine;
	private int tempsPasse;

	private Intervenant intervenant;
	private Projet projet;

	public Affectation(int annee, int semaine, int tempsPasse, Intervenant intervenant, Projet projet) {
		this.annee = annee;
		this.semaine = semaine;
		this.tempsPasse = tempsPasse;
		this.intervenant = intervenant;
		this.projet = projet;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public Intervenant getIntervenant() {
		return intervenant;
	}

	public void setIntervenant(Intervenant intervenant) {
		this.intervenant = intervenant;
	}

	public Projet getProjet() {
		return projet;
	}

	public void setProjet(Projet projet) {
		this.projet = projet;
	}

	public int getSemaine() {
		return semaine;
	}

	public void setSemaine(int semaine) {
		this.semaine = semaine;
	}

	public int getTempsPasse() {
		return tempsPasse;
	}

	public void setTempsPasse(int tempsPasse) {
		this.tempsPasse = tempsPasse;
	}

}
