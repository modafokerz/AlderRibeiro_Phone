package nav;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

import components.HPButton;
import objects.Contact;

/**
 * Classe d'édition de contact
 * Est appelée par la classe ContactApp lors de la modification
 * ou la création d'un contact.
 * 
 * Son design est composé de:
 * - Un topPanel avec un titre
 * - Un panel pour la photo
 * - Un panel pour les informations des contacts
 *  - Un JLabel pour les titre et JTextField pour les champs
 *  - Un JFormattedTextField pour les numéros de tel.
 * - Un panel pour les boutons enregistrer, supprimer et retour.
 * 
 * Cette classe contient aussi :
 * - Un contact sélectionné dans "ContactApp" + sa copie statique
 * - Un nouveau contact créé lors de la modification ou création
 * - Une image de contact + sa copie statique.
 * - Un "Flag" désignant si le contact vient d'être créé ou non.
 * 
 * @see ContactApp
 * 
 * @author Jean-Marie Alder
 *
 */
@SuppressWarnings("serial")
public class ContactEdition extends AppBaseFrame {

	private Contact oldContact; //Contact selectionné dans contactApp
	private static Contact staticOldContact;
	private Contact newContact; //Contact après modifications
	private Image photo;//Photo du contact
	private static Image staticPhoto;
	private boolean isNew = false; //Si vrai, crée un nouveau contact

	//Initialisation des panels
	private JPanel contactEditPanel = new JPanel();//Panel modification contact
	private JPanel topTextPanel = new JPanel(); //Panel pour titre
	private JLabel topText = new JLabel("Editeur de contact");
	private JPanel photoPanel = new JPanel();
	private JPanel informations = new JPanel(); //Panel pour les informations de contact
	private JPanel buttons = new JPanel(); //Panel pour les boutons edit, supprimer et retour

	//initialisations des 3 boutons du bas
	private HPButton supprimer = new HPButton("Supprimer");
	private HPButton enregistrer = new HPButton("Enregistrer");
	private HPButton retour = new HPButton("Retour");

	//Initialisation du bouton photo
	private static JButton photoButton = new JButton();

	//Initialisation des champs du contact
	//les labels commencent par l et les textFields commencent par tf

	private JLabel lPrenom = new JLabel("Prenom :");
	private JLabel lNom = new JLabel("Nom :");
	private JLabel lTel = new JLabel("Tel (10n) :");
	private JLabel lAdresse = new JLabel("Adresse :");
	private JLabel lEmail = new JLabel("Email :");
	private JTextField tfPrenom = new JTextField();
	private JTextField tfNom = new JTextField();
	private JFormattedTextField tfTel = new JFormattedTextField();
	private JTextField tfAdresse = new JTextField();
	private JTextField tfEmail = new JTextField();

