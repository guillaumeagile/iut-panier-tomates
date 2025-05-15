package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import modele.Client;
import modele.Commande;
import modele.OutilsBaseDonneesTomates;
import modele.Panier;
import modele.Tomates;

/**
 * Fenêtre de dialogue affichant une facture générée pour un client après
 * validation de son panier. Permet également d'imprimer ou de quitter
 * l'application.
 */
public class FenetreFacture extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextPane textPaneFacture;

    private Tomates tomates;

    /**
     * Crée une fenêtre modale affichant la facture du client au format HTML.
     *
     * @param client  Le client ayant effectué la commande.
     * @param panier  Le panier contenant les produits commandés.
     * @param tomates L'ensemble des tomates disponibles (utilisé pour mettre à
     *                jour le stock).
     */
    public FenetreFacture(Client client, Panier panier, Tomates tomates) {
        this.tomates = tomates;
        this.setModal(true);
        this.construireControleurFenetre();

        this.setTitle("Ô'Tomates");
        this.setIconImage(Toolkit.getDefaultToolkit()
                .getImage("./src/main/resources/images/Tomate10.png"));
        this.setBounds(100, 100, 646, 635);
        this.getContentPane().setLayout(new BorderLayout());
        this.contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.getContentPane().add(this.contentPanel, BorderLayout.CENTER);
        this.contentPanel.setLayout(new BorderLayout(0, 0));
        {
            JPanel panel = new JPanel();
            panel.setBorder(new EmptyBorder(5, 0, 5, 100));
            this.contentPanel.add(panel, BorderLayout.NORTH);
            {
                JLabel lblNewLabel_2 = new JLabel(new ImageIcon(
                        "./src/main/resources/images/logofacture4.png"));
                panel.add(lblNewLabel_2);
            }
            {
                JLabel lblNewLabel_1 = new JLabel("Votre facture");
                lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
                lblNewLabel_1.setForeground(new Color(0, 128, 0));
                lblNewLabel_1.setFont(new Font("Segoe Print", Font.BOLD, 25));
                panel.add(lblNewLabel_1);
            }
            {
                JLabel lblNewLabel_2 = new JLabel(new ImageIcon(
                        "./src/main/resources/images/Tomate10.png"));
                panel.add(lblNewLabel_2);
            }
        }
        {
            JPanel panel = new JPanel();
            this.contentPanel.add(panel, BorderLayout.CENTER);
            panel.setLayout(new BorderLayout(0, 0));
            {
                JTextPane textPaneMerci = new JTextPane();
                textPaneMerci.setBorder(new LineBorder(new Color(0, 128, 0)));
                textPaneMerci.setEditable(false);
                textPaneMerci.setFont(new Font("Segoe Print", Font.BOLD, 14));
                textPaneMerci.setForeground(new Color(0, 128, 0));
                textPaneMerci.setText("Merci de votre visite !");
                panel.add(textPaneMerci, BorderLayout.NORTH);
            }
            {
                JScrollPane scrollPane = new JScrollPane();
                panel.add(scrollPane, BorderLayout.CENTER);
                {
                    this.textPaneFacture = new JTextPane();
                    this.textPaneFacture
                            .setBorder(new EmptyBorder(0, 15, 0, 0));
                    // textPaneFacture.setFont(new Font("Consolas", Font.BOLD,
                    // 11));
                    this.textPaneFacture.setEditable(false);

                    // Créer un HTMLEditorKit
                    HTMLEditorKit kit = new HTMLEditorKit();
                    this.textPaneFacture.setEditorKit(kit);

                    // Créer un HTMLDocument
                    HTMLDocument doc = (HTMLDocument) kit
                            .createDefaultDocument();
                    this.textPaneFacture.setDocument(doc);

                    // Créer le texte de la facture
                    String htmlText = new Commande(panier, client)
                            .generationFacture();

                    try {
                        kit.read(new java.io.StringReader(htmlText), doc, 0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    scrollPane.setViewportView(this.textPaneFacture);
                }
            }

        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            this.getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("Quitter");
                this.construireControleurBoutonQuitter(okButton);
                {
                    JButton btnNewButton = new JButton("imprimer");
                    this.construireControleurBoutonImprimer(btnNewButton);
                    buttonPane.add(btnNewButton);
                }
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                this.getRootPane().setDefaultButton(okButton);
            }
        }
    }

    /**
     * définit le comportement à la fermeture de la fenêtre : met à jour le
     * stock dans le fichier JSON puis quitte l'application.
     *
     */
    private void construireControleurFenetre() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                FenetreFacture.this.mettreAJourLesStocksDansFichierJSon();
                System.exit(0);
            }
        });
    }

    /**
     * définit le comportement du bouton "Quitter" : met à jour le stock dans le
     * fichier JSON puis quitte l'application.
     * 
     * @param okButton Le bouton sur lequel l'action est attachée.
     */
    private void construireControleurBoutonQuitter(JButton okButton) {
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FenetreFacture.this.mettreAJourLesStocksDansFichierJSon();
                System.exit(0);
            }

        });
    }

    /**
     * Crée l'action associée au bouton d'impression. Permet d'imprimer le
     * contenu HTML de la facture.
     * 
     * @param btnNewButton Le bouton sur lequel l'action est attachée.
     */
    private void construireControleurBoutonImprimer(JButton btnNewButton) {
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FenetreFacture.this.textPaneFacture.print();
                } catch (PrinterException ex) {
                    System.out.println("Erreur lors de l'impression");
                }
            }
        });
    }

    /**
     * Met à jour le fichier JSON des stocks avec les quantités restantes après
     * la commande.
     */
    private void mettreAJourLesStocksDansFichierJSon() {
        OutilsBaseDonneesTomates.sauvegarderBaseDeTomates(this.tomates,
                "src/main/resources/data/tomatesPostCommande.json");
    }

}
