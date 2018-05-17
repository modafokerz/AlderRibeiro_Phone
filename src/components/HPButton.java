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

import nav.BaseFrame;

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
		setBackground(BaseFrame.baseColor);
		
		
		// Ajoute l'action au hover du bouton
		addMouseListener(new MouseAdapter() {
            
            public void mouseEntered(MouseEvent me) { //quand la souris passe sur le bouton, change couleur
            	
            	
            	setBackground(Color.black);
            	setForeground(BaseFrame.baseColor);
            	setBorder(new LineBorder(BaseFrame.baseColor, 3));
            	
            }
            public void mouseExited(MouseEvent me) { //quand la souris sors du bouton, remet à l'état précédent
            	setBorder(new LineBorder(Color.black, 3));
        		setForeground(Color.black);
        		setBackground(BaseFrame.baseColor);
            }
         });
	}
}
