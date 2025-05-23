package tests.useCases;

import handlers.CommandHandler;
import modele.LigneCommande;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CommandHandlerTests {

    @Test
    public void consulterStockTest() {
        var sut = new CommandHandler();
        Assertions.assertEquals(10,
                sut.consulterStock("Tomate Mirabelle Blanche"));
    }

    @ Test
  public void ajouterTomateAuPanier() {

        var sut = new CommandHandler();

        var panier = sut.getPanier();
        Assertions.assertEquals(0, panier.getContenu().size());

        sut.ajouterTomateAuPanier("Tomate Mirabelle Blanche", 1);

         panier = sut.getPanier();

        Assertions.assertEquals(1, panier.getContenu().size());

        Assertions.assertEquals(1,  panier.getContenu().get(0).getQuantité());

        Assertions.assertEquals("Tomate Mirabelle Blanche",
                panier.getContenu().get(0).getArticle().getDésignation());

        Assertions.assertEquals(3.95F, panier.prixTTC(), 0F);

        Assertions.assertEquals(9,
            sut.consulterStock("Tomate Mirabelle Blanche"));
  }

    @ Test
    public void ajouterTropPlusDeTomatesQueDisponibleDansLeStock() {

        var sut = new CommandHandler();

        var panier = sut.getPanier();

        sut.ajouterTomateAuPanierSafe("Tomate Mirabelle Blanche", 11);

        panier = sut.getPanier();

        Assertions.assertEquals(1, panier.getContenu().size());

        Assertions.assertEquals(10,  panier.getContenu().get(0).getQuantité());

        Assertions.assertEquals("Tomate Mirabelle Blanche",
                panier.getContenu().get(0).getArticle().getDésignation());

        Assertions.assertEquals(39.5F, panier.prixTTC(), 0F);

        Assertions.assertEquals(0,
                sut.consulterStock("Tomate Mirabelle Blanche"));


    }

    @Test
    public void ajouterLigneDeCommandeAvecBuild() {
        var actual = LigneCommande.Build("Tomate Mirabelle Blanche", 1);
        Assertions.assertEquals("Tomate Mirabelle Blanche, Prix TTC : 3.95 €, quantité commandée : 1, Sous-Total : 3.95 €",
                actual.isPresent());
    }

}
