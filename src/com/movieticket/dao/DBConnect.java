package com.movieticket.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class DBConnect {
	
	public static Connection getOracleConnection()
	{
		Connection con=null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","omts","movie");
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return con;
		
	}
	public static void closeConnection(Connection con)
	{
		if(con!=null) {
			try
			{
				con.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	public static void closePreparedStatementConnection(PreparedStatement pst)
	{
		if(pst!=null) {
			try
			{
				pst.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	public static void closeResultSetConnection(ResultSet rs)
	{
		if(rs!=null) {
			try
			{
				rs.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	

}
