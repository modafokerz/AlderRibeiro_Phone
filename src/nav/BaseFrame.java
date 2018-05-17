package nav;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;

import components.BackgroundPanel;
import components.Kernel32;
import components.TopJLabel;

/*
* Exercice FIG HES-SO (Sierre)
* Auteur : Nelson Ribeiro Teixeira
* Date de cr�ation : 16 avr. 2018
* Date de modification : /
*/

@SuppressWarnings("serial")
public class BaseFrame extends JFrame {
	
	// Le panel du Top et ses composants
	private JPanel topPanel = new JPanel();
	private TopJLabel batteryLife = new TopJLabel();
	private TopJLabel currentHour = new TopJLabel();
	private TopJLabel currentDate = new TopJLabel();
	
	// Le panel du centre et ses composants
	protected BackgroundPanel centerPanel = new BackgroundPanel();
	
	public static Color baseColor = new Color(0, 0, 0, 10);


	
	
	public BaseFrame() {
		// Param�tres de base de la JFrame
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
        topPanel.setBackground(baseColor);
        
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
		
		

		
		
		
		
		
		
	}
	
	public void turnOffTopHour() {
		currentHour.setVisible(false);
	}
	
	

}
