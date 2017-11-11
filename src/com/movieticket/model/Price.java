/*

    Online Movie Ticketing System
    Copyright (C) 2017 - Sayan Paul, Arjun Sengupta, Shubham Ghosh

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as published
    by the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.

*/

package com.movieticket.model;

import com.movieticket.dao.DAOClass;

public class Price {

	private long show_id;
	private String row_id;
	private String row_class;
	private double price;
	
	public Price()
	{
		show_id=0L;
		row_id="";
		row_class=new String();
		price=0.0d;
	}
	
	public long getShowId()
	{
		return this.show_id;
	}
	
	public String getRowId()
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
	
	public void setRowId(String id)
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
	
	public int addPrice()
	{
		DAOClass obj=new DAOClass();
		String query="insert into table_price values("+show_id+",'"+row_id+"','"+row_class+"',"+price+")";
		int nor=obj.InsertUpdateDeleteView(query);
		return nor;
	}
	
	public double[] viewPrices(long id)
	{
		DAOClass obj=new DAOClass();
		return obj.getPrices(id);
	}
	
	public int updatePrice()
	{
		DAOClass obj=new DAOClass();
		String query="update table_price set price="+price+" where show_id="+show_id+" and row_id='"+row_id+"'";
		int nor=obj.InsertUpdateDeleteView(query);
		return nor;
	}
}
