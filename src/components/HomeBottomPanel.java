/*
* Exercice FIG HES-SO (Sierre)
* Auteur : Nelson Ribeiro Teixeira
* Date de création : 17 mai 2018
* Date de modification : /
*/
package components;

import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import nav.AppBaseFrame;
import nav.HomeScreen;


@SuppressWarnings("serial")
public class HomeBottomPanel extends JPanel {
	
	private HPButton homeButton = new HPButton("HOME");
	
	
	public HomeBottomPanel() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));
		setSize(600,100);
		
		
		// Il manque l'action Listener
		add(homeButton);
		
	}
	
	public void addListener(AppBaseFrame ap) {
		homeButton.addActionListener(new ActionListener()
        {
        	  public void actionPerformed(ActionEvent e)
        	  {
        	    
        		new HomeScreen();
        		ap.dispose();
        	  }
        	});
	}

}
