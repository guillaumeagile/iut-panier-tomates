package tests.modele;

import static org.junit.Assert.*;

import modele.LigneCommande;
import modele.OutilsBaseDonneesTomates;
import modele.Tomate;
import modele.Tomates;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestLigneDeCommande {
	
	private static Tomates tomates;
	
	@BeforeClass
	public static void setUp() throws Exception {
		tomates = OutilsBaseDonneesTomates.générationBaseDeTomates("src/main/resources/data/tomates.json");
	}

	@Test
	public void testArticleALUnitéQuantitéAUn() {
		Tomate tomate = tomates.getTomate("Tomate Mirabelle Blanche");
		LigneCommande uneLigne = new LigneCommande(tomate, 1);
		assertEquals("Tomate Mirabelle Blanche, Prix TTC : 3.95 €, quantité commandée : 1, Sous-Total : 3.95 €", uneLigne.toString());
	}
	
	@Test
	public void testArticleALUnitéQuantitéSuperieurAUn() {
		Tomate tomate = tomates.getTomate("Tomate Mirabelle Blanche");
		LigneCommande uneLigne = new LigneCommande(tomate, 3);
		assertEquals("Tomate Mirabelle Blanche, Prix TTC : 3.95 €, quantité commandée : 3, Sous-Total : 11.85 €", uneLigne.toString());
	}
	
}
