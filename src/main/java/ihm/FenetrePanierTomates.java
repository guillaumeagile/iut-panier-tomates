package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import modele.LigneCommande;
import modele.Panier;
import modele.Tomates;

/**
 * Fenêtre de dialogue représentant le panier de l'utilisateur. Permet de
 * visualiser, modifier et valider les articles dans le panier, ainsi que de
 * recalculer les prix et expéditions.
 */
public class FenetrePanierTomates extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField textFieldPrixTTC;
    private JTextField textFieldPrixTransport;
    private JTextField textFieldPrixTotal;
    private JTable table;
    private DefaultTableModel modeleTable;
    private static final long serialVersionUID = 1L;

    private JButton btnPanier;
    private Tomates tomates;
    private Panier panier;

    private static final NumberFormat DF = new DecimalFormat("##0.00");

    /**
     * Crée une fenêtre modale affichant les détails du panier.
     * 
     * @param btnPanier Le bouton représentant le panier dans l'interface.
     * @param tomates   L'objet contenant toutes les tomates disponibles.
     * @param panier    Le panier de l'utilisateur.
     */
    public FenetrePanierTomates(JButton btnPanier, Tomates tomates,
            Panier panier) {
        this.setModal(true);
        this.btnPanier = btnPanier;
        this.panier = panier;
        this.tomates = tomates;

        this.setTitle("Ô'Tomates");
        this.setIconImage(Toolkit.getDefaultToolkit()
                .getImage("./src/main/resources/images/Tomate10.png"));

        this.setBounds(100, 100, 517, 457);
        this.getContentPane().setLayout(new BorderLayout());
        this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.getContentPane().add(this.contentPanel, BorderLayout.CENTER);
        this.contentPanel.setLayout(new BorderLayout(0, 0));
        {
            JLabel lblNewLabel = new JLabel("Votre panier");
            lblNewLabel.setForeground(new Color(0, 128, 0));
            lblNewLabel.setFont(new Font("Segoe Print", Font.BOLD, 25));
            lblNewLabel.setIcon(new ImageIcon(
                    "./src/main/resources/images/panierTomates.png"));
            this.contentPanel.add(lblNewLabel, BorderLayout.NORTH);
        }
        {
            JPanel panel = new JPanel();
            this.contentPanel.add(panel, BorderLayout.CENTER);
            panel.setLayout(new BorderLayout(10, 10));
            {
                JScrollPane scrollPane = new JScrollPane();
                panel.add(scrollPane, BorderLayout.CENTER);
                {
                    this.modeleTable = new DefaultTableModel(new Object[] { " ",
                            "produit", "prix", "quantité", "total" }, 0) {

                        @Override
                        public boolean isCellEditable(int row, int column) {
                            // Rendre non éditables les colonnes précisées
                            return column != 0 && column != 1 && column != 2
                                    && column != 4;
                        }
                    };

                    this.table = new JTable(this.modeleTable);
                    this.table.setModel(this.modeleTable);
                    this.table.getColumnModel().getColumn(1)
                            .setPreferredWidth(200);

                    // pour afficher les images dans la table
                    this.table.getColumnModel().getColumn(0)
                            .setCellRenderer(new ImageRenderer());
                    // pour afficher le spinner dans la quantité
                    this.table.getColumnModel().getColumn(3)
                            .setCellEditor(new MySpinnerEditor());

                    // pour cadrer à droite le prix du produit
                    DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
                    rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
                    this.table.getColumnModel().getColumn(2)
                            .setCellRenderer(rightRenderer);
                    // pour cadrer à droite le prix total du produit
                    this.table.getColumnModel().getColumn(4)
                            .setCellRenderer(rightRenderer);
                    // pour centrer la quantité
                    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                    centerRenderer
                            .setHorizontalAlignment(SwingConstants.CENTER);
                    this.table.getColumnModel().getColumn(3)
                            .setCellRenderer(centerRenderer);

                    scrollPane.setViewportView(this.table);
                }
            }
            {
                JPanel panel_1 = new JPanel();
                panel_1.setBorder(new EmptyBorder(6, 6, 6, 6));
                panel.add(panel_1, BorderLayout.SOUTH);
                panel_1.setLayout(new GridLayout(1, 2, 20, 20));
                {
                    JPanel panel_2 = new JPanel();
                    FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
                    panel_1.add(panel_2);
                    {
                        JButton btnRecalculer = new JButton(
                                "Recalculer le panier");
                        btnRecalculer
                                .setFont(new Font("Tahoma", Font.PLAIN, 12));
                        this.construireControleurBoutonRecalculer(
                                btnRecalculer);
                        panel_2.add(btnRecalculer);
                    }
                }
                {
                    JPanel panel_2 = new JPanel();
                    panel_1.add(panel_2);
                    panel_2.setLayout(new GridLayout(3, 2, 5, 5));
                    {
                        JLabel lblNewLabel_1 = new JLabel("Sous-Total : ");
                        lblNewLabel_1
                                .setFont(new Font("Tahoma", Font.BOLD, 10));
                        lblNewLabel_1
                                .setHorizontalAlignment(SwingConstants.RIGHT);
                        panel_2.add(lblNewLabel_1);

                    }
                    {
                        this.textFieldPrixTTC = new JTextField();
                        this.textFieldPrixTTC.setEditable(false);
                        this.textFieldPrixTTC
                                .setHorizontalAlignment(SwingConstants.RIGHT);
                        this.textFieldPrixTTC
                                .setFont(new Font("Tahoma", Font.BOLD, 10));
                        this.textFieldPrixTTC
                                .setBackground(new Color(245, 245, 220));
                        panel_2.add(this.textFieldPrixTTC);
                        this.textFieldPrixTTC.setColumns(10);

                    }
                    {
                        JLabel lblNewLabel_2 = new JLabel(
                                "Expédition (forfait) : ");
                        lblNewLabel_2
                                .setFont(new Font("Tahoma", Font.BOLD, 10));
                        lblNewLabel_2
                                .setHorizontalAlignment(SwingConstants.RIGHT);
                        panel_2.add(lblNewLabel_2);
                    }
                    {
                        this.textFieldPrixTransport = new JTextField();
                        this.textFieldPrixTransport.setEditable(false);
                        this.textFieldPrixTransport
                                .setHorizontalAlignment(SwingConstants.RIGHT);
                        this.textFieldPrixTransport
                                .setFont(new Font("Tahoma", Font.BOLD, 10));
                        this.textFieldPrixTransport
                                .setBackground(new Color(245, 245, 220));
                        panel_2.add(this.textFieldPrixTransport);
                        this.textFieldPrixTransport.setColumns(10);

                    }
                    {
                        JLabel lblNewLabel_3 = new JLabel("TOTAL : ");
                        lblNewLabel_3
                                .setHorizontalAlignment(SwingConstants.RIGHT);
                        lblNewLabel_3.setForeground(new Color(0, 128, 0));
                        lblNewLabel_3
                                .setFont(new Font("Tahoma", Font.BOLD, 12));
                        panel_2.add(lblNewLabel_3);
                    }
                    {
                        this.textFieldPrixTotal = new JTextField();
                        this.textFieldPrixTotal.setEditable(false);
                        this.textFieldPrixTotal
                                .setHorizontalAlignment(SwingConstants.RIGHT);
                        this.textFieldPrixTotal
                                .setForeground(new Color(0, 128, 0));
                        this.textFieldPrixTotal
                                .setBackground(new Color(240, 255, 240));
                        this.textFieldPrixTotal
                                .setFont(new Font("Tahoma", Font.BOLD, 12));
                        panel_2.add(this.textFieldPrixTotal);
                        this.textFieldPrixTotal.setColumns(10);

                    }
                }
            }
        }
        {
            this.valoriserLaTableAPartirDuPanier();
            this.valoriserLesPrixAPartirDuPanier();
            this.valoriserBoutonPanierAPartirDuPanier();
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
            this.getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton buttonValider = new JButton("Valider le panier");
                this.construireControleurBoutonValider(buttonValider);
                buttonValider.setActionCommand("OK");
                buttonPane.add(buttonValider);
                this.getRootPane().setDefaultButton(buttonValider);
            }
            {
                JButton buttonVider = new JButton("Vider le panier");
                this.construireControleurBoutonVider(buttonVider);
                buttonVider.setActionCommand("Cancel");
                buttonPane.add(buttonVider);
            }
            {
                JButton buttonContinuer = new JButton("Continuer les achats");
                this.construireControleurBoutonContinuer(buttonContinuer);
                buttonPane.add(buttonContinuer);
            }
        }
    }

    /**
     * Met à jour l'affichage du bouton panier avec le prix total.
     */
    private void valoriserBoutonPanierAPartirDuPanier() {
        this.btnPanier.setText(
                FenetrePanierTomates.DF.format(this.panier.prixTTC()) + " €");
        if (this.panier.estVide()) {
            this.dispose();
        }
    }

    /**
     * Met à jour les champs de prix affichés en fonction du contenu du panier.
     */
    private void valoriserLesPrixAPartirDuPanier() {
        this.textFieldPrixTTC.setText(
                FenetrePanierTomates.DF.format(this.panier.prixTTC()) + " €");
        this.textFieldPrixTransport.setText(
                FenetrePanierTomates.DF.format(this.panier.getPrixTransport())
                        + " €");
        this.textFieldPrixTotal.setText(
                FenetrePanierTomates.DF.format(this.panier.prixTotal()) + " €");
    }

    /**
     * Crée le contrôleur pour le bouton "Recalculer le panier".
     * 
     * @param btnRecalculer Le bouton pour recalculer le panier.
     */
    private void construireControleurBoutonRecalculer(JButton btnRecalculer) {
        btnRecalculer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                FenetrePanierTomates.this.modifierLePanierAPartirDesQuantites();

                FenetrePanierTomates.this.modeleTable.setRowCount(0);
                FenetrePanierTomates.this.valoriserLaTableAPartirDuPanier();

                FenetrePanierTomates.this.valoriserLesPrixAPartirDuPanier();

                FenetrePanierTomates.this
                        .valoriserBoutonPanierAPartirDuPanier();
            }

        });
    }

    /**
     * Remplie la table avec les éléments du panier.
     */
    private void valoriserLaTableAPartirDuPanier() {
        List<LigneCommande> contenu = this.panier.getContenu();
        this.table.setRowHeight(50);
        for (LigneCommande l : contenu) {
            this.modeleTable.addRow(new Object[] {
                    new ImageIcon("./src/main/resources/images/Tomates40x40/"
                            + l.getArticle().getNomImage() + ".jpg"),
                    l.getArticle().getDésignation(),
                    FenetrePanierTomates.DF.format(l.getArticle().getPrixTTC())
                            + " €",
                    l.getQuantité(),
                    FenetrePanierTomates.DF.format(l.getPrixTTC()) + " €" });
        }
    }

    /**
     * Reconstruit le panier à partir des informations de la table.
     */
    private void reconstruireLePanierAPartirDeLaTable() {
        FenetrePanierTomates.this.panier.vider();
        for (int i = 0; i < FenetrePanierTomates.this.modeleTable
                .getRowCount(); i++) {
            String designation = (String) FenetrePanierTomates.this.modeleTable
                    .getValueAt(i, 1);
            int quantite = (int) FenetrePanierTomates.this.modeleTable
                    .getValueAt(i, 3);
            if (quantite != 0) {
                FenetrePanierTomates.this.panier.addLigne(
                        new LigneCommande(FenetrePanierTomates.this.tomates
                                .getTomate(designation), quantite));
            }
        }
    }

    /**
     * Met à jour les quantités dans le panier en fonction de la table.
     */
    private void modifierLePanierAPartirDesQuantites() {
        for (int i = 0; i < FenetrePanierTomates.this.modeleTable
                .getRowCount(); i++) {
            int quantite = (int) FenetrePanierTomates.this.modeleTable
                    .getValueAt(i, 3);
            FenetrePanierTomates.this.panier.modifierQuantité(i, quantite);
        }
        for (int i = 0; i < FenetrePanierTomates.this.panier.getContenu()
                .size(); i++) {
            if (FenetrePanierTomates.this.panier.getContenu().get(i)
                    .getQuantité() == 0) {
                FenetrePanierTomates.this.panier.removeLigne(i);
                i--;
            }
        }
    }

    /**
     * Crée le contrôleur pour le bouton "Vider le panier".
     * 
     * @param buttonVider Le bouton pour vider le panier.
     */
    private void construireControleurBoutonVider(JButton buttonVider) {
        buttonVider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FenetrePanierTomates.this.panier.vider();
                int res = JOptionPane.showConfirmDialog(null,
                        "Voulez-vous vraiment supprimer le panier ?");
                if (res == JOptionPane.YES_OPTION) {
                    FenetrePanierTomates.this.dispose();
                    FenetrePanierTomates.this.btnPanier.setText("0,00 €");
                }
            }
        });
    }

    /**
     * Crée le contrôleur pour le bouton "Continuer les achats".
     * 
     * @param buttonContinuer Le bouton pour continuer les achats.
     */
    private void construireControleurBoutonContinuer(JButton buttonContinuer) {
        buttonContinuer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FenetrePanierTomates.this.dispose();
            }
        });
    }

    /**
     * Crée le contrôleur pour le bouton "Valider le panier".
     * 
     * @param buttonValider Le bouton pour valider le panier.
     */
    private void construireControleurBoutonValider(JButton buttonValider) {
        buttonValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FenetrePanierTomates.this.setModal(false);
                FenetreCoordonneesClient f = new FenetreCoordonneesClient(
                        FenetrePanierTomates.this.panier,
                        FenetrePanierTomates.this.tomates);
                f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                f.setVisible(true);
            }
        });
    }

    /**
     * Classe interne pour rendre l'affichage des images (à la place des noms)
     * dans la table.
     */
    class ImageRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table,
                Object value, boolean isSelected, boolean hasFocus, int row,
                int column) {
            JLabel label = new JLabel();
            if (value != null) {
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setIcon((Icon) value);
            }
            return label;
        }
    }

    /**
     * Classe interne pour éditer les quantités dans la table avec un spinner.
     */
    public static class MySpinnerEditor extends DefaultCellEditor {
        JSpinner sp;
        DefaultEditor defaultEditor;
        JTextField text;

        // initialise le spinner pour la quantité
        public MySpinnerEditor() {
            super(new JTextField());
            this.sp = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
            this.defaultEditor = ((DefaultEditor) this.sp.getEditor());
            this.text = this.defaultEditor.getTextField();
        }

        // prépare et retourne le composant spinner pour l'édition.
        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            // le nouveau modèle du spinner ne doit pas excéder la qté commandée
            this.sp.setModel(
                    new SpinnerNumberModel((int) value, 0, (int) value, 1));
            return this.sp;
        }

        // renvoie la valeur actuelle du spinner
        @Override
        public Object getCellEditorValue() {
            return this.sp.getValue();
        }
    }

}
