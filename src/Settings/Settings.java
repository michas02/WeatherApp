package Settings;

public class Settings {
	private int updateMode;
	private String cityName;
	private int cityId;
	private double latitude;
	private double longitude;
	
	private String website;
	private String line;
	private int updateTime;
	private boolean ownWebsite;
	
	private String dbUrl;
	private String dbUser;
	private String dbPassword;
	
	public Settings()
	{
		updateMode=0;
		cityName="Wroclaw";
		cityId=0;
		latitude=0;
		longitude=0;
		
		website="";
		line="";
		updateTime=3600;
		ownWebsite=false;
	}

	public int getUpdateMode() {
		return updateMode;
	}

	public void setUpdateMode(int updateMode) {
		this.updateMode = updateMode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public int getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(int updateTime) {
		this.updateTime = updateTime;
	}

	public boolean isOwnWebsite() {
		return ownWebsite;
	}

	public void setOwnWebsite(boolean ownWebsite) {
		this.ownWebsite = ownWebsite;
	}

	public String getDbUrl() {
		return dbUrl;
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	public String getDbUser() {
		return dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}
	
	
	
	
}
