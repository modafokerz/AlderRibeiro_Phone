/*
 * Smartphone 602_F FIG HES-SO (Sierre)
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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
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
	 /**
	  * Application météo du smartphone utilisant plusieurs libraires :
	  * La première c'est OkHTTP qui permet d'effectuer des requêtes HTTP de manière très simple.
	  * La deuxième ce'st JSONObject qui permet de convertir une string en objet JSON.
	  * 
	  * Elle est construite de manière suivante : Elle hérite de AppBaseFrame de manière a avoir la barre du bas et du haut
	  * avec les bouton home et lock etc.
	  * 
	  * Ensuite elle a un panel du haut qui a un bouton menant vers le choix de la ville à afficher : CityChoiceScreen.
	  * Et affiche toutes les informations relatives à la ville actuelle : Température, image correspondante à la méto
	  * bref resumé : Plui, beau temps ou autre selon la météo
	  * Vitesse du vent et probabilité de pluie.
	  * 
	  * Le panel du bas quant a lui gère les informations relatives à la météo des jours suivants.
	  * Avec un logo correspondant et la température maximale et minimale de ce jour.
	  * 
	  * Cette application utilise l'API de DarkSky : https://darksky.net/
	  */

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
		/**
		 * Constructeur de l'application, construit les éléments et effectue la requête http.
		 * Une fois que la requête est terminée remplace le tableau loading par la météo.
		 */
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
		/*
		 * Requête HTTP vers l'API de darksky, une fois qu'elle est terminée remplace le paneau Loading par la météo
		 * 
		 */



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
		/**
		 * Lit le fichier texte choixVille.txt
		 * de manière à savoir quelle ville on souhaite afficher et donne au programme
		 * les coordonnées de la ville (latitude + longitude) nécessaires pour faire la requête
		 * HTTP à l'API.
		 */
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
		/**
		 * Méthode qui permet de construire la page avec les infos recueillies.
		 */
		JSONObject currentWeather = (JSONObject) forecastInfo.get("currently");
		villeStatus = (String) currentWeather.get("summary");
		String icon = (String) currentWeather.get("icon");
		
		Object farenheitTemp = currentWeather.get("temperature");
		double celciusTemp = 0;
		

		// les If else suivants règlent le problème fourni par la réponse de l'API : Long / Double conversion
		
		if(farenheitTemp instanceof Double) {
			celciusTemp = toCelcius((double)farenheitTemp);
		} else if (farenheitTemp instanceof Long) {
			System.out.println("Long température !!");
			celciusTemp = Long.valueOf(farenheitTemp.toString()).doubleValue();
		}
			
			
		
		Object rainProbability = currentWeather.get("precipProbability");
		double rainProb = 0;
		
		if(rainProbability instanceof Double) {
			rainProb = (double) rainProbability;
		} else if (rainProbability instanceof Long) {
			System.out.println("Long rain Prob !!");
			rainProb = Long.valueOf(rainProbability.toString()).doubleValue();
		}
		
		rainProb = Math.round(rainProb*100);
		String cityRainProb = String.valueOf(rainProb);

		Object windSpeed = currentWeather.get("windSpeed");
		double dWindSpeed = 0;
		if (windSpeed instanceof Double) {
			dWindSpeed = (double) windSpeed;
		} else if (windSpeed instanceof Long) {
			System.out.println("Long windspeed");
			dWindSpeed = Long.valueOf(windSpeed.toString()).doubleValue();
		}
		dWindSpeed = Math.round(dWindSpeed * 1.60934*100); // Conversion en Km/h
		dWindSpeed = dWindSpeed/100; 
		
		String cityWindSpeed = String.valueOf(windSpeed);
		
		
		
		villeTemp = Double.toString(Math.round(celciusTemp));
		ville = contenuFichier.toUpperCase();
		cityLabel = new JLabel(ville);
		cityStatus = new JLabel(villeStatus);

		
		weatherAppPanel.add(topAppPanel);
		
		// Top App Weather Panel construction !
		topAppPanel.setPreferredSize(new Dimension(600, 390));
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
		System.out.println(icon);
		String iconPath = "img/weatherApp/weather-icons/"+icon+".png";
		

		BackgroundPanel CTPleft = new BackgroundPanel(iconPath);
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


		weatherAppPanel.add(topAppPanel);

		// Bottom App Panel construction !
		bottomAppPanel.setPreferredSize(new Dimension(600, 310));
		bottomAppPanel.setLayout(new FlowLayout());
		bottomAppPanel.setOpaque(true);
		bottomAppPanel.setBackground(new Color(0,0,0,65));

		JSONObject nextDaysInfo = (JSONObject) forecastInfo.get("daily");
		JSONArray nextDaysData = (JSONArray) nextDaysInfo.get("data");

		JPanel nextDaysPanel = new JPanel();
		nextDaysPanel.setLayout(new GridLayout(3,3));
		nextDaysPanel.setPreferredSize(new Dimension(600,310));
		nextDaysPanel.setOpaque(false);

		
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E");
		
		for(int i = 0; i < 3; i++) {
			// Ajout du jour suivant au panel
			LocalDate nextDay = today.plus(i+1, ChronoUnit.DAYS);
			String formattedString = nextDay.format(formatter);
			JLabel tomorrowLabel = new JLabel(formattedString);
			tomorrowLabel.setHorizontalAlignment(SwingConstants.CENTER);
			tomorrowLabel.setForeground(Color.white);
			tomorrowLabel.setFont(new Font("Arial", Font.BOLD, 30));
			nextDaysPanel.add(tomorrowLabel);
			
			// Icone choisie selon l'API
			JSONObject dayInfo = (JSONObject) nextDaysData.get(i);
			String iconsPath = "img/weatherApp/weather-icons/";
			String icon1_path = iconsPath+dayInfo.get("icon")+".png";
			BackgroundPanel day_icon = new BackgroundPanel(icon1_path, 100,100);
			nextDaysPanel.add(day_icon);
			
			// Température maximale et minimale issue de l'API
			NextDayTemp day_temp = new NextDayTemp();
			double day_maxTemp = (double) dayInfo.get("temperatureHigh");
			double day_minTemp = (double) dayInfo.get("temperatureLow");
			
			day_temp.setTemps(day_maxTemp, day_minTemp);
			nextDaysPanel.add(day_temp);
			
			
		}

		

		

		bottomAppPanel.add(nextDaysPanel);
		weatherAppPanel.add(bottomAppPanel);

		revalidate();
		repaint();
	}



	private double toCelcius(double farenheitTemp) {
		/**
		 * convertit une température double de Farenheit à Celcius.
		 */
		return  (farenheitTemp-32) * 5/9;
	}



	class TitleLabel extends JLabel {
		/**
		 * Label titre de l'application du panel du haut pour la température etc. (apparence même).
		 * @param str message à afficher.
		 */
		public TitleLabel(String str) {
			super(str);
			setHorizontalAlignment(SwingConstants.CENTER);
			setOpaque(false);
			setForeground(Color.white);
			setFont(new Font("Arial",Font.BOLD,20));
		}
	}

	class DataLabel extends JLabel {
		/**
		 * Label pour les données à afficher du panel du haut (sous les titres).
		 * @param str donnée à afficher.
		 */
		public DataLabel(String str) {
			super(str);
			setHorizontalAlignment(SwingConstants.CENTER);
			setOpaque(false);
			setForeground(Color.white);
			setFont(new Font("Arial",Font.PLAIN,15));
		}
	}

	class NextDayTemp extends JPanel {
		/**
		 * Classe regroupant les données nécessaires relatives aux journées suivantes
		 * Température min
		 * Température max 
		 * 
		 * ainsi que l'apparence de celui-ci (Jpanel).
		 */
		private double tempMin;
		private double tempMax;

		public NextDayTemp() {
			/*
			 * Constructeur de la classe qui met le layout désiré et la met en transparent.
			 */
			super();
			setLayout(new GridLayout(2,0));
			setOpaque(false);
		}

		protected void setTemps(double tempMax, double tempMin) {
			/*
			 * Setter : permet d'attributer à l'objet les données que l'on veut pour les températures.
			 * puis construit l'objet (panel).
			 */
			this.tempMin = tempMin;
			this.tempMax = tempMax;
			constructObject();
		}

		private void constructObject() {
			/**
			 * Construit l'objet une fois que l'on lui a attribué les données nécessaires
			 */
			tempMin = Math.round(toCelcius(tempMin));
			tempMax = Math.round(toCelcius(tempMax));
			
			int IntTempMax = (int)(tempMax);
			int IntTempMin = (int)(tempMin);
			
			JLabel tempMx = new JLabel(String.valueOf(IntTempMax));
			tempMx.setForeground(Color.white);
			tempMx.setFont(new Font("Arial", Font.BOLD, 20));
			tempMx.setHorizontalAlignment(SwingConstants.CENTER);
			tempMx.setVerticalAlignment(SwingConstants.BOTTOM);
			
			JLabel tempMn = new JLabel(String.valueOf(IntTempMin));
			tempMn.setForeground(new Color(211,211,211));
			tempMn.setFont(new Font("Arial", Font.PLAIN, 15));
			tempMn.setHorizontalAlignment(SwingConstants.CENTER);
			tempMn.setVerticalAlignment(SwingConstants.TOP);
			
			add(tempMx);
			add(tempMn);
			
		}
	}

	
}





