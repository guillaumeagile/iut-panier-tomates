package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import modele.Couleur;
import modele.OutilsBaseDonneesTomates;
import modele.Panier;
import modele.Tomate;
import modele.Tomates;
import modele.TypeTomate;

/**
 * Représente l'application principale de gestion des graines de tomates. Cette
 * classe est une interface graphique permettant de consulter, filtrer et
 * ajouter des tomates à un panier.
 */
public class ApplicationTomates extends JFrame {

    private Tomates tomates;
    private Panier panier;

    private JPanel contentPane;

    private JList list;
    private JScrollPane scrollPane;
    private JButton btnPanier;
    private JComboBox comboBoxType;
    private JComboBox comboBoxCouleur;

    /**
     * Point d'entrée de l'application. Lance l'interface graphique et charge
     * les données des tomates.
     *
     * @param args les arguments de la ligne de commande (non utilisés)
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    // construire la base de tomates
                    Tomates tomates = OutilsBaseDonneesTomates
                            .générationBaseDeTomates(
                                    "src/main/resources/data/tomates.json");
                    ApplicationTomates frame = new ApplicationTomates(tomates);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Constructeur de la fenêtre principale de l'application. Initialise
     * l'affichage des tomates et les composants graphiques.
     *
     * @param tomates la base de données des tomates à afficher
     */
    public ApplicationTomates(Tomates tomates) {
        this.tomates = tomates;

        this.panier = new Panier();

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setForeground(new Color(0, 128, 0));
        this.setTitle("Ô'Tomates");
        this.setIconImage(Toolkit.getDefaultToolkit()
                .getImage("./src/main/resources/images/Tomate10.png"));
        this.setSize(579, 624);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.contentPane = new JPanel();
        this.setContentPane(this.contentPane);
        this.contentPane.setLayout(new BorderLayout(0, 0));

        // panneau NORTH de la fenêtre
        JPanel panel1 = new JPanel();
        panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.contentPane.add(panel1, BorderLayout.NORTH);
        panel1.setLayout(new BorderLayout(0, 0));

        JPanel panel1_2 = new JPanel();
        panel1.add(panel1_2, BorderLayout.CENTER);

        JLabel label1 = new JLabel("Nos graines de tomates");
        label1.setForeground(new Color(0, 128, 0));
        panel1_2.add(label1);
        label1.setFont(new Font("Segoe Print", Font.BOLD, 25));

        JLabel label2 = new JLabel(
                new ImageIcon("./src/main/resources/images/Tomate10.png"));
        panel1_2.add(label2);

        this.btnPanier = new JButton("0,00 €");
        this.construireControleurBoutonPanier();
        this.btnPanier.setForeground(Color.BLACK);
        this.btnPanier.setBackground(new Color(255, 228, 196));
        this.btnPanier.setIcon(
                new ImageIcon("./src/main/resources/images/panier2.png"));
        panel1.add(this.btnPanier, BorderLayout.EAST);

        // panneau CENTER de la fenêtre
        JPanel panel2 = new JPanel();
        this.contentPane.add(panel2, BorderLayout.CENTER);
        panel2.setLayout(new BorderLayout(0, 0));

        this.scrollPane = new JScrollPane();
        panel2.add(this.scrollPane, BorderLayout.CENTER);
        List<Tomate> listeDesTomates = tomates.getTomates();
        this.list = new JList(
                this.constructionTableauDesTomates(listeDesTomates));
        this.construireControleurListe(tomates);
        this.list.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.list.setFont(new Font("Arial", Font.BOLD, 16));
        this.scrollPane.setViewportView(this.list);

        // panneau SOUTH de la fenêtre
        JPanel panel3 = new JPanel();
        panel3.setBorder(new EmptyBorder(0, 5, 5, 0));
        this.contentPane.add(panel3, BorderLayout.SOUTH);
        panel3.setLayout(new BorderLayout(0, 0));

        JPanel panel3_1 = new JPanel();
        panel3_1.setBorder(
                new TitledBorder(new LineBorder(new Color(0, 128, 0), 2),
                        "Filtres", TitledBorder.LEFT, TitledBorder.TOP, null,
                        new Color(0, 128, 0)));
        panel3.add(panel3_1, BorderLayout.CENTER);

        JPanel panel3_1_1 = new JPanel();
        panel3_1.add(panel3_1_1);
        panel3_1_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel label3 = new JLabel("");
        panel3_1_1.add(label3);
        label3.setHorizontalAlignment(SwingConstants.RIGHT);
        label3.setIcon(
                new ImageIcon("./src/main/resources/images/melange.png"));

        this.comboBoxType = new JComboBox();
        this.comboBoxType
                .setToolTipText("Filtre sur les catégories de tomates");
        this.comboBoxType.addItem("Toutes les tomates");
        for (TypeTomate c : TypeTomate.values()) {
            this.comboBoxType.addItem(c.getDénomination());
        }

        panel3_1_1.add(this.comboBoxType);
        panel3_1.setLayout(new GridLayout(1, 2, 0, 0));

        JPanel panel3_1_2 = new JPanel();
        panel3_1_2.setBorder(new EmptyBorder(0, 0, 0, 0));
        panel3_1_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel label4 = new JLabel();
        label4.setHorizontalAlignment(SwingConstants.RIGHT);
        label4.setIcon(new ImageIcon(
                "./src/main/resources/images/paletteCouleur.png"));
        label4.setFont(new Font("Arial Narrow", Font.BOLD, 10));
        panel3_1_2.add(label4);
        panel3_1.add(panel3_1_2);

        JPanel panel3_2 = new JPanel();
        panel3_2.setBorder(new EmptyBorder(20, 20, 20, 10));
        panel3.add(panel3_2, BorderLayout.EAST);

        this.comboBoxCouleur = new JComboBox();
        this.comboBoxCouleur
                .setToolTipText("Filtre sur les couleurs de tomates");
        this.comboBoxCouleur.addItem("Toutes les couleurs");
        for (Couleur c : Couleur.values()) {
            this.comboBoxCouleur.addItem(c.getDénomination());
        }

        panel3_1_2.add(this.comboBoxCouleur);
        panel3_2.setLayout(new BorderLayout(0, 0));

        this.construireControleurComboBoxType();
        this.construireControleurComboBoxCouleur();

        JButton btnConseils = new JButton("");
        this.construireControleurBoutonConseils(btnConseils);
        btnConseils.setToolTipText("Conseils de culture");
        panel3_2.add(btnConseils);
        btnConseils.setIcon(
                new ImageIcon("./src/main/resources/images/mainSemis.png"));

    }

