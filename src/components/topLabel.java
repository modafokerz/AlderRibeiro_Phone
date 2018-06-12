/*
* Smartphone 602_F FIG HES-SO (Sierre)
* Auteur : Nelson Ribeiro Teixeira
* Date de création : 11 juin 2018
* Date de modification : /
*/
package components;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class topLabel extends JLabel {
	/**
	 * Label de la partie du haut de la caméra
	 * @author Nelson
	 */
	public topLabel(String str) {
		/**
		 * Construction du composant à l'instanciation
		 * 
		 * @param str    message a afficher sur le JLabel
		 * 
		 */
		super(str);
		setPreferredSize(new Dimension(350,100));
		setHorizontalAlignment(SwingConstants.CENTER);
		setFont(new Font("Impact", Font.PLAIN, 35));
	}
}
