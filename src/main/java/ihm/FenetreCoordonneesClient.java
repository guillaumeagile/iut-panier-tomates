package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import modele.Client;
import modele.ModePaiement;
import modele.Panier;
import modele.Tomates;

/**
 * Cette classe représente une fenêtre de dialogue permettant à l'utilisateur de
 * saisir ses coordonnées personnelles, de choisir un mode de paiement, et de
 * s'abonner ou non à la newsletter.
 * 
 * Une fois les données saisies, un objet {@link Client} est créé et la fenêtre
 * de facture est affichée.
 */
public class FenetreCoordonneesClient extends JDialog {

    private Client client;
    private Panier panier;
    private Tomates tomates;

    private final JPanel contentPanel = new JPanel();
    private JTextField nom;
    private JTextField prenom;
    private JTextField adresse1;
    private JTextField adresse2;
    private JTextField codePostal;
    private JTextField ville;
    private JTextField telephone;
    private JTextField mail;
    private final ButtonGroup buttonGroupPaiement = new ButtonGroup();
    private final ButtonGroup buttonGroupNewsLetter = new ButtonGroup();

    /**
     * Crée la boîte de dialogue FenetreCoordonneesClient.
     *
     * @param panier  le panier courant contenant les articles sélectionnés
     * @param tomates l'objet Tomates contenant les conseils de culture
     */
    public FenetreCoordonneesClient(Panier panier, Tomates tomates) {
        this.setModal(true);
        this.panier = panier;
        this.tomates = tomates;
        this.setTitle("Ô'Tomates");
        this.setIconImage(Toolkit.getDefaultToolkit()
                .getImage("./src/main/resources/images/Tomate10.png"));
        this.getContentPane().setFont(new Font("Arial", Font.PLAIN, 23));
        this.setBounds(100, 100, 519, 410);
        this.getContentPane().setLayout(new BorderLayout());
        this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.getContentPane().add(this.contentPanel, BorderLayout.CENTER);
        this.contentPanel.setLayout(new BorderLayout(0, 0));
        {
            JPanel panel = new JPanel();
            this.contentPanel.add(panel);
            panel.setLayout(new BorderLayout(0, 0));
            {
                JPanel panel_1 = new JPanel();
                panel_1.setBorder(new EmptyBorder(0, 0, 0, 130));
                panel.add(panel_1, BorderLayout.NORTH);
                {
                    JLabel lblNewLabel_9 = new JLabel(new ImageIcon(
                            "./src/main/resources/images/logoRenseignement2.png"));
                    panel_1.add(lblNewLabel_9);
                }

                {
                    JLabel lblNewLabel_8 = new JLabel("Vos coordonnées");
                    lblNewLabel_8.setHorizontalAlignment(SwingConstants.LEFT);
                    lblNewLabel_8.setForeground(new Color(0, 128, 0));
                    lblNewLabel_8
                            .setFont(new Font("Segoe Print", Font.BOLD, 25));
                    panel_1.add(lblNewLabel_8);
                }
                {
                    JLabel lblNewLabel_9 = new JLabel(new ImageIcon(
                            "./src/main/resources/images/Tomate10.png"));
                    panel_1.add(lblNewLabel_9);
                }
            }
            {
                JPanel panel_1 = new JPanel();
                panel_1.setBorder(new EmptyBorder(0, 10, 0, 0));
                panel.add(panel_1, BorderLayout.WEST);
                panel_1.setLayout(new GridLayout(8, 1, 0, 0));
                {
                    JLabel lblNewLabel = new JLabel("Nom : ");
                    lblNewLabel.setBorder(new EmptyBorder(0, 0, 0, 5));
                    lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                    lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 14));
                    panel_1.add(lblNewLabel);
                }
                {
                    JLabel lblNewLabel_4 = new JLabel("Prénom : ");
                    lblNewLabel_4.setBorder(new EmptyBorder(0, 0, 0, 5));
                    lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
                    lblNewLabel_4.setFont(new Font("Consolas", Font.BOLD, 14));
                    panel_1.add(lblNewLabel_4);
                }
                {
                    JLabel lblNewLabel_1 = new JLabel("Adresse 1 : ");
                    lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
                    lblNewLabel_1.setBorder(new EmptyBorder(0, 0, 0, 5));
                    lblNewLabel_1.setFont(new Font("Consolas", Font.BOLD, 14));
                    panel_1.add(lblNewLabel_1);
                }
                {
                    JLabel lblNewLabel_2 = new JLabel("Adresse 2 : ");
                    lblNewLabel_2.setBorder(new EmptyBorder(0, 0, 0, 5));
                    lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
                    lblNewLabel_2.setFont(new Font("Consolas", Font.BOLD, 14));
                    panel_1.add(lblNewLabel_2);
                }
                {
                    JLabel lblNewLabel_3 = new JLabel("Code postal : ");
                    lblNewLabel_3.setBorder(new EmptyBorder(0, 0, 0, 5));
                    lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
                    lblNewLabel_3.setFont(new Font("Consolas", Font.BOLD, 14));
                    panel_1.add(lblNewLabel_3);
                }

                {
                    JLabel lblNewLabel_5 = new JLabel("Ville : ");
                    lblNewLabel_5.setBorder(new EmptyBorder(0, 0, 0, 5));
                    lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
                    lblNewLabel_5.setFont(new Font("Consolas", Font.BOLD, 14));
                    panel_1.add(lblNewLabel_5);
                }
                {
                    JLabel lblNewLabel_6 = new JLabel("Téléphone : ");
                    lblNewLabel_6.setBorder(new EmptyBorder(0, 0, 0, 5));
                    lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
                    lblNewLabel_6.setFont(new Font("Consolas", Font.BOLD, 14));
                    panel_1.add(lblNewLabel_6);
                }
                {
                    JLabel lblNewLabel_7 = new JLabel("Mail : ");
                    lblNewLabel_7.setBorder(new EmptyBorder(0, 0, 0, 5));
                    lblNewLabel_7.setHorizontalAlignment(SwingConstants.RIGHT);
                    lblNewLabel_7.setFont(new Font("Consolas", Font.BOLD, 14));
                    panel_1.add(lblNewLabel_7);
                }
            }
            {
                JPanel panel_1 = new JPanel();
                panel_1.setBorder(new EmptyBorder(0, 0, 0, 10));
                panel.add(panel_1, BorderLayout.CENTER);
                panel_1.setLayout(new GridLayout(8, 1, 0, 0));
                {
                    this.nom = new JTextField();
                    this.nom.setFont(new Font("Consolas", Font.PLAIN, 14));
                    panel_1.add(this.nom);
                    this.nom.setColumns(10);
                }
                {
                    this.prenom = new JTextField();
                    this.prenom.setFont(new Font("Consolas", Font.PLAIN, 14));
                    panel_1.add(this.prenom);
                    this.prenom.setColumns(10);
                }
                {
                    this.adresse1 = new JTextField();
                    this.adresse1.setFont(new Font("Consolas", Font.PLAIN, 14));
                    panel_1.add(this.adresse1);
                    this.adresse1.setColumns(10);
                }
                {
                    this.adresse2 = new JTextField();
                    this.adresse2.setFont(new Font("Consolas", Font.PLAIN, 14));
                    panel_1.add(this.adresse2);
                    this.adresse2.setColumns(10);
                }
                {
                    this.codePostal = new JTextField();
                    this.codePostal
                            .setFont(new Font("Consolas", Font.PLAIN, 14));
                    panel_1.add(this.codePostal);
                    this.codePostal.setColumns(10);
                }
                {
                    this.ville = new JTextField();
                    this.ville.setFont(new Font("Consolas", Font.PLAIN, 14));
                    panel_1.add(this.ville);
                    this.ville.setColumns(10);
                }
                {
                    this.telephone = new JTextField();
                    this.telephone
                            .setFont(new Font("Consolas", Font.PLAIN, 14));
                    panel_1.add(this.telephone);
                    this.telephone.setColumns(10);
                }
                {
                    this.mail = new JTextField();
                    this.mail.setFont(new Font("Consolas", Font.PLAIN, 14));
                    panel_1.add(this.mail);
                    this.mail.setColumns(10);
                }
            }
        }
        {
            JPanel panel = new JPanel();
            this.contentPanel.add(panel, BorderLayout.SOUTH);
            panel.setLayout(new GridLayout(0, 1, 0, 0));
            {
                JPanel panel_1 = new JPanel();
                panel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
                panel_1.setBorder(new TitledBorder(
                        new LineBorder(new Color(0, 128, 0), 2),
                        "Moyen de paiement ", TitledBorder.LEADING,
                        TitledBorder.TOP, null, new Color(0, 128, 0)));
                panel.add(panel_1);

                // création des boutons radio de paiement
                boolean premierBoutonRadio = true;
                for (ModePaiement p : ModePaiement.values()) {
                    JRadioButton rdbtnNewRadioButton = new JRadioButton(
                            p.getDénomination());
                    rdbtnNewRadioButton
                            .setActionCommand(rdbtnNewRadioButton.getText());

                    if (premierBoutonRadio) {
                        rdbtnNewRadioButton.setSelected(true);
                    }
                    this.buttonGroupPaiement.add(rdbtnNewRadioButton);
                    rdbtnNewRadioButton
                            .setFont(new Font("Tahoma", Font.BOLD, 11));
                    panel_1.add(rdbtnNewRadioButton);
                }
            }
            {
                JPanel panel_1 = new JPanel();
                panel_1.setBorder(new TitledBorder(
                        new LineBorder(new Color(0, 128, 0), 2),
                        "Abonnement à notre Newsletter ", TitledBorder.LEADING,
                        TitledBorder.TOP, null, new Color(0, 128, 0)));
                panel.add(panel_1);
                // création des boutons radio newsletter
                {
                    JRadioButton radioButtonOui = new JRadioButton("Oui");
                    radioButtonOui.setSelected(true);
                    radioButtonOui.setActionCommand(radioButtonOui.getText());
                    this.buttonGroupNewsLetter.add(radioButtonOui);
                    radioButtonOui.setFont(new Font("Tahoma", Font.BOLD, 11));
                    panel_1.add(radioButtonOui);
                }
                {
                    JRadioButton radioButtonNon = new JRadioButton("Non");
                    radioButtonNon.setFont(new Font("Tahoma", Font.BOLD, 11));
                    radioButtonNon.setActionCommand(radioButtonNon.getText());
                    this.buttonGroupNewsLetter.add(radioButtonNon);
                    panel_1.add(radioButtonNon);
                }
            }
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            this.getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("OK");
                this.construireControleurBoutonValider(okButton);
                okButton.setActionCommand("Valider");
                buttonPane.add(okButton);
                this.getRootPane().setDefaultButton(okButton);
            }
            {
                JButton cancelButton = new JButton("Annuler");
                this.construireControleurBoutonAnnuler(cancelButton);
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
    }

    /**
     * Crée le contrôleur pour le bouton OK. Lors du clic, les données du
     * formulaire sont utilisées pour créer un client et une fenêtre de facture
     * est ouverte.
     *
     * @param okButton le bouton de validation
     */
    private void construireControleurBoutonValider(JButton okButton) {
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                FenetreCoordonneesClient.this.setModal(false);
                String paiement = FenetreCoordonneesClient.this.buttonGroupPaiement
                        .getSelection().getActionCommand();
                String newsLetter = FenetreCoordonneesClient.this.buttonGroupNewsLetter
                        .getSelection().getActionCommand();
                FenetreCoordonneesClient.this.client = new Client(
                        FenetreCoordonneesClient.this.nom.getText(),
                        FenetreCoordonneesClient.this.prenom.getText(),
                        FenetreCoordonneesClient.this.adresse1.getText(),
                        FenetreCoordonneesClient.this.adresse2.getText(),
                        FenetreCoordonneesClient.this.codePostal.getText(),
                        FenetreCoordonneesClient.this.ville.getText(),
                        FenetreCoordonneesClient.this.telephone.getText(),
                        FenetreCoordonneesClient.this.mail.getText(),
                        newsLetter.equals("Oui"),
                        ModePaiement.getModePaiement(paiement));
                FenetreFacture f = new FenetreFacture(
                        FenetreCoordonneesClient.this.client,
                        FenetreCoordonneesClient.this.panier,
                        FenetreCoordonneesClient.this.tomates);
                f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                f.setVisible(true);
            }
        });
    }

    /**
     * Crée le contrôleur pour le bouton Annuler. Lors du clic, la fenêtre est
     * simplement fermée.
     *
     * @param cancelButton le bouton d'annulation
     */
    private void construireControleurBoutonAnnuler(JButton cancelButton) {
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FenetreCoordonneesClient.this.dispose();
            }
        });
    }

}