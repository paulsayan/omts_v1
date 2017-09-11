package com.movieticket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.movieticket.model.User;
import com.movieticket.dao.DBConnect;
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
			con.setAutoCommit(false);
			if(con != null){
				pst = con.prepareStatement(query);
				nor = pst.executeUpdate();
				
			}
			con.commit();
			
		}
		catch(SQLException e)	
		{		
			//se.printStackTrace();
			if(con!=null)
			{
				try
				{
					System.err.print("Transaction Rollback!!!");
					con.rollback();
					nor=0;
				}
				catch(Exception e2)
				{
					e2.printStackTrace();
				}
			}
			
		}
		catch(Exception e)	{		e.printStackTrace();		}
		finally {
			try {
				con.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DBConnect.closePreparedStatementConnection(pst);
			DBConnect.closeConnection(con);
			
		}
		return nor;
	}
	
	public int InsertUpdateDeleteViewMultiple(String[] query)
	{
		Connection con= null;PreparedStatement pst=null;int nor= 0;
		try {
			con = DBConnect.getOracleConnection();
			con.setAutoCommit(false);
			if(con != null){
				for(String pstquery: query) 
				{
					System.out.println("Entering " + pstquery);
					pst = con.prepareStatement(pstquery);
					nor += pst.executeUpdate();
					System.out.println("Exiting "+ pstquery);
				}
			}
			con.commit();
			
		}
		catch(SQLException e)	
		{		
			//se.printStackTrace();
			if(con!=null)
			{
				try
				{
					System.err.println("Transaction Rollback!!!");
					con.rollback();
					nor=0;
				}
				catch(Exception e2)
				{
					e2.printStackTrace();
				}
			}
			
		}
		catch(Exception e)	{		e.printStackTrace();		}
		finally {
			try {
				con.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DBConnect.closePreparedStatementConnection(pst);
			DBConnect.closeConnection(con);
			
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
	
	//User Functions
	
	//Fetch a Particular User's Data by Id
	public User fetchUserData(long id)

	{
		Connection con= null; PreparedStatement pst= null; ResultSet rs= null;
		int x=0;
		User u=new User();
		try {
			con = DBConnect.getOracleConnection();
			if(con != null){
				pst = con.prepareStatement("select * from table_user where user_id="+id);
				rs = pst.executeQuery();
				if(rs.next())
				{
				
					u.setid(rs.getLong(1));
					u.setname(rs.getString(2));
					u.settype(rs.getString(3));
					u.setemail(rs.getString(4));
					u.setmobile(rs.getLong(5));
					u.setpwd(rs.getString(6));
					x++;
				}
				if(x==0)
					u=null;
			}
		}catch(Exception e)	{		e.printStackTrace();		}
		finally {
			DBConnect.closeConnection(con);
			DBConnect.closePreparedStatementConnection(pst);
			DBConnect.closeResultSetConnection(rs);
		}
		return u;
	}

	//Fetch a Particular User's Data by Email
	public User fetchUserData(String email)

	{
		Connection con= null; PreparedStatement pst= null; ResultSet rs= null;
		int x=0;
		User u=new User();
		try {
			con = DBConnect.getOracleConnection();
			if(con != null){
				pst = con.prepareStatement("select * from table_user where email='"+email+"'");
				rs = pst.executeQuery();
				if(rs.next())
				{
				
					u.setid(rs.getLong(1));
					u.setname(rs.getString(2));
					u.settype(rs.getString(3));
					u.setemail(rs.getString(4));
					u.setmobile(rs.getLong(5));
					u.setpwd(rs.getString(6));
					x++;
				}
				if(x==0)
					u=null;
			}
		}catch(Exception e)	{		e.printStackTrace();		}
		finally {
			DBConnect.closeConnection(con);
			DBConnect.closePreparedStatementConnection(pst);
			DBConnect.closeResultSetConnection(rs);
		}
		return u;
	}
	
	// Adds a new user to the database
	public int addUserData(User u)

	{
		Connection con= null; PreparedStatement pst= null; ResultSet rs= null;
		int x=0,nor=0;
		try {
			con = DBConnect.getOracleConnection();
			if(con != null){
				pst = con.prepareStatement("select * from table_user where email='"+u.getemail()+"'");
				rs = pst.executeQuery();
				if(rs.next())
				{
					x++;
				}
				if(x==0)
				{
					pst = con.prepareStatement("insert into table_user values(user_id_seq.nextval,?,?,?,?,?)");
					pst.setString(1, u.getname());
					pst.setString(2, u.gettype());
					pst.setString(3, u.getemail());
					pst.setLong(4, u.getmobile());
					pst.setString(5, u.getpwd());
					nor=pst.executeUpdate();
				}
			
			}
		}catch(Exception e)	{		e.printStackTrace();		}
		finally {
			DBConnect.closeConnection(con);
			DBConnect.closePreparedStatementConnection(pst);
			DBConnect.closeResultSetConnection(rs);
		}
		if(x==0 && nor>0)
			return 0;
		else if(x!=0)
			return 1;
		else
			return 2;
	}
	
	//Updates an existing user in the database
	public int updateUserData(User u)

	{
		Connection con= null; PreparedStatement pst= null; ResultSet rs= null;
		int x=0,nor=0;
		try {
			con = DBConnect.getOracleConnection();
			if(con != null){
				pst = con.prepareStatement("select * from table_user where email='"+u.getemail()+"' and user_id!="+u.getid());
				rs = pst.executeQuery();
				if(rs.next())
				{
					x++;
				}
				DBConnect.closePreparedStatementConnection(pst);
				if(x==0)
				{
					pst = con.prepareStatement("update table_user set user_name=?, user_type=?, email=?, mobile=?, pwd=? where user_id=?");
					
					pst.setString(1, u.getname());
					pst.setString(2, u.gettype());
					pst.setString(3, u.getemail());
					pst.setLong(4, u.getmobile());
					pst.setString(5, u.getpwd());
					
					pst.setLong(6, u.getid());
					nor=pst.executeUpdate();
				}
			
			}
		}catch(Exception e)	{		e.printStackTrace();		}
		finally {
			DBConnect.closeConnection(con);
			DBConnect.closePreparedStatementConnection(pst);
			DBConnect.closeResultSetConnection(rs);
		}
		if(x==0 && nor>0)
			return 0;
		else if(x!=0)
			return 1;
		else
			return 2;
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
				pst = con.prepareStatement("select * from table_hall order by hall_id");
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
					//System.out.println(h);
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
    
    //To add a new Hall
    public boolean addHall(Hall h)
    {
		Connection con=null;
		PreparedStatement pst=null;
		int nor=0;
		try
		 {
	         con = DBConnect.getOracleConnection();
	         if(con!=null)
	         {
			     pst = con.prepareStatement("insert into table_hall values(hall_id_seq.nextval,?,?,?,?)");
		         pst.setString(1, h.getHallName());
		         pst.setString(2, h.getLoc());
		         pst.setString(3,h.getAddr());
		         pst.setLong(4, h.getAdminId());
			        		     
			     nor = pst.executeUpdate();
			     
	         } 
	    }catch(Exception e){System.out.print(e.toString());}
	    finally
	    {
	    	DBConnect.closeConnection(con);
	    	DBConnect.closePreparedStatementConnection(pst);
	    }
		if(nor>0)
	    	 return true;
	     else
	    	 return false;
		
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

