package components;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import nav.CalculatorApp;
import nav.PinScreen;

/*
* Smartphone 602_F FIG HES-SO (Sierre)
* Auteur : Nelson Ribeiro Teixeira
* Date de création : 30 avr. 2018
* Date de modification : /
*/

@SuppressWarnings("serial")
public class KeyboardButton extends JButton {
	/**
	 * composant JButton qui est utilisé dans le PinScreen(code pin) et dans la calculatrice qui compose le clavier.
	 * @author Nelson
	 */
	private PinScreen ps;
	@SuppressWarnings("unused")
	private CalculatorApp ca;
	
	public KeyboardButton(String string, PinScreen ps){
		/**
		 * constructeur qui appelle la méthode construction à l'instanciation du composant.
		 * @param string représentant le nom à donner au bouton.
		 * @param pinscreen, instance du pinscreen qui y update un composant JLabel.
		 */
		super(string);
		this.ps = ps;
		construction();
		addActionListener();
	}
	
	private void construction() {
		/**
		 * méthode qui construit l'apparence du composant.
		 */
		setFocusPainted(false);
		setFont(new Font("Tahoma", Font.BOLD, 30));
		setForeground(Color.WHITE);
		setBackground(Color.darkGray);
		setBorderPainted(false); //pour supprimer les bords du clavier
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		addMouseListener(new MouseAdapter() {
            
			// Action au hover de la souris en entrée
            public void mouseEntered(MouseEvent me) {
            	
            	setContentAreaFilled(true);
            	setOpaque(true);
            	setBackground(Color.WHITE);
            	setForeground(Color.darkGray);
            	
            	
            }
            
         // Action au hover de la souris en sortie
            public void mouseExited(MouseEvent me) {
            	
            	setContentAreaFilled(false);
                setOpaque(false);
                setForeground(Color.WHITE);
  
            	
            }
         });
        
        
	}
	
	public KeyboardButton(String str, CalculatorApp calculatorApp) {
		/**
		 * constructeur qui appelle la méthode construction à l'instanciation du composant.
		 * @author Jean Marie
		 * @param string représentant le nom à donner au bouton.
		 * @param calculatorApp, instance de la calculatrice qui y update un composant JLabel.
		 */
		super(str);
		this.ca = calculatorApp;
		
		construction();
	}
	
	private void addActionListener() {
		/**
		 * méthode qui ajoute un actionlistener au composant lui même qui update le JLabel du pinscreen affichant l'état du code .
		 * @author Nelson
		 */
		addActionListener(new ActionListener()
        {
        	  public void actionPerformed(ActionEvent e)
        	  {
        	    
        		ps.updateText(KeyboardButton.this.getText());

        	  }
        	});
	}
	

}
