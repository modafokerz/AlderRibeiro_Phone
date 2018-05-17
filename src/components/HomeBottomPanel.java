/*
* Exercice FIG HES-SO (Sierre)
* Auteur : Nelson Ribeiro Teixeira
* Date de création : 17 mai 2018
* Date de modification : /
*/
package components;

import java.awt.FlowLayout;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class HomeBottomPanel extends JPanel {
	
	private HPButton homeButton = new HPButton("HOME");
	private HPButton precedentButton = new HPButton("RETOUR");
	
	public HomeBottomPanel() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));
		setSize(600,100);
		
		// Il manque l'action Listener
		add(precedentButton);
		
		// Il manque l'action Listener
		add(homeButton);
		
	}

}
