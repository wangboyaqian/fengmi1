package com.dl.code.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {
	public static Connection getConn() {
		Connection conn=null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_fengmi?useSSL=true", "root", "");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
}
	//增删改
	public int  setUpdate(String sql,Object[] obj)  {
		int result=0;
		PreparedStatement ps;
		try {
			ps = getConn().prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				ps.setObject(i+1, obj[i]);
				
			}
			result=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	return result;
	}
	
	//查询
		public ResultSet select(String sql,Object[] obj) throws Exception {
			ResultSet result=null;
			Connection  conn=getConn();
			 PreparedStatement ps = conn.prepareStatement(sql);
			 
			 
			for (int i = 0; i < obj.length; i++) {
				ps.setObject(i+1, obj[i]);
				
			}
			 //result=ps.executeQuery();
			result=ps.executeQuery();
			return result;
		}
	
	
	

}
