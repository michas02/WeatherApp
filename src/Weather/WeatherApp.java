package Weather;
import java.io.IOException;

import Settings.Settings;


public class WeatherApp {
	private int sleepTime;
	
	private Settings settings;
	
	private String weatherInfoJSON = null;
	private HttpClient client;
	private WeatherParser parser;
	private WeatherInfo weatherInfo;
	private WebScrapper scrapper;
	
	private UpdateDeamon deamon;
	private boolean deamonRunning;
	private boolean deamonPaused;
	
	private DBManager dbManager;
	
	public WeatherApp()
	{
		settings = new Settings();
		dbManager = new DBManager("","","");
		deamon = new UpdateDeamon();
		deamonRunning=true;
		deamonPaused=true;
		deamon.start();
		sleepTime=1;
		client = new HttpClient();
		parser = new WeatherParser();
		weatherInfo = new WeatherInfo();
		scrapper = new WebScrapper();
	}
	
	class UpdateDeamon extends Thread
	{		
		public void run()
		{
			while(deamonRunning)
			{
				if(deamonPaused)
				{
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
				{
					try {
						updateData();
						System.out.println(weatherInfo.getTemperature()+","+weatherInfo.getHumidity()+","+weatherInfo.getWindSpeed());
					} catch (Exception e) {
						e.printStackTrace();
					}
					for(int i=0;i<sleepTime;i++)
					{
						if(!deamonRunning||deamonPaused)
							break;
						try {
							Thread.sleep(settings.getUpdateTime()*1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
	
	public WeatherInfo getWeatherInfo()
	{
		return weatherInfo;
	}
	public double getChillWind()
	{
		//Wzor wziety z tad
		//http://forum.lowcyburz.pl/viewtopic.php?p=22208
		
		return 13.12 + 0.6215*weatherInfo.getTemperature()-11.37*Math.pow(weatherInfo.getWindSpeed(),0.16)+0.3965*weatherInfo.getTemperature()*Math.pow(weatherInfo.getWindSpeed(),0.16);
	}
	
	public double getChillWind(double temperature,double humidity,double windSpeed)
	{
		return (37-((37-temperature)/(0.68-0.0014*humidity+(1/(1.76+1.4*Math.pow((windSpeed/3.6/3.6), 0.75)))))
				-0.29*temperature*(1-(humidity/100)));
	}
	public void updateData(int id) throws Exception
	{
		setCity(id);
		weatherInfoJSON = client.getWeatherDataByCityId(id);
		parseData();
	}
	
	public void updateData(String name) throws Exception
	{
		setCity(name);
		weatherInfoJSON = client.getWeatherDataByCityName(name);
		parseData();
	}
	
	public void updateData(double latitude,double longitude) throws Exception
	{
		setCity(latitude,longitude);
		weatherInfoJSON = client.getWeatherDataByCoords(latitude, longitude);
		parseData();
	}
	
	public void updateData() throws Exception
	{
		if(settings.getCityId()==0&&settings.getCityName().equals("")&&settings.getLatitude()==0&&settings.getLongitude()==0)
		{
			throw new Exception("Brak wcześniej zapisanych miast");
		}
		else if(settings.getCityId()!=0)
		{
			updateData(settings.getCityId());
		}
		else if(!settings.getCityName().equals(""))
		{
			updateData(settings.getCityName());
		}
		else
		{
			updateData(settings.getLatitude(),settings.getLongitude());
		}
		if(settings.isOwnWebsite())
		{
			scrapper.updateWeatherInfo();
			weatherInfo.setTemperature(scrapper.getWeatherInfo().getTemperature());
		}
		dbManager.insertData((float)getChillWind());
	}
	
	private void parseData()
	{
		weatherInfo.setTemperature(parser.getTemperature(weatherInfoJSON));
		weatherInfo.setHumidity(parser.getHumidity(weatherInfoJSON));
		weatherInfo.setWindSpeed(parser.getWindSpeed(weatherInfoJSON));
		weatherInfo.setPressure(parser.getPressure(weatherInfoJSON));
	}
	
	private void setCity(Object data)
	{
		settings.setCityId(0);
		settings.setCityName("");
		settings.setLatitude(0);
		settings.setLongitude(0);
		if(data instanceof Integer)
		{
			settings.setCityId((Integer)data);;
		}
		else if(data instanceof String)
		{
			settings.setCityName((String)data);
		}
	}
	private void setCity(double latitude, double longitude)
	{
		settings.setCityId(0);
		settings.setCityName("");
		settings.setLatitude(latitude);
		settings.setLongitude(longitude);
	}
	
	public void setSleepTime(int minutes)
	{
		this.sleepTime=minutes;
	}
	synchronized public void startAutoUpdate()
	{
		deamonPaused=false;
	}
	synchronized public void pausedAutoUpdate()
	{
		deamonPaused=true;
	}
	synchronized public void stopAutoUpdate()
	{
		deamonRunning=false;
	}
	
	public String toString()
	{
		return weatherInfoJSON;
	}
	
	public void setSettings(Settings settings)
	{
		this.settings=settings;
		scrapper.setWebsiteAddress(settings.getWebsite());
		scrapper.setTemperatureForm(settings.getLine());
		dbManager = new DBManager(settings);
	}
	public Settings getSettings()
	{
		return this.settings;
	}
}