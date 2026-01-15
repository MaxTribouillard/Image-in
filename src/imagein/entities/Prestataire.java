package imagein.entities;

public class Prestataire extends Intervenant {

	private boolean forfait;
	private int coutMinute;

	private Societe societe;

	public Prestataire() {

	}

	public Prestataire(boolean forfait, int coutMinute, Societe societe) {
		this.forfait = forfait;
		this.coutMinute = coutMinute;
		this.societe = societe;
	}

	@Override
	public int getTempsPassee(String nomProjet) {
		// TODO Auto-generated method stub
		return super.getTempsPassee(nomProjet);
	}

	public int getCoutProjetForfait(String nomProjet) {
		int cout = this.societe.getCoutMinute();

		int temps = this.getTempsPassee(nomProjet);

		return temps * cout;
	}

	public int getCoutProjetPresta(String nomProjet) {
		return this.getTempsPassee(nomProjet) * this.coutMinute;
	}

	public int getCoutProjet(boolean forfait, String nomProjet) {
		if (forfait) {
			return this.getCoutProjetForfait(nomProjet);
		} else {
			return this.getCoutProjetPresta(nomProjet);
		}
	}

}