    /**
     * Ajoute un écouteur au bouton "Conseils" pour afficher une fenêtre d'aide.
     */
    private void construireControleurBoutonConseils(JButton btnConseils) {
        btnConseils.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FenetreConseilCulture fcc = new FenetreConseilCulture();
                fcc.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                fcc.setVisible(true);
            }
        });
    }

    /**
     * Ajoute un écouteur de sélection au comboBox de type de tomate.
     */
    private void construireControleurComboBoxType() {
        this.comboBoxType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ApplicationTomates.this.mettreAJourListeDesArticles();
            }
        });
    }

    /**
     * Ajoute un écouteur de sélection au comboBox de couleur de tomate.
     */
    private void construireControleurComboBoxCouleur() {
        this.comboBoxCouleur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ApplicationTomates.this.mettreAJourListeDesArticles();
            }
        });
    }

    /**
     * Ajoute un écouteur sur la liste pour ouvrir la fiche d'une tomate au
     * clic.
     */
    private void construireControleurListe(Tomates tomates) {
        this.list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                FenetreFicheTomate fft = new FenetreFicheTomate(
                        ApplicationTomates.this.list.getSelectedValue()
                                .toString(),
                        tomates, ApplicationTomates.this.btnPanier,
                        ApplicationTomates.this.panier);
                fft.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                fft.setVisible(true);
            }
        });
    }

    /**
     * Ajoute un écouteur au bouton panier pour afficher son contenu.
     */
    private void construireControleurBoutonPanier() {
        this.btnPanier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (ApplicationTomates.this.panier.estVide()) {
                    JOptionPane.showMessageDialog(null, "le panier est vide");
                } else {
                    FenetrePanierTomates f = new FenetrePanierTomates(
                            ApplicationTomates.this.btnPanier,
                            ApplicationTomates.this.tomates,
                            ApplicationTomates.this.panier);
                    f.setDefaultCloseOperation(
                            WindowConstants.DISPOSE_ON_CLOSE);
                    f.setVisible(true);
                }

            }
        });
    }

    /**
     * Construit un tableau contenant les désignations des tomates.
     *
     * @param listeDeTomates la liste des tomates à afficher
     * @return un tableau de chaînes de caractères
     */
    private String[] constructionTableauDesTomates(
            List<Tomate> listeDeTomates) {
        String[] tabNomsTomates = new String[listeDeTomates.size()];
        int i = 0;
        for (Tomate t : listeDeTomates) {
            tabNomsTomates[i] = t.getDésignation();
            i++;
        }
        return tabNomsTomates;
    }

    /**
     * Met à jour la liste de tomates affichée selon les filtres sélectionnés.
     */
    private void mettreAJourListeDesArticles() {
        List<Tomate> listeTomates;
        String couleurChoisie = this.comboBoxCouleur.getSelectedItem()
                .toString();
        String typeTomateChoisie = this.comboBoxType.getSelectedItem()
                .toString();

        int rangCouleurChoisie = this.comboBoxCouleur.getSelectedIndex();
        int rangTypeTomateChoisie = this.comboBoxType.getSelectedIndex();

        if (rangTypeTomateChoisie == 0 && rangCouleurChoisie == 0) {
            listeTomates = this.tomates.getTomates();
        } else if (rangTypeTomateChoisie == 0 && rangCouleurChoisie != 0) {
            listeTomates = this.tomates
                    .tomatesDeCouleur(Couleur.getCouleur(couleurChoisie));
        } else if (rangTypeTomateChoisie != 0 && rangCouleurChoisie == 0) {
            listeTomates = this.tomates
                    .tomatesDeType(TypeTomate.getTypeTomate(typeTomateChoisie));
        } else {
            listeTomates = this.tomates.tomatesDetypeDeCouleur(
                    TypeTomate.getTypeTomate(typeTomateChoisie),
                    Couleur.getCouleur(couleurChoisie));
        }

        this.list = new JList(this.constructionTableauDesTomates(listeTomates));
        this.construireControleurListe(this.tomates);
        this.list.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.list.setFont(new Font("Arial", Font.BOLD, 16));
        this.scrollPane.setViewportView(this.list);
    }

}
