package imagein.test;

import imagein.entities.Categorie;
import imagein.entities.Intervenant;

public class CategorieTestNA {

	public static void main(String[] args) {

		Categorie cat = new Categorie(1, "categorie 1");

		Intervenant inter1 = new Intervenant(1, "Benjamain", "Boniface");
		Intervenant inter2 = new Intervenant(1, "Marius", "Margueray");

		cat.addIntervenant(inter1);
		cat.addIntervenant(inter2);

		System.out.println("nombre intervenants : " + cat.getNbIntervenant());

	}

}
