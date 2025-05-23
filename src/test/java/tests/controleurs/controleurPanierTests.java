package tests.controleurs;

import controleurs.ControlleurDuPanier;
import modele.LigneCommande;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class controleurPanierTests {

    @Test
    public void parDefautLePanierEstVide() {

        ControlleurDuPanier sut = new ControlleurDuPanier();

        List<LigneCommande> panier = sut.GetPanier();

        assertTrue(panier.size() == 0);

    }


    @Test
    public void testAjoutProduit() {

     ControlleurDuPanier sut = new ControlleurDuPanier();
       sut.ajouterProduit("test", 1);

     List<LigneCommande> panier = sut.GetPanier();

     assertTrue(panier.size() == 1);

    }
}
