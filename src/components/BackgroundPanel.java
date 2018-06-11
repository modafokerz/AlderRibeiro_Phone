package components;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/*
 * Smartphone 602_F FIG HES-SO (Sierre)
 * Auteur : Nelson Ribeiro Teixeira
 * Date de création : 17 avr. 2018
 * Date de modification : 0
 */

@SuppressWarnings("serial")
public class BackgroundPanel extends JPanel{
	/**
	 * classe héritant de JPanel permettant l'instantiation d'un panel avec une image peinte en fond d'écran.
	 * @author Nelson
	 * @param string qui définit le path pour aller chercher l'image à mettre en fond d'écran.
	 */
	private String str;
	private int width;
	private int height;
	private Image imgPanel = null;

	
	public BackgroundPanel(String str) {
		/**
		 * Constructeur qui transmet à la classe le chemin d'accès à l'image à mettre en fond d'écran.
		 * @param chemin d'accès à l'image sous forme de string.
		 * @author Nelson
		 */
		this.str = str;
	}
	
	public BackgroundPanel(String str, int width, int height) {
		/**
		 * Constructeur qui transmet à la classe le chemin d'accès à l'image à mettre en fond d'écran.
		 * @param chemin d'accès à l'image sous forme de string.
		 * @author Nelson
		 */
		this.str = str;
		this.width = width;
		this.height = height;
	}

	public BackgroundPanel(Image img) {
		/**
		 * Constructeur qui prend l'image à mettre en fond d'écran.
		 * @param image à mettre en fond.
		 * @author Nelson
		 */
		imgPanel = img;
	}

	
	
	public void paintComponent(Graphics g) {
		/**
		 * Méthode de la classe JPanel redéfinie pour qu'elle mette en fond l'image désirée.
		 * @author Nelson
		 * @param élément graphique.
		 */
		if(width==0 || height == 0) {
			width = this.getWidth();
			height = this.getHeight();
		}
		
		if(imgPanel==null) {	
			try {
				Image img = ImageIO.read(new File(str));
				g.drawImage(img, 0, 0, this);
				g.drawImage(img, 0, 0, width, height, this);
			} catch (IOException e) {
				e.printStackTrace();
			}

			
		} else {
			g.drawImage(imgPanel, 0, 0, this);
			g.drawImage(imgPanel, 0, 0, width, height, this);
		}
	}
}
