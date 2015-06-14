package Weather;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class WebScrapper {
	private WeatherInfo weatherInfo;
	
	private String websiteAddress;
	
	private String temperatureForm;
	private String humidityForm;
	private String windSpeedForm;
	
	
	public WebScrapper()
	{
		weatherInfo = new WeatherInfo();
		temperatureForm="";
		humidityForm="";
		windSpeedForm="";
	}
	
	public void updateWeatherInfo() throws IOException
	{
		if(!websiteAddress.isEmpty())
		{
			if(!temperatureForm.isEmpty())
			{
				weatherInfo.setTemperature(getData(temperatureForm));
			}
			if(!humidityForm.isEmpty())
			{
				weatherInfo.setHumidity(getData(humidityForm));
			}
			if(!windSpeedForm.isEmpty())
			{
				weatherInfo.setWindSpeed(getData(windSpeedForm));
			}
		}
	}
	
	
	public void setTemperatureForm(String temperatureForm)
	{
		this.temperatureForm=temperatureForm;
		this.temperatureForm=createRegex(temperatureForm);
	}
	
	public void setWebsiteAddress(String websiteAddress)
	{
		this.websiteAddress=websiteAddress;
	}
	
	public void setHumidityForm(String humidityForm) {
		this.humidityForm = humidityForm;
		this.humidityForm = createRegex(humidityForm);
	}

	public void setWindSpeedForm(String windSpeedForm) {
		this.windSpeedForm = windSpeedForm;
		this.windSpeedForm = createRegex(windSpeedForm);
	}
	
	
	private String getWebsite(String website) throws IOException
	{
		URL url = new URL(website);

		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
		  response.append(inputLine + "\n");
		}
		in.close();
		return response.toString();
	}
	
	private double getData(String dataForm) throws IOException
	{
		String regex = dataForm;
		String source = getWebsite(websiteAddress);
		String line ="";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(source);
		if (matcher.find())
		{
		    line=matcher.group(0);
		}
		pattern = Pattern.compile("\\d.*");
		matcher = pattern.matcher(line);
		if (matcher.find())
		{
			return Double.parseDouble(matcher.group(0));
		}
		return 0;
	}
	
	private String createRegex(String line)
	{	
		String[] regexArray = line.split("\\d");
		line = regexArray[0] + "\\d{1,}";;
		line = line.replaceAll("\\+", "");
		line = line.replaceAll("\"",".{1}");
		return line;
	}
	
	public WeatherInfo getWeatherInfo()
	{
		return weatherInfo;
	}
}
