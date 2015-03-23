import java.io.IOException;


public class WeatherApp {
	private String weatherInfoJSON = null;
	private HttpClient client;
	private WeatherParser parser;
	private WeatherInfo weatherInfo;
	public WeatherApp()
	{
		client = new HttpClient();
		parser = new WeatherParser();
		weatherInfo = new WeatherInfo();
		//weatherInfoJSON = client.getWeatherDataByCityName("Wroclaw");
	}
	
	public WeatherInfo getWeatherInfo()
	{
		return weatherInfo;
	}
	public double getChillWind()
	{
		//Wzor wziety z tad
		//http://forum.lowcyburz.pl/viewtopic.php?p=22208
		
		return (37-((37-weatherInfo.getTemperature())/(0.68-0.0014*weatherInfo.getHumidity()+(1/(1.76+1.4*Math.pow((weatherInfo.getWindSpeed()/3.6), 0.75)))))
				-0.29*weatherInfo.getTemperature()*(1-(weatherInfo.getHumidity()/100)));
	}
	public void updateData(int id) throws Exception
	{
		weatherInfoJSON = client.getWeatherDataByCityId(id);
		parseData();
	}
	
	public void updateData(String name) throws Exception
	{
		weatherInfoJSON = client.getWeatherDataByCityName(name);
		parseData();
	}
	
	public void updateData(double latitude,double longitude) throws Exception
	{
		weatherInfoJSON = client.getWeatherDataByCoords(latitude, longitude);
		parseData();
	}
	
	private void parseData()
	{
		weatherInfo.setTemperature(parser.getTemperature(weatherInfoJSON));
		weatherInfo.setHumidity(parser.getHumidity(weatherInfoJSON));
		weatherInfo.setWindSpeed(parser.getWindSpeed(weatherInfoJSON));
	}
	
}