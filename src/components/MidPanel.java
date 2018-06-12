/*
* Exercice FIG HES-SO (Sierre)
* Auteur : Nelson Ribeiro Teixeira
* Date de création : 12 juin 2018
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
	 * Panel de l'application Camera utilisé pour afficher le message lorsque la caméra est éteinte
	 * ou qu'une photo a été prise.
	 * @autor Nelson
	 */
	
	public MidPanel(String str) {
		/**
		 * Constructeur du panel qui lui donne la forme souhaitée et affiche la String
		 * passée en paramètre sous forme de message au milieu du panel.
		 * @param String message à afficher.
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