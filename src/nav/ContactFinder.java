package nav;

import java.awt.BorderLayout;
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

import components.HPButton;

/**
 * Classe ContactFinder
 * Est appelée lorsque le bouton "recherche" de ContactApp est cliqué
 * 
 * Recherche sur tous les champs de contact (nom, prénom, adresse, tel et email)
 * 
 * Son design est constitué de :
 * - Un topPanel avec le texte "Recherche"
 * - Un panel central avec un JTextField pour entrer la recherche
 * - Un panel avec le bouton retour et chercher.
 * 
 * @author Jean-Marie Alder
 *
 */
@SuppressWarnings("serial")
public class ContactFinder extends AppBaseFrame {

	private JPanel contactFinderPanel = new JPanel();//Panel modification contact
	private JPanel topTextPanel = new JPanel(); //Panel pour titre
	private JLabel topText = new JLabel("Recherche");
	private JPanel middlePanel = new JPanel(); //Panel pour les informations de contact
	private JPanel buttons = new JPanel(); //Panel pour les boutons edit, supprimer et retour
	
	private JTextField input = new JTextField();

	private HPButton chercher = new HPButton("Chercher");
	private HPButton retour = new HPButton("Retour");
	
	/**
	 * Constructeur de la recherche de contact.
	 * 
	 * Design de la frame.
	 * 
	 * Le focus est donné au champ de recherche pour povoir directement écrire.
	 */
	public ContactFinder() {
		super();
		remove(centerPanel);
		
		topText.setPreferredSize(new Dimension(300, 60)); //Taille du composant (taille du JLabel)
		topText.setHorizontalAlignment(SwingConstants.CENTER);
		topText.setVerticalAlignment(SwingConstants.TOP);
		topText.setBackground(Color.WHITE);
		topText.setFont(new Font("Arial Black", Font.PLAIN, 25));

		topTextPanel.setPreferredSize(new Dimension(600,200));
		topTextPanel.setLayout(new BorderLayout());
		topTextPanel.add(topText, BorderLayout.SOUTH);
		
		input.setFont(new Font("Arial Black", Font.PLAIN, 25));
		input.setPreferredSize(new Dimension(580, 180));
		input.setHorizontalAlignment(JTextField.CENTER);
		
		middlePanel.setLayout(new FlowLayout());
		middlePanel.setPreferredSize(new Dimension(600,200));
		middlePanel.setBackground(Color.LIGHT_GRAY);
		middlePanel.add(input);
		
		chercher.setPreferredSize(new Dimension(250,80));
		retour.setPreferredSize(new Dimension(250,80));
		
		chercher.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ContactApp.search = input.getText();
				new ContactApp();
				ContactFinder.this.dispose();
			}
		});
		
		buttons.setLayout(new FlowLayout());
		buttons.setPreferredSize(new Dimension(600,100));
		
		retour.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ContactApp();
				ContactFinder.this.dispose();
			}
		});
		buttons.add(chercher);
		buttons.add(retour);
		
		contactFinderPanel.setLayout(new BorderLayout());
		contactFinderPanel.add(topTextPanel, BorderLayout.NORTH);
		contactFinderPanel.add(middlePanel, BorderLayout.CENTER);
		contactFinderPanel.add(buttons, BorderLayout.SOUTH);
		
		add(contactFinderPanel, BorderLayout.CENTER);
		
		//Focus sur le JTextfield
		input.grabFocus();
		
	}
}
