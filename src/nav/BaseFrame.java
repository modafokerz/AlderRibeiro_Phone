package nav;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;

import components.BackgroundPanel;
import components.Kernel32;
import components.TopJLabel;

/*
* Smartphone 602_F FIG HES-SO (Sierre)
* Auteur : Nelson Ribeiro Teixeira
* Date de cr�ation : 16 avr. 2018
* Date de modification : /
*/

@SuppressWarnings("serial")
public class BaseFrame extends JFrame implements KeyListener{
	/**
	 * Frame de base qui d�finit le fond d'�cran ainsi que la barre du haut comprenant la batterie
	 * la date et un JLabel qui simule l'op�rateur.
	 * @author Nelson
	 */
	
	// Le panel du Top et ses composants
	private JPanel topPanel = new JPanel();
	private TopJLabel batteryLife = new TopJLabel();
	private TopJLabel currentHour = new TopJLabel();
	private TopJLabel currentDate = new TopJLabel();
	
	// Le panel du centre et ses composants
	protected BackgroundPanel centerPanel = new BackgroundPanel("img/HomeBackground.jpg");
	
	


	
	
	public BaseFrame() {
		/**
		 * Constructeur de la classe qui initalise les composants et les construit.
		 */
		
		super();
		// Param�tres de base de la JFrame
		setUndecorated(true);
		setVisible(true);
		setSize(600,800);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null); //mettre la fen�tre au milieu de l'�cran.
		
		
		// Heure + Date dans les label + g�re la batterie
		Calendar cal = Calendar.getInstance(); //lib. java qui prend l'instance date de l'ordi
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm"); //formatage
        currentHour.setText(sdf.format(cal.getTime()));
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date();
        currentDate.setText(dateFormat.format(date));
		
        //Pour r�cup�rer la batterie de l'ordinateur
        Kernel32.SYSTEM_POWER_STATUS batteryStatus = new Kernel32.SYSTEM_POWER_STATUS();
        Kernel32.INSTANCE.GetSystemPowerStatus(batteryStatus);
        batteryLife.setText("Power : " + batteryStatus.getBatteryLifePercent());
        
		// Panel du top (Operateur, Heure, Date)
        topPanel.setVisible(true);
        topPanel.setBackground(new Color(0, 0, 0, 10));
        
        // -- G�re l'alignement de mani�re � ce que l'heure soit toujours au milieu de l'�cran,
        //    L'op�rateur � gauche et la date � droite
        
        topPanel.setLayout(new GridLayout(1,3));
        topPanel.add(batteryLife);
        topPanel.add(currentHour);
        currentHour.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(currentDate);
        currentDate.setHorizontalAlignment(SwingConstants.RIGHT);
		
		add(topPanel, BorderLayout.NORTH);
		
		// CenterPanel : le Background 
		add(centerPanel, BorderLayout.CENTER);
		centerPanel.setVisible(true);
		centerPanel.setSize(600, 650);
		centerPanel.setLayout(new GridLayout(3,3, 10, 10));
		
		
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
	            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Cancel"); 
	        getRootPane().getActionMap().put("Cancel", new AbstractAction(){ 
	            public void actionPerformed(ActionEvent e)
	            {
	                BaseFrame.this.dispose();
	            }
	        });
	        
	        
	        addWindowListener(new WindowAdapter() {
	            public void windowClosing(java.awt.event.WindowEvent evt) 
	            {
	            	BaseFrame.this.dispose();
	            }
	        });
		
		
		
		
		
		
	}
	
	public void turnOffTopHour() {
		/**
		 * m�thode permettant de rendre invisible l'heure affich�e dans la barre du haut
		 * (utilis�e dans le LockScreen).
		 */
		currentHour.setVisible(false);
	}

	@SuppressWarnings("static-access")
	@Override
	public void keyPressed(KeyEvent e) {
		/**
		 * M�tode permettant de �teindre le programme lorsqu'on appuie sur ECHAP.
		 */
		if(e.getKeyCode() == e.VK_ESCAPE) {
			System.exit(0);
		} 
	}

	@Override
	public void keyReleased(KeyEvent e) {
		/**
		 * M�tode h�rit�e du KeyListener, inutile ici.
		 */
	}

	@Override
	public void keyTyped(KeyEvent e) {
		/**
		 * M�tode h�rit�e du KeyListener, inutile ici.
		 */
		
	}
	
	

}
