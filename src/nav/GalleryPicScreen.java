/*
 * Smartphone 602_F FIG HES-SO (Sierre)
 * Auteur : Nelson Ribeiro Teixeira
 * Date de cr�ation : 4 juin 2018
 * Date de modification : /
 */
package nav;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import components.BackgroundPanel;
import components.topButton;
import components.topLabel;

@SuppressWarnings("serial")
public class GalleryPicScreen extends GalleryScreen {
	/**
	 * Classe qui g�re l'image affich�e de la gallerie. Elle h�rite de GalleryScreen et a 2 boutons en haut
	 * l'un permet de revenir � la gallerie normale et l'autre d'effacher l'image affich�e.
	 * 
	 * De plus, l'image est affich�e en "plein �cran"
	 * et des informations sur celle-ci sont affich�es plus bas. (Date, nom, etc.)
	 *
	 * @author Nelson
	 */
	private GalleryPicture chosenPic;
	private String[] pictureInformations;

	private topButton returnButton = new topButton("img/icons/return-icon.png");
	private topButton deleteButton = new topButton("img/icons/delete-pic-icon.png");
	private topLabel picName;

	private JPanel picturePanel = new JPanel();
	private JPanel informationsPanel = new JPanel();
	private JLabel infoLabel = new JLabel("Informations");
	private JPanel detailsPanel = new JPanel();

	private JScrollPane scrollImage;

	public GalleryPicScreen(GalleryPicture picture) {
		/**
		 * Constructeur de la classe qui prend en entr�e l'image a afficher.
		 * Il construit les composants de la fen�tre et les actions des boutons. (Effacer et retour).
		 * Le bouton effacer g�re aussi le nom des images de mani�re � ce que la continuit� de celles-ci ne se perde pas.
		 * Les images du d�but restent toujours au d�but, etc.
		 * 
		 * @param Image � afficher.
		 */
		chosenPic = picture;
		pictureInformations = chosenPic.getPictureInformations();
		picName = new topLabel(pictureInformations[0]);

		returnButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				new GalleryScreen();
				GalleryPicScreen.this.dispose();
			}
		});

		deleteButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				String path = pictureInformations[2];
				File thisPic = new File(path);
				thisPic.delete();

				File galleryFolder = new File("img/gallery/");
				File [] pictures = galleryFolder.listFiles();

				if(pictures.length>0) {
					String previousPath;
					String previousName;
					String generatedPath="";
					Boolean nameChangeNeeded = false;
					for (int i = 0; i < pictures.length; i++) {
						previousPath = pictures[i].getPath();
						previousName = pictures[i].getName();

						System.out.println(previousPath);
						if(i<10) {
							if (i!=Integer.parseInt(previousName.substring(2,3))) {
								generatedPath = "00"+i+"_";
								nameChangeNeeded = true;
							}

						} else if (i<100) {
							if (i!=Integer.parseInt(previousName.substring(1,3))) {
								generatedPath = "0"+i+"_";
								nameChangeNeeded = true;
							}
						} else {
							if (i!=Integer.parseInt(previousName.substring(0,3))) {
								generatedPath = i+"_";
								nameChangeNeeded = true;
							}
						}

						if(nameChangeNeeded) {
							previousPath = pictures[i].getPath();
							File previousNameFile = new File(previousPath);
							File newNameFile = new File("img/gallery/"+generatedPath+previousName.substring(4));
							
							if(previousNameFile.renameTo(newNameFile)){
					            System.out.println("File renamed");
					        }else{
					            System.out.println("Sorry! the file can't be renamed");
					        }
						}



						nameChangeNeeded = false;
					}
				}


				new GalleryScreen();
				GalleryPicScreen.this.dispose();
			}
		});

		setTopPanel(returnButton,picName,deleteButton);

		picturePanel.setLayout(new GridLayout(0,1));
		Image img = chosenPic.getPicImage();

		BackgroundPanel imagePanel = new BackgroundPanel(img);
		imagePanel.setPreferredSize(new Dimension(600,550));
		informationsPanel.setPreferredSize(new Dimension(600,550));
		infoLabel.setPreferredSize(new Dimension(600,50));
		infoLabel.setFont(new Font("Impact",Font.BOLD,35));
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setVerticalAlignment(SwingConstants.CENTER);

		informationsPanel.add(infoLabel);

		detailsPanel.setPreferredSize(new Dimension(600,550));
		detailsPanel.setLayout(new FlowLayout());

		InfoLabel nomInfo = new InfoLabel("Nom : ");
		PicInfoLabel nomPic = new PicInfoLabel(pictureInformations[0]);

		InfoLabel dateInfo = new InfoLabel("Date : ");
		PicInfoLabel datePic = new PicInfoLabel(pictureInformations[1]);

		InfoLabel srcInfo = new InfoLabel("Source : ");
		PicInfoLabel srcPic = new PicInfoLabel(pictureInformations[2]);

		InfoLabel extInfo = new InfoLabel("Extension : ");
		PicInfoLabel extPic = new PicInfoLabel(pictureInformations[3]);

		detailsPanel.add(nomInfo);
		detailsPanel.add(nomPic);
		detailsPanel.add(dateInfo);
		detailsPanel.add(datePic);
		detailsPanel.add(srcInfo);
		detailsPanel.add(srcPic);
		detailsPanel.add(extInfo);
		detailsPanel.add(extPic);

		informationsPanel.add(detailsPanel);

		picturePanel.add(imagePanel);
		picturePanel.add(informationsPanel);

		scrollImage = new JScrollPane(picturePanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollImage.setVisible(true);
		scrollImage.setPreferredSize(new Dimension(600,550));
		setMidPanel(scrollImage);

	}

	public class InfoLabel extends JLabel {
		/**
		 * Class qui g�re les label des informations de l'image pour qu'ils aient tous la m�me apparence.
		 * @param str � afficher dans le label
		 */

		public InfoLabel(String str) {
			/**
			 * Appele la m�thode super de JLabel et la m�thode construction qui construit les composants.
			 */
			super(str);
			construction();
		}

		private void construction() {
			/**
			 * Construit le JLabel (Formes etc.)
			 */
			setPreferredSize(new Dimension(200,50));

			setForeground(Color.black);
			setFont(new Font("Arial", Font.PLAIN, 20));

		}
	}

	public class PicInfoLabel extends JLabel {
		/**
		 * Classe qui g�re le JLabel des Infos de l'image (Titre � gauche) 
		 * @param str a afficher � l'int�rieur.
		 */
		public PicInfoLabel(String str) {
			/*
			 * Constructeur qui fait appel � la m�thode construction et au super(constructeur) de JLabel
			 */
			super(str);
			construction();
		}

		private void construction() {
			/*
			 * Construit le JLabel.
			 */
			setPreferredSize(new Dimension(350,50));
			setFont(new Font("Arial", Font.PLAIN, 13));
		}
	}

}
