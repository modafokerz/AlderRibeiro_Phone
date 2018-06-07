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
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/*
 * Projet Smartphone
 * Auteur : Jean-Marie Alder
 * Date de création : 6.6.18
 * Date de modification :
 * Description : Application contact.
 */

public class ContactApp extends AppBaseFrame {

	private JPanel contactPanel = new JPanel();//Panel principal app contact
	private JPanel topPanel = new JPanel(); //Panel pour titre et btn ajouter
	private JPanel contactList = new JPanel(); //Panel pour la liste des contacts
	private JScrollPane scrollPane; //Panel pour faire défiler la liste de contacts

	private TopButton recherche = new TopButton("img/icons/search_icon.png");
	private JLabel topText = new JLabel("Contacts");
	private	TopButton addContact = new TopButton("img/icons/add_icon.png");

	//Boutons tests :
	private ContactButton c1 = new ContactButton("test");
	private ContactButton c2 = new ContactButton("test");
	private ContactButton c3 = new ContactButton("test");
	private ContactButton c4 = new ContactButton("test");
	private ContactButton c5 = new ContactButton("test");
	private ContactButton c6 = new ContactButton("test");
	private ContactButton c7 = new ContactButton("test");
	private ContactButton c8 = new ContactButton("test");
	private ContactButton c9 = new ContactButton("test");
	private ContactButton c0 = new ContactButton("test");

	public ContactApp() {
		super();
		remove(centerPanel);

		topText.setPreferredSize(new Dimension(300, 100)); //Taille du composant (taille du JLabel)
		topText.setHorizontalAlignment(SwingConstants.CENTER);
		topText.setVerticalAlignment(SwingConstants.TOP);
		topText.setBackground(Color.WHITE);
		topText.setFont(new Font("Arial Black", Font.PLAIN, 25));

		addContact.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ContactEdition();
				ContactApp.this.dispose();

			}
		});

		topPanel.setPreferredSize(new Dimension(600,100));
		topPanel.setLayout(new FlowLayout());
		topPanel.add(recherche);
		topPanel.add(topText);
		topPanel.add(addContact);

		contactList.setLayout(new GridLayout(0,1));

		contactList.add(c1);
		contactList.add(c2);
		contactList.add(c3);
		contactList.add(c4);
		contactList.add(c5);
		contactList.add(c6);
		contactList.add(c7);
		contactList.add(c8);
		contactList.add(c9);
		contactList.add(c0);



		scrollPane = new JScrollPane (contactList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(550,540));
		//		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		//		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


		contactPanel.setPreferredSize(new Dimension(600,650));
		contactPanel.setLayout(new FlowLayout());

		contactPanel.add(topPanel);
		contactPanel.add(scrollPane);


		add(contactPanel, BorderLayout.CENTER);
		
		System.out.println("ContactApp construite!");
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
