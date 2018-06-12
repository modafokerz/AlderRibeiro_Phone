package nav;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import components.KeyboardButton;


/*
 * Projet Smartphone
 * Auteur : Jean-Marie Alder
 * Date de création : 6.6.18
 * Date de modification :
 * Description : Application calculatrice standard, 4 opérations (+,-,*,/)
 */
/**
 * Classe CalculatorApp
 * Est appelée lorsque l'icone "calculatrice" du "homeScreen" est cliquée.
 * 
 * Son design est constitué de :
 * -un TopPanel avec :
 *  - Le titre "Calculatrice"
 *  - L'historique des calculs
 *  
 * -Un panel pour les "inputs" et les calculs + le bouton Cancel
 * 
 * -Un panel avec les 16 touches de la calculatrice
 * 
 * Ses attrbuts sont :
 * - un résultat
 * - un opérateur (+,-,*,/) sous forme de int
 * - deux variables a et b
 * - Un champ texte pour garder l'historique
 * 
 * Note : gère les exceptions comme la division par zéro, la virgule unique
 * et le nombre max. de chiffres autorisés. Gère aussi les gros nombres
 * sous la forme scientifique.
 * 
 * @author Jean-Marie Alder
 *
 */
@SuppressWarnings("serial")
public class CalculatorApp extends AppBaseFrame{

	private double result;
	private int operator;
	private double a;
	private double b;
	private String lastOperation;

	private JPanel calculatorPanel = new JPanel();

	private String text = "";
	private JLabel topText = new JLabel("Calculatrice");
	private JLabel lastInput = new JLabel("0");
	private JPanel topTextPanel = new JPanel();
	private JPanel inputPanel = new JPanel();
	private JLabel input = new JLabel();



	private JPanel keyboard = new JPanel();
	private KeyboardButton b1 = new KeyboardButton("1", this);
	private KeyboardButton b2 = new KeyboardButton("2", this);
	private KeyboardButton b3 = new KeyboardButton("3", this);
	private KeyboardButton b4 = new KeyboardButton("4", this);
	private KeyboardButton b5 = new KeyboardButton("5", this);
	private KeyboardButton b6 = new KeyboardButton("6", this);
	private KeyboardButton b7 = new KeyboardButton("7", this);
	private KeyboardButton b8 = new KeyboardButton("8", this);
	private KeyboardButton b9 = new KeyboardButton("9", this);
	private KeyboardButton b0 = new KeyboardButton("0", this);
	private KeyboardButton bplus = new KeyboardButton("+", this);
	private KeyboardButton bmoins = new KeyboardButton("-", this);
	private KeyboardButton bmult = new KeyboardButton("*", this);
	private KeyboardButton bdiv = new KeyboardButton("/", this);
	private KeyboardButton bequ = new KeyboardButton("=", this);
	private JButton bc = new JButton("C");
	private KeyboardButton bpoint = new KeyboardButton(".", this);

	/**
	 * Constructeur de la calculatrice
	 * 
	 * Design de la frame et initialisation des champs et boutons.
	 * 
	 * @see CalculatorApp#addActionListener(JButton)
	 */
	public CalculatorApp() {
		super();
		bc.setPreferredSize(new Dimension(100,100));
		bc.setFocusPainted(false);
		bc.setFont(new Font("Tahoma", Font.BOLD, 30));
		bc.setBackground(Color.darkGray);
		bc.setForeground(Color.white);
		bc.setBorderPainted(false); //pour supprimer les bords du clavier
		bc.setCursor(new Cursor(Cursor.HAND_CURSOR));


		addActionListener(b1);
		addActionListener(b2);
		addActionListener(b3);
		addActionListener(b4);
		addActionListener(b5);
		addActionListener(b6);
		addActionListener(b7);
		addActionListener(b8);
		addActionListener(b9);
		addActionListener(b0);
		addActionListener(bplus);
		addActionListener(bmoins);
		addActionListener(bmult);
		addActionListener(bdiv);
		addActionListener(bequ);
		addActionListener(bpoint);
		addActionListener(bc);


		this.remove(centerPanel);
		setBackground(Color.WHITE);
		// Gère le top Panel comprenant le texte
		topText.setPreferredSize(new Dimension(600, 50)); //Taille du composant (taille du JLabel)
		topText.setHorizontalAlignment(SwingConstants.CENTER);
		topText.setVerticalAlignment(SwingConstants.TOP);
		topText.setBackground(Color.WHITE);
		topText.setFont(new Font("Arial Black", Font.PLAIN, 25));

		lastInput.setPreferredSize(new Dimension(600, 48));
		lastInput.setHorizontalAlignment(SwingConstants.CENTER);
		lastInput.setFont(new Font("Arial Black", Font.PLAIN, 16));

		topTextPanel.setPreferredSize(new Dimension(600,100));
		topTextPanel.setLayout(new FlowLayout());
		topTextPanel.add(topText);
		topTextPanel.add(lastInput);
		topTextPanel.setBackground(Color.white);

		inputPanel.setLayout( new FlowLayout());
		inputPanel.add(topTextPanel);
		inputPanel.setBackground(Color.WHITE);
		inputPanel.add(input);
		inputPanel.add(bc);

		inputPanel.setPreferredSize(new Dimension(600,200));

		input.setHorizontalAlignment(SwingConstants.CENTER);
		input.setFont(new Font("Helvetica", Font.PLAIN, 35));
		input.setForeground(Color.darkGray);
		input.setPreferredSize(new Dimension(450,100));
		input.setText(text);

		keyboard.setBackground(Color.darkGray);
		keyboard.setLayout(new GridLayout(4,4));
		keyboard.setPreferredSize(new Dimension(600,450));
		keyboard.setBorder(new LineBorder(Color.WHITE, 3));

		keyboard.add(bdiv);
		keyboard.add(b7);
		keyboard.add(b8);
		keyboard.add(b9);
		keyboard.add(bmult);
		keyboard.add(b4);
		keyboard.add(b5);
		keyboard.add(b6);
		keyboard.add(bplus);
		keyboard.add(b1);
		keyboard.add(b2);
		keyboard.add(b3);
		keyboard.add(bmoins);
		keyboard.add(bpoint);
		keyboard.add(b0);
		keyboard.add(bequ);


		calculatorPanel.add(inputPanel,BorderLayout.NORTH);
		calculatorPanel.add(keyboard, BorderLayout.CENTER);

		add(calculatorPanel,BorderLayout.CENTER);

	}
	
