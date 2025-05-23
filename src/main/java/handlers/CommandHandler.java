package handlers;

import adaptateur.OutilsBaseDonneesTomates;
import modele.LigneCommande;
import modele.Panier;
import modele.Tomates;

public class CommandHandler {

    private final Panier _panier;
    private final Tomates _tomates;

    public CommandHandler() {
        _panier = new Panier();
        _tomates =  OutilsBaseDonneesTomates.générationBaseDeTomates(
                OutilsBaseDonneesTomates.cheminFichier
        );
    }


    public void ajouterTomateAuPanier(String identifiantTomate, int quantite) {

        var tomate = _tomates.getTomate(identifiantTomate);
        _panier.addLigne(new LigneCommande(tomate, quantite));

    }

    public Panier getPanier() {
        return _panier;
    }
}
