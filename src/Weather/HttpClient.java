package Weather;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpClient {

private static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?";
private static String KEY = "&APPID=1f82c652fd313a95283ccb439377d378";

public String getWeatherDataByCityId(int id)
{
	String url = BASE_URL+"id="+Integer.toString(id);
	return getWeatherData(url);
}

public String getWeatherDataByCityName(String name)
{
	String url =BASE_URL+"q="+name;
	return getWeatherData(url);
}

public String getWeatherDataByCoords(double latitude, double longitude)
{
	String url =BASE_URL+"lat="+Double.toString(latitude)+"&lon="+Double.toString(longitude);
	return getWeatherData(url);
}
 
private String getWeatherData(String url) {
	 HttpURLConnection con = null ;	 
     InputStream is = null;
	 url+="&units=metric";
	 url+=KEY;
	 try {
		 con = (HttpURLConnection) ( new URL(url)).openConnection();
		 con.setRequestMethod("GET");
	 	 con.setDoInput(true);
	 
	     con.setDoOutput(true);
	 
	     con.connect();
	 
	     StringBuffer buffer = new StringBuffer();
	 	 is = con.getInputStream();
	 
	     BufferedReader br = new BufferedReader(new InputStreamReader(is));
	 
	     String line = null;
	     while (  (line = br.readLine()) != null )
	    	 buffer.append(line + "\r\n");
	     is.close();
	     con.disconnect();
	 
	     return buffer.toString();
	 
	     }
	 
	     catch(Throwable t) {
	    	 t.printStackTrace();
	    	 }
	 
	    finally {
	    	try { is.close(); } catch(Throwable t) {}
	    	try { con.disconnect(); } catch(Throwable t) {}
	    	}
	 return null;
	 }
}
