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
import java.awt.image.BufferedImage;
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

public class GalleryScreen extends AppBaseFrame {
	
	private JPanel galleryPanel = new JPanel();
	private JPanel topPanel = new JPanel();
	private JPanel picturesPanel = new JPanel();
	
	// Eléments du topPanel
	private topButton recherche = new topButton("img/icons/search_icon.png");
	private JLabel galerieLabel = new JLabel("Galerie");
	private	topButton addPic = new topButton("img/icons/add_icon.png");
	
	public GalleryScreen() {
		remove(centerPanel);
		
		
		// Initialisation des Panel
		topPanel.setPreferredSize(new Dimension(600,100));
		
		picturesPanel.setPreferredSize(new Dimension(600,550));

		
		galleryPanel.setPreferredSize(new Dimension(600,650));
		galleryPanel.setLayout(new FlowLayout());
		
		galleryPanel.add(topPanel,BorderLayout.NORTH);
		
		
		add(galleryPanel, BorderLayout.CENTER);
		
		
		// Construction du panel du top
		topPanel.setLayout(new FlowLayout());
		
		galerieLabel.setPreferredSize(new Dimension(350,100));
		galerieLabel.setHorizontalAlignment(SwingConstants.CENTER);
		galerieLabel.setFont(new Font("Impact", Font.PLAIN, 35));
		
		topPanel.add(recherche);
		topPanel.add(galerieLabel);
		topPanel.add(addPic);
		
		// Construction du panel contenant les photos
		picturesPanel.setLayout(new GridLayout(3,3));
		
		
		
		GalleryPicture gl = new GalleryPicture("pic01.jpg");
		picturesPanel.add(gl);
		for (int i = 0; i < 8 ; i++) {
			picturesPanel.add(new JLabel("test"));
		}
		
		JScrollPane scroll = new JScrollPane(picturesPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		galleryPanel.add(picturesPanel, BorderLayout.SOUTH);
	}
	
	class topButton extends JButton {
		private String path;
		
		public topButton(String str) {
			path = str;
			
			setPreferredSize(new Dimension(100,70));
			setCursor(new Cursor(Cursor.HAND_CURSOR));
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
	
	class GalleryPicture extends JButton {
		
		private boolean isIcon = true;
		private String path = "img/gallery/";
		
		public GalleryPicture(String pictureName) {
			if(isIcon) {
				
				try {
					Image img = ImageIO.read(new File(path+pictureName));
					img = getImageIcon(img);
					setIcon(new ImageIcon(img));
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} else {
				
			}
		}
		
		private Image getImageIcon (Image img) {
			 	BufferedImage resizedImg = new BufferedImage(180, 200, BufferedImage.TYPE_INT_RGB);
		        Graphics2D g2 = resizedImg.createGraphics();
		        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		        g2.drawImage(img, 0, 0, 180, 200, null);
		        g2.dispose();
		        return resizedImg;
		}
		
		private Image getFullImage () {
			return null;
		}
		
		public void setAsImage() {
			isIcon = false;
		}
	}
}
