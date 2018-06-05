package nav;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import components.*;

public class CalculatorApp extends PinScreen{

	private String text = "";
	private JLabel topText = new JLabel("Calculatrice");
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
	

}
