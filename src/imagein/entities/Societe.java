package imagein.entities;

import java.util.ArrayList;

public class Societe {

	private int id;
	private String nom;
	private int coutMinute;

	private ArrayList<Salarie> salaries;

	public Societe() {

	}

	public Societe(int i, String nom, int coutMinute) {
		this.id = i;
		this.nom = nom;
		this.coutMinute = coutMinute;
	}

	public int getCoutMinute() {
		return coutMinute;
	}

	public int getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public void addSalarie(Salarie salarie) {
		if (salaries == null) {
			salaries = new ArrayList<Salarie>();
		}
		salaries.add(salarie);
	}

}
