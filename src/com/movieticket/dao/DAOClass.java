package com.movieticket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.movieticket.model.User;
import com.movieticket.dao.DBConnect;
import com.movieticket.model.Hall;
import com.movieticket.model.Movie;
import com.movieticket.model.Show;
import com.movieticket.model.Ticket;

public class DAOClass 
{

	private static final long OL = 0;

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
					s.setStartTime(rs.getTimestamp(4).toString());
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
    
    public Timestamp fetchShowTime(long id)
	{
		
		Connection con= null; PreparedStatement pst= null; ResultSet rs= null;
		int x=0;
		
		//Show s=new Show();
		
		Timestamp st=null;
		
		try {
			con = DBConnect.getOracleConnection();
			if(con != null){
				pst = con.prepareStatement("select * from table_show where show_id="+id);
				rs = pst.executeQuery();
				if(rs.next())
				{
				
					st=rs.getTimestamp(4);
					
					x++;
				}
				if(x==0)
					st=null;
			}
		}catch(Exception e)	{		e.printStackTrace();		}
		finally {
			DBConnect.closeConnection(con);
			DBConnect.closePreparedStatementConnection(pst);
			DBConnect.closeResultSetConnection(rs);
		}
		
		return st;
		
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
					s.setStartTime(rs.getTimestamp(4).toString());
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
				while(rs.next())
				{
				
					Show s=new Show();
					s.setShowId(rs.getLong(1));
					s.setHallId(rs.getLong(2));
					s.setMovieId(rs.getLong(3));
					s.setStartTime(rs.getTimestamp(4).toString());
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
    
    public long getMovieIdByShowId(long id)
    {
    	Connection con= null; PreparedStatement pst= null; ResultSet rs= null;
		int x=0;long i=0L;
		try {
			con = DBConnect.getOracleConnection();
			if(con != null){
				pst = con.prepareStatement("select movie_id from table_show where show_id="+id);
				rs = pst.executeQuery();
				if(rs.next())
				{
					i=rs.getLong(1);
					x++;
				}
				if(x==0)
					i=0;
			}
		}catch(Exception e)	{		e.printStackTrace();		}
		finally {
			DBConnect.closeConnection(con);
			DBConnect.closePreparedStatementConnection(pst);
			DBConnect.closeResultSetConnection(rs);
		}
		return i;
    }
    
    public ArrayList<Movie> fetchAllMovies()
    {
    	
    	Connection con= null; PreparedStatement pst= null; ResultSet rs= null;
		int x=0;
		
		ArrayList<Movie> movlist=new ArrayList<Movie>();
		
		
		try {
			con = DBConnect.getOracleConnection();
			if(con != null){
				pst = con.prepareStatement("select * from table_movie order by movie_id");
				rs = pst.executeQuery();
				while(rs.next())
				{
					Movie m=new Movie();
					
					m.setMovieId(rs.getLong(1));
					m.setMovieName(rs.getString(2));
					
					System.out.println(m.getMovieName());
					
					m.setGenre(rs.getString(3));
					m.setRelDate(rs.getString(4));
					m.setDirector(rs.getString(5));
					m.setCast(rs.getString(6));
					m.setDuration(rs.getInt(7));
					m.setPoster(rs.getString(8));
					
					movlist.add(m);
					
					x++;
				}
				if(x==0)
					movlist=null;
			}
		}catch(Exception e)	{		e.printStackTrace();		}
		finally {
			DBConnect.closeConnection(con);
			DBConnect.closePreparedStatementConnection(pst);
			DBConnect.closeResultSetConnection(rs);
		}
		return movlist;
    	
				
    }
    
    public Movie fetchMovieData(String mov)

	{
		Connection con= null; PreparedStatement pst= null; ResultSet rs= null;
		int x=0;
		Movie m=new Movie();
		try {
			con = DBConnect.getOracleConnection();
			if(con != null){
				pst = con.prepareStatement("select * from table_movie where MOVIE_NAME='" + mov + "'");
				rs = pst.executeQuery();
				if(rs.next())
				{
				
					
					m.setMovieId(rs.getLong(1));
					m.setMovieName(rs.getString(2));
					m.setGenre(rs.getString(3));
					m.setRelDate(rs.getString(4));
					m.setDirector(rs.getString(5));
					m.setCast(rs.getString(6));
					m.setDuration(rs.getInt(7));
					m.setPoster(rs.getString(8));
					x++;
				}
				if(x==0)
					m=null;
			}
		}catch(Exception e)	{		e.printStackTrace();		}
		finally {
			DBConnect.closeConnection(con);
			DBConnect.closePreparedStatementConnection(pst);
			DBConnect.closeResultSetConnection(rs);
		}
		return m;
	}
    
    public Movie fetchMovieData(long id)

	{
		Connection con= null; PreparedStatement pst= null; ResultSet rs= null;
		int x=0;
		Movie m=new Movie();
		try {
			con = DBConnect.getOracleConnection();
			if(con != null){
				pst = con.prepareStatement("select * from table_movie where movie_id=" + id);
				rs = pst.executeQuery();
				if(rs.next())
				{
				
					
					m.setMovieId(rs.getLong(1));
					m.setMovieName(rs.getString(2));
					m.setGenre(rs.getString(3));
					m.setRelDate(rs.getString(4));
					m.setDirector(rs.getString(5));
					m.setCast(rs.getString(6));
					m.setDuration(rs.getInt(7));
					m.setPoster(rs.getString(8));
					x++;
				}
				if(x==0)
					m=null;
			}
		}catch(Exception e)	{		e.printStackTrace();		}
		finally {
			DBConnect.closeConnection(con);
			DBConnect.closePreparedStatementConnection(pst);
			DBConnect.closeResultSetConnection(rs);
		}
		return m;
	}
    
    
    public String getMovieByMovieId(long id)
    {
    	Connection con= null; PreparedStatement pst= null; ResultSet rs= null;
		int x=0;
		String mov=new String();
		try {
			con = DBConnect.getOracleConnection();
			if(con != null){
				pst = con.prepareStatement("select movie_name from table_movie where movie_id='" + id + "'");
				rs = pst.executeQuery();
				if(rs.next())
				{
				
					mov=rs.getString(1);
					x++;
				}
				if(x==0)
					mov=null;
			}
		}catch(Exception e)	{		e.printStackTrace();		}
		finally {
			DBConnect.closeConnection(con);
			DBConnect.closePreparedStatementConnection(pst);
			DBConnect.closeResultSetConnection(rs);
		}
		return mov;
    }
    
    public double[] getPrices(long id)
    {
    	double prices[]=new double[3];
    	double p;
    	Connection con= null; PreparedStatement pst= null; ResultSet rs= null;
		int x=0;
		//System.out.println("DAOClass:: Show Id"+id);
		try {
			con = DBConnect.getOracleConnection();
			if(con != null){
				pst = con.prepareStatement("select distinct price from table_price where show_id="+id+" order by price asc");
				rs = pst.executeQuery();
				while(rs.next())
				{
				
					p=Double.parseDouble(rs.getString(1));
					//System.out.println("DAOClass:: P:"+p);
					prices[x]=p;
					x++;
				}
				if(x==0)
					prices=null;
			}
		}catch(Exception e)	{		e.printStackTrace();		}
		finally {
			DBConnect.closeConnection(con);
			DBConnect.closePreparedStatementConnection(pst);
			DBConnect.closeResultSetConnection(rs);
		}
		//System.out.println("DAOClass:: "+prices[0]+prices[1]+prices[2]);
		return prices;
    }
    
    public ArrayList<String> getBookedSeats(long show_id)
    {
    	ArrayList<String> bookedSeats = new ArrayList<String>();
    	
    	
    	Connection con= null; PreparedStatement pst= null; ResultSet rs= null;
		int x=0;
		
		try {
			con = DBConnect.getOracleConnection();
			if(con != null){
				pst = con.prepareStatement("select seat_id from table_seatsbooked where show_id="+show_id);
				rs = pst.executeQuery();
				while(rs.next())
				{
				
					bookedSeats.add(rs.getString(1));
					x++;
				}
				if(x==0)
					bookedSeats=null;
			}
		}catch(Exception e)	{		e.printStackTrace();		}
		finally {
			DBConnect.closeConnection(con);
			DBConnect.closePreparedStatementConnection(pst);
			DBConnect.closeResultSetConnection(rs);
		}
		return bookedSeats;
    }
    
    
    public Hall getHallByShowId(long id)

	{
		Connection con= null; PreparedStatement pst= null; ResultSet rs= null;
		int x=0;
		Hall h=new Hall();
		try {
			con = DBConnect.getOracleConnection();
			if(con != null){
				pst = con.prepareStatement("select * from table_hall where hall_id=(select hall_id from table_show where show_id="+id+")");
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
    
    public double getPriceByRow(long showid, String row)
    {
    	double p=0;
    	Connection con= null; PreparedStatement pst= null; ResultSet rs= null;
		int x=0;
		
		try {
			con = DBConnect.getOracleConnection();
			if(con != null){
				pst = con.prepareStatement("select price from table_price where show_id="+showid+"and row_id='"+row+"'");
				rs = pst.executeQuery();
				if(rs.next())
				{
				
					p=Double.parseDouble(rs.getString(1));
					x++;
				}
				if(x==0)
					p=0;
			}
		}catch(Exception e)	{		e.printStackTrace();		}
		finally {
			DBConnect.closeConnection(con);
			DBConnect.closePreparedStatementConnection(pst);
			DBConnect.closeResultSetConnection(rs);
		}
		
		return p;
    }
    
    public ArrayList<Ticket> getTicketDataByBookingId(String booking_id)
    {
    	ArrayList<Ticket> tickets = new ArrayList<Ticket>();
    	
    	
    	Connection con= null; PreparedStatement pst= null; ResultSet rs= null;
		int x=0;
		
		try {
			con = DBConnect.getOracleConnection();
			if(con != null){
				pst = con.prepareStatement("select * from table_ticket where booking_id='"+booking_id+"'");
				rs = pst.executeQuery();
				while(rs.next())
				{
				
					Ticket t=new Ticket();
					t.setBookingId(rs.getString(1));
					t.setSeatId(rs.getString(2));
					t.setCustId(rs.getLong(3));
					t.setHallId(rs.getLong(4));
					t.setShowId(rs.getLong(5));
					t.setBookingDate(rs.getString(6));
					t.setShowDate(rs.getString(7));
					t.setMovieName(rs.getString(8));
					t.setPrice(rs.getDouble(9));
					t.sethaId(rs.getLong(10));
					tickets.add(t);
					
					x++;
				}
				if(x==0)
					tickets=null;
			}
		}catch(Exception e)	{		e.printStackTrace();		}
		finally {
			DBConnect.closeConnection(con);
			DBConnect.closePreparedStatementConnection(pst);
			DBConnect.closeResultSetConnection(rs);
		}
		return tickets;
    }
    
    
    public ArrayList<Ticket> getTicketDataByCustId(long id)
    {
    	ArrayList<Ticket> tickets = new ArrayList<Ticket>();
    	
    	
    	Connection con= null; PreparedStatement pst= null; ResultSet rs= null;
		int x=0;
		
		try {
			con = DBConnect.getOracleConnection();
			if(con != null){
				pst = con.prepareStatement("select * from table_ticket where cust_id="+id);
				rs = pst.executeQuery();
				while(rs.next())
				{
				
					Ticket t=new Ticket();
					t.setBookingId(rs.getString(1));
					t.setSeatId(rs.getString(2));
					t.setCustId(rs.getLong(3));
					t.setHallId(rs.getLong(4));
					t.setShowId(rs.getLong(5));
					t.setBookingDate(rs.getString(6));
					t.setShowDate(rs.getString(7));
					t.setMovieName(rs.getString(8));
					t.setPrice(rs.getDouble(9));
					t.sethaId(rs.getLong(10));
					tickets.add(t);
					
					x++;
				}
				if(x==0)
					tickets=null;
			}
		}catch(Exception e)	{		e.printStackTrace();		}
		finally {
			DBConnect.closeConnection(con);
			DBConnect.closePreparedStatementConnection(pst);
			DBConnect.closeResultSetConnection(rs);
		}
		return tickets;
    }
    
    public ArrayList<Show> getShowsAvailableForBooking(String today)
    {
    	ArrayList<Show> shows = new ArrayList<Show>();
    	
    	
    	Connection con= null; PreparedStatement pst= null; ResultSet rs= null;
		int x=0;
		
		try {
			con = DBConnect.getOracleConnection();
			if(con != null){
				pst = con.prepareStatement("select * from table_show where start_time > (to_timestamp('"+today+"','YYYY-MM-DD HH24:MI:SS'))");
				rs = pst.executeQuery();
				while(rs.next())
				{
				
					Show s=new Show();
					s.setShowId(rs.getLong(1));
					s.setHallId(rs.getLong(2));
					s.setMovieId(rs.getLong(3));
					s.setStartTime(rs.getString(4));
					s.setLanguage(rs.getString(5));
					shows.add(s);
					
					x++;
				}
				if(x==0)
					shows=null;
			}
		}catch(Exception e)	{		e.printStackTrace();		}
		finally {
			DBConnect.closeConnection(con);
			DBConnect.closePreparedStatementConnection(pst);
			DBConnect.closeResultSetConnection(rs);
		}
		return shows;
    }
    
    public ArrayList<Show> getShowsAvailableForBooking(String today,long movieid)
    {
    	ArrayList<Show> shows = new ArrayList<Show>();
    	
    	
    	Connection con= null; PreparedStatement pst= null; ResultSet rs= null;
		int x=0;
		
		try {
			con = DBConnect.getOracleConnection();
			if(con != null){
				pst = con.prepareStatement("select * from table_show where start_time > (to_timestamp('"+today+"','YYYY-MM-DD HH24:MI:SS')) and movie_id="+movieid);
				rs = pst.executeQuery();
				while(rs.next())
				{
				
					Show s=new Show();
					s.setShowId(rs.getLong(1));
					s.setHallId(rs.getLong(2));
					s.setMovieId(rs.getLong(3));
					s.setStartTime(rs.getString(4));
					s.setLanguage(rs.getString(5));
					shows.add(s);
					
					x++;
				}
				if(x==0)
					shows=null;
			}
		}catch(Exception e)	{		e.printStackTrace();		}
		finally {
			DBConnect.closeConnection(con);
			DBConnect.closePreparedStatementConnection(pst);
			DBConnect.closeResultSetConnection(rs);
		}
		return shows;
    }
    
    public ArrayList<Show> getShowsAvailableForBooking(long movieid,String showdate)
    {
    	ArrayList<Show> shows = new ArrayList<Show>();
    	
    	
    	Connection con= null; PreparedStatement pst= null; ResultSet rs= null;
		int x=0;
		
		try {
			con = DBConnect.getOracleConnection();
			if(con != null){
				
				pst = con.prepareStatement("select * from table_show where start_time > (to_timestamp('"+showdate+" 00:00:00','YYYY-MM-DD HH24:MI:SS')) and start_time < (to_timestamp('"+showdate+" 23:59:59','YYYY-MM-DD HH24:MI:SS')) and movie_id="+movieid);
				
				rs = pst.executeQuery();
				while(rs.next())
				{
				
					Show s=new Show();
					s.setShowId(rs.getLong(1));
					s.setHallId(rs.getLong(2));
					s.setMovieId(rs.getLong(3));
					s.setStartTime(rs.getString(4));
					s.setLanguage(rs.getString(5));
					shows.add(s);
					
					x++;
				}
				if(x==0)
					shows=null;
			}
		}catch(Exception e)	{		e.printStackTrace();		}
		finally {
			DBConnect.closeConnection(con);
			DBConnect.closePreparedStatementConnection(pst);
			DBConnect.closeResultSetConnection(rs);
		}
		return shows;
    }
    
    
    public ArrayList<Movie> getMoviesAvailableForBooking(String today)
    {
    	ArrayList<Movie> movies = new ArrayList<Movie>();
    	
    	
    	Connection con= null; PreparedStatement pst= null; ResultSet rs= null;
		int x=0;
		
		try {
			con = DBConnect.getOracleConnection();
			if(con != null){
				pst = con.prepareStatement("select * from table_movie where movie_id in (select movie_id from table_show where start_time > (to_timestamp('"+today+"','YYYY-MM-DD HH24:MI:SS')))");
				rs = pst.executeQuery();
				while(rs.next())
				{
				
					Movie m=new Movie();
					m.setMovieId(rs.getLong(1));
					m.setMovieName(rs.getString(2));
					m.setGenre(rs.getString(3));
					m.setRelDate(rs.getString(4));
					m.setDirector(rs.getString(5));
					m.setCast(rs.getString(6));
					m.setDuration(rs.getInt(7));
					m.setPoster(rs.getString(8));
					movies.add(m);
					
					x++;
				}
				if(x==0)
					movies=null;
			}
		}catch(Exception e)	{		e.printStackTrace();		}
		finally {
			DBConnect.closeConnection(con);
			DBConnect.closePreparedStatementConnection(pst);
			DBConnect.closeResultSetConnection(rs);
		}
		return movies;
    }
    
}

