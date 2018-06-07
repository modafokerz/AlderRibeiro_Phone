/*
* Smartphone 602_F FIG HES-SO (Sierre)
* Auteur : Nelson Ribeiro Teixeira
* Date de cr�ation : 19 mai 2018
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
	/**
	 * Composant h�ritant de JButton qui g�re les icones pr�sents dans le homescreen, leur donne la forme souhait�e et
	 * leur attribue l'icone qu'on souhaite.
	 * @author Nelson
	 */
	public HomeIcon (String str, ImageIcon ic) {
		/**
		 * Constructeur qui donne � l'icone la forme qu'on veut � l'instanciation.
		 * @author Nelson
		 * @param string qui donne le nom de l'icone.
		 * @param ImageIcone qui d�finit l'icone.
		 */
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
