package handlers;

import adaptateur.OutilsBaseDonneesTomates;
import modele.LigneCommande;
import modele.Panier;
import modele.Tomates;

public class CommandHandler {

    private final Panier panier;
    private final Tomates tomates;

    public CommandHandler() {
        panier = new Panier();
        tomates = OutilsBaseDonneesTomates.générationBaseDeTomates(
                OutilsBaseDonneesTomates.cheminFichier
        );
    }

    public void ajouterTomateAuPanier(String identifiantTomate, int quantite) {

        var tomate = tomates.getTomate(identifiantTomate);
        panier.addLigne(new LigneCommande(tomate, quantite));

    }

    public Panier getPanier() {
        return panier;
    }

    public int consulterStock(String identifiantTomate) {
        var x = tomates.getTomate(identifiantTomate);
        return x.getStock();

    }

    public void ajouterTomateAuPanierSafe(String id, int i) {
      var stockDispo = consulterStock(id);
      if (stockDispo == 0)
          return;
      if ( i > stockDispo) {
          ajouterTomateAuPanier(id, stockDispo);
          return;
      }
      ajouterTomateAuPanier(id, i);

    }
}
