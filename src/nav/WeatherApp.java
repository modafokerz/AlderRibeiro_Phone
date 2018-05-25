/*
 * Exercice FIG HES-SO (Sierre)
 * Auteur : Nelson Ribeiro Teixeira
 * Date de création : 24 mai 2018
 * Date de modification : /
 */
package nav;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.LineBorder;

import components.WeatherJPanel;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;





@SuppressWarnings("serial")
public class WeatherApp extends AppBaseFrame {
	
	
	private String ville = "Sierre";
	private String villeStatus = "Ensoleillé";
	private String villeTemp = "25";
	private WeatherJPanel weatherAppPanel = new WeatherJPanel();
	
	private String apiKey = "0761f6abebe05ed6cd5e1985cdd37791";
	private double[] cityCoordinates = new double[2];
	
	private String forecastUrl = "https://api.darksky.net/forecast/"+ apiKey + "/" + cityCoordinates[0]+ "," + cityCoordinates[1];
	
	private JPanel topAppPanel = new JPanel();
	private JButton cityChoiceButton = new JButton("Choisir une autre ville");
	private JLabel cityLabel = new JLabel(ville);
	private JLabel cityStatus = new JLabel(villeStatus);
	private JLabel cityTemp = new JLabel(villeTemp + " °C");
	
	
	private JPanel bottomAppPanel = new JPanel();
	
	
	public WeatherApp() {
		super();
		httpRequest();
		
		remove(centerPanel);
		add(weatherAppPanel, BorderLayout.CENTER);
		weatherAppPanel.setSize(600, 650);
		
		weatherAppPanel.setLayout(new FlowLayout());
		weatherAppPanel.add(topAppPanel);
		// Top App Weather Panel construction !
		topAppPanel.setPreferredSize(new Dimension(600, 400));
		topAppPanel.setOpaque(false);
		topAppPanel.setLayout(new FlowLayout());
		
		// Top App Panel - Components construction
		cityChoiceButton.setPreferredSize(new Dimension(575,30));
		cityChoiceButton.setForeground(Color.white);
		cityChoiceButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		cityChoiceButton.setHorizontalAlignment(SwingConstants.CENTER);
		cityChoiceButton.setContentAreaFilled(false);
		cityChoiceButton.setOpaque(false);
		cityChoiceButton.setBorder(new LineBorder(Color.WHITE, 3));
		
		topAppPanel.add(cityChoiceButton);
		
		cityLabel.setPreferredSize(new Dimension(600,100));
		cityLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cityLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		cityLabel.setFont(new Font("Impact", Font.PLAIN, 80));
		cityLabel.setForeground(Color.white);
		topAppPanel.add(cityLabel);
		
		cityStatus.setPreferredSize(new Dimension(600,20));
		cityStatus.setHorizontalAlignment(SwingConstants.CENTER);
		cityStatus.setForeground(Color.white);
		topAppPanel.add(cityStatus);
		
		cityTemp.setPreferredSize(new Dimension(600,200));
		topAppPanel.add(cityTemp);
		
		weatherAppPanel.add(topAppPanel, BorderLayout.CENTER);
		
		// Bottom App Panel construction !
		bottomAppPanel.setPreferredSize(new Dimension(600, 250));
		
		weatherAppPanel.add(bottomAppPanel,BorderLayout.SOUTH);
	}
	
	private void httpRequest() {
		cityCoordinates[0] = 37.8267;
		cityCoordinates[1] = -122.4233;
		
		System.out.println("blabla 1");
		// Tâche effectuée sur un thread différent de manière à n'avoir aucune latence
		// Youhou,,,, ca marche enfin !! putin je suis trop content mdr
		new SwingWorker <String, Void>() {

			@Override
			protected String doInBackground() throws Exception {
				OkHttpClient client = new OkHttpClient();
				Request request = new Request.Builder().url(forecastUrl).build();
				
				Call call = client.newCall(request);
				try {
					Response response = call.execute();
					return response.body().string();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				return null;
			}
			
			@Override
			protected void done () {
				try {
					System.out.println(get());
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				} 
			}
			
		}.execute();
		System.out.println("blabla 2");
	}
	
	

	
	
	
	
	
}
