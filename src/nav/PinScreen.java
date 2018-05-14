package nav;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import components.KeyboardButton;

/*
 * Exercice FIG HES-SO (Sierre)
 * Auteur : Nelson Ribeiro Teixeira
 * Date de création : 30 avr. 2018
 * Date de modification : /
 */

@SuppressWarnings("serial")
public class PinScreen extends BaseFrame {
	private String text = "";
	private JLabel topText = new JLabel("Insérez le code PIN");
	private JPanel topTextPanel = new JPanel();
	private JPanel pinPanel = new JPanel();
	private JLabel pinEntered = new JLabel();

	private JPanel keyboard = new JPanel();
	private JButton returnButton = new JButton("Return");

	public PinScreen() {
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

		keyboard.setBackground(Color.darkGray);
		keyboard.setLayout(new GridLayout(4,3));
		keyboard.setPreferredSize(new Dimension(600,500));
		
		KeyboardButton erase = new KeyboardButton("", this);
		KeyboardButton validate = new KeyboardButton("", this);
		
		
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
		
		pinPanel.add(keyboard,BorderLayout.CENTER);
		add(returnButton, BorderLayout.SOUTH);
		add(pinPanel,BorderLayout.CENTER);

	}

	public void updateText(String str) {

		if (pinEntered.getText().length() < 6) {
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
			System.out.println(pinEntered.getText());
		} else {
			if(pinEntered.getText().equals("0000")) {
				this.topText.setText("PIN correct");
				this.pinEntered.setText("");
				text="";
			} else {
				this.topText.setText("PIN Incorrect !");
				this.pinEntered.setText("");
				text="";
			}
		}
		
	}

}
