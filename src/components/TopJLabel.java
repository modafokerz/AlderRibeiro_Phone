package components;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

/*
* Smartphone 602_F FIG HES-SO (Sierre)
* Auteur : Nelson Ribeiro Teixeira
* Date de cr�ation : 30 avr. 2018
* Date de modification : /
*/

@SuppressWarnings("serial")
public class TopJLabel extends JLabel {
	/**
	 * composant JLabel qui est utilis� dans le PinScreen(code pin) et dans la calculatrice qui compose le clavier.
	 * @author Nelson
	 */
	public TopJLabel() {
		/**
		 * constructeur par d�faut du composant qui appelle la m�thode construction.
		 * @author Nelson
		 */
		super();
		construction();
	}
	
	public TopJLabel(String string) {
		/**
		 * constructeur prenant en entr�e une string qui donnera le texte du jlabel et appele ensuite la m�thode construction.
		 * @author Nelson
		 * @param string qui donnera le texte affich� du jlabel.
		 */
		super(string);
		construction();
	}
	
	private void construction() {
		/**
		 * m�thode qui build l'apparence du JLabel.
		 * @author Nelson
		 */
		setSize(getPreferredSize());
		setForeground(Color.darkGray);
		setFont(new Font("Arial Black", Font.PLAIN, 12));
	}
}
