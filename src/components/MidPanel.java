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

public class MidPanel extends JPanel {
	public MidPanel(String str) {
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