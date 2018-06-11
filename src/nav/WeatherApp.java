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
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import components.BackgroundPanel;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;





@SuppressWarnings("serial")
public class WeatherApp extends AppBaseFrame {


	private String ville = "";
	private String villeStatus = "";
	private String villeTemp = "";

	private BackgroundPanel weatherAppPanel = new BackgroundPanel("img/weatherApp/weatherAppBackground.png");
	private JLabel loadingLabel = new JLabel("Loading...");
	private static boolean APILoading = true;
	private int loadingCount = 1;

	private String apiKey = "0761f6abebe05ed6cd5e1985cdd37791";

	private double[] cityCoordinates = new double[2];

	private String forecastUrl;
	private String jsonData;
	private JSONObject forecastInfo;

	private JPanel topAppPanel = new JPanel();
	private JButton cityChoiceButton = new JButton("Choisir une autre ville");
	private JLabel cityLabel;
	private String contenuFichier = "";
	private JLabel cityStatus;
	private JPanel coordinatesPanel = new JPanel();

	private JPanel bottomAppPanel = new JPanel();


	public WeatherApp() {
		super();

		// latitude + longitude










		// Choix de la ville à prendre la météo
		getCityCoordinates();

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



		// Tâche effectuée sur un thread différent de manière à n'avoir aucune latence d'application
		new SwingWorker <String, Void>() {

			@Override
			protected String doInBackground() throws Exception {

				// Libraire OkHTTP permettant d'effectuer des requetes HTTP
				OkHttpClient client = new OkHttpClient();
				Request request = new Request.Builder().url(forecastUrl).build();

				Call call = client.newCall(request);
				try {
					Response response = call.execute();

					// Réponse du serveur sous forme de string au format JSON
					jsonData = response.body().string();

				} catch (IOException e) {
					e.printStackTrace();
				}

				return null;
			}

			@Override
			protected void done () {
				System.out.println("HTTP Request Done");
				APILoading = false;
				weatherAppPanel.remove(loadingLabel);

				// Object JSON créé à partir de la réponse du serveur
				forecastInfo = (JSONObject) JSONValue.parse(jsonData);
				constructPage();
			}
		}.execute();

	}

	private void getCityCoordinates() {
		File fichierVille = new File("saves/choixVille.txt");

		try {
			FileReader fr = new FileReader(fichierVille);
			BufferedReader bfr = new BufferedReader(fr);

			try {
				contenuFichier = bfr.readLine();
				bfr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		contenuFichier = contenuFichier.toLowerCase();
		if(contenuFichier.equals("aigle")) {
			cityCoordinates[0] = 46.3179;
			cityCoordinates[1] = 6.9689;
		} else if (contenuFichier.equals("sion")) {
			cityCoordinates[0] = 46.2312;
			cityCoordinates[1] = 7.3589;
		} else {
			cityCoordinates[0] = 46.2923;
			cityCoordinates[1] = 7.5323;
		}
	}

	private void constructPage() {
		JSONObject currentWeather = (JSONObject) forecastInfo.get("currently");
		villeStatus = (String) currentWeather.get("summary");
		String icon = (String) currentWeather.get("icon");
		Object farenheitTemp = currentWeather.get("temperature");

		double celciusTemp = ((double)farenheitTemp -32) * 5/9;
		double rainProb = (double) currentWeather.get("precipProbability");
		rainProb = rainProb*100;
		String cityRainProb =  String.valueOf(rainProb);

		double windSpeed = (double) currentWeather.get("windSpeed");
		windSpeed = Math.round(windSpeed * 1.60934*100); // Conversion en Km/h environ
		windSpeed = windSpeed/100; 
		String cityWindSpeed = String.valueOf(windSpeed);
		villeTemp = Double.toString(Math.round(celciusTemp));
		ville = contenuFichier.toUpperCase();
		cityLabel = new JLabel(ville);
		cityStatus = new JLabel(villeStatus);




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
		cityChoiceButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				new CityChoiceScreen();
				WeatherApp.this.dispose();

			}

		});

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

		cityStatus.setPreferredSize(new Dimension(600,70));
		cityStatus.setHorizontalAlignment(SwingConstants.CENTER);
		cityStatus.setForeground(Color.white);
		cityStatus.setVerticalAlignment(SwingConstants.CENTER);
		cityStatus.setFont(new Font("Impact", Font.PLAIN, 30));
		topAppPanel.add(cityStatus);

		JPanel cityTempPanel = new JPanel();
		cityTempPanel.setPreferredSize(new Dimension(600,150));
		cityTempPanel.setLayout(new FlowLayout());

		String iconPath = "img/weatherApp/weather-icons/"+icon+".png";
		Image  img = new ImageIcon(iconPath).getImage();

