package com.movieticket.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.movieticket.dao.*;

public class Seat {

	private long show_id,cust_id;
	private String seat_id;
	private Date show_date;
	
	public Seat()
	{
		show_id=0;
		cust_id=0;
		seat_id="";
		show_date=null;
	}
	
	public long getShowId()
	{
		return show_id;
	}
	
	public long getCustId()
	{
		return cust_id;
	}
	
	public String getSeatId()
	{
		return seat_id;
	}
	
	public Date getShowDate()
	{
		return show_date;
	}
	
	public void setShowId(long id)
	{
		show_id=id;
	}
	
	public void setCustId(long id)
	{
		cust_id=id;
	}
	
	public void setSeatId(String id)
	{
		seat_id=id;
	}
	
	public void setShowDate(String date)
	{
		show_date=Date.valueOf(date);
	}
	
	
	public ArrayList<String> getBookedSeats(long show_id)
	{
		DAOClass obj=new DAOClass();
		return obj.getBookedSeats(show_id);
	}
	
	public int bookSeat()
	{
		DAOClass obj=new DAOClass();
		String query="insert into table_seatsbooked values("+show_id+", to_date('"+show_date+"','YYYY-MM-DD'),'"+seat_id+"',"+cust_id+")";
		int nor=obj.InsertUpdateDeleteView(query);
		return nor;
	}
	
	public double getPrice()
	{
		String row=seat_id.charAt(0)+"";
		DAOClass obj=new DAOClass();
		return obj.getPriceByRow(show_id, row);
	}
}
