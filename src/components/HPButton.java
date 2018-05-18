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
import java.awt.Font;
import java.awt.Image;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;




@SuppressWarnings("serial")
public class HPButton extends JButton {
	
	
	
	public HPButton(String str) {
		super(str);
		construction();
	}
	public HPButton() {
		construction();
	}
	
	
	
	private void construction() {
		
		
		
		try {
			Image img = ImageIO.read(new File("img/icons/home-icon.png"));
			setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setFont(new Font("Helvetica", Font.BOLD, 20));
		setVerticalTextPosition(SwingConstants.CENTER);
		setHorizontalAlignment(SwingConstants.LEFT);
		setForeground(Color.black);
		setIconTextGap(30);
		
		setPreferredSize(new Dimension(200,50));
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setHorizontalAlignment(SwingConstants.CENTER);
		
		setBorder(new LineBorder(Color.black, 3));
 		
 		setBackground(Color.darkGray);
		setOpaque(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		
		
		// Ajoute l'action au hover du bouton
		addMouseListener(new MouseAdapter() {
            
			 public void mouseEntered(MouseEvent me) { //quand la souris passe sur le bouton, change couleur
	            	
	            	
				 	setContentAreaFilled(true);
	                setOpaque(true);
	                setBackground(Color.gray);
	        		
	            	
	            	
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
