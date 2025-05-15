package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import modele.Tomates;

/**
 * Cette classe représente une fenêtre de dialogue affichant les conseils de
 * culture pour les tomates. Elle s'affiche sous forme de JDialog avec un texte
 * de présentation suivi d'une zone déroulante contenant les conseils.
 */
public class FenetreConseilCulture extends JDialog {

    /** Panneau principal de contenu de la fenêtre */
    private final JPanel contentPanel = new JPanel();

    /**
     * Constructeur de la fenêtre de conseils de culture. Configure la
     * disposition, le titre, l'icône, et le contenu affiché.
     */
    public FenetreConseilCulture() {
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.setTitle("Ô'Tomates");
        this.setIconImage(Toolkit.getDefaultToolkit()
                .getImage("./src/main/resources/images/Tomate10.png"));
        this.setBounds(100, 100, 450, 520);
        this.getContentPane().setLayout(new BorderLayout());

        // Panneau principal
        this.contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.getContentPane().add(this.contentPanel, BorderLayout.CENTER);
        this.contentPanel.setLayout(new BorderLayout(0, 0));

        // En-tête avec titre et icône
        {
            JPanel panel = new JPanel();
            panel.setBorder(new EmptyBorder(5, 0, 5, 100));
            this.contentPanel.add(panel, BorderLayout.NORTH);
            {
                JLabel lblNewLabel_1 = new JLabel("Conseils de culture");
                lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
                lblNewLabel_1.setForeground(new Color(0, 128, 0));
                lblNewLabel_1.setFont(new Font("Segoe Print", Font.PLAIN, 25));
                panel.add(lblNewLabel_1);
            }
            {
                JLabel lblNewLabel_2 = new JLabel(new ImageIcon(
                        "./src/main/resources/images/Tomate10.png"));
                panel.add(lblNewLabel_2);
            }
        }

        // Corps du texte contenant les conseils
        {
            JPanel panel = new JPanel();
            this.contentPanel.add(panel, BorderLayout.CENTER);
            panel.setLayout(new BorderLayout(0, 0));

            // Titre du texte des conseils
            {
                JTextPane textPane = new JTextPane();
                textPane.setBorder(new LineBorder(new Color(0, 128, 0)));
                textPane.setEditable(false);
                textPane.setFont(new Font("Segoe Print", Font.BOLD, 14));
                textPane.setForeground(new Color(0, 128, 0));
                textPane.setText(Tomates.CONSEILS_DE_CULTURE_TITRE);
                panel.add(textPane, BorderLayout.NORTH);
            }

            // Zone scrollable avec les conseils détaillés
            {
                JScrollPane scrollPane = new JScrollPane();
                panel.add(scrollPane, BorderLayout.CENTER);
                {
                    JTextPane textPane = new JTextPane();
                    textPane.setFont(new Font("Tahoma", Font.BOLD, 12));
                    textPane.setEditable(false);
                    textPane.setText(Tomates.CONSEILS_DE_CULTURE);
                    scrollPane.setViewportView(textPane);
                }
            }
        }

        // Panneau de bouton avec le bouton OK pour fermer la fenêtre
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            this.getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("OK");
                this.construireControleurBoutonOK(okButton);
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                this.getRootPane().setDefaultButton(okButton);
            }
        }
    }

    /**
     * Construit le contrôleur pour le bouton OK afin de fermer la fenêtre.
     *
     * @param okButton Le bouton OK à contrôler
     */
    private void construireControleurBoutonOK(JButton okButton) {
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FenetreConseilCulture.this.dispose();
            }
        });
    }
}
