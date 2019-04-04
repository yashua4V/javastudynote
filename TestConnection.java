package com.yashua.mysql;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.Test;
import com.mysql.cj.jdbc.Driver;
public class TestConnection {
	@Test
	public void testDriver() throws SQLException {
		
		Driver driver = new com.mysql.cj.jdbc.Driver();
		String url = "jdbc:mysql://47.95.0.152:3306/test";
		Properties info = new Properties();
		info.put("user","root");
		info.put("password","yashua");
		Connection connection = driver.connect(url, info);
		System.out.println(connection);
	}
	public Connection getConnection() throws Exception {
		String driverClass = null;
		String url         = null;
		String user        = null;
		String password    = null;
		InputStream in = getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		Properties properties = new Properties();
		properties.load(in);
		driverClass = properties.getProperty("driver");
		url = properties.getProperty("jdbcUrl");
		user = properties.getProperty("user");
		password = properties.getProperty("password");
		Driver driver = 
				(Driver) Class.forName(driverClass).newInstance();
		Class.forName(driverClass);
		Properties info = new Properties();
		info.put("user",user);
		info.put("password",password);
		//Connection connection2 = driver.connect(url,info);
		Connection connection = DriverManager.getConnection(url, info);
		return connection;
	}
	@Test
	public void testGetConnection() throws Exception {
		System.out.println(getConnection());
	}
}
