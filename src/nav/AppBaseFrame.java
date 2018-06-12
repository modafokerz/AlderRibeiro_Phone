/*
* Smartphone 602_F FIG HES-SO (Sierre)
* Auteur : Nelson Ribeiro Teixeira
* Date de cr�ation : 17 mai 2018
* Date de modification : /
*/
package nav;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import components.HomeBottomPanel;

@SuppressWarnings("serial")
public class AppBaseFrame extends BaseFrame {
	/**
	 * Squelette de nos applications qui h�rite de la frame de base qui a le fond d'�cran et la barre du haut.
	 * Cet el�ment a en plus la HomeBottomPanel qui g�re le bouton home et lock.
	 * @author Nelson
	 */
	private HomeBottomPanel bp = new HomeBottomPanel();
	
	public AppBaseFrame() {
		/*
		 * Constructeur de l'application qui ajoute les �l�ments dans leur position respectives.
		 */
		super();
		add(bp,BorderLayout.SOUTH);
		

		bp.addListener(this);
		bp.addLockListener(this);
	}
	
	public void addHomeAction(ActionListener a) {
		/**
		 * M�thode permettant d'ajouter une action au bouton Home en plus (Utilis�e notamment pour la cam�ra)
		 * ce qui permet de fermer l'acc�s � la webcam lorsqu'on on veut retourner dans le HomeScreen.
		 * @param Actionlistener � ajouter au bouton.
		 */
		bp.addHomeAction(a);
	}
	
	public void addLockAction(ActionListener a) {
		/**
		 * M�thode permettant d'ajouter une action au bouton Lock en plus (Utilis�e notamment pour la cam�ra)
		 * ce qui permet de fermer l'acc�s � la webcam lorsqu'on on veut retourner dans le LockScreen.
		 * @param Actionlistener � ajouter au bouton.
		 */
		bp.addLockAction(a);
	}
}
