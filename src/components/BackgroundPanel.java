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
	
	public void paintComponent(Graphics g) {
		try {
			Image img = ImageIO.read(new File("img/HomeBackground.jpg"));
			g.drawImage(img, 0, 0, this);
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
