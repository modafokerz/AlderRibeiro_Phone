/*
* Exercice FIG HES-SO (Sierre)
* Auteur : Nelson Ribeiro Teixeira
* Date de création : 28 mai 2018
* Date de modification : /
*/
package objects;

import java.io.Serializable;


public class WeatherCity implements Serializable {
	private static final long serialVersionUID = 8212470682425818271L;
	private double cityLatitude;
	private double cityLongitude;
	
	public WeatherCity (double latitude, double longitude) {
		cityLatitude = latitude;
		cityLongitude = longitude;
	}
	
	public double getCityLatitude() {
		return cityLatitude;
	}
	
	public double getCityLongitude() {
		return cityLongitude;
	}
}
