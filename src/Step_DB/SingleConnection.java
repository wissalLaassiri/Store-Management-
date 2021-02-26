package Step_DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingleConnection {
	String db = "magasin-db";
	String user ="root";
	String pwd ="";
	String url ="jdbc:mysql://localhost:3306/"+db;
	// !!! on doit y avoir une seule connexion pour toute les requétes
private static	Connection connection= null;
	private SingleConnection() {
		try {
			connection = DriverManager.getConnection(url,user,pwd);
			System.out.println("connection crée");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
public static Connection getConnection() {
	if(connection == null) 
		new SingleConnection();
	return connection; 
}
}
