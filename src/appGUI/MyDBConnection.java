package appGUI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MyDBConnection {
	
	 private static Connection conn = null;

	    public static Connection getConnection() {
	        if (conn == null) {
	            try {
	                Class.forName("org.h2.Driver");
	                conn = DriverManager.getConnection(
	                	    "jdbc:h2:tcp://localhost/C:/Users/user/Desktop/ПУ-2 КУРС/Практикум ООП/CarTrackDb",
	                	    "Admin",
	                	    "3788"
	                	);
	                System.out.println("[INFO] Връзката към базата е създадена.");
	            } catch (ClassNotFoundException | SQLException e) {
	                System.err.println("[ERROR] Грешка при създаване на връзка: " + e.getMessage());
	            }
	        } else {
	            System.out.println("[INFO] Използва се вече съществуваща връзка.");
	        }
	        return conn;
	    }

}
