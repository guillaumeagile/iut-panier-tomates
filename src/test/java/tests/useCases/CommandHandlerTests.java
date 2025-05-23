package tests.useCases;

import handlers.CommandHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CommandHandlerTests {

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

  }


}
