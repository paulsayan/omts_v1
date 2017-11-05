package com.movieticket.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;

import com.movieticket.dao.DAOClass;

public class Ticket {

	private long cust_id,hall_id,show_id;
	private String booking_id,seat_id,movie_name;
	private String booking_date,show_date;
	private double price;
	private long ha_id;
	
	
	public Ticket()
	{
		cust_id=0;
		hall_id=0;
		show_id=0;
		booking_id="";
		seat_id="";
		booking_date=null;
		show_date=null;
		movie_name="";
		price=0;
		ha_id=0;
		
	}
	public String getBookingId()
	{
		return booking_id;
	}
	
	public long getCustId()
	{
		return cust_id;
	}
	
	public long getHallId()
	{
		return hall_id;
	}
	
	public long getShowId()
	{
		return show_id;
	}
	
	public String getSeatId()
	{
		return seat_id;
	}
	
	public String getBookingDate()
	{
		return booking_date;
	}
	
	public String getShowDate()
	{
		return show_date;
	}
	
	public String getMovieName()
	{
		return movie_name;
	}
	
	public double getPrice()
	{
		return price;
	}
	
	public long gethaId()
	{
		return ha_id;
	}
	
	public void setBookingId(String id)
	{
		booking_id=id;
	}
	
	public void setCustId(long id)
	{
		cust_id=id;
	}
	
	public void setShowId(long id)
	{
		show_id=id;
	}
	
	public void setHallId(long id)
	{
		hall_id=id;
	}
	
	public void setSeatId(String id)
	{
		seat_id=id;
	}
	
	public void setBookingDate(String date)
	{
		booking_date=date;
	}
	
	public void setShowDate(String date)
	{
		show_date=date;
	}
	
	public void setMovieName(String name)
	{
		movie_name=name;
	}
	
	public void setPrice(double p)
	{
		price=p;
	}
	
	public void sethaId(long id)
	{
		ha_id=id;
	}
	
	public int generateTicket()
	{
		DAOClass obj=new DAOClass();
		String query="insert into table_ticket values('"+booking_id+"','"+seat_id+"',"+cust_id+","+hall_id+","+show_id+", to_date('"+booking_date.toString()+"','YYYY-MM-DD'),to_date('"+show_date.toString()+"','YYYY-MM-DD'),'"+movie_name+"',"+price+",NULL)";
		int nor=obj.InsertUpdateDeleteView(query);
		return nor;
	}
	
	public int confirmTicket()
	{
		DAOClass obj=new DAOClass();
		String query="update table_ticket set ha_id="+ha_id+" where booking_id='"+booking_id+"' and ha_id is NULL";
		int nor=obj.InsertUpdateDeleteView(query);
		return nor;
	}
	
	public ArrayList<Ticket> getTicketsByBookingId(String bookingid)
	{
		DAOClass obj=new DAOClass();
		return obj.getTicketDataByBookingId(bookingid);
	}
	
	public HashSet<String> getBookingIdsByCustId(long id)
	{
		DAOClass obj=new DAOClass();
		
		ArrayList<Ticket> tk=new ArrayList<Ticket>();
		HashSet<String> bids=new HashSet<String>();
		
		tk=obj.getTicketDataByCustId(id);
		
		for(Ticket t:tk)
		{
			bids.add(t.getBookingId());
		}
		
		return bids;
	}
	
}