	/**
	 * Défini le texte (input de chiffres) à afficher
	 * sur la calculatrice
	 * @param text	le texte à afficher
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * Retourne la chaine de texte des inputs
	 * @return	le texte à afficher sur la calculatrice
	 */
	public String getText() {
		return text;
	}

	/**
	 * Ajoute l'actionListener à tous les boutons.
	 * 
	 * Lorsqu'un bouton est cliqué. son nom (identifiant) est récupéré
	 * et en fonction, fait l'action correspondante.
	 * 
	 * 
	 * @param button	Le bouton implémentant l'actionListener
	 * 
	 * @throws NumberFormatException	Si le format du nombre n'est pas correct.
	 */
	private void addActionListener(JButton button) {
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Object kb = e.getSource();
				if(operator == 5 && kb != bplus && kb != bmoins
						&& kb != bmult && kb != bdiv) {
					//si "=" est utilisé, réinitialise le calcul
					setText("");
					operator = 0;
				}
				if(getText().length() < 15) {
					//l'input ne peut pas être plus grand que 15 chiffres.
					if(e.getSource()==b1)
						setText(getText().concat("1"));

					if(e.getSource()==b2)
						setText(getText().concat("2"));

					if(e.getSource()==b3)
						setText(getText().concat("3"));

					if(e.getSource()==b4)
						setText(getText().concat("4"));

					if(e.getSource()==b5)
						setText(getText().concat("5"));

					if(e.getSource()==b6)
						setText(getText().concat("6"));

					if(e.getSource()==b7)
						setText(getText().concat("7"));

					if(e.getSource()==b8)
						setText(getText().concat("8"));

					if(e.getSource()==b9)
						setText(getText().concat("9"));

					if(e.getSource()==b0)
						setText(getText().concat("0"));

					if(e.getSource()==bpoint && !(getText().contains("."))
							&& getText().length() < 14)
						//Un point peut être ajouté uniquement si c'est le seul
						//on ne peut pas mettre un point pour le dernier chiffre
						setText(getText().concat("."));
				}


				if(e.getSource()==bplus)
				{
					try {
						a=Double.parseDouble(getText());
						operator=1;
						setText("");
						lastOperation = Double.toString(a) + " +"; 
					}catch(NumberFormatException f) {

					}

				} 

				if(e.getSource()==bmoins)
				{
					try {
						a=Double.parseDouble(getText());
						operator=2;
						setText("");
						lastOperation = Double.toString(a) + " -";
					}catch(NumberFormatException f) {

					}

				}

				if(e.getSource()==bmult)
				{
					try {
						a=Double.parseDouble(getText());
						operator=3;
						setText("");
						lastOperation = Double.toString(a) + " *";
					}catch(NumberFormatException f) {

					}

				}

				if(e.getSource()==bdiv)
				{
					try {
						a=Double.parseDouble(getText());
						operator=4;
						setText("");
						lastOperation = Double.toString(a) + " /";
					}catch(NumberFormatException f) {

					}

				}

				if(e.getSource()==bequ)
				{
					try {
						//pour contourner l'erreur lorsque "=" est utilisé deux fois de suite
						b=Double.parseDouble(getText());
					}
					catch(NumberFormatException f) {
						lastOperation = "0";
						operator = 5;
					}

					switch(operator)
					{
					case 1: result=a+b;
					break;

					case 2: result=a-b;
					break;

					case 3: result=a*b;
					break;

					case 4: 
						if(b != 0) {
							result=a/b;
						}else {
							lastOperation += " " + Double.toString(b);
							setText("NaN");
							operator = 5;
						}
						break;

					default: result=0;
					break;
					}
					a=result;
					if(operator != 5) {
						setText(""+result);
						lastOperation += " " + Double.toString(b);
					}

					operator =5; //permet de savoir quand "=" est utilisé
				}

				if(e.getSource()==bc) {
					setText("");

				}

				if(getText().endsWith(".0") && !(getText().startsWith(".0"))) {
					//Supprime ".0" à la fin d'un nombre sans chiffres après la virgule
					setText(getText().substring(0, getText().length()-2));
				}
				input.setText(text);
				lastInput.setText(lastOperation);
			}
		});
	}
}
