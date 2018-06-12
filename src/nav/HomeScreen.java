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
	/*
	 * Fenêtre Home avec les icones.
	 * Elle hérite de AppBaseFrame de manière a avoir le haut (batterie, etc.) et le bas(bouton Home et lock)
	 * 
	 * Affiche les icones des applications dans un gridLayout.
	 * Lorsqu'on clique sur une icone elle nous mène vers l'application de celle-ci.
	 */

	private BackgroundPanel newCenterPanel = new BackgroundPanel("img/HomeBackground2.jpg");

	private HomeIcon galleryIcon;
	private HomeIcon contactsIcon;
	private HomeIcon weatherIcon;
	private HomeIcon calculatorIcon;
	private HomeIcon cameraIcon;
	private HomeIcon blocNotesIcon;
	
	public HomeScreen () {
		/**
		 * Constructeur de l'application qui instancie les HomeIcon (classe) qui sont des boutons menant vers
		 * les applications correspondantes. Les dispose en gridlayout.
		 */

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
		contactsIcon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ContactApp();
				HomeScreen.this.dispose();
			}

		});
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
		
		// Icone caméra
		try {
			Image img = ImageIO.read(new File("img/icons/camera-icon.png"));
			cameraIcon = new HomeIcon("Caméra",new ImageIcon(img));

		} catch (Exception ex) {
			System.out.println(ex);
		}
		
		// Icone bloc notes
		
		try {
			Image img = ImageIO.read(new File("img/icons/blocnotes-icon.png"));
			blocNotesIcon = new HomeIcon("Bloc-notes",new ImageIcon(img));

		} catch (Exception ex) {
			System.out.println(ex);
		}
		cameraIcon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new CameraApp();
				HomeScreen.this.dispose();
			}
			
		});

		newCenterPanel.add(cameraIcon);
		
		
		blocNotesIcon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new NotepadSelection();
				HomeScreen.this.dispose();
			}
		}); 
		
		newCenterPanel.add(blocNotesIcon);
		
		for(int i = 0; i < 18; i++)
			newCenterPanel.add(new JLabel());


	}


}
