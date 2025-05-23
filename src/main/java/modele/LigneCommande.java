package modele;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

/**
 * Représente une ligne de commande pour un article de type Tomate.
 */
public class LigneCommande {

    private Tomate article;
    private int quantité;

    /**
     * Constructeur pour initialiser une ligne de commande avec un article et
     * une quantité donnée.
     *
     * @param article  l'article de type Tomate associé à cette ligne de
     *                 commande
     * @param quantité la quantité commandée de l'article
     * @assert quantité doit être supérieure à 0
     */
    public LigneCommande(Tomate article, int quantité) {

        this.article = article;
        this.quantité = quantité;

    }

    public static Optional< LigneCommande> Build(Tomate article, int quantité) {

        if (article == null) {
            return Optional.empty();
        }
        if (quantité <= 0) {
            return Optional.empty();
        }
            assert (quantité > 0);
            article.préempterQuantité(quantité);
            return Optional.of(new LigneCommande(article, quantité));
        }


    /**
     * Calcule le prix total TTC pour cette ligne de commande.
     *
     * @return le prix total TTC arrondi à deux décimales
     */
    public float getPrixTTC() {
        return new BigDecimal(this.article.getPrixTTC() * this.quantité)
                .setScale(2, RoundingMode.HALF_UP).floatValue();
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de cette
     * ligne de commande.
     *
     * @return une chaîne décrivant l'article, le prix TTC, la quantité
     *         commandée et le sous-total
     */
    @Override
    public String toString() {
        return this.article.getDésignation() + ", Prix TTC : "
                + this.article.getPrixTTC() + " €, quantité commandée : "
                + this.quantité + ", Sous-Total : " + this.getPrixTTC() + " €";
    }

    /**
     * Retourne l'article associé à cette ligne de commande.
     *
     * @return l'article de type Tomate
     */
    public Tomate getArticle() {
        return this.article;
    }

    /**
     * Retourne la quantité commandée de l'article.
     *
     * @return la quantité commandée
     */
    public int getQuantité() {
        return this.quantité;
    }

    /**
     * Met à jour la quantité commandée en ajoutant une quantité supplémentaire.
     *
     * @param quantitéSupplémentaire la quantité supplémentaire à ajouter
     * @assert quantitéSupplémentaire doit être supérieure à 0
     */
    public void updateQuantitéCommandée(int quantitéSupplémentaire) {
        assert (quantitéSupplémentaire > 0);
        this.quantité += quantitéSupplémentaire;
    }

    /**
     * Modifie la quantité commandée de l'article.
     *
     * @param quantitéCommandée la nouvelle quantité commandée
     */
    public void modifierQuantité(int quantitéCommandée) {
        this.article.rendreQuantité(this.quantité);
        if (this.quantité > this.article.getStock()) {
            this.quantité = this.article.getStock();
        } else {
            this.quantité = quantitéCommandée;
        }
        this.article.préempterQuantité(this.quantité);
    }

    /**
     * Annule la commande en rendant la quantité commandée à l'article.
     */
    public void défaire() {
        this.article.rendreQuantité(this.quantité);
    }
}