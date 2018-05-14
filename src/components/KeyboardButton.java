package components;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

import nav.PinScreen;

/*
* Exercice FIG HES-SO (Sierre)
* Auteur : Nelson Ribeiro Teixeira
* Date de création : 30 avr. 2018
* Date de modification : /
*/

@SuppressWarnings("serial")
public class KeyboardButton extends JButton {
	private PinScreen ps;
	public KeyboardButton(String string, PinScreen ps){
		super(string);
		this.ps = ps;
		construction();
	}
	
	public void construction() {
		setFont(new Font("Tahoma", Font.BOLD, 30));
		setForeground(Color.WHITE);
		setBackground(Color.darkGray);
		setBorder(new LineBorder(Color.darkGray, 0));
		
		addMouseListener(new MouseAdapter() {
            
            public void mouseEntered(MouseEvent me) {
            	
            	setContentAreaFilled(true);
            	setOpaque(true);
            	setBackground(Color.WHITE);
            	setForeground(Color.darkGray);
            	
            	
            }
            public void mouseExited(MouseEvent me) {
            	
            	setContentAreaFilled(false);
                setOpaque(false);
                setForeground(Color.WHITE);
  
            	
            }
         });
        
        addActionListener(new ActionListener()
        {
        	  public void actionPerformed(ActionEvent e)
        	  {
        	    
        		ps.updateText(KeyboardButton.this.getText());

        	  }
        	});
	}
}
