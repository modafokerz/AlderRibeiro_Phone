package nav;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import components.KeyboardButton;

/*
 * Smartphone 602_F FIG HES-SO (Sierre)
 * Auteur : Nelson Ribeiro Teixeira
 * Date de création : 30 avr. 2018
 * Date de modification : /
 */

@SuppressWarnings("serial")
public class PinScreen extends BaseFrame {
	/**
	 * PinScreen : fenêtre ou on doit mettre le code pour accéder à l'application
	 * par défaut c'est 0000.
	 * 
	 * La fenêtre est construite de la facon suivante.
	 * Elle hérite de BaseFrame (fond d'écran et barre du haut)
	 * puis elle en haut un jlabel avec le titre : Insérez code.
	 * 
	 * Ensuite elle affiche le code entré et plus bas un
	 * panel en gridlayout avec les chiffres, etc. pour pouvoir entrer le code.
	 * 
	 */
	private String text = "";
	private JLabel topText = new JLabel("Insérez code : 0000");
	private JPanel topTextPanel = new JPanel();
	private JPanel pinPanel = new JPanel();
	private JLabel pinEntered = new JLabel();

	private JPanel keyboard = new JPanel();
	private JButton returnButton = new JButton("RETOUR");

	public PinScreen() {
		/*
		 * Constructeur de la classe qui construit les éléments et leur apparence.
		 */
		super();
		this.remove(centerPanel);
		setBackground(Color.WHITE);
		// Gère le top Panel comprenant le texte
		topText.setPreferredSize(new Dimension(600, 100)); //Taille du composant (taille du JLabel)
		topText.setHorizontalAlignment(SwingConstants.CENTER);
		topText.setVerticalAlignment(SwingConstants.TOP);
		topText.setBackground(Color.WHITE);
		topText.setFont(new Font("Arial Black", Font.PLAIN, 45));
		topTextPanel.add(topText,BorderLayout.CENTER);
		topTextPanel.setBackground(Color.white);
		
		pinPanel.add(topTextPanel,BorderLayout.NORTH);
		pinPanel.setBackground(Color.WHITE);
		pinPanel.add(pinEntered,BorderLayout.CENTER);
		pinEntered.setPreferredSize(new Dimension(600,50));
		pinEntered.setHorizontalAlignment(SwingConstants.CENTER);
		pinEntered.setFont(new Font("Helvetica", Font.PLAIN, 35));
		pinEntered.setForeground(Color.darkGray);
		pinEntered.setPreferredSize(new Dimension(600,100));
		pinEntered.setText(text);

		
		// Gère le keyboard afin de pouvoir entrer le code
		keyboard.setBackground(Color.darkGray);
		keyboard.setLayout(new GridLayout(4,3));
		keyboard.setPreferredSize(new Dimension(600,450));
		
		// Gère les bouton pour effacer dernier caractère entré et valider
		KeyboardButton erase = new KeyboardButton("", this);
		KeyboardButton validate = new KeyboardButton("", this);
		
		
		
		// Icones des boutons
		
		
		  try {
		    Image img = ImageIO.read(new File("img/icons/delete-icon2.png"));
		    erase.setIcon(new ImageIcon(img));
		    
		    Image img2 = ImageIO.read(new File("img/icons/validate-icon2.png"));
		    validate.setIcon(new ImageIcon(img2));
		  } catch (Exception ex) {
		    System.out.println(ex);
		  }
		  
		  erase.addMouseListener(new MouseAdapter() {
	            
	            public void mouseEntered(MouseEvent me) { //quand la souris passe sur le bouton, change couleur
	            	try {
	        		    Image img = ImageIO.read(new File("img/icons/delete-icon.png"));
	        		    erase.setIcon(new ImageIcon(img));
	        		    
	        		  } catch (Exception ex) {
	        		    System.out.println(ex);
	        		  }
	            }
	            public void mouseExited(MouseEvent me) { //quand la souris sors du bouton, remet normal.

	            	try {
	        		    Image img = ImageIO.read(new File("img/icons/delete-icon2.png"));
	        		    erase.setIcon(new ImageIcon(img));
	        		    
	        		  } catch (Exception ex) {
	        		    System.out.println(ex);
	        		  }
	        		
	            }
	         });
		  
		  validate.addMouseListener(new MouseAdapter() {
	            
	            public void mouseEntered(MouseEvent me) { //quand la souris passe sur le bouton, change couleur
	            	try {
	        		    Image img = ImageIO.read(new File("img/icons/validate-icon.png"));
	        		    validate.setIcon(new ImageIcon(img));
	        		    
	        		  } catch (Exception ex) {
	        		    System.out.println(ex);
	        		  }
	            }
	            public void mouseExited(MouseEvent me) { //quand la souris sors du bouton, remet normal.

	            	try {
	        		    Image img = ImageIO.read(new File("img/icons/validate-icon2.png"));
	        		    validate.setIcon(new ImageIcon(img));
	        		    
	        		  } catch (Exception ex) {
	        		    System.out.println(ex);
	        		  }
	        		
	            }
	         });
		  
		
		// supprime les action listener des boutons (ceux dont ils ont hérité)
		for( ActionListener al : erase.getActionListeners() ) {
	        erase.removeActionListener( al );
	    }
		
		for( ActionListener al : validate.getActionListeners() ) {
	        validate.removeActionListener( al );
	    }
		
		
		// crée les nouveaux action listener et les ajoute au bouton
		erase.addActionListener(new ActionListener()
        {
      	  public void actionPerformed(ActionEvent e)
      	  {
      	    
      		eraseLastCarac();

      	  }
      	});
		
		validate.addActionListener(new ActionListener()
        {
	      	  public void actionPerformed(ActionEvent e)
	      	  {
	      	    
	      		verifyCodeEntered();

	      	  }
	      	});
		
		keyboard.setBorder(new LineBorder(Color.WHITE, 3));
		keyboard.add(new KeyboardButton("1", this));
		keyboard.add(new KeyboardButton("2", this));
		keyboard.add(new KeyboardButton("3", this));
		keyboard.add(new KeyboardButton("4", this));
		keyboard.add(new KeyboardButton("5", this));
		keyboard.add(new KeyboardButton("6", this));
		keyboard.add(new KeyboardButton("7", this));
		keyboard.add(new KeyboardButton("8", this));
		keyboard.add(new KeyboardButton("9", this));
		keyboard.add(erase);
		keyboard.add(new KeyboardButton("0", this));
		keyboard.add(validate);
		
		
		
		// apparance bouton du bas + action
		returnButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		returnButton.setHorizontalAlignment(SwingConstants.CENTER);
		returnButton.setContentAreaFilled(false);
		returnButton.setOpaque(true);
		returnButton.setFont(new Font("Helvetica", Font.BOLD, 17));
		returnButton.setBackground(Color.white);
		returnButton.setForeground(Color.darkGray);
        returnButton.setPreferredSize(new Dimension(600,80));
        returnButton.setBorderPainted(false);
        returnButton.setFocusPainted(false);
		returnButton.addMouseListener(new MouseAdapter() {
            
            public void mouseEntered(MouseEvent me) { //quand la souris passe sur le bouton, change couleur
            	
            	returnButton.setBorderPainted(true);
            	returnButton.setBorder(new LineBorder(Color.DARK_GRAY, 5));
            }
            public void mouseExited(MouseEvent me) { //quand la souris sors du bouton, remet normal.

            	returnButton.setBackground(Color.white);
            	returnButton.setBorderPainted(false);
        		
            }
         });
        
		returnButton.addActionListener(new ActionListener()
        {
        	  public void actionPerformed(ActionEvent e)
        	  {
        	    
        		new LockScreen();
				PinScreen.this.dispose();

        	  }
        	});
		
		// Layouts
		pinPanel.add(keyboard,BorderLayout.CENTER);
		add(returnButton, BorderLayout.SOUTH);
		add(pinPanel,BorderLayout.CENTER);

	}

