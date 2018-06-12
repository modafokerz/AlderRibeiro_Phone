/*
* Smartphone 602_F FIG HES-SO (Sierre)
* Auteur : Nelson Ribeiro Teixeira
* Date de création : 7 juin 2018
* Date de modification : /
*/
package nav;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import components.topButton;
import components.topLabel;


@SuppressWarnings("serial")
public class GalleryRechercheScreen extends GalleryScreen {
	/**
	 * Ecran de recherche pour la gallerie
	 * Permet d'effectuer des recherches par date ou par nom pour les images de la gallerie.
	 * 
	 * Il est composé des boutons du haut (retour) un titre : recherche d'images et un bouton recherche.
	 * Ainsi que deux champs à remplir nom ou date qui génère tous deux des messages d'erreurs si les conditions
	 * de recherche ne sont pas respectées.
	 * 
	 * Exemple : recherche par nom ET par date ou aucun champ rempli.
	 * 
	 * @author Nelson
	 */
	private topButton returnButton = new topButton("img/icons/return-icon.png");
	private topLabel rechercheLabel = new topLabel("Recherche d'images");
	private topButton rechercheButton = new topButton("img/icons/search_icon.png");
	
	private JPanel recherchePanel = new JPanel();
	private boolean errorAlreadyPrint = false;
	private boolean error2AlreadyPrint = false;
	
	public GalleryRechercheScreen() {
		/**
		 * Constructeur qui définit les éléments et les construit à l'instanciation.
		 */
		setTopPanel(returnButton,rechercheLabel,rechercheButton);
		
		returnButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				new GalleryScreen();
				GalleryRechercheScreen.this.dispose();
			}
		});
		
		recherchePanel.setPreferredSize(new Dimension(600,550));
		recherchePanel.setLayout(new FlowLayout());
		
		GRSPanel rechercheParNom = new GRSPanel();
		GRSLabel rechercheNom = new GRSLabel("Par nom : ");
		GRSTextField picNom = new GRSTextField ();
		
		rechercheParNom.add(rechercheNom);
		rechercheParNom.add(picNom);
		
		GRSPanel rechercheParDate = new GRSPanel();
		GRSLabel rechercheDate = new GRSLabel("Par date : ");
		GRSTextField picDate = new GRSTextField();
		
		rechercheParDate.add(rechercheDate);
		rechercheParDate.add(picDate);
		
		recherchePanel.add(rechercheParNom);
		recherchePanel.add(rechercheParDate);
		JLabel tipDate = new JLabel("TIP : La recherche par date doit se faire au format suivant : '04/06/2018'");
		tipDate.setPreferredSize(new Dimension(600,100));
		tipDate.setHorizontalAlignment(SwingConstants.CENTER);
		recherchePanel.add(tipDate);
		
		setMidPanel(recherchePanel);
		
		
		rechercheButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
				if(!(picNom.getText().equals(""))&&!(picDate.getText().equals(""))) {
					JLabel errorLabel = new JLabel("Un seul critère de recherche à la fois !");
					errorLabel.setPreferredSize(new Dimension(600,100));
					errorLabel.setForeground(Color.red);
					errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
					if (!errorAlreadyPrint) {
						
						recherchePanel.add(errorLabel);
						revalidate();
						repaint();
						errorAlreadyPrint = true;
					}
					picNom.setText(null);
					picDate.setText(null);
					
				} else if (picNom.getText().equals("")&&picDate.getText().equals("")) {
					JLabel errorLabel = new JLabel("Champ de recherche vide");
					errorLabel.setPreferredSize(new Dimension(600,100));
					errorLabel.setForeground(Color.red);
					errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
					if (!error2AlreadyPrint) {
						
						recherchePanel.add(errorLabel);
						revalidate();
						repaint();
						error2AlreadyPrint = true;
					}
					picNom.setText(null);
					picDate.setText(null);
				}else {
					if(picNom.getText().equals(""))
						new GalleryScreen(picDate.getText(),true);
					else
						new GalleryScreen(picNom.getText(), false);
					GalleryRechercheScreen.this.dispose();
				}
				
			}
		});
	}
	
	protected class GRSPanel extends JPanel {
		/**
		 * Class qui gère le panel de la GalleryRechercheScreen ou il aura a l'intérieur le champ
		 * à remplir ainsi que le titre.
		 */
		public GRSPanel() {
			setPreferredSize(new Dimension(600,100));
			setLayout(new FlowLayout());
		}
	}
	
	protected class GRSLabel extends JLabel {
		/**
		 * Label à l'intérieur de GRSPanel ou titre du champ.
		 * @param str titre à afficher.
		 */
		public GRSLabel(String str) {
			super(str);
			setPreferredSize(new Dimension(200,100));
			setFont(new Font("Arial", Font.BOLD, 35));
		}
	}
	
	protected class GRSTextField extends JTextField {
		/*
		 * Champ à remplir pour le GRSPanel.
		 */
		public GRSTextField() {
			super();
			setPreferredSize(new Dimension(350,100));
			setFont(new Font("Arial", Font.BOLD, 25));
		}
	}
}
