package imagein.entities;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Projet {

	private int id;
	private String nom;
	private int nbJourPrevus;
	private LocalDate dateDebutPrevue;
	private LocalDate dateDebutEffective;
	private LocalDate dateFinPrevue;
	private LocalDate dateFinEffective;

	private Intervenant intervenant;
	private ArrayList<Affectation> affectations;

	public Projet(int id, String nom, int nbJourPrevus, LocalDate dateDebutPrevue, LocalDate dateDebutEffective,
			LocalDate dateFinPrevue, LocalDate dateFinEffective, Intervenant intervenant) {

		this.id = id;
		this.nom = nom;
		this.nbJourPrevus = nbJourPrevus;
		this.dateDebutPrevue = dateDebutPrevue;
		this.dateDebutEffective = dateDebutEffective;
		this.dateFinPrevue = dateFinPrevue;
		this.dateFinEffective = dateFinEffective;
		this.intervenant = intervenant;

		this.affectations = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNbJourPrevus() {
		return nbJourPrevus;
	}

	public void setNbJourPrevus(int nbJourPrevus) {
		this.nbJourPrevus = nbJourPrevus;
	}

	public LocalDate getDateDebutEffective() {
		return dateDebutEffective;
	}

	public void setDateDebutEffective(LocalDate dateDebutEffective) {
		this.dateDebutEffective = dateDebutEffective;
	}

	public LocalDate getDateDebutPrevue() {
		return dateDebutPrevue;
	}

	public void setDateDebutPrevue(LocalDate dateDebutPrevue) {
		this.dateDebutPrevue = dateDebutPrevue;
	}

	public LocalDate getDateFinEffective() {
		return dateFinEffective;
	}

	public void setDateFinEffective(LocalDate dateFinEffective) {
		this.dateFinEffective = dateFinEffective;
	}

	public LocalDate getDateFinPrevue() {
		return dateFinPrevue;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setDateFinPrevue(LocalDate dateFinPrevue) {
		this.dateFinPrevue = dateFinPrevue;
	}

	private long getDureePrevue() {
		return ChronoUnit.DAYS.between(dateDebutPrevue, dateFinPrevue);
	}

	private long getDureeEffective() {
		return ChronoUnit.DAYS.between(dateDebutEffective, dateFinEffective);
	}

	public long getRetardProjet() {
		return getDureeEffective() - getDureePrevue();
	}

	public int getNombreIntervenants() {
		if (affectations == null) {
			return 0;
		}

		Set<Intervenant> intervenantsUniques = new HashSet<>();
		for (Affectation a : affectations) {
			intervenantsUniques.add(a.getIntervenant());
		}

		return intervenantsUniques.size();
	}

	public int getTempsPasseParIntervenant(String nomIntervenant) {
		if (affectations == null) {
			return 0;
		}

		int tempsTotal = 0;
		for (Affectation a : affectations) {
			if (a.getIntervenant().getNom().equals(nomIntervenant)) {
				tempsTotal += a.getTempsPasse();
			}
		}

		return tempsTotal;
	}

}
