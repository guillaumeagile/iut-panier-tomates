package useCases;

import modele.LigneCommande;

import java.util.ArrayList;
import java.util.List;

public class UseCasesDuPanier {

    public boolean ajouterProduit(String produit, int quantite) {
        return true;
    }

    public List<LigneCommande> GetPanier() {
        return new ArrayList<>();
    }
}
