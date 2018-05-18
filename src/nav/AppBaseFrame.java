/*
* Exercice FIG HES-SO (Sierre)
* Auteur : Nelson Ribeiro Teixeira
* Date de création : 17 mai 2018
* Date de modification : /
*/
package nav;

import java.awt.BorderLayout;

import components.HomeBottomPanel;

@SuppressWarnings("serial")
public class AppBaseFrame extends BaseFrame {
	private HomeBottomPanel bp = new HomeBottomPanel();
	
	public AppBaseFrame() {
		add(bp,BorderLayout.SOUTH);
		
		
		bp.addListener(this);
	}
}
