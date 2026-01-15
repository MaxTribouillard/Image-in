package imagein.entities;

import java.util.ArrayList;

public class Categorie {

	private int id;
	private String nom;

	private ArrayList<Intervenant> intervenants;

	public Categorie() {

	}

	public Categorie(int id, String nom) {

		this.id = id;
		this.nom = nom;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void addIntervenant(Intervenant intervenant) {
		if (intervenants == null) {
			intervenants = new ArrayList<Intervenant>();
		}
		intervenants.add(intervenant);
	}

	public int getNbIntervenant() {
		if (intervenants == null) {
			intervenants = new ArrayList<Intervenant>();
		}
		return intervenants.size();
	}

}
