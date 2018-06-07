package objects;

import java.io.Serializable;

/*
* Projet Smartphone
* Auteur : Jean-Marie Alder
* Date de création : 6.6.18
* Date de modification :
* Description : Objet Contact qui définit les attributs d'un contact. Cette classe est serialisable
*/

public class Contact implements Serializable{

	private String nom;
	private String prenom;
	private String tel;
	private String adresse;
	private String email;
	
	public Contact(String nom, String prenom, String tel, String adresse, String email) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.tel = tel;
		this.adresse = adresse;
		this.email = email;
	}
	public Contact() {
		this("", "", "", "", "");
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
	
	
}
