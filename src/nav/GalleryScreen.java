/*
 * Smartphone 602_F FIG HES-SO (Sierre)
 * Auteur : Nelson Ribeiro Teixeira
 * Date de création : 3 juin 2018
 * Date de modification : /
 */
package nav;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import components.topButton;
import components.topLabel;

@SuppressWarnings("serial")
public class GalleryScreen extends AppBaseFrame {
	/**
	 * Galerie du smartphone, elle affiche les images enregistrées dans le dossier gallery du smartphone.
	 * Elle est composée en 2 parties :
	 * la 1ère : Panel du top avec 2 boutons et un Jlabel.
	 * Bouton de gauche : Enclenche la fonction de recherche de l'application (GalleryRechercheScreen).
	 * Label : Titre de l'application : Galerie
	 * Bouton de droite : Permet d'ajouter une photo à la galerie depuis un dossier local.
	 * 
	 * 2ème partie :
	 * Panel en grid layout qui affiche les images existantes dans le dossier gallery du smartphone.
	 * Lorsqu'on clique sur l'une d'elles cela nous mène vers le GalleryPicScreen de cette même image.
	 * 
	 * @author Nelson 
	 */

	private JPanel galleryPanel = new JPanel();
	private JPanel topPanel = new JPanel();
	private JPanel picturesPanel = new JPanel();
	
	private JScrollPane jscroll;
	
	private File [] pictures;
	private boolean contactFlag;
	// Eléments du topPanel
	private topButton recherche = new topButton("img/icons/search_icon.png");
	private topLabel galerieLabel = new topLabel ("Galerie");
	private	topButton addPic = new topButton("img/icons/add_icon.png");

	public GalleryScreen() {
		/**
		 * Constructeur par défaut de l'application qui l'instancie en false (paramètre permettant de savoir
		 * si la galerie est instanciée depuis les contacts ou non).
		 */
		this(false);

		
	}
	
	public GalleryScreen(boolean contactFlag) {
		/**
		 * Construit la frame selon le paramètre. Si c'est true elle n'a pas les boutons d'options et elle
		 * fait une autre tache au clic. (Ajoute l'image aux contacts).
		 * @param boolean pour savoir si l'application est instanciée depuis les contacts ou non.
		 */
		
		// construction de la Frame
		construction(contactFlag);
	}
	
	
	public GalleryScreen(String str, boolean isSearchByDate) {
		/**
		 * Constructeur qui permet d'instancier la gallerie depuis la recherche d'images.
		 * @param String ou texte à rechercher dans les images.
		 * @param boolean pour savoir si c'est instancié depuis la fenêtre de recherche.
		 */
		remove(centerPanel);
		
		researchConstruction(str, isSearchByDate);
	}
	
