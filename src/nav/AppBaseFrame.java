/*
* Exercice FIG HES-SO (Sierre)
* Auteur : Nelson Ribeiro Teixeira
* Date de cr�ation : 17 mai 2018
* Date de modification : /
*/
package nav;

import java.awt.BorderLayout;

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
}
