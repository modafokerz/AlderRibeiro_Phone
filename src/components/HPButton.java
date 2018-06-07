/*
* Smartphone 602_F FIG HES-SO (Sierre)
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
	/**
	 * Composant JButton qui donne la forme désirée aux boutons du HomeBottomPanel c'est à dire : Home et Lock.
	 * @author Nelson
	 */
	
	
	public HPButton(String str) {
		/**
		 * constructeur qui appelle la méthode construction à l'instanciation du composant.
		 * @author Nelson
		 * @param string représentant le nom à donner au bouton.
		 */
		super(str);
		construction();
	}
	public HPButton(boolean isLockButton) {
		/**
		 * constructeur qui appelle la méthode construction à l'instanciation du composant et qui attribue l'image lock-icon.png au bouton.
		 * @author Nelson
		 * @param boolean permettant de savoir si c'est un lockbutton ou non
		 */
		construction();
		if(isLockButton) {
			try {
				Image img = ImageIO.read(new File("img/icons/lock-icon.png"));
				setIcon(new ImageIcon(img));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		this.setPreferredSize(new Dimension(50,50));
		
		
	}
	
	
	
	private void construction() {
		
		
		
		
		
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
