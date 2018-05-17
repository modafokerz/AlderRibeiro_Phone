/*
* Exercice FIG HES-SO (Sierre)
* Auteur : Nelson Ribeiro Teixeira
* Date de cr�ation : 17 mai 2018
* Date de modification : /
*/
package components;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class GalleryIcon extends JButton {

	
	public GalleryIcon () {
		
		
		try {
			Image img = ImageIO.read(new File("img/gallery-icon.png"));
			setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	}
}
