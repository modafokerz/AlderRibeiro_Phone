package main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import nav.CityChoiceScreen;
import nav.GalleryScreen;
import nav.LockScreen;


/*
* Exercice FIG HES-SO (Sierre)
* Auteur : Nelson Ribeiro Teixeira
* Date de création : 16 avr. 2018
* Date de modification : /
*/

public class test {

	public static void main(String[] args) {
		LockScreen ls = new LockScreen();

		Timer t = new javax.swing.Timer(1000, new ActionListener(){

	    public void actionPerformed(ActionEvent e){
		    	
		    	ls.updateSeconds();
	    	
		    }
		});
		t.start();
		
		
		
	}

}
