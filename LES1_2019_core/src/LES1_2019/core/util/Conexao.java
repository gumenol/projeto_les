package LES1_2019.core.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	public static Connection getConnection()
		throws ClassNotFoundException, 
		SQLException{
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost/menolstore";
		String user = "root";
		String paswd = "root";
		Class.forName( driver );
		Connection conn = DriverManager.getConnection(url, user, paswd);
		
		return conn;
	}
}