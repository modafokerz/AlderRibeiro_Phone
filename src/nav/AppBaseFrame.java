/*
* Smartphone 602_F FIG HES-SO (Sierre)
* Auteur : Nelson Ribeiro Teixeira
* Date de création : 17 mai 2018
* Date de modification : /
*/
package nav;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import components.HomeBottomPanel;

@SuppressWarnings("serial")
public class AppBaseFrame extends BaseFrame {
	/**
	 * Squelette de nos applications qui hérite de la frame de base qui a le fond d'écran et la barre du haut.
	 * Cet elément a en plus la HomeBottomPanel qui gère le bouton home et lock.
	 * @author Nelson
	 */
	private HomeBottomPanel bp = new HomeBottomPanel();
	
	public AppBaseFrame() {
		/*
		 * Constructeur de l'application qui ajoute les éléments dans leur position respectives.
		 */
		super();
		add(bp,BorderLayout.SOUTH);
		

		bp.addListener(this);
		bp.addLockListener(this);
	}
	
	public void addHomeAction(ActionListener a) {
		/**
		 * Méthode permettant d'ajouter une action au bouton Home en plus (Utilisée notamment pour la caméra)
		 * ce qui permet de fermer l'accès à la webcam lorsqu'on on veut retourner dans le HomeScreen.
		 * @param Actionlistener à ajouter au bouton.
		 */
		bp.addHomeAction(a);
	}
	
	public void addLockAction(ActionListener a) {
		/**
		 * Méthode permettant d'ajouter une action au bouton Lock en plus (Utilisée notamment pour la caméra)
		 * ce qui permet de fermer l'accès à la webcam lorsqu'on on veut retourner dans le LockScreen.
		 * @param Actionlistener à ajouter au bouton.
		 */
		bp.addLockAction(a);
	}
}
