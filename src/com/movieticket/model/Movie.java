package com.movieticket.model;

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
	
	public String RelDate()
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
	
	public void setPoster(String p)
	{
		this.poster=p;
	}

}
