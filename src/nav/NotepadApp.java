package nav;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/**
 * Classe NotepadApp, bloc note
 * appel�e lorsqu'une note est s�lectionn�e dans "NotepadSelection"
 * 
 * Son design est constitu� de :
 * - Un topPanel avec :
 *  - Un bouton "sauver et quitter"
 *  - le titre de la note modifiable
 *  - Un bouton retour
 *  - Un bouton supprimer
 *  
 * - Un panel pour la note avec liste d�roulante
 * 
 * Cette classe est constitu�e de :
 * - Un chemin de fichier pour la note s�lectionn�e
 * - Un nom de fichier avec et sans extention
 * 
 * G�re les diff�rents cas de figure lors de la cr�ation, modification et suppression.
 * 
 * La taille de la note est d�finie et n'est plus affich�e si d�pass�e (trop de lignes)
 * 
 * @author Jean-Marie Alder
 *
 */
@SuppressWarnings("serial")
public class NotepadApp extends AppBaseFrame {

	private static File filePath = new File("Notes/note1.txt");
	private String fileName;
	private String fileNameWithoutExt;

	private JPanel mainPanel = new JPanel();

	private JPanel topPanel = new JPanel();
	private TopButton sauverQuitter = new TopButton("Sauver & Quitter");
	private TopButton annuler = new TopButton("Annuler");
	private TopButton supprimer = new TopButton("Supprimer");
	private JTextField noteTitle = new JTextField();

	private JPanel notes = new JPanel();
	private JTextArea input = new JTextArea();
	private JScrollPane scrollPane; //Panel pour faire d�filer les notes

	/**
	 * Constructeur lors de la cr�ation d'une nouvelle note.
	 * 
	 * Appelle getFirstNewNote() pour trouver le premier chemin
	 * qui n'est pas d�j� existant.
	 * 
	 * @see NotepadApp#getFirstNewNote()
	 * @see NotepadApp#NotepadApp(File)
	 */
	public NotepadApp() {
		this(new File(getFirstNewNote()));
	}

