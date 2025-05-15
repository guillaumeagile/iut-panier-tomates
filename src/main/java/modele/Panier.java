package modele;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Représente un panier d'achat contenant des lignes de commande pour des
 * articles de type Tomate.
 */
public class Panier {

    /**
     * Prix de transport fixe pour la France.
     */
    public static float PRIX_TRANSPORT_FRANCE = 5.5F;

    private List<LigneCommande> lignes;

    /**
     * Constructeur par défaut initialisant un panier vide.
     */
    public Panier() {
        this.lignes = new ArrayList<LigneCommande>();
    }

    /**
     * Vide le panier en annulant toutes les lignes de commande.
     */
    public void vider() {
        for (LigneCommande ligne : this.lignes) {
            ligne.défaire();
        }
        this.lignes.clear();
    }

    /**
     * Ajoute une ligne de commande au panier. Si l'article existe déjà dans le
     * panier, met à jour la quantité commandée.
     *
     * @param uneLigne la ligne de commande à ajouter
     * @assert uneLigne ne doit pas être null
     */
    public void addLigne(LigneCommande uneLigne) {
        assert !(uneLigne == null);
        if (this.contientDéjà(uneLigne.getArticle())) {
            this.ligneQuiContient(uneLigne.getArticle())
                    .updateQuantitéCommandée(uneLigne.getQuantité());
        } else {
            this.lignes.add(uneLigne);
        }
    }

    /**
     * Vérifie si le panier contient déjà un article spécifique.
     *
     * @param article l'article à rechercher
     * @return true si l'article est déjà dans le panier, false sinon
     */
    public boolean contientDéjà(Tomate article) {
        for (LigneCommande ligne : this.lignes) {
            if (article.equals(ligne.getArticle())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retourne la ligne de commande qui contient un article spécifique.
     *
     * @param article l'article à rechercher
     * @return la ligne de commande contenant l'article, ou null si l'article
     *         n'est pas trouvé
     */
    public LigneCommande ligneQuiContient(Tomate article) {
        for (LigneCommande ligne : this.lignes) {
            if (article.equals(ligne.getArticle())) {
                return ligne;
            }
        }
        return null;
    }

    /**
     * Supprime une ligne de commande du panier à un index donné.
     *
     * @param numéro l'index de la ligne de commande à supprimer
     * @assert numéro doit être dans les limites de la liste des lignes
     */
    public void removeLigne(int numéro) {
        assert (numéro >= 0 && numéro < this.lignes.size());
        this.lignes.remove(numéro);
    }

    /**
     * Retourne le contenu du panier sous forme de liste de lignes de commande.
     *
     * @return la liste des lignes de commande dans le panier
     */
    public List<LigneCommande> getContenu() {
        return this.lignes;
    }

    /**
     * Calcule le prix total TTC des articles dans le panier.
     *
     * @return le prix total TTC arrondi à deux décimales
     */
    public float prixTTC() {
        float prixTTC = 0F;
        for (LigneCommande ligne : this.lignes) {
            prixTTC += ligne.getPrixTTC();
        }
        return new BigDecimal(prixTTC).setScale(2, RoundingMode.HALF_UP)
                .floatValue();
    }

    /**
     * Retourne le prix de transport pour la France.
     *
     * @return le prix de transport
     */
    public float getPrixTransport() {
        return PRIX_TRANSPORT_FRANCE;
    }

    /**
     * Calcule le prix total du panier, y compris le transport.
     *
     * @return le prix total arrondi à deux décimales
     */
    public float prixTotal() {
        return new BigDecimal(this.prixTTC() + this.getPrixTransport())
                .setScale(2, RoundingMode.HALF_UP).floatValue();
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères du panier.
     *
     * @return une chaîne décrivant le contenu du panier
     */
    @Override
    public String toString() {
        return "MonPanier [lignes=" + this.lignes + "]";
    }

    /**
     * Vérifie si le panier est vide.
     *
     * @return true si le panier est vide, false sinon
     */
    public boolean estVide() {
        return this.lignes.size() == 0;
    }

    /**
     * Modifie la quantité d'une ligne de commande à un index donné.
     *
     * @param numéro   l'index de la ligne de commande à modifier
     * @param quantité la nouvelle quantité
     * @assert numéro doit être dans les limites de la liste des lignes
     */
    public void modifierQuantité(int numéro, int quantité) {
        assert (numéro >= 0 && numéro < this.lignes.size());
        this.lignes.get(numéro).modifierQuantité(quantité);
    }
}
