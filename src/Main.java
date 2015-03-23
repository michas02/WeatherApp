import java.io.IOException;


public class Main {

	public static void main(String[] args) {
		WeatherApp weather = new WeatherApp();
		try {
			weather.updateData("Wroclaw");
			System.out.println("Temperatura: "+Double.toString(weather.getWeatherInfo().getTemperature())
					+" Stopni Celsjusza\nWilgotnosc: "+Double.toString(weather.getWeatherInfo().getHumidity())
					+" %\nPredkosc wiatru: "+Double.toString(weather.getWeatherInfo().getWindSpeed())
					+" m/s");
			System.out.println("Temperatura odczuwalna "+weather.getChillWind());
		} catch (Exception e) {
			System.out.println("Nie udało się uzyskać informacji o pogodzie");
			e.printStackTrace();
		}
		
	}

}
