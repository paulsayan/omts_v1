package com.movieticket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.movieticket.model.Hall;
import com.movieticket.model.Show;

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
	
	//Hall Function 1
	public Hall getHallByAdminId(long id)
	{
		Connection con= null; PreparedStatement pst= null; ResultSet rs= null;
		int x=0;
		Hall h=new Hall();
		try {
			con = DBConnect.getOracleConnection();
			if(con != null){
				pst = con.prepareStatement("select * from table_hall where admin_id="+id);
				rs = pst.executeQuery();
				if(rs.next())
				{
				
					h.setHallId(rs.getLong(1));
					h.setHallName(rs.getString(2));
					h.setLoc(rs.getString(3));
					h.setAddr(rs.getString(4));
					h.setAdminId(rs.getLong(5));
					
					x++;
				}
				if(x==0)
					h=null;
			}
		}catch(Exception e)	{		e.printStackTrace();		}
		finally {
			DBConnect.closeConnection(con);
			DBConnect.closePreparedStatementConnection(pst);
			DBConnect.closeResultSetConnection(rs);
		}
		return h;
		
	}

	//Hall Function 2 -- To Fetch the Hall Data of a particular Hall.
	public Hall fetchHallData(long id)

	{
		Connection con= null; PreparedStatement pst= null; ResultSet rs= null;
		int x=0;
		Hall h=new Hall();
		try {
			con = DBConnect.getOracleConnection();
			if(con != null){
				pst = con.prepareStatement("select * from table_hall where hall_id="+id);
				rs = pst.executeQuery();
				if(rs.next())
				{
				
					
					h.setHallId(rs.getLong(1));
					h.setHallName(rs.getString(2));
					h.setLoc(rs.getString(3));
					h.setAddr(rs.getString(4));
					h.setAdminId(rs.getLong(5));
					x++;
				}
				if(x==0)
					h=null;
			}
		}catch(Exception e)	{		e.printStackTrace();		}
		finally {
			DBConnect.closeConnection(con);
			DBConnect.closePreparedStatementConnection(pst);
			DBConnect.closeResultSetConnection(rs);
		}
		return h;
	}
	
	//Hall Function 3 -- To Fetch all the Hall Data.
    public ArrayList<Hall> fetchHallData()
	{
		Connection con= null; PreparedStatement pst= null; ResultSet rs= null;
		int x=0;
		ArrayList<Hall> hlist=new ArrayList<Hall>();
		try {
			con = DBConnect.getOracleConnection();
			if(con != null){
				pst = con.prepareStatement("select * from table_hall");
				rs = pst.executeQuery();
				while(rs.next())
				{
				
					Hall h=new Hall();
					h.setHallId(rs.getLong(1));
					h.setHallName(rs.getString(2));
					h.setLoc(rs.getString(3));
					h.setAddr(rs.getString(4));
					h.setAdminId(rs.getLong(5));
					hlist.add(h);
					x++;
				}
				if(x==0)
					hlist=null;
			}
		}catch(Exception e)	{		e.printStackTrace();		}
		finally {
			DBConnect.closeConnection(con);
			DBConnect.closePreparedStatementConnection(pst);
			DBConnect.closeResultSetConnection(rs);
		}
		return hlist;
	}
    
    //Show Function 1 -- To Fetch the Show Data of a Particular Show.
    public Show fetchShowData(long id)
	{
		
		Connection con= null; PreparedStatement pst= null; ResultSet rs= null;
		int x=0;
		Show s=new Show();
		try {
			con = DBConnect.getOracleConnection();
			if(con != null){
				pst = con.prepareStatement("select * from table_show where show_id="+id);
				rs = pst.executeQuery();
				if(rs.next())
				{
				
					
					s.setShowId(rs.getLong(1));
					s.setHallId(rs.getLong(2));
					s.setMovieId(rs.getLong(3));
					s.setStartTime(rs.getString(4));
					s.setLanguage(rs.getString(5));
					x++;
				}
				if(x==0)
					s=null;
			}
		}catch(Exception e)	{		e.printStackTrace();		}
		finally {
			DBConnect.closeConnection(con);
			DBConnect.closePreparedStatementConnection(pst);
			DBConnect.closeResultSetConnection(rs);
		}
		return s;
		
	}
    
  //Function 2 -- To Fetch all the shows.
    public ArrayList<Show> fetchShowData()
	{
		
		Connection con= null; PreparedStatement pst= null; ResultSet rs= null;
		int x=0;
		ArrayList<Show> slist=new ArrayList<Show>();
		try {
			con = DBConnect.getOracleConnection();
			if(con != null){
				pst = con.prepareStatement("select * from table_show");
				rs = pst.executeQuery();
				while(rs.next())
				{
				
					Show s=new Show();
					s.setShowId(rs.getLong(1));
					s.setHallId(rs.getLong(2));
					s.setMovieId(rs.getLong(3));
					s.setStartTime(rs.getString(4));
					s.setLanguage(rs.getString(5));
					slist.add(s);
					x++;
				}
				if(x==0)
					slist=null;
			}
		}catch(Exception e)	{		e.printStackTrace();		}
		finally {
			DBConnect.closeConnection(con);
			DBConnect.closePreparedStatementConnection(pst);
			DBConnect.closeResultSetConnection(rs);
		}
		return slist;
	}

    
    //Function 3 -- To fetch all the shows of a particular hall id.
    public ArrayList<Show> fetchShowDataByHallId(long id)
	{
		
		Connection con= null; PreparedStatement pst= null; ResultSet rs= null;
		int x=0;
		ArrayList<Show> slist=new ArrayList<Show>();
		try {
			con = DBConnect.getOracleConnection();
			if(con != null){
				pst = con.prepareStatement("select * from table_show where hall_id="+id);
				rs = pst.executeQuery();
				if(rs.next())
				{
				
					Show s=new Show();
					s.setShowId(rs.getLong(1));
					s.setHallId(rs.getLong(2));
					s.setMovieId(rs.getLong(3));
					s.setStartTime(rs.getString(4));
					s.setLanguage(rs.getString(5));
					slist.add(s);
					x++;
				}
				if(x==0)
					slist=null;
			}
		}catch(Exception e)	{		e.printStackTrace();		}
		finally {
			DBConnect.closeConnection(con);
			DBConnect.closePreparedStatementConnection(pst);
			DBConnect.closeResultSetConnection(rs);
		}
		return slist;
		
	}
		
}

