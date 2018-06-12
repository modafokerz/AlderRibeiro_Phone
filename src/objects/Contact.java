package objects;

import java.io.Serializable;


/*
 * Projet Smartphone
 * Auteur : Jean-Marie Alder
 * Date de cr�ation : 6.6.18
 * Date de modification :
 * Description : Objet Contact qui d�finit les attributs d'un contact. Cette classe est serialisable
 */
/**
 * Classe Contact
 * Permet de repr�senter les informations d'un contact
 * Un contact est caract�ris� par :
 * -un 
 * -un nom
 * -un pr�nom
 * -un num�ro de t�l�phone de 10 chiffres
 * -une adresse
 * -une adresse email
 * 
 * Toutes ces variables sont accessibles et modifiables
 * avec les getters et setters correspondants. 
 * 
 * @author Jean-Marie Alder
 *
 */
@SuppressWarnings("serial")
public class Contact implements Serializable{

	private String photoPath;
	private String nom;
	private String prenom;
	private String tel;
	private String adresse;
	private String email;
	
	

	/**
	 * Constructeur de Contact
	 * 
	 * Cette classe est utilis�e lors de la modification
	 * d'un contact dans la classe ContactEdition.
	 * 
	 * @see ContactEdition
	 * 
	 * 
	 * @param photoPath	le chemin d'acc�s � la photo
	 * @param nom		le nom du contact
	 * @param prenom	le prenom du contact
	 * @param tel		le telephone du contact de 10 chiffres
	 * @param adresse	l'adresse du contact
	 * @param email		l'email du contact
	 */
	public Contact(String photoPath, String nom, String prenom, String tel, String adresse, String email) {
		super();
		this.photoPath = photoPath;
		this.nom = nom;
		this.prenom = prenom;
		this.tel = tel;
		this.adresse = adresse;
		this.email = email;
	}
	
	/**
	 * Constructeur de contact avec photo par d�faut
	 * 
	 * Appelle le constructeur principal de contact
	 * 
	 * Le chemin de la photo par d�faut est "img/contactIcons/UserIcon.png"
	 * 
	 * @see Contact#Contact(String, String, String, String, String, String)
	 * 
	 * @param nom 		le nom du contact
	 * @param prenom	le prenom du contact
	 * @param tel		le t�l�phone du contact de 10 chiffres
	 * @param adresse	l'adresse du contact
	 * @param email		l'adresse email du contact
	 */
	public Contact(String nom, String prenom, String tel, String adresse, String email) {
		this("img/contactIcons/UserIcon.png", nom, prenom, tel, adresse, email);
	}
	
	/**
	 * Constructeur par d�faut sans param�tre.
	 * 
	 * Est appel�e lors de la cr�ation d'un nouveau contact
	 * 
	 * @see ContactEdition
	 */
	public Contact() {
		this("", "", "0000000000", "", "");
	}
	
	/**
	 * Validation du num�ro de t�l�phone.
	 * 
	 * Un num de tel doit etre constitu� de 10 chiffres et pas de lettres.
	 * 
	 * Test�e dans JUnit
	 * 
	 * @return	True si le num�ro est correct
	 * 			False si le num�ro n'est pas sous la bonne forme
	 */
	public boolean validatePhone() {
		int index = 0;
		
		if(tel == null) {
			return false;
		}
		
		for(index = 0; index < tel.length(); index++) {
			if(!(Character.isDigit(tel.charAt(index))) || tel.charAt(index) == '.') {
				return false;
			}
		}
		if(index != 10) {
			return false;
		}
		return true;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(String imagePath) {
		this.photoPath = imagePath;
	}
}
