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
 * Date de cr�ation : 17 avr. 2018
 * Date de modification : 0
 */

@SuppressWarnings("serial")
public class BackgroundPanel extends JPanel{
	/**
	 * classe h�ritant de JPanel permettant l'instantiation d'un panel avec une image peinte en fond d'�cran.
	 * @author Nelson
	 * @param string qui d�finit le path pour aller chercher l'image � mettre en fond d'�cran.
	 */
	private String str;
	private int width;
	private int height;
	private Image imgPanel = null;

	
	public BackgroundPanel(String str) {
		/**
		 * Constructeur qui transmet � la classe le chemin d'acc�s � l'image � mettre en fond d'�cran.
		 * @param chemin d'acc�s � l'image sous forme de string.
		 * @author Nelson
		 */
		this.str = str;
	}
	
	public BackgroundPanel(String str, int width, int height) {
		/**
		 * Constructeur qui transmet � la classe le chemin d'acc�s � l'image � mettre en fond d'�cran.
		 * @param chemin d'acc�s � l'image sous forme de string.
		 * @author Nelson
		 */
		this.str = str;
		this.width = width;
		this.height = height;
	}

	public BackgroundPanel(Image img) {
		/**
		 * Constructeur qui prend l'image � mettre en fond d'�cran.
		 * @param image � mettre en fond.
		 * @author Nelson
		 */
		imgPanel = img;
	}

	
	
	public void paintComponent(Graphics g) {
		/**
		 * M�thode de la classe JPanel red�finie pour qu'elle mette en fond l'image d�sir�e.
		 * @author Nelson
		 * @param �l�ment graphique.
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
