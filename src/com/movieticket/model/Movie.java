package com.movieticket.model;

import java.util.ArrayList;

import com.movieticket.dao.DAOClass;

public class Movie {
	
	private long movie_id;
	private String movie_name,genre,rel_date,director,cast,poster;
	private int duration;
	
	public Movie()
	{
		movie_id=0L;
		movie_name=new String();
		genre=new String();
		rel_date=new String();
		director=new String();
		cast=new String();
		poster=new String();
		duration=0;
	}
	
	public long getMovieId()
	{
		return this.movie_id;
	}
	
	public String getMovieName()
	{
		return this.movie_name;
	}
	
	public String getGenre()
	{
		return this.genre;
	}
	
	public String getRelDate()
	{
		return this.rel_date;
	}
	
	public String getDirector()
	{
		return this.director;
	}
	
	public String getCast()
	{
		return this.cast;
	}
	
	public int getDuration()
	{
		return this.duration;
	}
	
	public String getPoster()
	{
		return this.poster;
	}
	
	public void setMovieId(long id)
	{
		this.movie_id=id;
	}
	
	public void setMovieName(String mov)
	{
		this.movie_name=mov;
	}
	
	public void setGenre(String genre)
	{
		this.genre=genre;
	}
	
	public void setRelDate(String date)
	{
		this.rel_date=date;
	}
	
	public void setDirector(String d)
	{
		this.director=d;
	}
	
	public void setCast(String c)
	{
		this.cast=c;
	}
	
	public void setDuration(int d)
	{
		this.duration=d;
	}
	
	public void setPoster(String p)
	{
		this.poster=p;
	}
	
	
	public boolean addMovie()
	{
		DAOClass obj=new DAOClass();
		String query="insert into table_movie values ('movie_id.seq.nextval','" + this.getMovieName() + "','" + this.getGenre() + "','" + this.getRelDate() + "','" + this.getDirector() + "','" + this.getCast() + "','" + this.getDuration() + "','" + this.getPoster() + "')";
		
		int nor=obj.InsertUpdateDeleteView(query);
	    if(nor>0)
	    	return true;
	    else
	    	return false;
	}
	
	public boolean deleteMovie()
	{
		DAOClass obj=new DAOClass();
		String query="delete from table_movie where movie_id='" + this.getMovieId() + "'";
		int nor=obj.InsertUpdateDeleteView(query);
	    if(nor>0)
	    	return true;
	    else
	    	return false;
	}
	
	public boolean updateMovie()
	{
		
		DAOClass obj=new DAOClass();
		String query="update table_movie set movie_name='" + this.getMovieName() + "',genre='" + this.getGenre()  + "',release_date='" + this.getRelDate() + "',director='" + this.getDirector() + "',cast='" + this.getCast()+ "',duration='" + this.getDuration()+ "',poster='" + this.getPoster()+"' where movie_id='" + this.getMovieId() +"'";
		
		int nor=obj.InsertUpdateDeleteView(query);
	    if(nor>0)
	    	return true;
	    else
	    	return false;
		
	}
	
	public ArrayList<String> getMovies()
	{
		DAOClass obj=new DAOClass();
	
		ArrayList<String> movlist=new ArrayList<String>();
		
		movlist=obj.fetchAllMovies();
		return movlist;
			
	}
	
	public Movie getMovieIdByName(String mov)
	{
		DAOClass obj=new DAOClass();
		Movie m=new Movie();
		m=obj.fetchMovieData(mov);
		return m;
	}
	public String getMovieByMovieId(long id)
	{
		DAOClass obj=new DAOClass();
		String mov=obj.getMovieByMovieId(id);
		return mov;
	}
	

}
