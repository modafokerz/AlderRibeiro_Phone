/*
* Exercice FIG HES-SO (Sierre)
* Auteur : Nelson Ribeiro Teixeira
* Date de création : 17 mai 2018
* Date de modification : /
*/
package components;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class HomeBottomPanel extends JPanel {
	
	JButton homeButton = new JButton();
	
	public HomeBottomPanel() {
		setLayout(new FlowLayout());
		setSize(600,100);
		
		add(homeButton);
		homeButton.setPreferredSize(new Dimension(200,50));
		
	}

}
