/*
* Exercice FIG HES-SO (Sierre)
* Auteur : Nelson Ribeiro Teixeira
* Date de création : 11 juin 2018
* Date de modification : /
*/
package components;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class topLabel extends JLabel {
	public topLabel(String str) {
		super(str);
		setPreferredSize(new Dimension(350,100));
		setHorizontalAlignment(SwingConstants.CENTER);
		setFont(new Font("Impact", Font.PLAIN, 35));
	}
}
