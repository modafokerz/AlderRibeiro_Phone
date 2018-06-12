package nav;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import objects.Contact;

/*
 * Projet Smartphone
 * Auteur : Jean-Marie Alder
 * Date de création : 6.6.18
 * Date de modification :
 * Description : Application contact.
 */
/**
 * Classe ContactApp
 * Ecran principal de l'application contact.
 * Est appelée lorsque l'on clique sur l'icone "Contact" du "HomeScreen".
 * Est aussi appelée lors de la fermeture de "ContactEdition".
 * 
 * Son design est constitué des éléments suivants :
 * - Un "topPanel" en haut, avec : 
 *  - Un label titre
 *  - Un bouton recherche qui appelle ContactFinder
 *  - Un bouton ajouter un contact
 * 
 * -Un panel "ContactPanel" constitué d'une liste de boutons "contact"
 * 
 * Elle garde aussi un index pour mémoriser le contact et un index interne
 * pour initialiser les boutons contacts correctement.
 * 
 * Elle mémorise aussi le manière static une recherche sous la forme d'une "String".
 * 
 * Elle est aussi constituée d'une liste de contact sous forme de "ArrayList"
 * cette liste est déserialisée à partir du fichier saves/contacts.ser
 * et permet la serialisation dans le même fichier.
 * 
 * @see HomeScreen
 * @see ContactEdition
 * @see TopButton
 * @see ContactButton
 * @see ContactFinder
 * @see ContactApp#serializeObject()
 * @see ContactApp#deSerializeObject()
 * 
 * @author Jean-Marie Alder
 *
 */
@SuppressWarnings("serial")
public class ContactApp extends AppBaseFrame {
	//Pour garder en mémoire le dernier index de contact cliqué
	public static int lastIndex;
	private static int lastInternIndex;

	//variable statique pour la recherche.
	public static String search = null;

	//Tableau de contacts
	private ArrayList<Contact> contacts = new ArrayList<Contact>();

	//Initialisation des pannels
	private JPanel contactPanel = new JPanel();//Panel principal app contact
	private JPanel topPanel = new JPanel(); //Panel pour titre et btn ajouter
	private JPanel contactList = new JPanel(); //Panel pour la liste des contacts
	private JScrollPane scrollPane; //Panel pour faire défiler la liste de contacts

	//Initialisation des boutons et labels
	private TopButton recherche = new TopButton("img/icons/search_icon.png");
	private JLabel topText = new JLabel("Contacts");
	private	TopButton addContact = new TopButton("img/icons/add_icon.png");

