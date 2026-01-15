package imagein.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import imagein.entities.Affectation;
import imagein.entities.Prestataire;
import imagein.entities.Projet;
import imagein.entities.Salarie;
import imagein.entities.Societe;

public class CoutProjetTest {

	private Salarie salarie;
	private Prestataire prestataire;
	private Projet projet1;
	private Projet projet2;
	private Societe societe;

	@BeforeEach
	public void setUp() {
		// Création d'une société avec un coût de 10€/minute
		societe = new Societe(1, "TechCorp", 10);

		// Création d'un salarié avec un coût de 5€/minute
		salarie = new Salarie(LocalDate.of(2020, 1, 1), 3);
		salarie.setId(1);
		salarie.setNom("Dupont");
		salarie.setPrenom("Jean");
		salarie.setCout(5); // Coût de 5€/minute

		// Création d'un prestataire avec coût minute de 15€/minute
		prestataire = new Prestataire(500, 15, societe);
		prestataire.setId(2);
		prestataire.setNom("Martin");
		prestataire.setPrenom("Marie");

		// Création de deux projets
		projet1 = new Projet(1, "Projet Alpha", 30, 
				LocalDate.of(2024, 1, 1), 
				LocalDate.of(2024, 1, 1),
				LocalDate.of(2024, 2, 1), 
				LocalDate.of(2024, 2, 1), 
				null);

		projet2 = new Projet(2, "Projet Beta", 20, 
				LocalDate.of(2024, 3, 1), 
				LocalDate.of(2024, 3, 1),
				LocalDate.of(2024, 4, 1), 
				LocalDate.of(2024, 4, 1), 
				null);
	}

	@Test
	public void testCoutProjetSalarie() {
		// Scénario : Le salarié travaille 100 minutes sur le Projet Alpha
		// Coût attendu : 100 * 5 = 500€

		// Création des affectations
		Affectation aff1 = new Affectation(2024, 1, 60, salarie, projet1); // 60 minutes semaine 1
		Affectation aff2 = new Affectation(2024, 2, 40, salarie, projet1); // 40 minutes semaine 2

		salarie.addAffectation(aff1);
		salarie.addAffectation(aff2);

		// Calcul du coût total
		int coutTotal = salarie.getCoutProjet("Projet Alpha");

		// Vérification : 100 minutes * 5€/min = 500€
		assertEquals(500, coutTotal, "Le coût du projet pour le salarié devrait être 500€");
	}

	@Test
	public void testCoutProjetSalarieMultiplesProjets() {
		// Scénario : Le salarié travaille sur deux projets différents
		// Projet Alpha : 80 minutes, Coût attendu : 400€
		// Projet Beta : 50 minutes

		Affectation aff1 = new Affectation(2024, 1, 50, salarie, projet1);
		Affectation aff2 = new Affectation(2024, 2, 30, salarie, projet1);
		Affectation aff3 = new Affectation(2024, 3, 50, salarie, projet2);

		salarie.addAffectation(aff1);
		salarie.addAffectation(aff2);
		salarie.addAffectation(aff3);

		int coutProjet1 = salarie.getCoutProjet("Projet Alpha");
		int coutProjet2 = salarie.getCoutProjet("Projet Beta");

		assertEquals(400, coutProjet1, "Le coût du Projet Alpha devrait être 400€");
		assertEquals(250, coutProjet2, "Le coût du Projet Beta devrait être 250€");
	}

	@Test
	public void testCoutProjetPrestataire() {
		// Scénario : Le prestataire travaille 120 minutes sur le Projet Alpha
		// Coût attendu : 120 * 15 = 1800€ (tarif prestataire)

		Affectation aff1 = new Affectation(2024, 1, 70, prestataire, projet1);
		Affectation aff2 = new Affectation(2024, 2, 50, prestataire, projet1);

		prestataire.addAffectation(aff1);
		prestataire.addAffectation(aff2);

		int coutTotal = prestataire.getCoutProjetPresta("Projet Alpha");

		// Vérification : 120 minutes * 15€/min = 1800€
		assertEquals(1800, coutTotal, "Le coût du projet pour le prestataire devrait être 1800€");
	}

	@Test
	public void testCoutProjetPrestataireForfait() {
		// Scénario : Calcul avec le coût de la société (forfait)
		// Coût attendu : 120 minutes * 10€/min = 1200€ (tarif société)

		Affectation aff1 = new Affectation(2024, 1, 70, prestataire, projet1);
		Affectation aff2 = new Affectation(2024, 2, 50, prestataire, projet1);

		prestataire.addAffectation(aff1);
		prestataire.addAffectation(aff2);

		int coutForfait = prestataire.getCoutProjetForfait("Projet Alpha");

		// Vérification : 120 minutes * 10€/min = 1200€
		assertEquals(1200, coutForfait, "Le coût forfait du projet devrait être 1200€");
	}

	@Test
	public void testCoutProjetPrestataireDifferencesForfaitVsPresta() {
		// Scénario : Comparaison entre tarif prestataire et tarif forfait société
		// 100 minutes de travail
		// Coût prestataire : 100 * 15 = 1500€
		// Coût forfait : 100 * 10 = 1000€

		Affectation aff1 = new Affectation(2024, 1, 100, prestataire, projet1);
		prestataire.addAffectation(aff1);

		int coutPresta = prestataire.getCoutProjetPresta("Projet Alpha");
		int coutForfait = prestataire.getCoutProjetForfait("Projet Alpha");

		assertEquals(1500, coutPresta, "Le coût prestataire devrait être 1500€");
		assertEquals(1000, coutForfait, "Le coût forfait devrait être 1000€");
		
		// Le coût prestataire devrait être supérieur au coût forfait
		assertTrue(coutPresta > coutForfait, "Le coût prestataire devrait être supérieur au coût forfait");
	}

	@Test
	public void testCoutProjetSansAffectation() {
		// Scénario : Un intervenant n'a aucune affectation sur un projet
		// Coût attendu : 0€

		int coutSalarie = salarie.getCoutProjet("Projet Inexistant");
		int coutPrestataire = prestataire.getCoutProjetPresta("Projet Inexistant");

		assertEquals(0, coutSalarie, "Le coût devrait être 0 pour un projet sans affectation (salarié)");
		assertEquals(0, coutPrestataire, "Le coût devrait être 0 pour un projet sans affectation (prestataire)");
	}

	@Test
	public void testComparaisonCoutsSalarieVsPrestataire() {
		// Scénario : Même temps passé (100 minutes) sur le même projet
		// Salarié : 100 * 5 = 500€
		// Prestataire : 100 * 15 = 1500€

		Affectation affSalarie = new Affectation(2024, 1, 100, salarie, projet1);
		Affectation affPrestataire = new Affectation(2024, 1, 100, prestataire, projet1);

		salarie.addAffectation(affSalarie);
		prestataire.addAffectation(affPrestataire);

		int coutSalarie = salarie.getCoutProjet("Projet Alpha");
		int coutPrestataire = prestataire.getCoutProjetPresta("Projet Alpha");

		assertEquals(500, coutSalarie, "Le coût du salarié devrait être 500€");
		assertEquals(1500, coutPrestataire, "Le coût du prestataire devrait être 1500€");
		
		// Le prestataire coûte plus cher que le salarié
		assertTrue(coutPrestataire > coutSalarie, "Le prestataire devrait coûter plus cher que le salarié");
	}
}
