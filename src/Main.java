import java.io.IOException;


public class Main {

	public static void main(String[] args) {
		//WeatherApp weather = new WeatherApp();
		
		/*try {
			weather.updateData("Wroclaw");
			System.out.println("Temperatura: "+Double.toString(weather.getWeatherInfo().getTemperature())
					+" Stopni Celsjusza\nWilgotnosc: "+Double.toString(weather.getWeatherInfo().getHumidity())
					+" %\nPredkosc wiatru: "+Double.toString(weather.getWeatherInfo().getWindSpeed())
					+" m/s"
					+"\nCisnienie: "+Double.toString(weather.getWeatherInfo().getPressure())+" hPa");
			System.out.println("Temperatura odczuwalna "+weather.getChillWind());
		} catch (Exception e) {
			System.out.println("Nie udało się uzyskać informacji o pogodzie");
			e.printStackTrace();
		}*/
		
		WebScrapper s = new WebScrapper();
		/*s.setWebsiteAddress("http://www.meteoprog.pl/pl/weather/Szczyrk/");
		s.setTemperatureForm("<div class=\"temp\">+12°C</div>");*/
		
		s.setWebsiteAddress("http://www.twojapogoda.pl/polska/dolnoslaskie/wroclaw");
		s.setTemperatureForm("<strong>14</strong>");
		s.setHumidityForm("<div class=\"value\">30 %</div>");
		s.setWindSpeedForm("<div class=\"value\">25 km/h");
		
		/*s.setWebsiteAddress("http://pogoda.interia.pl/prognoza-szczegolowa-wroclaw,cId,39240");
		s.setTemperatureForm("<div class=\"weather-currently-temp-strict\">14°C</div>");*/
		try {
			s.updateWeatherInfo();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Temperatura "+s.getWeatherInfo().getTemperature());
		System.out.println("Wiater "+s.getWeatherInfo().getWindSpeed());
		System.out.println("Wilg "+s.getWeatherInfo().getHumidity());
		
		
		//System.out.println(weather.toString());
		
		//weather.startAutoUpdate();*/
		
	}

}
