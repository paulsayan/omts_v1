package com.movieticket.model;

import java.util.ArrayList;

import com.movieticket.dao.DAOClass;

public class Show {

	private long show_id,hall_id,movie_id;
	private String start_time,language;
	
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
	
	public void setStartTime(String s)
	{
		this.start_time=s;
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
	
	
}