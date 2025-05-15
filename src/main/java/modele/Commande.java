package modele;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;

/**
 * Représente une commande associée à un panier et un client.
 */
public class Commande {

    private static final NumberFormat DF = new DecimalFormat("##0.00");

    private Panier panier;
    private Client client;

    /**
     * Constructeur pour initialiser une commande avec un panier et un client.
     *
     * @param panier le panier associé à la commande
     * @param client le client qui passe la commande
     * @assert panier et client ne doivent pas être null
     */
    public Commande(Panier panier, Client client) {
        assert panier != null && client != null;
        this.panier = panier;
        this.client = client;
    }

    /**
     * Génère une facture au format HTML pour la commande.
     *
     * @return une chaîne HTML représentant la facture
     */
    public String generationFacture() {
        StringBuilder facture = new StringBuilder();
        facture.append(
                "<!DOCTYPE html>\n<html>\n<head><title>Facture</title></head>\n<body lang=\"fr-FR\">\n");
        this.enteteFacture(facture);
        this.informationClient(facture);
        this.informationCommande(facture);
        facture.append("</body>\n</html>");
        return facture.toString();
    }

    /**
     * Ajoute l'en-tête de la facture au buffer.
     *
     * @param facture le buffer où ajouter l'en-tête
     */
    private void enteteFacture(StringBuilder facture) {
        DateFormat fullDateFormat = DateFormat
                .getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);
        String date = fullDateFormat.format(new Date());

        facture.append("<p style=\"color: rgb(0, 128, 0); font-size: 14px;\">")
                .append("<strong>Ô'Tomates, redécouvrez le goût des tomates anciennes !</strong>")
                .append("</p>\n");

        facture.append("<p style=\"font-size: 11px;\"><em>Commande du ")
                .append(date).append("</em></p>\n");
    }

    /**
     * Ajoute les informations du client à la facture.
     *
     * @param facture le buffer où ajouter les informations du client
     */
    private void informationClient(StringBuilder facture) {
        facture.append("<p>\n").append("<em><strong>")
                .append(this.client.getPrenom()).append(" ")
                .append(this.client.getNom()).append("</strong></em><br>\n")
                .append("Adresse : <em>").append(this.client.getAdresse1())
                .append(" ").append(this.client.getAdresse2()).append(" ")
                .append(this.client.getCodePostal()).append(" ")
                .append(this.client.getVille()).append("</em><br>\n")
                .append("Téléphone : <em>").append(this.client.getTelephone())
                .append("</em><br>\n").append("Mél : <em>")
                .append(this.client.getMail()).append("</em><br><br>\n")
                .append("</p>\n");
    }

    /**
     * Ajoute les informations de la commande à la facture.
     *
     * @param facture le buffer où ajouter les informations de la commande
     */
    private void informationCommande(StringBuilder facture) {
        facture.append(
                "<table width=\"552\" cellpadding=\"7\" cellspacing=\"0\" style=\"border-collapse: collapse;\">\n")
                .append("<thead><tr>\n")
                .append("<th style=\"border: 1px solid #000; text-align: center;\">Produit</th>\n")
                .append("<th style=\"border: 1px solid #000; text-align: center;\">Prix unitaire</th>\n")
                .append("<th style=\"border: 1px solid #000; text-align: center;\">Quantité</th>\n")
                .append("<th style=\"border: 1px solid #000; text-align: center;\">Prix TTC</th>\n")
                .append("</tr></thead><tbody>\n");

        for (LigneCommande ligne : this.panier.getContenu()) {
            facture.append("<tr>\n")
                    .append("<td style=\"border: 1px solid #000;\">")
                    .append(ligne.getArticle().getDésignation())
                    .append("</td>\n")
                    .append("<td style=\"border: 1px solid #000; text-align: right;\">")
                    .append(DF.format(ligne.getArticle().getPrixTTC()))
                    .append(" &euro;</td>\n")
                    .append("<td style=\"border: 1px solid #000; text-align: center;\">")
                    .append(ligne.getQuantité()).append("</td>\n")
                    .append("<td style=\"border: 1px solid #000; text-align: right;\">")
                    .append(DF.format(ligne.getPrixTTC()))
                    .append(" &euro;</td>\n").append("</tr>\n");
        }

        facture.append("</tbody></table><br>\n");

        facture.append(
                "<table width=\"562\" cellpadding=\"7\" cellspacing=\"0\">\n")
                .append("<tbody>\n").append("<tr>\n")
                .append("<td width=\"187\">TOTAL TTC COMMANDE :</td>\n")
                .append("<td width=\"77\" style=\"text-align: right;\">")
                .append(DF.format(this.panier.prixTTC()))
                .append(" &euro;</td>\n").append("<td></td>\n")
                .append("</tr>\n")

                .append("<tr>\n").append("<td>FORFAIT FRAIS DE PORT :</td>\n")
                .append("<td style=\"text-align: right;\">")
                .append(DF.format(this.panier.getPrixTransport()))
                .append(" &euro;</td>\n").append("<td></td>\n")
                .append("</tr>\n")

                .append("<tr>\n")
                .append("<td><strong>PRIX TOTAL TTC :</strong></td>\n")
                .append("<td style=\"text-align: right;\"><strong>")
                .append(DF.format(this.panier.prixTotal()))
                .append(" &euro;</strong></td>\n")
                .append("<td><strong>payé par ")
                .append(this.client.getModePaiement().getDénomination())
                .append("</strong></td>\n").append("</tr>\n")
                .append("</tbody></table>\n");
    }
}
