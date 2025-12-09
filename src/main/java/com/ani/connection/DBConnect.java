package com.ani.connection;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnect 
{
private static Connection con = null;
	
	private DBConnect()
	{
		
	}
	
	static
	{
		try
		{
			FileInputStream fis = new FileInputStream("D:\\Advance_java\\ABS_Banking_Management_System\\src\\main\\java\\com\\ani\\connection\\db.properties");
			
			Properties props = new Properties();
			
			props.load(fis);
			String drivarName = props.getProperty("driverName");
			String url = props.getProperty("url");
			String username = props.getProperty("username");
			String password = props.getProperty("password");
		
			Class.forName(drivarName);
			con = DriverManager.getConnection(url, username, password);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
	}
	
	public static Connection connect()
	{
		return con;
	}
}
