/*
 * Exercice FIG HES-SO (Sierre)
 * Auteur : Nelson Ribeiro Teixeira
 * Date de création : 24 mai 2018
 * Date de modification : /
 */
package nav;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;





public class WeatherApp extends AppBaseFrame {
	
	
	private String ville = "Sierre";
	private String villeStatus = "Ensoleillé";
	private String villeTemp = "25";
	private JPanel weatherAppPanel = new JPanel();
	
	
	private JPanel topAppPanel = new JPanel();
	private JButton cityChoiceButton = new JButton();
	private JLabel cityLabel = new JLabel(ville);
	private JLabel cityStatus = new JLabel(villeStatus);
	private JLabel cityTemp = new JLabel(villeTemp + " °C");
	
	
	private JPanel bottomAppPanel = new JPanel();
	
	
	public WeatherApp() {
		super();

		remove(centerPanel);
		add(weatherAppPanel, BorderLayout.CENTER);
		weatherAppPanel.setSize(600, 650);
		
		
		// Top App Weather Panel construction !
		
		
	}
	
	

	
	
	
	
	
}