		BackgroundPanel CTPleft = new BackgroundPanel(img);
		CTPleft.setPreferredSize(new Dimension(100,100));
		CTPleft.setOpaque(false);

		JPanel CTPRight = new JPanel();
		CTPRight.setPreferredSize(new Dimension(450,100));
		CTPRight.setLayout(new GridLayout(2,3));
		CTPRight.setOpaque(false);

		TitleLabel tAct = new TitleLabel("Temp. actuelle");
		TitleLabel pAct = new TitleLabel("Prob. pluie");
		TitleLabel vAct = new TitleLabel("Vitesse vent");
		tAct.setHorizontalAlignment(SwingConstants.CENTER);


		DataLabel labelCityTemp = new DataLabel(villeTemp + "°C");
		DataLabel labelCityRainProb = new DataLabel(cityRainProb + "%");
		DataLabel labelCityWindSpeed = new DataLabel(cityWindSpeed + "km/h");

		CTPRight.add(tAct);
		CTPRight.add(pAct);
		CTPRight.add(vAct);
		CTPRight.add(labelCityTemp);
		CTPRight.add(labelCityRainProb);
		CTPRight.add(labelCityWindSpeed);

		cityTempPanel.setOpaque(false);
		cityTempPanel.add(CTPleft);
		cityTempPanel.add(CTPRight);
		topAppPanel.add(cityTempPanel);
		topAppPanel.setOpaque(false);


		weatherAppPanel.add(topAppPanel, BorderLayout.CENTER);

		// Bottom App Panel construction !
		bottomAppPanel.setPreferredSize(new Dimension(600, 300));
		bottomAppPanel.setLayout(new FlowLayout());
		bottomAppPanel.setOpaque(false);

		JLabel csLabel = new JLabel("Les prochains jours");
		csLabel.setPreferredSize(new Dimension(600,50));
		csLabel.setOpaque(false);
		csLabel.setFont(new Font("Impact", Font.PLAIN, 30));
		csLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bottomAppPanel.add(csLabel);

		JSONObject nextDaysInfo = (JSONObject) forecastInfo.get("daily");
		JSONArray nextDaysData = (JSONArray) nextDaysInfo.get("data");
		JSONObject day_1 = (JSONObject) nextDaysData.get(0);
		JPanel nextDaysPanel = new JPanel();
		nextDaysPanel.setLayout(new GridLayout(3,3));
		nextDaysPanel.setPreferredSize(new Dimension(600,250));
		nextDaysPanel.setOpaque(false);

		JLabel tomorrowLabel = new JLabel("Demain");
		int count = 1;
		nextDaysPanel.add(tomorrowLabel);
		for(int i = 0; i < 3; i++) {
			JSONObject dayInfo = (JSONObject) nextDaysData.get(i);
			String iconsPath = "img/weatherApp/weather-icons/";
			String icon1_path = iconsPath+dayInfo.get("icon")+".png";
			System.out.println(icon1_path);
			BackgroundPanel day_icon = new BackgroundPanel(icon1_path, 100,100);
			nextDaysPanel.add(day_icon);
			
			NextDayTemp day_temp = new NextDayTemp();
			double day_maxTemp = (double) dayInfo.get("temperatureHigh");
			double day_minTemp = (double) dayInfo.get("temperatureLow");
			
			day_temp.setTemps(day_minTemp, day_maxTemp);
			nextDaysPanel.add(day_temp);
			
			if(count<=2)
			nextDaysPanel.add(new JLabel("test"));
			
			count++;
		}

		

		

		bottomAppPanel.add(nextDaysPanel);
		weatherAppPanel.add(bottomAppPanel,BorderLayout.SOUTH);

		revalidate();
		repaint();
	}



	class TitleLabel extends JLabel {
		public TitleLabel(String str) {
			super(str);
			setHorizontalAlignment(SwingConstants.CENTER);
			setOpaque(false);
			setForeground(Color.white);
			setFont(new Font("Arial",Font.BOLD,20));
		}
	}

	class DataLabel extends JLabel {
		public DataLabel(String str) {
			super(str);
			setHorizontalAlignment(SwingConstants.CENTER);
			setOpaque(false);
			setForeground(Color.white);
			setFont(new Font("Arial",Font.PLAIN,15));
		}
	}

	class NextDayTemp extends JPanel {
		private double tempMin;
		private double tempMax;

		public NextDayTemp() {
			super();
			setLayout(new GridLayout(2,0));
		}

		protected void setTemps(double tempMin, double tempMax) {
			this.tempMin = tempMin;
			this.tempMax = tempMax;
			constructObject();
		}

		private void constructObject() {
			JLabel tempMn = new JLabel(String.valueOf(tempMin));
			JLabel tempMx = new JLabel(String.valueOf(tempMax));

			add(tempMn);
			add(tempMx);
		}
	}

	
}





