package com.neusoft.system.db;

import java.sql.*;
import java.util.ResourceBundle;


public class DBUtils {
	private static String driver;
	private static String url;
	private static String userName;
	private static String password;
	private static final ThreadLocal<java.sql.Connection> threadLocal=new ThreadLocal<>();
	
	/*
	 * 
	 * 静态块，第一次运行时执行，以后不执行
	 */
	
	static {
		try {
			//获取资源文件解析器对象
			ResourceBundle bundle=ResourceBundle.getBundle("DBOptions");
			//通过解析对象,获取数据
			driver=bundle.getString("DRIVER");
			url=bundle.getString("URL");
			userName=bundle.getString("USERNAME");
			password=bundle.getString("PASSWORD");
			
			//3.加载驱动
			Class.forName(driver);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	
	private DBUtils(){}
	
	private static Connection getConnection() throws Exception{
		Connection connection = threadLocal.get();
		if(connection == null || connection.isClosed()) {
			DriverManager.getConnection(url, userName, password);
			threadLocal.set(connection);
		}
		return connection;
	}
	
	public static PreparedStatement prepareStatement(String sql) throws Exception{
		return DBUtils.getConnection().prepareStatement(sql);
	}
	
	public static void commit() throws Exception{
		DBUtils.getConnection().commit();
	}
	
	public static void rollBack() {
		try {
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				DBUtils.getConnection().rollback();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public static void beginTranscation() throws Exception{
		DBUtils.getConnection().setAutoCommit(false);
	}
	
	public static void endTranscation() throws Exception{
		try {
			
			
			DBUtils.getConnection().setAutoCommit(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public static void close(PreparedStatement st) {
		// TODO Auto-generated method stub
			try {
				if(st != null)
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public static void close() {
		try {
			Connection conn = threadLocal.get();
			if(conn.isClosed() == false || conn != null)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs) {
		try {
			if(rs != null)
				rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			for(int i = 0; i < 1000; i ++) {
				System.out.println(DBUtils.getConnection());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
