package com.movieticket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAOClass 
{

	//generalized function to insert, update, delete, view
	public int InsertUpdateDeleteView(String query)
	{
		Connection con= null;PreparedStatement pst=null;int nor= 0;
		try {
			con = DBConnect.getOracleConnection();
			if(con != null){
				pst = con.prepareStatement(query);
				nor = pst.executeUpdate();
			}
		}catch(Exception e)	{		e.printStackTrace();		}
		finally {
			DBConnect.closeConnection(con);
			DBConnect.closePreparedStatementConnection(pst);
		}
		return nor;
	}
	
	//to check login
	public String checkLoginData(String query)
	{
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		String nm="";
		boolean f=false;
		try{
			con=DBConnect.getOracleConnection();
			if(con!=null){
				pst=con.prepareStatement(query);				
				rs = pst.executeQuery();
				
				if(rs.next()){
					nm=rs.getString(1);
					f=true;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally	{
			DBConnect.closeConnection(con);
			DBConnect.closePreparedStatementConnection(pst);
			DBConnect.closeResultSetConnection(rs);
		}
		if(f==true)
			return nm;
		else
			return null;
	}
	
	public ResultSet getData(String query)
	{
		Connection con= null; PreparedStatement pst= null; ResultSet rs= null;
		try {
			con = DBConnect.getOracleConnection();
			if(con != null){
				pst = con.prepareStatement(query);
				rs = pst.executeQuery();
			}
		}catch(Exception e)	{		e.printStackTrace();		}
		finally {
			DBConnect.closeConnection(con);
			DBConnect.closePreparedStatementConnection(pst);
		}
		return rs;
	}
	
	
	
		
}

