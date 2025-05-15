package modele;

/**
 * Enumération représentant les différents modes de paiement disponibles.
 */
public enum ModePaiement {

    /**
     * Mode de paiement par carte de crédit.
     */
    CARTE("Carte de crédit"),

    /**
     * Mode de paiement via PayPal.
     */
    PAYPAL("Paypal"),

    /**
     * Mode de paiement par chèque.
     */
    CHEQUE("Chèque");

    private final String dénomination;

    /**
     * Constructeur privé pour initialiser un mode de paiement avec une
     * dénomination spécifique.
     *
     * @param dénomination la dénomination du mode de paiement
     */
    private ModePaiement(String dénomination) {
        this.dénomination = dénomination;
    }

    /**
     * Retourne la dénomination du mode de paiement.
     *
     * @return la dénomination du mode de paiement
     */
    public String getDénomination() {
        return this.dénomination;
    }

    /**
     * Retourne le mode de paiement correspondant à une dénomination donnée.
     *
     * @param dénomination la dénomination à rechercher
     * @return le mode de paiement correspondant, ou null si aucun mode de
     *         paiement ne correspond
     */
    public static ModePaiement getModePaiement(String dénomination) {
        for (ModePaiement m : ModePaiement.values()) {
            if (m.getDénomination().equals(dénomination)) {
                return m;
            }
        }
        return null;
    }
}