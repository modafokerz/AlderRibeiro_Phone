package nav;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import components.HPButton;
import objects.Contact;

public class ContactEdition extends AppBaseFrame {

	private Contact oldContact;
	private Contact newContact;
	private boolean isNew = false;

	private JPanel contactEditPanel = new JPanel();//Panel modification contact
	private JPanel topTextPanel = new JPanel(); //Panel pour titre
	private JLabel topText = new JLabel("Editeur de contact");
	private JPanel informations = new JPanel(); //Panel pour les informations de contact
	private JPanel buttons = new JPanel(); //Panel pour les boutons edit, supprimer et retour

	private HPButton supprimer = new HPButton("Supprimer");
	private HPButton enregistrer = new HPButton("Enregistrer");
	private HPButton retour = new HPButton("Retour");

	//les labels commencent par l et les textFields commencent par tf
	private JLabel lPrenom = new JLabel("Prenom :");
	private JLabel lNom = new JLabel("Nom :");
	private JLabel lTel = new JLabel("Tel :");
	private JLabel lAdresse = new JLabel("Adresse :");
	private JLabel lEmail = new JLabel("Email :");
	private JTextField tfPrenom = new JTextField();
	private JTextField tfNom = new JTextField();
	private JTextField tfTel = new JTextField();
	private JTextField tfAdresse = new JTextField();
	private JTextField tfEmail = new JTextField();

	public ContactEdition(Contact contact) {
		super();
		remove(centerPanel);

		oldContact = contact;

		topText.setPreferredSize(new Dimension(300, 60)); //Taille du composant (taille du JLabel)
		topText.setHorizontalAlignment(SwingConstants.CENTER);
		topText.setVerticalAlignment(SwingConstants.TOP);
		topText.setBackground(Color.WHITE);
		topText.setFont(new Font("Arial Black", Font.PLAIN, 25));

		topTextPanel.setPreferredSize(new Dimension(600,100));
		topTextPanel.setLayout(new FlowLayout());
		topTextPanel.add(topText);

		informations.setLayout(new GridLayout(5,2));
		informations.setPreferredSize(new Dimension(600,480));
		informations.setBackground(Color.LIGHT_GRAY);

		tfPrenom.setText(contact.getPrenom());
		tfNom.setText(contact.getNom());
		tfTel.setText(contact.getNom());
		tfAdresse.setText(contact.getAdresse());
		tfEmail.setText(contact.getEmail());

		lPrenom.setFont(new Font("Arial Black", Font.PLAIN, 25));
		lNom.setFont(new Font("Arial Black", Font.PLAIN, 25));
		lTel.setFont(new Font("Arial Black", Font.PLAIN, 25));
		lAdresse.setFont(new Font("Arial Black", Font.PLAIN, 25));
		lEmail.setFont(new Font("Arial Black", Font.PLAIN, 25));

		tfPrenom.setFont(new Font("Arial Black", Font.PLAIN, 20));
		tfNom.setFont(new Font("Arial Black", Font.PLAIN, 20));
		tfTel.setFont(new Font("Arial Black", Font.PLAIN, 20));
		tfAdresse.setFont(new Font("Arial Black", Font.PLAIN, 20));
		tfEmail.setFont(new Font("Arial Black", Font.PLAIN, 20));


		informations.add(lPrenom);
		informations.add(tfPrenom);
		informations.add(lNom);
		informations.add(tfNom);
		informations.add(lTel);
		informations.add(tfTel);
		informations.add(lAdresse);
		informations.add(tfAdresse);
		informations.add(lEmail);
		informations.add(tfEmail);

		supprimer.setPreferredSize(new Dimension(190, 50));
		enregistrer.setPreferredSize(new Dimension(190, 50));
		retour.setPreferredSize(new Dimension(190, 50));

		supprimer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				//Si nouveau contact, le bouton delete ne fait rien
				if(!isNew) {
					new ContactApp(oldContact, true);
				}else {
					new ContactApp();
				}
				
				ContactEdition.this.dispose();
			}
		});

		enregistrer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				setNewContact();
				if(isNew)
					new ContactApp(newContact);
				else
					new ContactApp(oldContact, newContact);
				
				ContactEdition.this.dispose();
			}
		});
		
		retour.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ContactApp();
				ContactEdition.this.dispose();
			}
		});

		buttons.setLayout(new FlowLayout());
		buttons.setPreferredSize(new Dimension(600, 50));
		buttons.add(supprimer);
		buttons.add(enregistrer);
		buttons.add(retour);

		contactEditPanel.setPreferredSize(new Dimension(600,650));
		contactEditPanel.setLayout(new FlowLayout());
		contactEditPanel.add(topTextPanel);
		contactEditPanel.add(informations);
		contactEditPanel.add(buttons);

		add(contactEditPanel, BorderLayout.CENTER);

		System.out.println("ContactEdition Construite!");
	}

	//Constructeur pour l'ajout d'un nouveau contact
	public ContactEdition() {
		this(new Contact()); //Appel de contact sans param. (tout = "")
		isNew = true;

	}

	//Permet de prendre en compte les changements faits dans les textfields
	public void setNewContact() {
		this.newContact = new Contact(tfNom.getText(), tfPrenom.getText(), tfTel.getText(),
										tfAdresse.getText(), tfEmail.getText());
	}
}
