package components;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

/*
* Exercice FIG HES-SO (Sierre)
* Auteur : Nelson Ribeiro Teixeira
* Date de création : 30 avr. 2018
* Date de modification : /
*/

@SuppressWarnings("serial")
public class TopJLabel extends JLabel {
	public TopJLabel() {
		super();
		construction();
	}
	
	public TopJLabel(String string) {
		super(string);
		construction();
	}
	
	private void construction() {
		setSize(getPreferredSize());
		setForeground(Color.darkGray);
		setFont(new Font("Arial Black", Font.PLAIN, 12));
	}
}
