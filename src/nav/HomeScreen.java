/*
 * Exercice FIG HES-SO (Sierre)
 * Auteur : Nelson Ribeiro Teixeira
 * Date de création : 16 mai 2018
 * Date de modification : /
 */
package nav;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import components.BackgroundPanel;
import components.HomeIcon;


@SuppressWarnings("serial")
public class HomeScreen extends AppBaseFrame {

	private BackgroundPanel newCenterPanel = new BackgroundPanel("img/HomeBackground2.jpg");

	private HomeIcon galleryIcon;
	private HomeIcon contactsIcon;
	private HomeIcon weatherIcon;
	private HomeIcon calculatorIcon;

	public HomeScreen () {

		// Mis à jour du panel du centre afin de corriger le bug de l'image rognée
		remove(centerPanel);
		add(newCenterPanel, BorderLayout.CENTER);
		newCenterPanel.setLayout(new GridLayout(6,4));

		// Icone de gallerie
		try {
			Image img = ImageIO.read(new File("img/icons/gallery-icon.png"));
			galleryIcon = new HomeIcon("Galerie",new ImageIcon(img));

		} catch (Exception ex) {
			System.out.println(ex);
		}

		galleryIcon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new GalleryScreen();
				HomeScreen.this.dispose();
			}

		});
		newCenterPanel.add(galleryIcon);


		// Icone de contacts
		try {
			Image img = ImageIO.read(new File("img/icons/contacts-icon.png"));
			contactsIcon = new HomeIcon("Contacts",new ImageIcon(img));

		} catch (Exception ex) {
			System.out.println(ex);
		}

		newCenterPanel.add(contactsIcon);

		// Icone de météo
		try {
			Image img = ImageIO.read(new File("img/icons/weather-icon.png"));
			weatherIcon = new HomeIcon("Météo",new ImageIcon(img));

		} catch (Exception ex) {
			System.out.println(ex);
		}

		weatherIcon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new WeatherApp();
				HomeScreen.this.dispose();
			}

		});
		newCenterPanel.add(weatherIcon);

		// Icone de calculatrice
		try {
			Image img = ImageIO.read(new File("img/icons/calculator-icon.png"));
			calculatorIcon = new HomeIcon("Calculatrice",new ImageIcon(img));

		} catch (Exception ex) {
			System.out.println(ex);
		}

		calculatorIcon.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new CalculatorApp();
				HomeScreen.this.dispose();
				
			}
		});

		newCenterPanel.add(calculatorIcon);

		for(int i = 0; i < 20; i++)
			newCenterPanel.add(new JLabel());


	}


}
