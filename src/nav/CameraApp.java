/*
* Smartphone 602_F FIG HES-SO (Sierre)
* Auteur : Nelson Ribeiro Teixeira
* Date de création : 11 juin 2018
* Date de modification : /
*/
package nav;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

import components.MidPanel;
import components.topButton;
import components.topLabel;



public class CameraApp extends AppBaseFrame {
	/**
	 * Application caméra permettant de prendre des photos depuis la webcam de l'ordinateure et de les importer automatiquement dans
	 * la gallerie. La libraire gitHub de sarxos a été utilisée : https://github.com/sarxos/webcam-capture/
	 * @author Nelson
	 */
	
	private JPanel cameraAppPanel = new JPanel();
	
	private JPanel topCameraPanel = new JPanel();
	

	private topButton turnCameraButton = new topButton("img/icons/onoff-icon.png");
	private topLabel topCameraLabel = new topLabel("Caméra");
	private topButton captButton = new topButton("img/icons/capture-icon.png");
	
	private MidPanel midPanel = new MidPanel("La caméra est éteinte.");
	private MidPanel picPanel = new MidPanel("> Photo enregistrée <");
	
	private Webcam wCam = Webcam.getDefault();
	private WebcamPanel wCamPanel = new WebcamPanel(wCam, new Dimension(600,550),false);
	
	public CameraApp() {
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
	
	
	




