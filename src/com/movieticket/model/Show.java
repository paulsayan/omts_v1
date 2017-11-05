package com.movieticket.model;

import java.util.ArrayList;

import com.movieticket.dao.DAOClass;
import java.util.*;
import java.sql.Timestamp;
import java.text.*;

public class Show {

	private long show_id,hall_id,movie_id;
	private String language;
	private String start_time;
	
	public Show()
	{
		show_id=0L;
		hall_id=0L;
		movie_id=0L;
		start_time=new String();
		language=new String();
	}
	
	public long getShowId()
	{
		return this.show_id;
	}
	
	public long getHallId()
	{
		return this.hall_id;
	}
	
	public long getMovieId()
	{
		return this.movie_id;
	}
	
	public String getStartTime()
	{
		return this.start_time;
	}
	
	public String getLanguage()
	{
		return this.language;
	}
	
	public void setShowId(long id)
	{
		this.show_id=id;
	}
	
	public void setHallId(long id)
	{
		this.hall_id=id;
	}
	
	public void setMovieId(long id)
	{
		this.movie_id=id;
	}
	
	public void setStartTime(String t)
	{
		this.start_time=t;
	}
	
	public void setLanguage(String s)
	{
		this.language=s;
	}
	
	public ArrayList<Show> getShowByHallId(long id)
	{

		ArrayList<Show> show=new ArrayList<Show>();
		
		DAOClass obj=new DAOClass();
		try
		{
			show=obj.fetchShowDataByHallId(id);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return show;
	}
	
	public boolean deleteShowByMovieId(long id)
	{
		
		DAOClass obj=new DAOClass();
		String query="delete from table_show where movie_id='" + id + "'";
	    int nor=obj.InsertUpdateDeleteView(query);
	    if(nor>0)
	    	return true;
	    else
	    	return false;
		
	}
	
	public ArrayList<Show> getShowByMovieName(long movid,long hallid)
	{
		DAOClass obj=new DAOClass();
		ArrayList<Show> alist=new ArrayList<Show>();
		
		alist=obj.fetchShowDataByHallId(hallid);
		for(Show s:alist)
			if(s.getMovieId()!=movid)
				alist.remove(s);
		return alist;
	}
	
	public boolean deleteShowByHallId(long id)
	{
		
		DAOClass obj=new DAOClass();
		String query="delete from table_show where hall_id='" + id + "'";
	    int nor=obj.InsertUpdateDeleteView(query);
	    if(nor>0)
	    	return true;
	    else
	    	return false;
		
	}
	public boolean deleteShowByShowId(long id)
	{
		
		DAOClass obj=new DAOClass();
		String query[]=new String[3];
		query[0]="delete from table_price where show_id='" + id + "'";
		query[1]="delete from table_seatsbooked where show_id='" + id + "'"; 
		query[2]="delete from table_show where show_id='" + id + "'";
		
	    int nor=obj.InsertUpdateDeleteViewMultiple(query);
	    if(nor>0)
	    	return true;
	    else
	    	return false;
		
	}
	public boolean deleteShowByShowId(String showid[])
	{
		DAOClass obj=new DAOClass();
		String query[]=new String[showid.length*3];
		for(int i=0,j=0;i<showid.length && j<query.length;i++,j+=3)
		{
			long id=Long.parseLong(showid[i]);
			query[j]="delete from table_price where show_id='" + id + "'";
			query[j+1]="delete from table_seatsbooked where show_id='" + id + "'"; 
			query[j+2]="delete from table_show where show_id='" + id + "'";
		}
		
		int nor=obj.InsertUpdateDeleteViewMultiple(query);
		if(nor>0)
			return true;
		else
			return false;
	}
	
	public boolean addShow()
	{
		
		System.out.println(getHallId()+getMovieId()+getStartTime()+ getLanguage());
		DAOClass obj=new DAOClass();
		//String query="insert into table_show values (show_id_seq.nextval,'" + getHallId() + "','" + getMovieId() + "',to_timestamp('"+getStartTime()+"','dd-mm-yyyyHH24:MI:SS.FF'),'" + getLanguage() + "')";
		String query="insert into table_show values(show_id_seq.nextval,'" + getHallId() + "','" + getMovieId() + "',to_timestamp('"+getStartTime()+"','yyyy-mm-ddHH24:MI:SS.FF'),'" + getLanguage() + "')";
		int nor=obj.InsertUpdateDeleteView(query);
	    if(nor>0)
	    	return true;
	    else
	    	return false;
	
	}

	public boolean updateShow()
	{
		
		DAOClass obj=new DAOClass();
		String query="update table_show set hall_id='" + this.getHallId() + "',movie_id='" + this.getMovieId()  + "',start_time='" + this.getStartTime() + "',language='" + this.getLanguage() +"' where show_id='" + this.getShowId() +"'";

		int nor=obj.InsertUpdateDeleteView(query);
	    if(nor>0)
	    	return true;
	    else
	    	return false;
		
	}
	
	public String getMovieByShowId(long id)
	{
		DAOClass obj=new DAOClass();

		long movie_id=obj.getMovieIdByShowId(id);
		
		String movie=obj.getMovieByMovieId(movie_id);
		return movie;
	}
	
	public Show getShowByShowId(long id)
	{
		DAOClass obj =new DAOClass();
		return obj.fetchShowData(id);
	}
	
	public Hall getHallByShowId(long id)
	{
		DAOClass obj =new DAOClass();
		return obj.getHallByShowId(id);
	}
	
	public double[] getPriceByShowId(long id)
	{
		DAOClass obj =new DAOClass();
		return obj.getPrices(id);
	}
	
	public ArrayList<Show> getShowsAvailableForBooking(String today)
	{
		today=today+" 23:59:59";
		DAOClass obj =new DAOClass();
		return obj.getShowsAvailableForBooking(today);
		
	}
	
	public ArrayList<Show> getShowsAvailableForBooking(String today,long movieid)
	{
		today=today+" 23:59:59";
		DAOClass obj =new DAOClass();
		return obj.getShowsAvailableForBooking(today,movieid);
		
	}
	
	public ArrayList<Show> getShowsAvailableForBooking(long movieid, String showdate)
	{
		DAOClass obj =new DAOClass();
		return obj.getShowsAvailableForBooking(movieid,showdate);
		
	}
	
	
	public ArrayList<Movie> getMoviesAvailableForBooking(String today)
	{
		today=today+" 23:59:59";
		DAOClass obj =new DAOClass();
		return obj.getMoviesAvailableForBooking(today);
		
	}
	
	
	public String getShowTimeByShowId(long id)
	{
		DAOClass obj =new DAOClass();
		
		String showtime=obj.fetchShowTime(id).toString();
		
		return showtime;
		
	}
	
}