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
import nav.LockScreen;


@SuppressWarnings("serial")
public class HomeBottomPanel extends JPanel {
	
	private HPButton homeButton = new HPButton("HOME");
	private HPButton lockButton = new HPButton(true);
	
	
	public HomeBottomPanel() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));
		setSize(600,100);
		
		
		// Il manque l'action Listener
		add(homeButton);
		add(lockButton);
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
	
	public void addLockListener(AppBaseFrame ap) {
		lockButton.addActionListener(new ActionListener()
        {
        	  public void actionPerformed(ActionEvent e)
        	  {
        	    
        		new LockScreen();
        		ap.dispose();
        	  }
        	});
	}

}
