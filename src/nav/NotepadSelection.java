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
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/**
 * Classe NotepadSelection
 * Appel�e lorsque l'icone du bloc-note du HomeScreen est cliqu�.
 * 
 * Son design est constitu� de :
 * - Un topPanel pour le titre et le bouton ajouter.
 * - Un panel avec la liste de note, liste d�roulante
 * 
 * Ses attributs sont :
 * - Un fichier repr�sentant le dossier de base des notes ("Notes/")
 * - Une liste de fichiers repr�sentant chaque note.
 * 
 * @see HomeScreen
 * 
 * @author Jean-Marie Alder
 *
 */
@SuppressWarnings("serial")
public class NotepadSelection extends AppBaseFrame {

	private File directory = new File("Notes/");
	private ArrayList<File> notePaths;


	//Initialisation des pannels
	private JPanel mainPanel = new JPanel();//Panel principal app contact
	private JPanel topPanel = new JPanel(); //Panel pour titre et btn ajouter
	private JPanel notesList = new JPanel(); //Panel pour la liste des contacts
	private JScrollPane scrollPane; //Panel pour faire d�filer la liste de contacts

	//Initialisation des boutons et labels
	private JLabel topText = new JLabel("Bloc Note");
	private	TopButton addNote = new TopButton("img/icons/add_icon.png");
	private JLabel blank = new JLabel();

	/**
	 * Constructeur de la s�l�ction du bloc-note
	 * 
	 * Design de la frame et initialisation des boutons.
	 * Appelle placeNotes() pour lister les notes et leurs titres
	 * 
	 * @see NotepadSelection#placeNotes()
	 * @see NotepadSelection#preparePathList(File)
	 */
	public NotepadSelection() {
		super();
		remove(centerPanel);

		//Design de la liste de notes
		notesList.setLayout(new GridLayout(0,1));
		notesList.setBackground(Color.WHITE);

		//Place les boutons des notes et les initialise
		notePaths = preparePathList(directory);
		placeNotes();

		//Design du pannel titre avec bouton ajouter.
		topText.setPreferredSize(new Dimension(300, 100)); //Taille du composant (taille du JLabel)
		topText.setHorizontalAlignment(SwingConstants.CENTER);
		topText.setVerticalAlignment(SwingConstants.CENTER);
		topText.setBackground(Color.WHITE);
		topText.setFont(new Font("Arial Black", Font.PLAIN, 35));

		//ActionListener qui ouvre la fen�tre d'�dition vierge (nouveau contact)
		addNote.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				//Refresh
				new NotepadApp();
				NotepadSelection.this.dispose();

			}
		});

		blank.setPreferredSize(new Dimension(100,70));

		//Design final du top panel (titre et boutons)
		topPanel.setPreferredSize(new Dimension(600,100));
		topPanel.setLayout(new FlowLayout());
		//Ajout des �l�ments au top panel
		topPanel.add(blank);
		topPanel.add(topText);
		topPanel.add(addNote);


		//Cr�ation de la liste de d�filement vertical
		scrollPane = new JScrollPane (notesList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(550,540));

		//Design de la frame
		mainPanel.setPreferredSize(new Dimension(600,650));
		mainPanel.setLayout(new FlowLayout());
		mainPanel.add(topPanel);
		mainPanel.add(scrollPane);
		add(mainPanel, BorderLayout.CENTER);
	}

	/**
	 * Place les notes une par une dans la liste d�roulante.
	 * 
	 * Ajoute au bouton le bon index et le bon chemin du fichier.
	 * 
	 * @see NotepadApp#NotepadApp(File)
	 */
	private void placeNotes() {
		//Pour chaque contact
		//Si pas de contact : contact.size = 0 alors pas de boutons cr�es
		for(int i = 0; i < notePaths.size(); i++) {

			//Bouton temporaire � ajouter sur le contactPanel.
			NoteButton temp = new NoteButton(notePaths.get(i));
			int j = i; //Pour le new actionListener
			temp.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					//Ouvre l'�dition si cliqu� avec les informations du contact en param.
					System.out.println(j);
					new NotepadApp(notePaths.get(j));

					NotepadSelection.this.dispose();
				}
			});
			//Ajoute le bouton � la liste
			notesList.add(temp);



		}
	}

	/**
	 * Pr�pare la liste des chemins des fichiers dans une ArrayList
	 * 
	 * Appelle la fonction listFiles() pour lister tous les fichiers
	 * 
	 * @param directory	le dossier par d�faut � lister
	 * @return	la liste des chemins des notes
	 */
	private ArrayList<File> preparePathList(File directory){
		File[] notePaths;

		notePaths = directory.listFiles();
		return new ArrayList<File>(Arrays.asList(notePaths));
	}

	/**
	 * Classe TopButton.
	 * Design du bouton du haut (ajouter)
	 * 
	 * est constitu� d'un chemin de fichier pour l'icone.
	 * 
	 * @author Jean-Marie Alder
	 */
	class TopButton extends JButton {
		private String path;

		/**
		 * Constructeur de topButton,
		 * prend en param�tre le chemin de l'ic�ne n�cessaire.
		 * 
		 * @param str	le chemin d'acc�s � l'ic�ne
		 * throws IOException	si une erreur de lecture/ecriture est survenue
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
	 * Classe NoteButton
	 * Design des boutons repr�sentant les notes.
	 * 
	 * Un bouton note est constitu� de son titre.
	 * 
	 * @author Jean-Marie Alder
	 *
	 */
	class NoteButton extends JButton {
		
		/**
		 * Constructeur du bouton note
		 * 
		 * Ajoute au bouton un label avec le titre de la note.
		 * 
		 */
		public NoteButton(File file) {
			/**
			 * Constructeur de la classe
			 * 
			 * @param file      fichier dont on veut le nom pour l'afficher.
			 * 
			 */
			super(file.getName());

			this.setPreferredSize(new Dimension(530,80));
			this.setMinimumSize(new Dimension(530,80));
			this.setMaximumSize(new Dimension(530,80));
			setCursor(new Cursor(Cursor.HAND_CURSOR));
			setOpaque(false);
			setContentAreaFilled(false);
			setBorder(new LineBorder(Color.BLACK, 1));
			setBackground(Color.WHITE);
			setFont(new Font("Arial Black", Font.PLAIN, 20));

		}
	}
}
