package components;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/*
 * Exercice FIG HES-SO (Sierre)
 * Auteur : Nelson Ribeiro Teixeira
 * Date de création : 17 avr. 2018
 * Date de modification : /
 */

@SuppressWarnings("serial")
public class BackgroundPanel extends JPanel{
	private String str;
	private Image imgPanel = null;

	public BackgroundPanel(String str) {
		this.str = str;
	}

	public BackgroundPanel(Image img) {
		imgPanel = img;
	}

	public void paintComponent(Graphics g) {
		if(imgPanel==null) {
			try {
				Image img = ImageIO.read(new File(str));
				g.drawImage(img, 0, 0, this);
				g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			g.drawImage(imgPanel, 0, 0, this);
			g.drawImage(imgPanel, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}
}