	/**
	 * Constructeur de ContactEdition avec un contact en paramètre.
	 * 
	 * Design de la frame.
	 * 
	 * @param contact	Le contact à afficher
	 * 
	 * throws IOException	Si une erreur de lecture/écriture est survenue
	 * throws ParseException	Si une erreur lors de l'analyse du masque est détectée
	 */
	public ContactEdition(Contact contact) {
		super();
		remove(centerPanel);

		oldContact = contact;
		staticOldContact = oldContact;
		
		try {
			photo = ImageIO.read(new File(oldContact.getPhotoPath()));
		} catch (IOException e1) {
			System.out.println("Photo introuvable");
			e1.printStackTrace();
		}
		staticPhoto= photo;

		topText.setPreferredSize(new Dimension(300, 50)); //Taille du composant (taille du JLabel)
		topText.setHorizontalAlignment(SwingConstants.CENTER);
		topText.setVerticalAlignment(SwingConstants.TOP);
		topText.setBackground(Color.WHITE);
		topText.setFont(new Font("Arial Black", Font.PLAIN, 25));

		topTextPanel.setPreferredSize(new Dimension(600,50));
		topTextPanel.setLayout(new FlowLayout());
		topTextPanel.add(topText);

		informations.setLayout(new GridLayout(5,2));
		informations.setPreferredSize(new Dimension(580,300));
		informations.setBackground(Color.LIGHT_GRAY);
		
		try {
			MaskFormatter mf1 = new MaskFormatter("##########");
			mf1.setPlaceholder("0000000000");
			tfTel = new JFormattedTextField(mf1);
		} catch (ParseException e1) {
			System.out.println("ParseException levée...");
			e1.printStackTrace();
		}
		tfPrenom.setText(contact.getPrenom());
		tfNom.setText(contact.getNom());
		tfTel.setText(contact.getTel());
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

		photoButton.setPreferredSize(new Dimension(160,160));
		photoButton.setVisible(true);
		photoButton.setIcon(new ImageIcon(getResizedPhoto(photo)));
		photoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				staticOldContact.setNom(tfNom.getText());
				staticOldContact.setPrenom(tfPrenom.getText());
				staticOldContact.setTel(tfTel.getText());
				staticOldContact.setAdresse(tfAdresse.getText());
				staticOldContact.setEmail(tfEmail.getText());
				new GalleryScreen(true);
				ContactEdition.this.dispose();
			}
		});

		photoPanel.setLayout(new BorderLayout());
		photoPanel.add(photoButton, BorderLayout.CENTER);

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
					new ContactApp(true);
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
		contactEditPanel.add(photoPanel);
		contactEditPanel.add(informations);
		contactEditPanel.add(buttons);

		add(contactEditPanel, BorderLayout.CENTER);

		tfPrenom.grabFocus();

		System.out.println("ContactEdition Construite!");
	}

	/**
	 * Constructeur pour l'ajout d'un nouveau contact
	 * 
	 * Appelle le constructeur principal en lui attribuant un nouveau contact.
	 * 
	 * Donne la valeur "true" au "flag" nouveau contact
	 * 
	 * @see ContactEdition#ContactEdition(Contact)
	 * @see Contact#Contact()
	 */
	public ContactEdition() {
		this(new Contact()); //Appel de contact sans param. (tout = "")
		isNew = true;

	}
	
	/**
	 * Permet de mettre à jour la photo du contact
	 * 
	 * remplacée par setOldcontactPhotoPath()
	 * 
	 * @see ContactEdition#getResizedPhoto(Image)
	 * @see ContactEdition#setOldContactPhotoPath(String)
	 * 
	 * @deprecated depuis le 09/06/2018
	 */
	public static void updatePhoto() {
		try {
			staticPhoto = ImageIO.read(new File(staticOldContact.getPhotoPath()));
		} catch (IOException e1) {
			System.out.println("Photo introuvable");
			e1.printStackTrace();
		}
		photoButton.setIcon(new ImageIcon(getResizedPhoto(staticPhoto)));
	}
	
	/**
	 * Met à jour le chemin de la photo du contact
	 * 
	 * @see Contact#setPhotoPath(String)
	 * 
	 * @param path	Le nouveau chemin de l'image
	 */
	public static void setOldContactPhotoPath(String path) {
		staticOldContact.setPhotoPath(path);
	}
	
	/**
	 * Retourne le chemin de la photo du contact
	 * 
	 * @see Contact#getPhotoPath()
	 * 
	 * @return	le chemin actuel de la photo du contact
	 */
	public String getOldContactPhotoPath() {
		return staticOldContact.getPhotoPath();
	}
	
	

	/**
	 * Permet de prendre en compte les changements faits dans les textfields
	 * et les ajoute dans une nouvelle instance de Contact
	 * 
	 * 
	 * @see Contact
	 */
	public void setNewContact() {
		this.newContact = new Contact(staticOldContact.getPhotoPath(), tfNom.getText(), tfPrenom.getText(), tfTel.getText(),
				tfAdresse.getText(), tfEmail.getText());
	}

	/**
	 * Redimentionne une image avec la taille nécessaire
	 * pour l'insérer dans le bouton photo du contact.
	 * 
	 * @param image	L'image à modifier
	 * @return		L'image "croppée" dans la bonne dimension
	 */
	private static Image getResizedPhoto(Image image) {
		return image.getScaledInstance( 160, 160,  java.awt.Image.SCALE_SMOOTH ) ;
	}
	
	public static Contact getStaticOldContact() {
		return staticOldContact;
	}
}
