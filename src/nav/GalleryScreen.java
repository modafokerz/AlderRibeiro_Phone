/*
 * Exercice FIG HES-SO (Sierre)
 * Auteur : Nelson Ribeiro Teixeira
 * Date de création : 3 juin 2018
 * Date de modification : /
 */
package nav;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GalleryScreen extends AppBaseFrame {

	private JPanel galleryPanel = new JPanel();
	private JPanel topPanel = new JPanel();
	private JPanel picturesPanel = new JPanel();
	
	private File [] pictures;
	// Eléments du topPanel
	private topButton recherche = new topButton("img/icons/search_icon.png");
	private topLabel galerieLabel = new topLabel ("Galerie");
	private	topButton addPic = new topButton("img/icons/add_icon.png");

	public GalleryScreen() {
		remove(centerPanel);


		// Initialisation des Panel
		topPanel.setPreferredSize(new Dimension(600,100));

		galleryPanel.setPreferredSize(new Dimension(600,650));
		galleryPanel.setLayout(new FlowLayout());

		galleryPanel.add(topPanel,BorderLayout.NORTH);


		add(galleryPanel, BorderLayout.CENTER);


		// Construction du panel du top
		topPanel.setLayout(new FlowLayout());



		topPanel.add(recherche);
		topPanel.add(galerieLabel);
		topPanel.add(addPic);

		
		// Construction du panel contenant les photos
		picturesPanel.setLayout(new GridLayout(0,3));



		File galleryFolder = new File("img/gallery/");
		int nbPhotos = galleryFolder.listFiles().length;


		if(nbPhotos!=0) {

			pictures = galleryFolder.listFiles();
			for (int i = 0; i < pictures.length; i++) {



				picturesPanel.add(new GalleryPicture(pictures[i].getPath()));
			}
		}

		if (nbPhotos<9) {
			for (int i = 0; i < 9-nbPhotos; i++) {
				JLabel x = new JLabel();
				x.setPreferredSize(new Dimension(200,183));
				picturesPanel.add(x);
			}
		}

		JScrollPane jscroll = new JScrollPane (picturesPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jscroll.setVisible(true);
		jscroll.setPreferredSize(new Dimension(600,550));
		galleryPanel.add(jscroll, BorderLayout.CENTER);


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
						String pathImage = chooser.getSelectedFile().getPath();

						if(pictures.length<10) {
							generatedPath = "00"+pictures.length+"_";
						} else {
							generatedPath = "0"+pictures.length+"_";
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
	}



	protected void setTopPanel(JButton buttonLeft, JLabel label, JButton buttonRight) {
		topPanel.removeAll();
		topPanel.add(buttonLeft);
		topPanel.add(label);
		topPanel.add(buttonRight);

	}

	class topButton extends JButton {
		private String path;

		public topButton(String str) {
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

	class topLabel extends JLabel {
		public topLabel(String str) {
			super(str);
			setPreferredSize(new Dimension(350,100));
			setHorizontalAlignment(SwingConstants.CENTER);
			setFont(new Font("Impact", Font.PLAIN, 35));
		}
	}

	class GalleryPicture extends JButton {


		private String name;
		private String creationDate;
		private String path;
		private String extension;
		private Image img;
		private Image fullImg;

		public GalleryPicture(String path) {
			setPreferredSize(new Dimension(200,183));
			setSize(200,183);
			this.path = path;
			creationDate = getCreationDate(path);
			extension = getExtension(path);
			String galleryPath = "img\\gallery\\";
			setCursor(new Cursor(Cursor.HAND_CURSOR));
			name = path.replace(galleryPath, "");
			name = name.substring(4, name.length()-4);

			addActionListener();

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
			File file = new File(path);
			String fileName = file.getName();
			if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
				return fileName.substring(fileName.lastIndexOf(".")+1);
			else return "";
		}

		private Image getImageIcon (Image img) {
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
					
					GalleryScreen.this.dispose();
					new GalleryPicScreen(GalleryPicture.this);


				}
			});
		}

		private Image getFullImage (Image img) {
			
			BufferedImage resizedImg = new BufferedImage(500, 450, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2 = resizedImg.createGraphics();
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2.drawImage(img, 0, 0, 500, 450, null);
			g2.dispose();
			return resizedImg;
		}

		

		private String getCreationDate(String path) {
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
			String [] pictureInformations = null;
			pictureInformations[0]=name;
			pictureInformations[1]=creationDate;
			pictureInformations[2]=path;
			pictureInformations[3]=extension;

			return pictureInformations;
		}
	}
}
