package nav;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/*
* Smartphone 602_F FIG HES-SO (Sierre)
* Auteur : Nelson Ribeiro Teixeira
* Date de cr�ation : 26 avr. 2018
* Date de modification : /
*/

@SuppressWarnings("serial")
public class LockScreen extends BaseFrame {
	/**
	 * LockScreen ou fen�tre de d�part du smartphone
	 * Elle h�rite de BaseFrame pour avoir le fond d'�cran et la barre du haut (batterie, etc.)
	 * 
	 * Elle a en plus un bouton en bas et l'heure en plein milieu.
	 * Le bouton du bas m�ne vers la fen�tre de PinScreen ou le pin doit �tre entr� pour arriver au homescreen.
	 * 
	 * @author Nelson
	 */
	private JLabel hourText = new JLabel();
	
	private JPanel bottomPanel = new JPanel();
	private JButton unlockButton = new JButton("CLICK TO UNLOCK");
	
	public LockScreen() {
		/**
		 * Constructeur de la classe qui construit les �l�ments de la fa�on voulue
		 * Heure et bouton.
		 */
		// Configure le JLabel de mani�re � ce qu'il affiche l'heure actuelle
		super();
		turnOffTopHour();
		updateSeconds();
		unlockButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // Ajoute l'heure dans le deuxieme Grid au top
        centerPanel.add(hourText);
        
        hourText.setHorizontalAlignment(SwingConstants.LEFT);
        hourText.setVerticalAlignment(SwingConstants.BOTTOM);
        hourText.setForeground(Color.WHITE);
        hourText.setFont(new Font("Impact", Font.PLAIN, 100));
        
        // bottomPanel
        GridLayout gl = new GridLayout(3,3);
        
        bottomPanel.setLayout(gl);

        // A REGLER ---------------------
        bottomPanel.add(new JLabel());
        bottomPanel.add(new JLabel());
        bottomPanel.add(new JLabel());
        bottomPanel.add(new JLabel());

        bottomPanel.add(unlockButton);
        bottomPanel.add(new JLabel());
        bottomPanel.add(new JLabel());
        bottomPanel.add(new JLabel());
        
        unlockButton.setHorizontalAlignment(SwingConstants.CENTER);
        unlockButton.setContentAreaFilled(false);
        unlockButton.setOpaque(false);
        unlockButton.setBorder(new LineBorder(Color.WHITE, 3));
        
        
        
        unlockButton.addMouseListener(new MouseAdapter() {
            
            public void mouseEntered(MouseEvent me) { //quand la souris passe sur le bouton, change couleur
            	
            	unlockButton.setContentAreaFilled(true);
            	unlockButton.setOpaque(true);
            	unlockButton.setBackground(Color.WHITE);
            	unlockButton.setForeground(Color.BLACK);
            	
            	
            }
            public void mouseExited(MouseEvent me) { //quand la souris sors du bouton, remet normal.
            	unlockButton.setBorder(new LineBorder(Color.WHITE, 3));
            	unlockButton.setContentAreaFilled(false);
                unlockButton.setOpaque(false);
                unlockButton.setForeground(Color.WHITE);
  
            	
            }
         });
        
        unlockButton.addActionListener(new ActionListener()
        {
        	  public void actionPerformed(ActionEvent e)
        	  {
        	    
        		new PinScreen();
				LockScreen.this.dispose();

        	  }
        	});
        
        

       
        
        unlockButton.setFont(new Font("Helvetica", Font.BOLD, 17));
        unlockButton.setForeground(Color.WHITE);
        unlockButton.setFocusPainted(false);
        
        bottomPanel.setOpaque(false);
        
        
        
        centerPanel.add(new JLabel());
        centerPanel.add(bottomPanel);
	}
	
	//refresh l'heure toutes les secondes depuis le main
	public void updateSeconds() {
		/*
		 * Permet de update les secondes de l'heure.
		 */
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(" HH mm : ss");
        hourText.setText(sdf.format(cal.getTime()));
	}
}
