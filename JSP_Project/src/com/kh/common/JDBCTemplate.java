package com.kh.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCTemplate {

	// 1. Connection객체 생성 한 후 해당 Connection반환하는 메소드
	public static Connection getConnection() {

		Properties prop = new Properties();// Map 계열 컬렉션(key-value)

		// 읽어들이고자 하는 driver.properties파일의 물리적인 경로

		String fileName = JDBCTemplate.class.getResource("/sql/driver/driver.properties").getPath();
		// JDBCTemplate.class는 컴파일된 class파일을 의미함

		// getResource함수의 첫번째 /는 classes폴더를 의미함

		// String fileName =
		// "C:\Web-workspace2\JSP_Project\WebContent\WEB-INF\classes\sql\driver";
		try {
			prop.load(new FileInputStream(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Connection conn = null;

		// 1) jdbc 드라이버 등록
		try {
			Class.forName(prop.getProperty("driver"));
			// 2) DB와 접속 후 Connection객체 생성
			try {
				conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"),
						prop.getProperty("password"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		return conn;

	}
	
	// 2. 전달받은 Connection 객체를 가지고 commit해주는 메소드
	
	// 3. 전달받은 Connection 객체를 가지고 rollback해주는 메소드
	
	// 4. Connection객체를 반납해주는 메소드
	
	// 5. Statement객체를 반납시켜주는 메소드
	
	// 6. ResultSet객체를 반납시켜주는 메소드
	
	
	
	
	
	
	
	
	
	
	
	
	
}