	/**
	 * Constructeur par défaut de la frame contact.
	 * Toujours appelée par les autres constructeurs. (this())
	 * 
	 * Initialise la frame.
	 * Les "TopButtons" sont initialisés avec les actions correspondantes.
	 * 
	 * @see ContactApp#placeContacts()
	 * @see ContactApp#deSerializeObject()
	 * @see ContactApp#askForSerialization()
	 */
	public ContactApp(){
		super();

		//Supprime le fond d'écran
		remove(centerPanel);

		//deserialisation pour reprendre la liste des contacts
		deSerializeObject();

		//Design de la liste de contacts
		contactList.setLayout(new GridLayout(0,1));

		//Place les boutons contacts et les initialise
		placeContacts();

		//Design du pannel titre avec boutons rechercher et ajouter.
		topText.setPreferredSize(new Dimension(300, 100)); //Taille du composant (taille du JLabel)
		topText.setHorizontalAlignment(SwingConstants.CENTER);
		topText.setVerticalAlignment(SwingConstants.TOP);
		topText.setBackground(Color.WHITE);
		topText.setFont(new Font("Arial Black", Font.PLAIN, 25));

		//ActionListener qui ouvre la fenêtre d'édition vierge (nouveau contact)
		addContact.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Si pas de recherche en cours, serialiser l'objet, 
				//sinon garder l'original et réinit. la recherche.
				askForSerialization();

				//Refresh
				new ContactEdition();
				ContactApp.this.dispose();

			}
		});

		//ActionListener ouvrant la fenêtre de recherche
		recherche.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Si pas de recherche en cours, serialiser l'objet, 
				//sinon garder l'original et réinit. la recherche.
				askForSerialization();

				//Refresh
				new ContactFinder();
				ContactApp.this.dispose();
			}
		});

		//Design final du top panel (titre et boutons)
		topPanel.setPreferredSize(new Dimension(600,100));
		topPanel.setLayout(new FlowLayout());
		//Ajout des éléments au top panel
		topPanel.add(recherche);
		topPanel.add(topText);
		topPanel.add(addContact);


		//Création de la liste de défilement vertical
		scrollPane = new JScrollPane (contactList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(550,540));

		//Design de la frame
		contactPanel.setPreferredSize(new Dimension(600,650));
		contactPanel.setLayout(new FlowLayout());
		contactPanel.add(topPanel);
		contactPanel.add(scrollPane);
		add(contactPanel, BorderLayout.CENTER);

	}

	/**
	 * Constructeur lors d'un nouveau contact
	 * 
	 * Est appelée lorsqu'un nouveau contact est sauvegardé
	 * 
	 * @see ContactEdition
	 * 
	 * @param newContact	Le contact créé avec les nouvelles informations
	 */
	public ContactApp(Contact newContact) {
		this();//appelle le constructeur par défaut
		contacts.add(newContact); //Ajoute le contact à la fin
		askForSerialization();

		//Refresh du layout
		ContactApp.this.dispose();
		new ContactApp();

	}

	/**
	 * Constructeur lors de la modification d'un contact.
	 * 
	 * Est appelée lors de l'enregistrement d'une modification
	 * sur un contact déjà existant.
	 * Appelle le constructeur par défaut
	 * 
	 * @see ContactEdition
	 * 
	 * @param oldContact	le contact avant la modification
	 * @param newContact	le contact après la modification
	 */
	public ContactApp(Contact oldContact, Contact newContact) {
		this();
		contacts.set(lastIndex, newContact);//Remplace le contact au bon endroit
		askForSerialization();

		//Refresh du layout
		ContactApp.this.dispose();
		new ContactApp();
	}

	/**
	 * Constructeur lors de la suppression d'un contact.
	 * 
	 * Supprime le contact à l'index "lastIndex"
	 * 
	 * Appelle le constructeur par défaut
	 * 
	 * @see ContactEdition
	 * 
	 * @param toBeDeleted	"flag" de suppression
	 */
	public ContactApp(boolean toBeDeleted) {
		this();
		contacts.remove(lastIndex);//Supprime le contact selectionné
		askForSerialization();

		//Refresh du layout
		ContactApp.this.dispose();
		new ContactApp();
	}

	/**
	 * Sérialisation des contacts
	 * Les contacts sont enregistrés dans "saves/contacts.ser"
	 * 
	 * @throws IOException	si une erreur d'écriture/lecture est survenue.
	 */
	private void serializeObject() 
	{
		try 
		{
			FileOutputStream file = new FileOutputStream("saves/contacts.ser");
			ObjectOutputStream oos = new ObjectOutputStream(file);
			oos.writeObject(contacts);
			oos.flush();
			oos.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * Deserialisation des contacts
	 * Les contacts sont récupérés de "saves/contacts.ser"
	 * 
	 * @throws IOException	Si une erreur de lecture/écriture est survenue,
	 * 						Crée une liste de contact vide
	 * @throws ClassNotFoundException	si la classe n'est pas trouvée
	 * 									Crée une liste de contact vide
	 */
	@SuppressWarnings("unchecked")
	private void deSerializeObject() 
	{
		try 
		{
			FileInputStream file = new FileInputStream("saves/contacts.ser");
			ObjectInputStream ois = new ObjectInputStream(file);
			contacts = (ArrayList<Contact>) ois.readObject();
			ois.close();
		} 
		catch (IOException e) 
		{
			contacts = new ArrayList<Contact>();
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			System.out.println("Pas de liste de contact trouvée");
			//Création d'une nouvelle liste si aucunne n'est trouvée
			contacts = new ArrayList<Contact>();

		}
	}
	
	/**
	 * Place les boutons contacts et les initialise. 
	 * 
	 * Lors d'une recherche, permet de sélectionner uniquement les contacts
	 * correspondants à la recherche.
	 * Cette recherche se fait sur tous les champs (sauf "imagePath") d'un contact.
	 * 
	 * Chaque contact est inséeé dans un bouton temporaire qui est ajouté à son tour
	 * au "ScrollPanel".
	 * 
	 * Lorsqu'un bouton est cliqué, ContactApp est serialisée (si pas de recherche)
	 * et ContactEdition est ouvert avec les informations du contact séléctionné.
	 */
	private void placeContacts() {
		//Pour chaque contact
		//Si pas de contact : contact.size = 0 alors pas de boutons crées
		for(int i = 0; i < contacts.size(); i++) {

			//Si une recherche n'est pas effectuée, ou que la recherche trouve dans les contacts
			//Ajoute le bouton et l'actionlistener
			if(search == null || contacts.get(i).getPrenom().contains(search)
					|| contacts.get(i).getNom().contains(search)
					|| contacts.get(i).getTel().contains(search)
					|| contacts.get(i).getAdresse().contains(search)
					|| contacts.get(i).getEmail().contains(search)) {

				//Index de contact mémorisé pour l'ajout de l'image
				lastInternIndex=i;

				//Bouton temporaire à ajouter sur le contactPanel.
				ContactButton temp = new ContactButton(contacts.get(i).getPrenom() +" "+ contacts.get(i).getNom());
				int j = i; //Pour le new actionListener
				temp.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						askForSerialization();

						//Ouvre l'édition si cliqué avec les informations du contact en param.
						new ContactEdition(contacts.get(j));
						//Mémorise l'index du contact cliqué en cas de modification
						lastIndex = contacts.indexOf(contacts.get(j));
						ContactApp.this.dispose();
					}
				});
				//Ajoute le bouton à la liste
				contactList.add(temp);
			}


		}
	}

	/**
	 * Prend une image en paramètre et lui donne la bonne taille
	 * pour être ajoutée à un bouton de contact.
	 * 
	 * L'image est rognée lros de sa modification.
	 * 
	 * @param image	L'image à modifier
	 * @return		L'image avec la dimention d'un bouton de contact
	 */
	private static Image getResizedPhoto(Image image) {
		return image.getScaledInstance( 80, 80,  java.awt.Image.SCALE_SMOOTH );
	}

	/**
	 * Vérifie que la serialisation peut être effectuée.
	 * Si une recherche est effectuée, il ne faut pas serialiser,
	 * mais réinitialiser la valeur de recherche.
	 * 
	 * Si pas de recherche en cours, serialiser l'objet,
	 * sinon garder l'original et réinit. la recherche.
	 * 
	 * @see ContactApp#serializeObject()
	 */
	private void askForSerialization() {
		if(search == null) {
			serializeObject();
		}else {
			search=null;
		}
	}
	
	/**
	 * Classe TopButton.
	 * Design des boutons du haut (recherche et ajouter)
	 * 
	 * est constitué d'un chemin de fichier pour l'icone.
	 * 
	 * @author Jean-Marie Alder
	 */
	class TopButton extends JButton {
		private String path;

		/**
		 * Constructeur de topButton,
		 * prend en paramètre le chemin de l'icône nécessaire.
		 * 
		 * @param str	le chemin d'accès à l'icône
		 * @throws IOException	si une erreur de lecture/ecriture est survenue
		 */
		public TopButton(String str) {
			path = str;

			setPreferredSize(new Dimension(100,70));
			setCursor(new Cursor(Cursor.HAND_CURSOR));
			setOpaque(false);
			setContentAreaFilled(false);
			setBorder(new LineBorder(Color.BLACK, 1));


			try {
				Image img = ImageIO.read(new File(path));
				setIcon(new ImageIcon(img));
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * Classe ContactButton
	 * Design des boutons représentant les contacts.
	 * 
	 * Un bouton contact est constitué de son image, son nom et prénom.
	 * 
	 * @author Jean-Marie Alder
	 *
	 */
	class ContactButton extends JButton {
		/**
		 * Constructeur du bouton contact
		 * 
		 * Ajoute au bouton une icône et un label
		 * avec le nom et le prénom d'un contact.
		 * 
		 * @param str	le nom et le prénom du contact
		 * 
		 * @throws IOException	si une erreur de lecture/ecriture est survenue
		 */
		public ContactButton(String str) {
			JLabel lNom = new JLabel(str);
			lNom.setFont(new Font("Arial Black", Font.PLAIN, 20));
			JLabel imgPanel = new JLabel();
			Image img = null;
			try {
				img = ImageIO.read(new File(contacts.get(lastInternIndex).getPhotoPath()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			img = getResizedPhoto(img);	
			imgPanel.setIcon(new ImageIcon(img));

			lNom.setPreferredSize(new Dimension(400,80));

			this.setLayout(new FlowLayout());
			this.setPreferredSize(new Dimension(530,80));
			this.setMinimumSize(new Dimension(530,80));
			this.setMaximumSize(new Dimension(530,80));
			setCursor(new Cursor(Cursor.HAND_CURSOR));
			setOpaque(false);
			setContentAreaFilled(false);
			setBorder(new LineBorder(Color.BLACK, 1));
			this.add(imgPanel);
			this.add(lNom);

		}
	}
}
