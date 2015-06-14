package Weather;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;





public class DBManager {

	private String user = "";
	private String password = "";
	private String url = "";
	
    private Connection conn;
    private Statement stat;
	
	public DBManager()
	{
	    try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Brak sterownika JDBC");
            e.printStackTrace();
        }
 
        try {
            conn = DriverManager.getConnection(url,user,password);
            stat = conn.createStatement();
        } catch (SQLException e) {
            System.err.println("Problem z otwarciem polaczenia");
            e.printStackTrace();
        }
	}
	
	public void insertData(float temperature)
	{
		java.util.Date date = new java.util.Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		 PreparedStatement prepStmt;
		try {
			prepStmt = conn.prepareStatement("insert into Weather values (NULL, ?, ?, ?);");
			 prepStmt.setInt(1, 1);
	         prepStmt.setFloat(2, temperature);
	         prepStmt.setTimestamp(3, timestamp);
	         prepStmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
}
