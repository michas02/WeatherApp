package Weather;
import java.io.IOException;

import javax.xml.transform.TransformerException;

import Settings.Settings;
import Settings.XmlLoader;


public class Main {

	public static void main(String[] args) {
		WeatherApp weather = new WeatherApp(); // utworzenie nowej instancji klasy WeatherApp
		XmlLoader loader = new XmlLoader("Config.xml"); // wczytywanie pliku konfiguracyjnego
		weather.setSettings(loader.getSettings()); // załadowanie pliku konfiguracyjnego do obiektu WeatherApp
		weather.startAutoUpdate();	// uruchomienie automatycznego aktualizowania danych
	}
}
