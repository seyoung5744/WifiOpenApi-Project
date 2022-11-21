package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCTemplate {
	public static Connection getConnection() {
		try {
			// SQLite JDBC 체크
			Class.forName("org.sqlite.JDBC");
		} catch (Exception e) {
			// 드라이버 연결 실패
			e.printStackTrace();
		}
		
		String url = "jdbc:sqlite:";
		String db = "C:\\Users\\seyou\\Desktop\\my_Git_Hub\\zero-base-java\\WifiOpenApi-Project\\wifi.db";
		Connection conn = null;
		
		try {
			// SQLite 데이터베이스 파일에 연결
			conn = DriverManager.getConnection(url + db);
			System.out.println("SQLite DB connected");
			conn.setAutoCommit(false);
		}catch (SQLException e) {
			// 계정 연결 실패
			e.printStackTrace();
		}

		return conn;
	}
	
	public static boolean isConnection(Connection conn) {
		boolean valid = true;
		
		try {
			if(conn == null || conn.isClosed()) {
				valid = false;
			}
		} catch (Exception e) {
			valid = true;
			e.printStackTrace();
		}
		
		return valid;
	}
	
	// 연결 상태인지 아닌지 확인 -> 연결 상태 확인되면 close
	public static void close(Connection conn) {
		if (isConnection(conn)) {
			try {
				conn.close();
				System.out.println("SQLite DB connect close");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// stmt 가 null 이 아닐 때 close
	public static void close(PreparedStatement preparedStatement) {
		if(preparedStatement != null) {
			try {
				preparedStatement.close();
				System.out.println("PreparedStatement close");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	// rs 가 null이 아닐 때 close
	public static void close(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
				System.out.println("ResultSet close");
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	// 연결 상태라면 commit
	public static void commit(Connection conn) {
		if(isConnection(conn)) {
			try {
				conn.commit();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	public static void rollback(Connection conn) {
		if(isConnection(conn)) {
			try {
				conn.rollback();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}
