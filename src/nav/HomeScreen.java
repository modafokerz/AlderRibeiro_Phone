/*
* Exercice FIG HES-SO (Sierre)
* Auteur : Nelson Ribeiro Teixeira
* Date de création : 16 mai 2018
* Date de modification : /
*/
package nav;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;

import components.HomeBottomPanel;

@SuppressWarnings("serial")
public class HomeScreen extends BaseFrame {
	
	HomeBottomPanel bp = new HomeBottomPanel();
	
	public HomeScreen () {
		
		centerPanel.setLayout(new GridLayout(3,4));
		for(int i = 0; i < 12; i++)
			centerPanel.add(new JButton());
		
		add(bp,BorderLayout.SOUTH);
	}
	
	
}
