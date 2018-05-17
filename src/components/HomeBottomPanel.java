/*
* Exercice FIG HES-SO (Sierre)
* Auteur : Nelson Ribeiro Teixeira
* Date de création : 17 mai 2018
* Date de modification : /
*/
package components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class HomeBottomPanel extends JPanel {
	
	HPButton homeButton = new HPButton("HOME");
	HPButton precedentButton = new HPButton("RETOUR");
	
	public HomeBottomPanel() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));
		setSize(600,100);
		
		add(precedentButton);
		
		add(homeButton);
		
	}

}
