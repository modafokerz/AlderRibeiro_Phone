/*
* Exercice FIG HES-SO (Sierre)
* Auteur : Nelson Ribeiro Teixeira
* Date de création : 16 mai 2018
* Date de modification : /
*/
package nav;


import java.awt.GridLayout;
import javax.swing.JButton;


@SuppressWarnings("serial")
public class HomeScreen extends AppBaseFrame {
	
	
	
	
	public HomeScreen () {
		
		centerPanel.setLayout(new GridLayout(6,4));
		
		
		
		for(int i = 0; i < 24; i++)
			centerPanel.add(new JButton());
		
		
	}
	
	
}
