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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class HPButton extends JButton {
	public HPButton() {
		construction();
	}
	
	public HPButton(String str) {
		super(str);
		construction();
	}
	
	private void construction() {
		setPreferredSize(new Dimension(200,50));
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setHorizontalAlignment(SwingConstants.CENTER);

		setBorder(new LineBorder(Color.black, 3));
 		setForeground(Color.black);
 		setBackground(Color.darkGray);
		setOpaque(false);
		setContentAreaFilled(false);
		
		// Ajoute l'action au hover du bouton
		addMouseListener(new MouseAdapter() {
            
			 public void mouseEntered(MouseEvent me) { //quand la souris passe sur le bouton, change couleur
	            	
	            	setContentAreaFilled(true);
	            	setOpaque(true);
	            	setBorder(new LineBorder(Color.white, 3));
	        		setForeground(Color.white);
	        		setBackground(Color.black);
	            	
	            	
	            }
	            public void mouseExited(MouseEvent me) { //quand la souris sors du bouton, remet normal.
	            	
	            	setContentAreaFilled(false);
	                setOpaque(false);
	                setBorder(new LineBorder(Color.black, 3));
	        		setForeground(Color.black);
	        		setBackground(Color.darkGray);
	  
	            	
	            }
         });
	}
}
