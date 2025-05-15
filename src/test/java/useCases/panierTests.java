package useCases;

import org.junit.Test;
import java.util.List;
import modele.*;

import static org.junit.Assert.assertTrue;

public class panierTests {

    @Test
    public void testAjoutProduit() {

     UseCasesDuPanier sut = new UseCasesDuPanier();
       sut.ajouterProduit("test", 1);


     List<LigneCommande> panier = sut.GetPanier();

     assertTrue(panier.size() == 1);

    }
}
