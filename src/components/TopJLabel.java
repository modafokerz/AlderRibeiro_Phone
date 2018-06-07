package components;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

/*
* Smartphone 602_F FIG HES-SO (Sierre)
* Auteur : Nelson Ribeiro Teixeira
* Date de création : 30 avr. 2018
* Date de modification : /
*/

@SuppressWarnings("serial")
public class TopJLabel extends JLabel {
	/**
	 * composant JLabel qui est utilisé dans le PinScreen(code pin) et dans la calculatrice qui compose le clavier.
	 * @author Nelson
	 */
	public TopJLabel() {
		/**
		 * constructeur par défaut du composant qui appelle la méthode construction.
		 * @author Nelson
		 */
		super();
		construction();
	}
	
	public TopJLabel(String string) {
		/**
		 * constructeur prenant en entrée une string qui donnera le texte du jlabel et appele ensuite la méthode construction.
		 * @author Nelson
		 * @param string qui donnera le texte affiché du jlabel.
		 */
		super(string);
		construction();
	}
	
	private void construction() {
		/**
		 * méthode qui build l'apparence du JLabel.
		 * @author Nelson
		 */
		setSize(getPreferredSize());
		setForeground(Color.darkGray);
		setFont(new Font("Arial Black", Font.PLAIN, 12));
	}
}
