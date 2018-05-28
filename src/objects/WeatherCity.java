/*
* Exercice FIG HES-SO (Sierre)
* Auteur : Nelson Ribeiro Teixeira
* Date de création : 28 mai 2018
* Date de modification : /
*/
package objects;

import java.io.Serializable;

@SuppressWarnings("serial")
public class WeatherCity implements Serializable {
private double [] cityCoordinates = new double[2];
	
	public WeatherCity (double latitude, double longitude) {
		cityCoordinates[0] = latitude;
		cityCoordinates[1] = longitude;
	}
	
	public double [] getCityCoordinates() {
		return cityCoordinates;
	}
}
