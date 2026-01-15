package imagein.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import imagein.entities.Affectation;
import imagein.entities.Categorie;
import imagein.entities.Intervenant;
import imagein.entities.Projet;

class CategorieTest {

	private Categorie categorie;
	private Intervenant inter1;
	private Intervenant inter2;
	private Projet projet1;

	private Projet projet2;
	private Affectation affect1;
	private Affectation affect2;

	@BeforeEach
	void setUp() {
		categorie = new Categorie(1, "Développeur");
		inter1 = new Intervenant(1, "Benjamain", "Boniface");
		inter2 = new Intervenant(2, "Marius", "Margueray");
		projet1 = new Projet(1, "Projet1", 30, LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 3),
				LocalDate.of(2026, 1, 31), LocalDate.of(2026, 2, 10), inter1);

		projet2 = new Projet(2, // id
				"Projet2", 20, // nbJourPrevus
				LocalDate.of(2026, 2, 1), // dateDebutPrevue
				LocalDate.of(2026, 2, 1), // dateDebutEffective
				LocalDate.of(2026, 2, 21), // dateFinPrevue (20 jours)
				LocalDate.of(2026, 3, 3), // dateFinEffective (30 jours)
				new Intervenant(101, "Bob", "Martin"));

		affect1 = new Affectation(25, 16, 20, inter1, projet1);
		affect2 = new Affectation(25, 16, 20, inter1, projet2);
		categorie.addIntervenant(inter1);
		categorie.addIntervenant(inter2);
		inter1.addProjet(projet1);
		inter1.addProjet(projet2);
		inter1.addAffectation(affect1);
		inter1.addAffectation(affect2);

	}

	@Test
	void testGetNbIntervenant() {
		assertEquals(2, categorie.getNbIntervenant());
	}

	@Test
	void testGetNbProjet() {
		assertEquals(2, inter1.getNbProjets());
	}

	@Test
	void testNbDureeProjet() {
		assertEquals(30, projet1.getNbJourPrevus());
	}

	@Test
	void testNbJourRetard() {
		assertEquals(10, projet2.getRetardProjet());
	}

	@Test
	void getNbProjetIntervenant() {
		assertEquals(2, inter1.getNbProjetTravailler());
	}

	@Test
	void getTempsPassee() {
		assertEquals(20, inter1.getTempsPassee("Projet1"));
	}

//	@Test
//	void testGetNbProjet() {
//		assertEquals(1, inter1.getNbProjets());
//	}
}
