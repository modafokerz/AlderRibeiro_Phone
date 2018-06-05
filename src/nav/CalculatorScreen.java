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

public class CalculatorScreen extends BaseFrame {

	private String text = "";
	private JLabel topText = new JLabel("Calcul :");
	private JPanel topTextPanel = new JPanel();
	private JPanel pinPanel = new JPanel();
	private JLabel pinEntered = new JLabel();

	private JPanel keyboard = new JPanel();
	private JButton returnButton = new JButton("RETOUR");
	
	public CalculatorScreen() {
		
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
	}
}
