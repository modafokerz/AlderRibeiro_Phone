/*
 * Exercice FIG HES-SO (Sierre)
 * Auteur : Nelson Ribeiro Teixeira
 * Date de création : 24 mai 2018
 * Date de modification : /
 */
package nav;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JPanel;

import objects.WeatherData;




public class WeatherApp extends AppBaseFrame {
	
	private String ville = "sierre";
	
	private String sierreVille = "sierre";
	private String aigleVille = "aigle-vd";
	private String sionVille = "sion-vs";
	
	private WeatherData weatherData;
	
	private JPanel weatherAppPanel = new JPanel();
	private JPanel topAppPanel = new JPanel();
	
	public WeatherApp() {
		
		getAPIinfo();
		remove(centerPanel);
		add(weatherAppPanel, BorderLayout.CENTER);
		weatherAppPanel.setSize(600, 650);
		weatherAppPanel.add(topAppPanel);
		
	}
	
	private void getAPIinfo() {
		
				// HTTP Request qui prend les infos météo de Sierre sur météo suisse
				try {
					URL url = new URL("http://www.prevision-meteo.ch/services/json/" + ville);
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					
					// Erreur 200, indique que le GET n'as pas pu être établi
					if (conn.getResponseCode() != 200) {
						throw new RuntimeException("Erreur code HTTP (réponse) : "
								+ conn.getResponseCode());
					}

					BufferedReader br = new BufferedReader(new InputStreamReader(
							(conn.getInputStream())));

					String output;
					System.out.println("-- Infos Prévisions Météo --");
					while ((output = br.readLine()) != null) {
						System.out.println(output);
					}
					
					
					weatherData = new WeatherData(output);
					conn.disconnect();
					weatherAppConstruction();
					
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
	}

	
	
	private void weatherAppConstruction() {
		
	}
	
	
}
