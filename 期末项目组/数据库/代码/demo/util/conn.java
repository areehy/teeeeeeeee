package demo.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class conn {

	public static Connection getcon()
	{
		Connection con=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/twohand?useUnicode=true&characterEncoding=utf-8";
			String user="root";
			String password="123456";
			con=DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
	}
}
