/*
 * Exercice FIG HES-SO (Sierre)
 * Auteur : Nelson Ribeiro Teixeira
 * Date de cr�ation : 24 mai 2018
 * Date de modification : /
 */
package nav;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;


public class WeatherApp extends AppBaseFrame {
	JSONObject weatherData;

	public WeatherApp() {
		
		// HTTP Request qui prend les infos m�t�o de Sierre sur m�t�o suisse
		try {
			URL url = new URL("http://www.prevision-meteo.ch/services/json/sierre");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			
			// Erreur 200, indique que le GET n'as pas pu �tre �tabli
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Erreur code HTTP (r�ponse) : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			System.out.println("-- Infos M�t�o Suisse --");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
			weatherData = new JSONObject(output);
			conn.disconnect();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
