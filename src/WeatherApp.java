import java.io.IOException;


public class WeatherApp {
	private int sleepTime;
	
	private String cityName;
	private int cityId;
	private double cityLatitude;
	private double cityLongitude;
	
	private String weatherInfoJSON = null;
	private HttpClient client;
	private WeatherParser parser;
	private WeatherInfo weatherInfo;
	
	private UpdateDeamon deamon;
	private boolean deamonRunning;
	private boolean deamonPaused;
	public WeatherApp()
	{
		deamon = new UpdateDeamon();
		deamonRunning=true;
		deamonPaused=true;
		deamon.start();
		sleepTime=1;
		cityName="";
		cityId=0;
		cityLatitude=0;
		cityLongitude=0;
		client = new HttpClient();
		parser = new WeatherParser();
		weatherInfo = new WeatherInfo();
		//weatherInfoJSON = client.getWeatherDataByCityName("Wroclaw");
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
							Thread.sleep(60*1000);
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
		
		return (37-((37-weatherInfo.getTemperature())/(0.68-0.0014*weatherInfo.getHumidity()+(1/(1.76+1.4*Math.pow((weatherInfo.getWindSpeed()/3.6), 0.75)))))
				-0.29*weatherInfo.getTemperature()*(1-(weatherInfo.getHumidity()/100)));
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
		if(cityId==0&&cityName.equals("")&&cityLatitude==0&&cityLongitude==0)
		{
			throw new Exception("Brak wcześniej zapisanych miast");
		}
		else if(cityId!=0)
		{
			updateData(cityId);
		}
		else if(!cityName.equals(""))
		{
			updateData(cityName);
		}
		else
		{
			updateData(cityLatitude,cityLongitude);
		}
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
		cityId=0;
		cityName="";
		cityLatitude=0;
		cityLongitude=0;
		if(data instanceof Integer)
		{
			cityId=(int)data;
		}
		else if(data instanceof String)
		{
			cityName=(String)data;
		}
	}
	private void setCity(double latitude, double longitude)
	{
		cityId=0;
		cityName="";
		cityLatitude=latitude;
		cityLongitude=longitude;
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
}