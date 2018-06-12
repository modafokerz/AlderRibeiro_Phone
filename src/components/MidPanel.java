/*
* Exercice FIG HES-SO (Sierre)
* Auteur : Nelson Ribeiro Teixeira
* Date de cr�ation : 12 juin 2018
* Date de modification : /
*/
package components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class MidPanel extends JPanel {
	/**
	 * Panel de l'application Camera utilis� pour afficher le message lorsque la cam�ra est �teinte
	 * ou qu'une photo a �t� prise.
	 * @autor Nelson
	 */
	
	public MidPanel(String str) {
		/**
		 * Constructeur du panel qui lui donne la forme souhait�e et affiche la String
		 * pass�e en param�tre sous forme de message au milieu du panel.
		 * @param String message � afficher.
		 */
		JLabel midLabel = new JLabel(str);
		midLabel.setForeground(Color.white);
		midLabel.setHorizontalAlignment(SwingConstants.CENTER);
		midLabel.setPreferredSize(new Dimension(600,550));
		setPreferredSize(new Dimension(600,550));
		setLayout(new FlowLayout());
		add(midLabel);
		setBackground(Color.gray);
	}
}