	private void researchConstruction(String str, boolean isSearchByDate) {
		/**
		 * Change la construction de la frame si jamais elle est instanciée depuis la fonction de recherche.
		 * Les boutons sont modifiées en Retour et l'autre est supprimé.
		 */
		
		baseConstruction();
		topPanel.setLayout(new FlowLayout());
		topButton returnButton = new topButton("img/icons/return-icon.png");
		
		returnButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				new GalleryScreen();
				GalleryScreen.this.dispose();
			}
		});
		
		topLabel rechercheLabel = new topLabel("Images trouvées : ");
		JLabel emptyLabel = new JLabel("");
		emptyLabel.setPreferredSize(new Dimension(100,70));
		
		topPanel.add(returnButton);
		topPanel.add(rechercheLabel);
		topPanel.add(emptyLabel);
		
		picturesPanel.setLayout(new GridLayout(0,3));
		File galleryFolder = new File("img/gallery/");
		int nbPhotos = galleryFolder.listFiles().length;
		int nbPics = 0;
		if(nbPhotos!=0) {
			pictures = galleryFolder.listFiles();
			for (int i = 0; i < pictures.length; i++) {
				
				GalleryPicture picture = new GalleryPicture(pictures[i].getPath(), contactFlag);
				String [] pictureInfos = picture.getPictureInformations();
				
				
				String picDate = pictureInfos[1].toLowerCase();
				String picName = pictureInfos[0].toLowerCase();
				
				if(isSearchByDate) {
					if(picDate.equals(str)) {
						picturesPanel.add(picture);
						nbPics++;
					}
					
				} else {
					str = str.toLowerCase();
					if(picName.contains(str)) {
						picturesPanel.add(picture);
						nbPics++;
					}
				}

			}
		}

		if (nbPics<9) {
			for (int i = 0; i < 9-nbPics; i++) {
				JLabel x = new JLabel();
				x.setPreferredSize(new Dimension(200,183));
				picturesPanel.add(x);
			}
		}

		jscroll = new JScrollPane (picturesPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jscroll.setVisible(true);
		jscroll.setPreferredSize(new Dimension(600,550));
		galleryPanel.add(jscroll, BorderLayout.CENTER);
		
		
	}


	private void construction(boolean contactFlag) {
		/**
		 * Change la construction de la frame si jamais elle est instanciée depuis la fenêtre de contacts.
		 * supprime les boutons et change l'action des images au clic en les attribuant au contact.
		 */
		remove(centerPanel);
		baseConstruction();


		// Construction du panel du top
		topPanel.setLayout(new FlowLayout());
		if(!contactFlag) {
			topPanel.add(recherche);
		}
		topPanel.add(galerieLabel);
		if(!contactFlag) {
			topPanel.add(addPic);
		}
		

		
		// Construction du panel contenant les photos
		picturesPanel.setLayout(new GridLayout(0,3));
		File galleryFolder = new File("img/gallery/");
		int nbPhotos = galleryFolder.listFiles().length;

		if(nbPhotos!=0) {
			pictures = galleryFolder.listFiles();
			for (int i = 0; i < pictures.length; i++) {

				picturesPanel.add(new GalleryPicture(pictures[i].getPath(), contactFlag));
			}
		}

		if (nbPhotos<9) {
			for (int i = 0; i < 9-nbPhotos; i++) {
				JLabel x = new JLabel();
				x.setPreferredSize(new Dimension(200,183));
				picturesPanel.add(x);
			}
		}

		jscroll = new JScrollPane (picturesPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jscroll.setVisible(true);
		jscroll.setPreferredSize(new Dimension(600,550));
		galleryPanel.add(jscroll, BorderLayout.CENTER);

		// boutton permettant l'ajout de photos
		addPic.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				JFileChooser chooser = new JFileChooser("src/gallery");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(GalleryScreen.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					try {

						String generatedPath = null;

						if(pictures.length<10) {
							generatedPath = "00"+pictures.length+"_";
						} else if (pictures.length<100) {
							generatedPath = "0"+pictures.length+"_";
						} else {
							generatedPath = pictures.length+"_";
						}

						Files.copy(chooser.getSelectedFile().toPath(), Paths.get("img/gallery/"+ generatedPath + chooser.getSelectedFile().getName()),
								StandardCopyOption.REPLACE_EXISTING);
						GalleryScreen.this.dispose();
						new GalleryScreen();

					} catch (IOException e1) {
						e1.printStackTrace();
					}

				}
			}});
		
		// Bouton permettant d'effectuer une recherche
		recherche.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new GalleryRechercheScreen();
				GalleryScreen.this.dispose();
			}
		});
		
	}
	
	private void baseConstruction() {
		/**
		 * Paramètres de base de la frame appelés à la construction à tous les constructeurs.
		 */
		topPanel.setPreferredSize(new Dimension(600,100));
		galleryPanel.setPreferredSize(new Dimension(600,650));
		galleryPanel.setLayout(new FlowLayout());
		galleryPanel.add(topPanel,BorderLayout.NORTH);
		add(galleryPanel, BorderLayout.CENTER);
	}
	
	protected void setTopPanel(JButton buttonLeft, JLabel label, JButton buttonRight) {
		/**
		 * Permet de définir le panel du top en modifiant ces éléments
		 * Est appelée par la fenêtre de recherche par exemple.
		 * @param JButton le bouton à mettre en haut à gauche.
		 * @param JLabel le label du centre.
		 * @param JButton le bouton à mettre en haut à droite.
		 */
		topPanel.removeAll();
		topPanel.add(buttonLeft);
		topPanel.add(label);
		topPanel.add(buttonRight);

	}
	
	protected void setMidPanel(JScrollPane panel) {
		/*
		 * Permet de changer le panel du centre avec le Jscroll en paramètre
		 * @param JScrollPane panel à mettre.
		 * Enlève le panel actuel et le remplace par le JScrollPane
		 * Méthode appelée par la méthode de recherche aussi.
		 */
		galleryPanel.remove(jscroll);
		galleryPanel.add(panel, BorderLayout.CENTER);
	}
	
	protected void setMidPanel(JPanel panel) {
		/*
		 * Permet de changer le panel du centre avec le panel en paramètre
		 * @param JPanel panel à mettre.
		 * Enlève le panel actuel et le remplace par le JPanel en paramètre
		 * Méthode appelée par la méthode de recherche aussi.
		 */
		galleryPanel.remove(jscroll);
		galleryPanel.add(panel, BorderLayout.CENTER);
	}


	class GalleryPicture extends JButton {
		/**
		 * Classe qui définit les attributs de chaque photo
		 * Nom, date de création, etc. 
		 * La libraire BasicFilesAttributes a été utilisée pour avoir ces informations.
		 */


		private String name;
		private String creationDate;
		private String path;
		private String extension;
		private Image img;
		private Image fullImg;

		public GalleryPicture(String path, boolean contactFlag) {
			/**
			 * Constructeur de la GalleryPicture qui construit l'image et les informations dont on a besoin.
			 * @param path vers l'image de la GalleryPicture
			 * @param boolean contactFlag pour savoir si c'cest appelé depuis les contact.
			 */
			setPreferredSize(new Dimension(200,183));
			setSize(200,183);
			this.path = path;
			creationDate = getCreationDate(path);
			extension = getExtension(path);
			String galleryPath = "img\\gallery\\";
			setCursor(new Cursor(Cursor.HAND_CURSOR));
			name = path.replace(galleryPath, "");
			name = name.substring(4, name.length()-4);

			if(contactFlag) {
				addContactActionListener();
			}else {
				addActionListener();
			}

			try {
				img = ImageIO.read(new File(path));
				img = getImageIcon(img);
				setIcon(new ImageIcon(img));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				fullImg = ImageIO.read(new File(path));
				fullImg = getFullImage(fullImg);
				setIcon(new ImageIcon(img));
			} catch (IOException e) {
				e.printStackTrace();
			}


		}

		private String getExtension(String path) {
			/*
			 * Méthode privée qui retourne l'extension d'un fichier
			 * @param path vers le fichier dont on veut conaitre l'extension.
			 */
			File file = new File(path);
			String fileName = file.getName();
			if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
				return fileName.substring(fileName.lastIndexOf(".")+1);
			else return "";
		}

		private Image getImageIcon (Image img) {
			/*
			 * Retourne une Icone de l'image en paramètre (Pour la galerie).
			 * @param image a resizer.
			 */
			BufferedImage resizedImg = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2 = resizedImg.createGraphics();
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2.drawImage(img, 0, 0, 200, 200, null);
			g2.dispose();
			return resizedImg;
		}

		private void addActionListener() {
			addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					
					
					new GalleryPicScreen(GalleryPicture.this);
					GalleryScreen.this.dispose();

				}
			});
		}

		private Image getFullImage (Image img) {
			/*
			 * Méthode privée qui retourne l'image en FullScreen de l'image en paramètres
			 * @param image à afficher en plein écran.
			 */
			BufferedImage resizedImg = new BufferedImage(500, 450, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2 = resizedImg.createGraphics();
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2.drawImage(img, 0, 0, 500, 450, null);
			g2.dispose();
			return resizedImg;
		}

		/**
		 * Ajoute l'action listener pour les boutons lors d'un changement
		 * d'image d'un contact
		 * 
		 * Met à jour le chemin d'accès de la photo du contact actuellement modifié.
		 * 
		 * @see ContactEdition
		 */
		private void addContactActionListener() {
			addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					ContactEdition.setOldContactPhotoPath(path);
					ContactEdition.updatePhoto();
					GalleryScreen.this.dispose();
				}
			});
		}

		private String getCreationDate(String path) {
			/*
			 * Méthode privée qui retourne la date de création d'un fichier
			 * @param path vers le fichier dont on veut la date de création.
			 * 
			 */
			BasicFileAttributes attr = null;
			try {
				attr = Files.readAttributes(Paths.get(path), BasicFileAttributes.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
			FileTime date = attr.creationTime();
			String str = new SimpleDateFormat("dd/MM/yyyy")
					.format(date.toMillis());

			return str;
		}


		protected String[] getPictureInformations() {
			/**
			 * Méthode qui retourne les informations de l'instance de GalleryPicture
			 * Elle retoune un tableau de String avec les informations suivantes:
			 * Name,CreationDate,Path,Extension.
			 */
			String [] pictureInformations= new String[4];
			pictureInformations[0]=name;
			pictureInformations[1]=creationDate;
			pictureInformations[2]=path;
			pictureInformations[3]=extension;

			return pictureInformations;
		}
		
		protected Image getPicImage() {
			/**
			 * Getter de fullImg.
			 */
			return fullImg;
		}
	}
}