	/**
	 * Constructeur lors de l'�dition d'une note existante.
	 * 
	 * Design de la frame et initialisation du titre, de la note et des boutons
	 * 
	 * 
	 * @param file	Le chemin de la note s�lectionn�e
	 */
	@SuppressWarnings("static-access")
	public NotepadApp(File file) {
		super();

		this.filePath = file;
		fileName = filePath.getName();
		fileNameWithoutExt = fileName.replaceFirst("[.][^.]+$", "");

		//Supprime le fond d'�cran
		remove(centerPanel);

		noteTitle.setPreferredSize(new Dimension(250, 70)); //Taille du composant (taille du JLabel)
		noteTitle.setHorizontalAlignment(SwingConstants.CENTER);
		noteTitle.setBackground(Color.LIGHT_GRAY);
		noteTitle.setFont(new Font("Arial Black", Font.PLAIN, 15));
		noteTitle.setText(fileNameWithoutExt);

		sauverQuitter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(fileIsAvailable()) {
					saveText();
					new NotepadSelection();
					NotepadApp.this.dispose();
				}else {
					System.out.println("Nom de fichier d�j� existant");
				}

			}


		});

		annuler.setPreferredSize(new Dimension(65, 70));
		annuler.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				new NotepadSelection();
				NotepadApp.this.dispose();
			}
		});
		
		supprimer.setPreferredSize(new Dimension(75, 70));
		supprimer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(filePath.delete()) {
					System.out.println("Note effac�e avec succ�s");
				}else {
					System.out.println("Aucunne note � supprimer");
				}
				filePath = new File("Notes/note1.txt");
				new NotepadSelection();
				NotepadApp.this.dispose();
			}
		});

		topPanel.setPreferredSize(new Dimension(600,90));
		topPanel.setLayout(new FlowLayout());
		topPanel.add(sauverQuitter);
		topPanel.add(noteTitle);
		topPanel.add(annuler);
		topPanel.add(supprimer);

		input.setFont(new Font("Serif", Font.ITALIC, 25));
		input.setLineWrap(true);
		input.setWrapStyleWord(true);
		input.setPreferredSize(new Dimension(600, 5000));
		input.setText(getText());

		notes.setPreferredSize(new Dimension(600,5000));
		notes.add(input);

		scrollPane = new JScrollPane (input, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(600,600));

		mainPanel.setLayout(new FlowLayout());
		mainPanel.add(topPanel);
		mainPanel.add(scrollPane);

		this.add(mainPanel, BorderLayout.CENTER);
	}

	/**
	 * Retourne le texte du fichier ".txt" s�l�ctionn�
	 * 
	 * La lecture se fait ligne par ligne et s'arr�te automatiquement
	 * lorsque toutes les lignes sont affich�es.
	 * 
	 * @return	L'entier du texte dans le fichier ".txt"
	 * 
	 * throws IOException	si une erreur de lecture/�criture est survenue
	 * throws FileNotFoundException	Si le fichier d�sir� n'est pas trouvable
	 */
	private String getText() {
		String retVal = "";
		String line = "";

		try {
			FileReader fr = new FileReader(filePath);
			BufferedReader br = new BufferedReader(fr);


			while((line = br.readLine()) != null) {
				retVal = retVal + line + "\n";
			}

			br.close();

		} catch (FileNotFoundException e) {
			System.out.println("Fichier non trouv�...");
			//			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Erreur lors de la lecture du fichier...");
			e.printStackTrace();
		}

		return retVal;
	}
	
	/**
	 * Enregistre le texte de la note dans le bon fichier ".txt" en fonction du titre.
	 * 
	 * Est appel�e lorsque le bouton "sauver et quitter" est cliqu�.
	 * 
	 * Utilise un scanner pour lire ligne par ligne et ins�rer
	 * correctement dans le ".txt".
	 * 
	 * throws IOException si une erreur de lecture/�criture est survenue
	 */
	private void saveText() {
		filePath = new File("Notes/" + noteTitle.getText() + ".txt");
		String toSave = input.getText();
		System.out.println("Tentative de sauvegarde");
		System.out.println(toSave);

		Scanner sc = new Scanner(toSave);

		try {
			FileWriter fw = new FileWriter(filePath);
			BufferedWriter bw = new BufferedWriter(fw);

			while(sc.hasNextLine()) {
				bw.write(sc.nextLine());
				bw.newLine();
			}

			bw.close();
		} catch (IOException e) {
			System.out.println("Erreur lors de l'�criture du fichier...");
			e.printStackTrace();
		}
		sc.close();
	}

	/**
	 * D�termine si le chemin du fichier est disponnible
	 * et reconnait si la note est modifi�e avec le m�me nom.
	 * 
	 * 
	 * 
	 * @return	"true" si le fichier modifi� a le m�me titre
	 * 			et si le nouveau fichier est disponible
	 * 			"false" si le fichier n'est pas disponible
	 */
	private boolean fileIsAvailable() {
		
		if(!fileName.equals((noteTitle.getText() + ".txt"))) {
			File[] existingFiles = new File("Notes").listFiles();
			for(File f : existingFiles) {
				if(f.getName().equals(noteTitle.getText() + ".txt")) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Retourne le premier fichier disponible lors de la cr�ation
	 * d'une nouvelle note.
	 * 
	 * Ajoute un chiffre apr�s "note" jusqu'� ce que le fichier soit libre.
	 * note1, note2 ... note n.
	 * 
	 * @return	Le premier fichier disponible
	 */
	private static String getFirstNewNote() {
		String retVal = filePath.getPath();
		int index = 1;

		while(new File(retVal).isFile()) {
			index++;
			retVal =  "Notes/note" + index + ".txt";			
		}		
		return retVal;
	}

	/**
	 * Classe TopButton.
	 * Design des boutons du haut (sauver et quitter, retour et delete)
	 * 
	 * prend le nom du bouton en param�tre
	 * 
	 * @author Jean-Marie Alder
	 */
	class TopButton extends JButton {

		/**
		 * Constructeur de topButton,
		 * prend en param�tre le nom de l'icone
		 * 
		 * @param str	le nom du bouton
		 */
		public TopButton(String str) {
			super(str);
			setPreferredSize(new Dimension(130,70));
			setCursor(new Cursor(Cursor.HAND_CURSOR));
			setOpaque(false);
			setContentAreaFilled(false);
			setBorder(new LineBorder(Color.BLACK, 1));
			setFont(new Font("Arial Black", Font.PLAIN, 13));
			setVisible(true);


		}
	}
}
