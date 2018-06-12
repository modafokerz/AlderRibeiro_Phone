/*
* Exercice FIG HES-SO (Sierre)
* Auteur : Nelson Ribeiro Teixeira
* Date de création : 11 juin 2018
* Date de modification : /
*/
package components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class topButton extends JButton {
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
