/*
* Exercice FIG HES-SO (Sierre)
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

import nav.GalleryScreen.topButton;

public class GalleryRechercheScreen extends GalleryScreen {
	private topButton returnButton = new topButton("img/icons/return-icon.png");
	private topLabel rechercheLabel = new topLabel("Recherche d'images");
	private topButton rechercheButton = new topButton("img/icons/search_icon.png");
	
	private JPanel recherchePanel = new JPanel();
	private boolean errorAlreadyPrint = false;
	private boolean error2AlreadyPrint = false;
	
	public GalleryRechercheScreen() {
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
					new GalleryScreen();
					GalleryRechercheScreen.this.dispose();
				}
				
			}
		});
	}
	
	protected class GRSPanel extends JPanel {
		public GRSPanel() {
			setPreferredSize(new Dimension(600,100));
			setLayout(new FlowLayout());
		}
	}
	
	protected class GRSLabel extends JLabel {
		public GRSLabel(String str) {
			super(str);
			setPreferredSize(new Dimension(200,100));
			setFont(new Font("Arial", Font.BOLD, 35));
		}
	}
	
	protected class GRSTextField extends JTextField {
		public GRSTextField() {
			super();
			setPreferredSize(new Dimension(350,100));
			setFont(new Font("Arial", Font.BOLD, 25));
		}
	}
}
