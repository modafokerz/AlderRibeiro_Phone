package components;

import java.awt.Cursor;

import javax.swing.JButton;

public class CalculatorButton extends JButton {

	public CalculatorButton(String name, boolean isOpaque) {
		
		super(name);
		
		this.setContentAreaFilled(true);
		this.setFocusPainted(false);
		this.setOpaque(isOpaque);
		this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));	
			
	}	
	
}
