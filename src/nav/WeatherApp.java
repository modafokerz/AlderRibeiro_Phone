/*
 * Exercice FIG HES-SO (Sierre)
 * Auteur : Nelson Ribeiro Teixeira
 * Date de création : 24 mai 2018
 * Date de modification : /
 */
package nav;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import com.teknikindustries.yahooweather.WeatherDisplay;
import com.teknikindustries.yahooweather.WeatherDoc;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;





public class WeatherApp extends AppBaseFrame {
	
	
	private String ville = "Sierre";
	
	
	private JPanel weatherAppPanel = new JPanel();
	private JPanel topAppPanel = new JPanel();
	
	public WeatherApp() {
		super();
		getAPIinfo(ville);
		remove(centerPanel);
		add(weatherAppPanel, BorderLayout.CENTER);
		weatherAppPanel.setSize(600, 650);
		
		// Top App Weather Panel construction !
		topAppPanel.setSize(600,350);
		
		weatherAppPanel.add(topAppPanel);
		
	}
	
	private void getAPIinfo(String str) 
	{

        // OpenWeatherMap object avec ma clé API
        OWM owm = new OWM("49f3502e61b9c40f4389aa6443c960de");

        // getting current weather data 
        CurrentWeather cwd = null;
		try {
			cwd = owm.currentWeatherByCityName("London");
		} catch (APIException e) {
			e.printStackTrace();
		}

        //printing city name from the retrieved data
        System.out.println("City: " + cwd.getCityName());

        // printing the max./min. temperature
        System.out.println("Temperature: " + cwd.getMainData().getTempMax()
                            + "/" + cwd.getMainData().getTempMin() + "\'K");
				
	}

	
	
	
	
	
}
