/*
* Exercice FIG HES-SO (Sierre)
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
	private HomeBottomPanel bp = new HomeBottomPanel();
	
	public AppBaseFrame() {
		super();
		add(bp,BorderLayout.SOUTH);
		

		bp.addListener(this);
		bp.addLockListener(this);
	}
	
	public void addHomeAction(ActionListener a) {
		bp.addHomeAction(a);
	}
	
	public void addLockAction(ActionListener a) {
		bp.addLockAction(a);
	}
}
