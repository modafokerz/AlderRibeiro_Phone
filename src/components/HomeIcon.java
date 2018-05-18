/*
* Exercice FIG HES-SO (Sierre)
* Auteur : Nelson Ribeiro Teixeira
* Date de création : 19 mai 2018
* Date de modification : /
*/
package components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class HomeIcon extends JButton {
	
	public HomeIcon (String str, ImageIcon ic) {
		super(str,ic);
		
		setFocusPainted(false);
		setFont(new Font("Helvetica", Font.BOLD, 13));
		setForeground(Color.WHITE);
		setContentAreaFilled(false);
        setOpaque(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBorderPainted(false);
        setHorizontalTextPosition(SwingConstants.CENTER);
        setVerticalTextPosition(SwingConstants.BOTTOM);
	}
}
