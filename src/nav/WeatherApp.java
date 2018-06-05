/*
 * Exercice FIG HES-SO (Sierre)
 * Auteur : Nelson Ribeiro Teixeira
 * Date de cr�ation : 24 mai 2018
 * Date de modification : /
 */
package nav;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import components.WeatherJPanel;
import objects.WeatherCity;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;





@SuppressWarnings("serial")
public class WeatherApp extends AppBaseFrame {
	
	
	private String ville = "Sierre";
	private String villeStatus = "";
	private String villeTemp = "";
	
	private WeatherJPanel weatherAppPanel = new WeatherJPanel();
	private JLabel loadingLabel = new JLabel("Loading...");
	private static boolean APILoading = true;
	private int loadingCount = 1;
	
	private String apiKey = "0761f6abebe05ed6cd5e1985cdd37791";
	private double[] sierreCoordinates = new double[2];
	private double[] sionCoordinates = new double[2];
	private double[] aigleCoordinates = new double[2];
	
	private double[] cityCoordinates = new double[2];
	
	private String forecastUrl;
	private String jsonData;
	private JSONObject forecastInfo;
	
	private JPanel topAppPanel = new JPanel();
	private JButton cityChoiceButton = new JButton("Choisir une autre ville");
	private JLabel cityLabel;
	private JLabel cityStatus;
	private JLabel cityTemp;
	private JPanel coordinatesPanel = new JPanel();
	
	private JPanel bottomAppPanel = new JPanel();
	
	
	public WeatherApp() {
		super();
		
		// latitude + longitude
		sierreCoordinates[0] = 46.2923;
		sierreCoordinates[1] = 7.5323;
		
		sionCoordinates[0] = 46.2312;
		sionCoordinates[1] = 7.3589;
		
		aigleCoordinates[0] = 46.3179;
		aigleCoordinates[1] = 6.9689;
		
		
		
		
		
		
		
		
		
		// Serialization du choix de la ville � faire
		cityCoordinates[0]=sierreCoordinates[0];
		cityCoordinates[1]=sierreCoordinates[1];
		
		remove(centerPanel);
		add(weatherAppPanel, BorderLayout.CENTER);
		weatherAppPanel.add(loadingLabel, BorderLayout.CENTER);
		weatherAppPanel.setSize(600, 650);
		weatherAppPanel.setLayout(new FlowLayout());
		loadingLabel.setVerticalAlignment(SwingConstants.CENTER);
		loadingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loadingLabel.setFont(new Font("Impact", Font.BOLD, 50));
		loadingLabel.setPreferredSize(new Dimension(600,650));
		loadingLabel.setForeground(Color.WHITE);
		
		forecastUrl = "https://api.darksky.net/forecast/"+ apiKey + "/" + cityCoordinates[0]+ "," + cityCoordinates[1];
		httpRequest();
	}
	
	private void httpRequest() {
		
		
		System.out.println("blabla 1");
		
		// T�che effectu�e sur un thread diff�rent de mani�re � n'avoir aucune latence d'application
		// Youhou,,,, ca marche enfin !! putin je suis trop content mdr
		new SwingWorker <String, Void>() {

				@Override
				protected String doInBackground() throws Exception {
					
					// Libraire OkHTTP permettant d'effectuer des requetes HTTP
					OkHttpClient client = new OkHttpClient();
					Request request = new Request.Builder().url(forecastUrl).build();
					
					Call call = client.newCall(request);
					try {
						Response response = call.execute();
						
						// R�ponse du serveur sous forme de string au format JSON
						jsonData = response.body().string();
						
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					return null;
				}
				
				@Override
				protected void done () {
					System.out.println("Done");
					APILoading = false;
					weatherAppPanel.remove(loadingLabel);
					
					// Object JSON cr�� � partir de la r�ponse du serveur
					forecastInfo = (JSONObject) JSONValue.parse(jsonData);
					constructPage();
				}
		}.execute();
		
//		while(APILoading) {
//			
//			switch(loadingCount) {
//			case 1 : loadingLabel.setText("Loading.");
//					break;
//			case 2 : loadingLabel.setText("Loading..");
//			break;
//			case 3 :  loadingLabel.setText("Loading...");
//			break;
//			}
//			
//			
//			if(loadingCount==3) {
//				loadingCount=1;
//			} else {
//				loadingCount++;
//			}
//			
//			repaint();
//		}
		
		
		
		System.out.println("blabla 2");
	}
	
	
	
	private void constructPage() {
		JSONObject currentWeather = (JSONObject) forecastInfo.get("currently");
		villeStatus = (String) currentWeather.get("summary");
		double farenheitTemp = (double) currentWeather.get("temperature");
		double celciusTemp = (farenheitTemp -32) * 5/9;
		
		villeTemp = Double.toString(Math.round(celciusTemp));
		
		cityLabel = new JLabel(ville);
		cityStatus = new JLabel(villeStatus);
		cityTemp = new JLabel(villeTemp + " �C");
		
		
		
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
		
		cityLabel.setPreferredSize(new Dimension(600,90));
		cityLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cityLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		cityLabel.setFont(new Font("Impact", Font.BOLD, 80));
		cityLabel.setForeground(Color.white);
		topAppPanel.add(cityLabel);
		
		coordinatesPanel.setOpaque(false);
		coordinatesPanel.setPreferredSize(new Dimension(600,20));
		
		String str1 = "[Latitude : " + cityCoordinates[0] + ", ";
		String str2 = "Longitude : " + cityCoordinates[1] + "]";
		JLabel latLabel = new JLabel(str1);
		latLabel.setVerticalAlignment(SwingConstants.TOP);
		
		JLabel longLabel = new JLabel(str2);
		longLabel.setVerticalAlignment(SwingConstants.TOP);
		
		coordinatesPanel.add(latLabel);
		coordinatesPanel.add(longLabel);
		topAppPanel.add(coordinatesPanel);
		
		cityStatus.setPreferredSize(new Dimension(600,35));
		cityStatus.setHorizontalAlignment(SwingConstants.CENTER);
		cityStatus.setForeground(Color.white);
		cityStatus.setVerticalAlignment(SwingConstants.BOTTOM);
		cityStatus.setFont(new Font("Impact", Font.PLAIN, 30));
		topAppPanel.add(cityStatus);
		
		cityTemp.setPreferredSize(new Dimension(600,200));
		
		topAppPanel.add(cityTemp);
		
		weatherAppPanel.add(topAppPanel, BorderLayout.CENTER);
		
		// Bottom App Panel construction !
		bottomAppPanel.setPreferredSize(new Dimension(600, 250));
		
		weatherAppPanel.add(bottomAppPanel,BorderLayout.SOUTH);
		
		revalidate();
		repaint();
	}
	
	

	
	
	
	
	
}
