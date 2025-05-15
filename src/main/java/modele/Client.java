package modele;

/**
 * Représente un client avec ses informations personnelles et ses préférences.
 */
public class Client {

    private String nom;
    private String prenom;
    private String adresse1;
    private String adresse2;
    private String codePostal;
    private String ville;
    private String telephone;
    private String mail;
    private boolean subscribeToNewsletter;
    private ModePaiement modePaiement;

    /**
     * Constructeur pour initialiser un client avec toutes ses informations.
     *
     * @param nom                   le nom du client
     * @param prenom                le prénom du client
     * @param adresse1              la première ligne de l'adresse du client
     * @param adresse2              la deuxième ligne de l'adresse du client
     * @param codePostal            le code postal du client
     * @param ville                 la ville du client
     * @param telephone             le numéro de téléphone du client
     * @param mail                  l'adresse e-mail du client
     * @param subscribeToNewsletter indique si le client est abonné à la
     *                              newsletter
     * @param modePaiement          le mode de paiement préféré du client
     */
    public Client(String nom, String prenom, String adresse1, String adresse2,
            String codePostal, String ville, String telephone, String mail,
            boolean subscribeToNewsletter, ModePaiement modePaiement) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse1 = adresse1;
        this.adresse2 = adresse2;
        this.codePostal = codePostal;
        this.ville = ville;
        this.telephone = telephone;
        this.mail = mail;
        this.subscribeToNewsletter = subscribeToNewsletter;
        this.modePaiement = modePaiement;
    }

    /**
     * Retourne le nom du client.
     *
     * @return le nom du client
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Retourne le prénom du client.
     *
     * @return le prénom du client
     */
    public String getPrenom() {
        return this.prenom;
    }

    /**
     * Retourne la première ligne de l'adresse du client.
     *
     * @return la première ligne de l'adresse
     */
    public String getAdresse1() {
        return this.adresse1;
    }

    /**
     * Retourne la deuxième ligne de l'adresse du client.
     *
     * @return la deuxième ligne de l'adresse
     */
    public String getAdresse2() {
        return this.adresse2;
    }

    /**
     * Retourne le code postal du client.
     *
     * @return le code postal
     */
    public String getCodePostal() {
        return this.codePostal;
    }

    /**
     * Retourne la ville du client.
     *
     * @return la ville
     */
    public String getVille() {
        return this.ville;
    }

    /**
     * Retourne le numéro de téléphone du client.
     *
     * @return le numéro de téléphone
     */
    public String getTelephone() {
        return this.telephone;
    }

    /**
     * Retourne l'adresse e-mail du client.
     *
     * @return l'adresse e-mail
     */
    public String getMail() {
        return this.mail;
    }

    /**
     * Indique si le client est abonné à la newsletter.
     *
     * @return true si le client est abonné, false sinon
     */
    public boolean isSubscribeToNewsletter() {
        return this.subscribeToNewsletter;
    }

    /**
     * Retourne le mode de paiement préféré du client.
     *
     * @return le mode de paiement
     */
    public ModePaiement getModePaiement() {
        return this.modePaiement;
    }
}
