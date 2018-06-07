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

@SuppressWarnings("serial")
public class ContactApp extends AppBaseFrame {



	private static int lastIndex;
	@SuppressWarnings("unused")
	private static boolean premier = true; //Only pour ajouter le bloc de contacts

	public static String search = null;

	//Tableau de contacts
	private ArrayList<Contact> contacts = new ArrayList<Contact>();

	private JPanel contactPanel = new JPanel();//Panel principal app contact
	private JPanel topPanel = new JPanel(); //Panel pour titre et btn ajouter
	private JPanel contactList = new JPanel(); //Panel pour la liste des contacts
	private JScrollPane scrollPane; //Panel pour faire défiler la liste de contacts

	private TopButton recherche = new TopButton("img/icons/search_icon.png");
	private JLabel topText = new JLabel("Contacts");
	private	TopButton addContact = new TopButton("img/icons/add_icon.png");

	public ContactApp() {
		super();
		remove(centerPanel);

		//Bloc pour ajouter 9 contacts si nécessaire
//		if(premier) {
//			contacts.add(new Contact("JM", "Alder", "09128","Arusdkjf", "a@mail"));
//			contacts.add(new Contact("JM2", "Alder", "09128","Arusdkjf", "a@mail"));
//			contacts.add(new Contact("JM3", "Alder", "09128","Arusdkjf", "a@mail"));
//			contacts.add(new Contact("JM4", "Alder", "09128","Arusdkjf", "a@mail"));
//			contacts.add(new Contact("JM5", "Alder", "09128","Arusdkjf", "a@mail"));
//			contacts.add(new Contact("JM6", "Alder", "09128","Arusdkjf", "a@mail"));
//			contacts.add(new Contact("JM7", "Alder", "09128","Arusdkjf", "a@mail"));
//			contacts.add(new Contact("JM8", "Alder", "09128","Arusdkjf", "a@mail"));
//			contacts.add(new Contact("JM9", "Alder", "09128","Arusdkjf", "a@mail"));
//			serializeObject();
//			premier = false;
//		}
		//deserialisation pour reprendre la liste des contacts
		deSerializeObject();

		//Place les boutons contacts et les initialise
		//@see placeContacts()
		placeContacts();

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
				
				new ContactFinder();
				ContactApp.this.dispose();
			}
		});

		//Design du top panel
		topPanel.setPreferredSize(new Dimension(600,100));
		topPanel.setLayout(new FlowLayout());
		topPanel.add(recherche);
		topPanel.add(topText);
		topPanel.add(addContact);

		//Design de la liste de contacts
		contactList.setLayout(new GridLayout(0,1));
		scrollPane = new JScrollPane (contactList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(550,540));

		//Design de la frame
		contactPanel.setPreferredSize(new Dimension(600,650));
		contactPanel.setLayout(new FlowLayout());
		contactPanel.add(topPanel);
		contactPanel.add(scrollPane);
		add(contactPanel, BorderLayout.CENTER);

		System.out.println("ContactApp construite! sans param");
	}

	//Constructeur lors d'un nouveau contact
	public ContactApp(Contact newContact) {
		this();//appelle le constructeur par défaut
		contacts.add(newContact); //Ajoute le contact à la fin
		askForSerialization();
		
		//Refresh du layout
		ContactApp.this.dispose();
		new ContactApp();

	}

	//Constructeur lors de la modification d'un contact.
	public ContactApp(Contact oldContact, Contact newContact) {
		this();//appelle le constructeur par défaut
		contacts.set(lastIndex, newContact);//Remplace le contact au bon endroit
		askForSerialization();
		
		//Refresh du layout
		ContactApp.this.dispose();
		new ContactApp();
	}

	//Constructeur lors de la suppression d'un contact
	public ContactApp(Contact oldContact, boolean toBeDeleted) {
		this();//appelle le constructeur par défaut
		contacts.remove(lastIndex);//Supprime le contact selectionné
		askForSerialization();
		
		//Refresh du layout
		ContactApp.this.dispose();
		new ContactApp();
	}

	//Sérialisation des contacts
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

	//Deserialisation des contacts
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

		}
	}

	//Place les boutons contacts et les initialise. 
	private void placeContacts() {
		//Pour chaque contact
		for(int i = 0; i < contacts.size(); i++) {

			//Si une recherche n'est pas effectuée, ou que la recherche trouve dans les contacts
			//Ajoute le bouton et l'actionlistener
			if(search == null || contacts.get(i).getPrenom().contains(search)
					|| contacts.get(i).getNom().contains(search)
					|| contacts.get(i).getTel().contains(search)
					|| contacts.get(i).getAdresse().contains(search)
					|| contacts.get(i).getEmail().contains(search)) {
				
				//Bouton temporaire à ajouter sur le contactPanel.
				ContactButton temp = new ContactButton(contacts.get(i).getPrenom() +" "+ contacts.get(i).getNom());
				int j = i; //Pour le new actionListener
				temp.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						//Mémorise l'index du contact cliqué en cas de modification
						lastIndex = contacts.indexOf(contacts.get(j));
						askForSerialization();
						
						//Ouvre l'édition si cliqué avec les informations du contact en param.
						new ContactEdition(contacts.get(j));
						ContactApp.this.dispose();
					}
				});
				//Ajoute le bouton à la liste
				contactList.add(temp);
			}


		}
	}
	
	//Si pas de recherche en cours, serialiser l'objet, 
	//sinon garder l'original et réinit. la recherche.
	private void askForSerialization() {
		if(search == null) {
			serializeObject();
		}else {
			search=null;
		}
	}

	class TopButton extends JButton {
		private String path;

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

	class ContactButton extends JButton {
		public ContactButton(String str) {
			super(str);

			this.setPreferredSize(new Dimension(545,80));
			this.setMinimumSize(new Dimension(545,150));
			this.setMaximumSize(new Dimension(545,150));
			setCursor(new Cursor(Cursor.HAND_CURSOR));
			setOpaque(false);
			setContentAreaFilled(false);
			setBorder(new LineBorder(Color.BLACK, 1));



		}
	}

}
