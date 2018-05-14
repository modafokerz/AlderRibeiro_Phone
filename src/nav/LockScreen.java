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
* Exercice FIG HES-SO (Sierre)
* Auteur : Nelson Ribeiro Teixeira
* Date de création : 26 avr. 2018
* Date de modification : /
*/

@SuppressWarnings("serial")
public class LockScreen extends BaseFrame {
	private JLabel hourText = new JLabel();
	
	private JPanel bottomPanel = new JPanel();
	private JButton unlockButton = new JButton("CLICK TO UNLOCK");
	
	public LockScreen() {
		// Configure le JLabel de manière à ce qu'il affiche l'heure actuelle
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
        
        
        bottomPanel.setOpaque(false);
        
        
        
        centerPanel.add(new JLabel());
        centerPanel.add(bottomPanel);
	}
	
	//refresh l'heure toutes les secondes depuis le main
	public void updateSeconds() {
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(" HH mm : ss");
        hourText.setText(sdf.format(cal.getTime()));
	}
}