	protected void verifyCodeEntered() {
		/**
		 * Methode permettant de vérifer le code entrer, s'il est faux
		 * modifie le texte du haut en CODE INVALIDE.
		 */
		if (pinEntered.getText().equals("0000")) {
			dispose();
			new HomeScreen();
		} else {
			topText.setText("CODE INVALIDE !");
		}
		
	}

	public void updateText(String str) {
		/**
		 * Méthode permettant aux boutons de update au clic le texte affiché.
		 */

		if (pinEntered.getText().length() < 4) {
			switch(str) {
			case "1": text+="1";
			break;
			case "2": text+="2";
			break;
			case "3": text+="3";
			break;
			case "4": text+="4";
			break;
			case "5": text+="5";
			break;
			case "6": text+="6";
			break;
			case "7": text+="7";
			break;
			case "8": text+="8";
			break;
			case "9": text+="9";
			break;
			case "0": text+="0";
			break;
			}
			
			pinEntered.setText(text);

		} 
		
		
	}
	
	protected void eraseLastCarac() {
		/*
		 * Méthode privée appelée par le bouton effacer qui supprime le dernier caractère entré.
		 */
		String actualPin = pinEntered.getText();
		String updatedText ="";
		
		if (actualPin.length()!=0)
			updatedText = actualPin.substring(0, actualPin.length()-1);
		
		pinEntered.setText(updatedText);
		text = updatedText;
	}

}
