import org.json.*;

public class WeatherParser {
	public double getTemperature(String data)
	{
		JSONObject obj = new JSONObject(data);
		return getValue("main","temp",obj);
	}
	public double getHumidity(String data)
	{
		JSONObject obj = new JSONObject(data);
		return getValue("main","humidity",obj);
	}
	
	public double getWindSpeed(String data)
	{
		JSONObject obj = new JSONObject(data);
		return getValue("wind","speed",obj);
	}
	
	public double getPressure(String data)
	{
		JSONObject obj = new JSONObject(data);
		return getValue("main","pressure",obj);
	}
	
	private double getValue(String tag, String name, JSONObject obj)
	{
		double value=0;
		JSONObject tmpObject = obj.getJSONObject(tag);
		value = tmpObject.getDouble(name);
		return value;
	}
}
