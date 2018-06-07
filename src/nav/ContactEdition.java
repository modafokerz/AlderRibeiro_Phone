package nav;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ContactEdition extends AppBaseFrame {

	private JPanel contactEditPanel = new JPanel();//Panel modification contact
	private JPanel topTextPanel = new JPanel(); //Panel pour titre
	private JLabel topText = new JLabel("Editeur de contact");
	private JPanel informations = new JPanel(); //Panel pour les informations de contact
	private JPanel buttons = new JPanel(); //Panel pour les boutons edit, supprimer et retour

	//les labels commencent par l et les textFields commencent par tf
	private JLabel lPrenom = new JLabel("Prenom :");
	private JLabel lNom = new JLabel("Nom :");
	private JLabel lTel = new JLabel("Tel :");
	private JLabel lAdresse = new JLabel("Adresse :");
	private JLabel lEmail = new JLabel("Email :");
	private JTextField tfPrenom = new JTextField();
	private JTextField tfNom = new JTextField();
	private JTextField tfTel = new JTextField();
	private JTextField tfAdresse = new JTextField();
	private JTextField tfEmail = new JTextField();
	
	
	public ContactEdition() {
		super();
		remove(centerPanel);
		
		topText.setPreferredSize(new Dimension(300, 100)); //Taille du composant (taille du JLabel)
		topText.setHorizontalAlignment(SwingConstants.CENTER);
		topText.setVerticalAlignment(SwingConstants.TOP);
		topText.setBackground(Color.WHITE);
		topText.setFont(new Font("Arial Black", Font.PLAIN, 25));
		
		topTextPanel.setPreferredSize(new Dimension(600,100));
		topTextPanel.setLayout(new FlowLayout());
		topTextPanel.add(topText);
		
		informations.setLayout(new GridLayout(5,2));
		informations.setPreferredSize(new Dimension(600,500));
		informations.setBackground(Color.LIGHT_GRAY);
		
		setMyFont(lPrenom);
		
		informations.add(lPrenom);
		informations.add(tfPrenom);
		informations.add(lNom);
		informations.add(tfNom);
		informations.add(lTel);
		informations.add(tfTel);
		informations.add(lAdresse);
		informations.add(tfAdresse);
		informations.add(lEmail);
		informations.add(tfEmail);
		
		buttons.setLayout(new FlowLayout());
		buttons.setPreferredSize(new Dimension(600, 50));
		
		contactEditPanel.setPreferredSize(new Dimension(600,650));
		contactEditPanel.setLayout(new FlowLayout());
		contactEditPanel.add(topTextPanel);
		contactEditPanel.add(informations);
		contactEditPanel.add(buttons);
		
		add(contactEditPanel);
		
		System.out.println("ContactEdition Construite!");
	}


	private void setMyFont(JLabel label) {
		// TODO Auto-generated method stub
		label.setFont(new Font("Arial Black", Font.PLAIN, 25));
	}
}
