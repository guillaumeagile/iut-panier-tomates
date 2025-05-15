package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import modele.LigneCommande;
import modele.Panier;
import modele.Tomate;
import modele.Tomates;

/**
 * Fenêtre de dialogue affichant les détails d'une tomate. Permet à
 * l'utilisateur d'ajouter une tomate au panier avec une quantité spécifiée.
 */
public class FenetreFicheTomate extends JDialog {

    private Tomates tomates;
    private JButton btnPanier;
    private Panier panier;
    private String nomTomate;

    private final JPanel contentPanel = new JPanel();
    private JTextField textFieldNbGraines;
    private JTextField textFieldPrix;
    private JComboBox comboBoxProduitsSimilaires;
    private JSpinner spinnerQuantite;
    private JButton okButton;

    /**
     * Crée la fenêtre de détail pour une tomate donnée.
     *
     * @param nomTomate Le nom de la tomate à afficher dans la fenêtre.
     * @param tomates   L'objet contenant toutes les tomates disponibles.
     * @param btnPanier Le bouton représentant le panier dans l'interface.
     * @param panier    Le panier auquel les produits sont ajoutés.
     */
    public FenetreFicheTomate(String nomTomate, Tomates tomates,
            JButton btnPanier, Panier panier) {
        this.tomates = tomates;
        this.panier = panier;
        this.btnPanier = btnPanier;
        this.nomTomate = nomTomate;

        Tomate tomate = tomates.getTomate(nomTomate);

        this.okButton = new JButton("Ajouter au panier");

        String titre = tomate.getDésignation();
        String sousTitre = tomate.getSousTitre();
        String description = tomate.getDescription();
        int nombreGraines = tomate.getNbGrainesParSachet();
        float prix = tomate.getPrixTTC();
        float stock = tomate.getStock();
        String disponible = stock > 0 ? "En Stock" : "En Rupture";
        List<Tomate> tomatesApparentées = tomate.getTomatesApparentées();
        String[] produitsSimilaires = new String[tomatesApparentées.size() + 1];
        produitsSimilaires[0] = "Produits similaires";
        int i = 1;
        for (Tomate t : tomatesApparentées) {
            produitsSimilaires[i] = t.getDésignation();
            i++;
        }

        this.setTitle("Ô'Tomates");
        this.setIconImage(Toolkit.getDefaultToolkit()
                .getImage("./src/main/resources/images/Tomate10.png"));

        this.setBounds(100, 100, 562, 342);
        this.getContentPane().setLayout(new BorderLayout());
        this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.getContentPane().add(this.contentPanel, BorderLayout.CENTER);
        this.contentPanel.setLayout(new BorderLayout(0, 0));
        {
            JPanel panel1 = new JPanel();
            this.contentPanel.add(panel1, BorderLayout.CENTER);
            panel1.setLayout(new GridLayout(0, 2, 0, 0));
            {
                JPanel panel1_1 = new JPanel();
                panel1.add(panel1_1);
                panel1_1.setLayout(new BorderLayout(0, 0));
                {
                    JPanel panel1_1_1 = new JPanel();
                    panel1_1.add(panel1_1_1, BorderLayout.NORTH);
                    panel1_1_1.setLayout(new GridLayout(2, 1, 0, 0));
                    {
                        JLabel lblTitre = new JLabel(titre);
                        lblTitre.setForeground(new Color(0, 128, 0));
                        lblTitre.setFont(
                                new Font("Segoe Print", Font.BOLD, 13));
                        lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
                        panel1_1_1.add(lblTitre);
                    }
                    {
                        JLabel lblSousTitre = new JLabel(sousTitre);
                        lblSousTitre.setForeground(new Color(0, 128, 0));
                        lblSousTitre.setFont(
                                new Font("Segoe Print", Font.ITALIC, 10));
                        lblSousTitre
                                .setHorizontalAlignment(SwingConstants.CENTER);
                        panel1_1_1.add(lblSousTitre);
                    }
                }
                {
                    JLabel lblImage = new JLabel("");
                    lblImage.setHorizontalAlignment(SwingConstants.CENTER);
                    lblImage.setIcon(new ImageIcon(
                            "./src/main/resources/images/Tomates200x200/"
                                    + tomate.getNomImage() + ".jpg"));
                    panel1_1.add(lblImage, BorderLayout.CENTER);
                }
            }
            {
                JPanel panel1_2 = new JPanel();
                panel1_2.setBorder(new TitledBorder(null, "Description",
                        TitledBorder.LEADING, TitledBorder.TOP, null,
                        new Color(0, 128, 0)));
                panel1.add(panel1_2);
                panel1_2.setLayout(new BorderLayout(0, 0));
                {
                    JScrollPane scrollPane = new JScrollPane();
                    panel1_2.add(scrollPane, BorderLayout.CENTER);
                    {
                        JTextPane textPaneDescription = new JTextPane();
                        textPaneDescription.setEditable(false);
                        textPaneDescription.setText(description);
                        scrollPane.setViewportView(textPaneDescription);
                    }
                }
            }
        }
        {
            JPanel panel2 = new JPanel();
            this.contentPanel.add(panel2, BorderLayout.SOUTH);
            panel2.setLayout(new GridLayout(1, 2, 0, 0));
            {
                JPanel panel2_1 = new JPanel();
                panel2_1.setBorder(new CompoundBorder(
                        new EmptyBorder(10, 10, 10, 10), null));
                panel2.add(panel2_1);
                {
                    this.comboBoxProduitsSimilaires = new JComboBox();
                    this.construireControleurComboBoxProduitsSimilaires(
                            tomates);
                    for (String s : produitsSimilaires) {
                        this.comboBoxProduitsSimilaires.addItem(s);
                    }
                    panel2_1.setLayout(new BorderLayout(10, 10));
                    {
                        JLabel lblStock = new JLabel(disponible);
                        if (tomate.getStock() > 0) {
                            lblStock.setForeground(new Color(0, 128, 0));
                        } else {
                            lblStock.setForeground(new Color(255, 0, 0));
                        }
                        lblStock.setHorizontalAlignment(SwingConstants.CENTER);
                        panel2_1.add(lblStock, BorderLayout.NORTH);
                    }
                    if (tomate.getStock() == 0) {
                        panel2_1.add(this.comboBoxProduitsSimilaires);
                    }
                }
            }
            {
                JPanel panel2_2 = new JPanel();
                panel2.add(panel2_2);
                panel2_2.setLayout(new GridLayout(2, 1, 0, 0));
                {
                    JPanel panel2_2_1 = new JPanel();
                    panel2_2.add(panel2_2_1);
                    {
                        JLabel lblNbGraines = new JLabel("Nombre de graines");
                        panel2_2_1.add(lblNbGraines);
                    }
                    {
                        this.textFieldNbGraines = new JTextField(
                                "" + nombreGraines);
                        this.textFieldNbGraines.setEditable(false);
                        panel2_2_1.add(this.textFieldNbGraines);
                        this.textFieldNbGraines.setColumns(2);
                    }
                }
                {
                    JPanel panel2_2_2 = new JPanel();
                    panel2_2.add(panel2_2_2);
                    {
                        JLabel lblPrix = new JLabel("Prix :");
                        panel2_2_2.add(lblPrix);
                    }
                    {
                        this.textFieldPrix = new JTextField();
                        this.textFieldPrix.setEditable(false);
                        DecimalFormat df = new DecimalFormat("#0.00");
                        this.textFieldPrix.setText(df.format(prix) + "€");
                        panel2_2_2.add(this.textFieldPrix);
                        this.textFieldPrix.setColumns(5);
                    }
                    {
                        this.spinnerQuantite = new JSpinner();
                        if (tomate.getStock() == 0) {
                            this.spinnerQuantite.setModel(
                                    new SpinnerNumberModel(1, 1, 1, 1));
                            this.spinnerQuantite.setValue(0);
                            this.spinnerQuantite.setEnabled(false);
                            this.okButton.setEnabled(false);
                        } else {
                            this.spinnerQuantite
                                    .setModel(new SpinnerNumberModel(1, 1,
                                            tomate.getStock(), 1));

                        }
                        panel2_2_2.add(this.spinnerQuantite);
                    }
                }
            }
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
            this.getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                this.construireControleurBoutonOk(nomTomate);
                this.okButton.setActionCommand("OK");
                buttonPane.add(this.okButton);
                this.getRootPane().setDefaultButton(this.okButton);
            }
            {
                JButton cancelButton = new JButton("Annuler");
                buttonPane.add(cancelButton);
                this.construireControleurBoutonAnnuler(cancelButton);
            }
        }
    }

    /**
     * Construit le contrôleur pour la comboBox des produits similaires. Ouvre
     * une nouvelle fenêtre pour le produit sélectionné.
     *
     * @param tomates L'ensemble des tomates disponibles.
     */
    private void construireControleurComboBoxProduitsSimilaires(
            Tomates tomates) {
        this.comboBoxProduitsSimilaires.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!FenetreFicheTomate.this.comboBoxProduitsSimilaires
                        .getSelectedItem().toString()
                        .equals("Produits similaires")) {
                    FenetreFicheTomate fft = new FenetreFicheTomate(
                            FenetreFicheTomate.this.comboBoxProduitsSimilaires
                                    .getSelectedItem().toString(),
                            tomates, FenetreFicheTomate.this.btnPanier,
                            FenetreFicheTomate.this.panier);
                    fft.setDefaultCloseOperation(
                            WindowConstants.DISPOSE_ON_CLOSE);
                    fft.setVisible(true);
                }
            }

        });
    }

    /**
     * Construit le contrôleur pour le bouton Annuler. Ferme la fenêtre de
     * détail sans effectuer d'action.
     *
     * @param cancelButton Le bouton d'annulation.
     */
    private void construireControleurBoutonAnnuler(JButton cancelButton) {
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FenetreFicheTomate.this.dispose();
            }
        });
    }

    /**
     * Construit le contrôleur pour le bouton OK. Ajoute la tomate sélectionnée
     * avec la quantité choisie au panier. Met à jour le stock de la tomate et
     * le texte du bouton panier.
     *
     * @param nomTomate Le nom de la tomate à ajouter au panier.
     */
    private void construireControleurBoutonOk(String nomTomate) {
        this.okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int quantite = (int) FenetreFicheTomate.this.spinnerQuantite
                        .getValue();
                LigneCommande ligne = new LigneCommande(
                        FenetreFicheTomate.this.tomates.getTomate(nomTomate),
                        quantite);
                FenetreFicheTomate.this.tomates.getTomate(nomTomate)
                        .préempterQuantité(quantite);
                FenetreFicheTomate.this.panier.addLigne(ligne);
                DecimalFormat df = new DecimalFormat("#0.00");
                FenetreFicheTomate.this.btnPanier.setText(
                        df.format(FenetreFicheTomate.this.panier.prixTTC())
                                + "€");
                FenetreFicheTomate.this.dispose();
            }
        });
    }

}
