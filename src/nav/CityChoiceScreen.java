/*
* Exercice FIG HES-SO (Sierre)
* Auteur : Nelson Ribeiro Teixeira
* Date de création : 8 juin 2018
* Date de modification : /
*/
package nav;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import components.BackgroundPanel;

@SuppressWarnings("serial")
public class CityChoiceScreen extends AppBaseFrame {
	private BackgroundPanel cPanel = new BackgroundPanel("img/weatherApp/weatherAppBackground.png");
	private CCSButton aigle = new CCSButton("Aigle");
	private CCSButton sion = new CCSButton("Sion");
	private CCSButton sierre = new CCSButton("Sierre");
	
	public CityChoiceScreen() {
		remove(centerPanel);
		cPanel.setLayout(new FlowLayout());
		cPanel.setPreferredSize(new Dimension(600,550));
		JLabel fvc = new JLabel("Choisissez une ville");
		fvc.setPreferredSize(new Dimension(600,200));
		fvc.setHorizontalAlignment(SwingConstants.CENTER);
		fvc.setFont(new Font("Impact", Font.BOLD, 35));
		fvc.setForeground(Color.white);
		cPanel.add(fvc);
		cPanel.add(aigle);
		cPanel.add(sion);
		cPanel.add(sierre);
		
		add(cPanel,BorderLayout.CENTER);
		
	}
	
	class CCSButton extends JButton {
		private String cityName;
		public CCSButton(String str) {
			super(str);
			cityName = str;
			setPreferredSize(new Dimension(550,100));
			setCursor(new Cursor(Cursor.HAND_CURSOR));
			setContentAreaFilled(false);
	        setOpaque(false);
	        setBorder(new LineBorder(Color.WHITE, 3));
	        setForeground(Color.white);
	        setFont(new Font("Arial", Font.PLAIN,35));
	        
	        addMouseListener(new MouseAdapter() {
	            
	            public void mouseEntered(MouseEvent me) { //quand la souris passe sur le bouton, change couleur
	            	
	            	setContentAreaFilled(true);
	            	setOpaque(true);
	            	setBackground(Color.WHITE);
	            	setForeground(Color.BLACK);
	            	
	            	
	            }
	            public void mouseExited(MouseEvent me) { //quand la souris sors du bouton, remet normal.
	            	setBorder(new LineBorder(Color.WHITE, 3));
	            	setContentAreaFilled(false);
	            	setOpaque(false);
	                setForeground(Color.WHITE);
	  
	            	
	            }
	         });
	        
			addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					File fichier = new File("saves/choixVille.txt");
					Writer w;
					try {
						w = new FileWriter(fichier);
						BufferedWriter bfw = new BufferedWriter(w);
						bfw.write(cityName);
						bfw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					new WeatherApp();
					CityChoiceScreen.this.dispose();
					
				}
				
			});
		}
		
	}
}
