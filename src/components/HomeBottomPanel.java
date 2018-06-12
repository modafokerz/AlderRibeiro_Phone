/*
* Smartphone 602_F FIG HES-SO (Sierre)
* Auteur : Nelson Ribeiro Teixeira
* Date de création : 17 mai 2018
* Date de modification : 08.06.2018
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
	/**
	 * classe héritant de JPanel permettant l'instantiation du panel gérant les boutons home et vérouillage du smartphone.
	 * @author Nelson
	 */
	private HPButton homeButton = new HPButton("HOME");
	private HPButton lockButton = new HPButton(true);
	
	
	public HomeBottomPanel() {
		/**
		 * constructeur de la classe permettant la construction des éléments à l'instanciation (taille, etc.).
		 * @author Nelson
		 */
		setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));
		setSize(600,100);
		
		// Il manque l'action Listener
		add(homeButton);
		add(lockButton);
	}
	
	public void addListener(AppBaseFrame ap) {
		/**
		 * Méthode qui ajoute l'action listener au home button et qui prend en entrée une application pour permettre sa fermeture lorsque 
		 * le homescreen est instancié.
		 * @author Nelson
		 * @param instance de l'application qui a appelé le bouton home.
		 */
		homeButton.addActionListener(new ActionListener()
        {
        	  public void actionPerformed(ActionEvent e)
        	  {
        	    
        		new HomeScreen();
        		ap.dispose();
        	  }
        	});
	}
	
	public void addHomeAction(ActionListener a) {
		homeButton.addActionListener(a);
	}
	
	public void addLockAction(ActionListener a) {
		lockButton.addActionListener(a);
	}
	
	public void addLockListener(AppBaseFrame ap) {
		/**
		 * Méthode qui ajoute l'action listener au lock button et qui prend en entrée une application pour permettre sa fermeture lorsque 
		 * le lockscreen est instancié.
		 * @author Nelson
		 * @param instance de l'application qui a appelé le bouton lock.
		 */
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
