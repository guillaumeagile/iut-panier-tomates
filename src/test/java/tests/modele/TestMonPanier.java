package tests.modele;

import static org.junit.Assert.*;

import adaptateur.OutilsBaseDonneesTomates;
import modele.*;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestMonPanier {
	
	private static Tomates tomates;
	private Panier panier;
	
	@BeforeClass
	public static void setUP() {
		tomates = OutilsBaseDonneesTomates.générationBaseDeTomates("src/main/resources/data/tomates.json");
	}

	@Before
	public void setUp() throws Exception {
		this.panier = new Panier();
		Tomate firstArticle = tomates.getTomate("Tomate Mirabelle Blanche");
		LigneCommande firstLigne = new LigneCommande(firstArticle, 3);
		this.panier.addLigne(firstLigne);
		Tomate secondArticle = tomates.getTomate("Tomate Andine Cornue");
		LigneCommande secondLigne = new LigneCommande(secondArticle, 1);
		this.panier.addLigne(secondLigne);
		Tomate thirdArticle = tomates.getTomate("Tomate Bonne Fée");
		LigneCommande thirdLigne = new LigneCommande(thirdArticle, 5);
		this.panier.addLigne(thirdLigne);
	}

	@After
	public void tearDown() throws Exception {
		this.panier = null;
	}

	@Test
	public void ajoutDeLignesDeCommande() {
		assertEquals(3, this.panier.getContenu().size());
		assertEquals(41.80F, this.panier.prixTTC(),0F);
		assertEquals(41.80F + 5.5F, this.panier.prixTotal(),0F);
	}
	
	@Test
	public void suppressionDuneLigneDeCommande() {
		this.panier.removeLigne(2);
		assertEquals(2, this.panier.getContenu().size());
		assertEquals(16.80F, this.panier.prixTTC(),0F);
		
		
	}

	@Test
	public void QuandJajouteUneLigneDeCommandeDontLArticleEstPrésentJeMetsAJourLaQuantitéCommandée() {
		Tomate forArticle = tomates.getTomate("Tomate Andine Cornue");
		LigneCommande forLigne = new LigneCommande(forArticle, 1);
		this.panier.addLigne(forLigne);
		assertEquals(3, this.panier.getContenu().size());
		assertEquals(2, this.panier.getContenu().get(1).getQuantité());
	}
}
