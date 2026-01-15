package imagein.entities;

import java.time.LocalDate;

public class Salarie extends Intervenant {

	private LocalDate dtEmbauche;
	private int echelon;
	private int cout; // Cout par minute

	public Salarie() {

	}

	public Salarie(LocalDate dtEmbauche, int echelon) {
		super();
		this.dtEmbauche = dtEmbauche;
		this.echelon = echelon;
	}

	public LocalDate getDtEmbauche() {
		return dtEmbauche;
	}

	public void setDtEmbauche(LocalDate dtEmbauche) {
		this.dtEmbauche = dtEmbauche;
	}

	public int getEchelon() {
		return echelon;
	}

	public void setEchelon(int echelon) {
		this.echelon = echelon;
	}

	public int getCout() {
		return cout;
	}

	public void setCout(int cout) {
		this.cout = cout;
	}

	@Override
	public int getNbProjets() {
		// TODO Auto-generated method stub
		return super.getNbProjets();
	}

	@Override
	public int getNbProjetTravailler() {
		// TODO Auto-generated method stub
		return super.getNbProjetTravailler();
	}

	@Override
	public int getTempsPassee(String nomProjet) {
		// TODO Auto-generated method stub
		return super.getTempsPassee(nomProjet);
	}

	public int getCoutProjet(String nomProjet) {
		int temps = this.getTempsPassee(nomProjet);
		return temps * this.cout;
	}

}
