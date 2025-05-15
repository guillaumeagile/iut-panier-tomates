package useCases;

import org.junit.Test;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class panierTests {

    @Test
    public void testAjoutProduit() {

     UseCasesDuPanier sut = new UseCasesDuPanier();
     boolean actual = sut.ajouterProduit("test", 1);

     assertTrue(actual);

   //  List<modele.LigneDeCommande> panier = sut.GetPanier();

    }
}
