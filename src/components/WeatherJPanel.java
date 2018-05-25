/*
* Exercice FIG HES-SO (Sierre)
* Auteur : Nelson Ribeiro Teixeira
* Date de création : 25 mai 2018
* Date de modification : /
*/
package components;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class WeatherJPanel extends JPanel {
	public void paintComponent(Graphics g) {
		try {
			Image img = ImageIO.read(new File("img/weatherApp/weatherAppBackground.png"));
			g.drawImage(img, 0, 0, this);
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
