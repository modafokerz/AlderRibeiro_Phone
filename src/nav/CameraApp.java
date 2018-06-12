/*
* Smartphone 602_F FIG HES-SO (Sierre)
* Auteur : Nelson Ribeiro Teixeira
* Date de cr�ation : 11 juin 2018
* Date de modification : /
*/
package nav;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

import components.MidPanel;
import components.topButton;
import components.topLabel;



@SuppressWarnings("serial")
public class CameraApp extends AppBaseFrame {
	/**
	 * Application cam�ra permettant de prendre des photos depuis la webcam de l'ordinateure et de les importer automatiquement dans
	 * la gallerie. La libraire gitHub de sarxos a �t� utilis�e : https://github.com/sarxos/webcam-capture/
	 * 
	 * Elle dispose d'un panneau du haut avec 2 boutons et un Label "Cam�ra"
	 * Le bouton de gauche permet d'�teindre et d'allumer l'acc�s � la cam�ra
	 * celui de droite de prendre en photo.
	 * 
	 * L'image est automatiquement stock�e dans la gallerie du smartphone.
	 * 
	 * @author Nelson
	 */
	
	private JPanel cameraAppPanel = new JPanel();
	
	private JPanel topCameraPanel = new JPanel();
	

	private topButton turnCameraButton = new topButton("img/icons/onoff-icon.png");
	private topLabel topCameraLabel = new topLabel("Cam�ra");
	private topButton captButton = new topButton("img/icons/capture-icon.png");
	
	private MidPanel midPanel = new MidPanel("La cam�ra est �teinte.");
	private MidPanel picPanel = new MidPanel("> Photo enregistr�e <");
	
	private Webcam wCam = Webcam.getDefault();
	private WebcamPanel wCamPanel = new WebcamPanel(wCam, new Dimension(600,550),false);
	
	public CameraApp() {
		/**
		 * Constructeur de la classe : Composants construits.
		 */
		wCam.setViewSize(WebcamResolution.VGA.getSize());
		
		
		
		remove(centerPanel);
		cameraAppPanel.setLayout(new FlowLayout());
		cameraAppPanel.setVisible(true);
		
		// Manage le panel du top avec les deux boutons et le label du centre
		topCameraPanel.setPreferredSize(new Dimension(600,100));
		topCameraPanel.setLayout(new FlowLayout());

		turnCameraButton.addActionListener(new OnOffCam());
		topCameraPanel.add(turnCameraButton);
		topCameraPanel.add(topCameraLabel);
		
		captButton.addActionListener(new Capture());
		topCameraPanel.add(captButton);
		
		cameraAppPanel.add(topCameraPanel);
		
		
		
		addHomeAction(new CloseCam());
		addLockAction(new CloseCam());
		cameraAppPanel.add(midPanel);
		add(cameraAppPanel,BorderLayout.CENTER);
	}
	
	public class OnOffCam implements ActionListener {
		/**
		 * Class ActionListener qui d�finit le comportement du bouton on off en haut � gauche de l'application.
		 * Allume la cam�ra au premier clic et l'�teint au second puis
		 * remplace le panel du centre (cam�ra) par un panel avec un message(cam�ra �teinte).
		 */
		private boolean isOff = true;
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
			if(isOff) {
				cameraAppPanel.remove(midPanel);
				cameraAppPanel.remove(picPanel);
				cameraAppPanel.add(wCamPanel);
				revalidate();
				repaint();
				Thread t = new Thread()	{
					public void run() {
						wCam.open();
						wCamPanel.start();
					}
				};
				t.setDaemon(true);
				t.start();
				
				isOff=false;
			} else {
				cameraAppPanel.remove(wCamPanel);
				cameraAppPanel.remove(picPanel);
				cameraAppPanel.add(midPanel);
				revalidate();
				repaint();
				isOff=true;
				wCam.close();
			}
		}
		
	}
	
	public class Capture implements ActionListener {
		/**
		 * class ActionListener qui d�finit le comportement du bouton de capture de la photo.
		 * celui-ci capture l'image affich�e et la met directement dans le dossier contenant les images de la gallerie.
		 * Puis enl�ve le panel avec la cam�ra et le remplace par un panel avec un message(photo enregistr�e.)
		 */

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(wCam.isOpen()) {
				File file = new File(String.format("img/gallery/Wphoto-%d.jpg", System.currentTimeMillis()));
				try {
					ImageIO.write(wCam.getImage(), "JPG", file);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				wCam.close();
				cameraAppPanel.remove(wCamPanel);
				cameraAppPanel.add(picPanel);
				revalidate();
				repaint();
			}
		}
		
		
		
	}
	
	public class CloseCam implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
				wCam.close();
			}
		}


		
		
	}
	
	
	




