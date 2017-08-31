package com.movieticket.model;

public class Price {

	private long show_id;
	private char row_id;
	private String row_class;
	private double price;
	
	public Price()
	{
		show_id=0L;
		row_id='\u0000';
		row_class=new String();
		price=0.0d;
	}
	
	public long getShowId()
	{
		return this.show_id;
	}
	
	public char getRowId()
	{
		return this.row_id;
	}
	
	public String getRowClass()
	{
		return this.row_class;
	}
	
	public double getRowPrice()
	{
		return price;
	}
	
	public void setShowId(long id)
	{
		this.show_id=id;
	}
	
	public void setRowId(char id)
	{
		this.row_id=id;
	}
	
	public void setRowClass(String c)
	{
		this.row_class=c;
	}
	
	public void setRowPrice(double p)
	{
		this.price=p;
	}
	
}
