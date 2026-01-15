package imagein.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public abstract class Intervenant {

	private int id;
	private String nom;
	private String prenom;

	private Categorie categorie;

	private ArrayList<Projet> projets;

	private ArrayList<Affectation> affectations;

	public Intervenant() {

	}

	public Intervenant(int id, String nom, String prenom) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void addProjet(Projet projet) {
		if (projets == null) {
			projets = new ArrayList<Projet>();
		}
		projets.add(projet);
	}

	public int getNbProjets() {
		if (projets == null) {
			projets = new ArrayList<Projet>();
		}
		return projets.size();
	}

	public void addAffectation(Affectation affectation) {
		if (affectations == null) {
			affectations = new ArrayList<Affectation>();
		}
		affectations.add(affectation);
	}

	public void removeAffectation(Affectation affectation) {
		if (affectations == null) {
			affectations = new ArrayList<Affectation>();
		}
		affectations.remove(affectation);
	}

	public int getNbProjetTravailler() {
		if (affectations == null) {
			affectations = new ArrayList<Affectation>();
		}

		Set<Projet> projetsUnique = new HashSet<>();

		for (Affectation a : affectations) {
			projetsUnique.add(a.getProjet());
		}

		return projetsUnique.size();
	}

	public int getTempsPassee(String nomProjet) {
		int tempsPassee = 0;
		if (affectations == null) {
			affectations = new ArrayList<Affectation>();
		}

		for (Affectation a : affectations) {
			if (a.getProjet().getNom().equals(nomProjet)) {
				tempsPassee += a.getTempsPasse();
			}
		}

		return tempsPassee;
	}

}
