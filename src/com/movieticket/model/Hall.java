package com.movieticket.model;
import java.util.ArrayList;
import com.movieticket.dao.DAOClass;

public class Hall {

	private long hall_id,admin_id;
	private String hall_name,loc,addr;
	public Hall()
	{
		hall_id=0L;
		admin_id=0L;
		hall_name=new String();
		loc=new String();
		addr=new String();
	}
	
	public long getHallId()
	{
		return this.hall_id;
	}
	
	public void setHallId(long id)
	{
		this.hall_id=id;
	}
	
	public long getAdminId()
	{
		return this.admin_id;
	}
	
	public void setAdminId(long id)
	{
		this.admin_id=id;
	}
	public void setHallName(String name)
	{
		this.hall_name=name;
	}
	
	public String getHallName()
	{
		return this.hall_name;
	}
	
	public void setLoc(String l)
	{
		this.loc=l;
	}
	
	public String getLoc()
	{
		return this.loc;
	}
	
	public void setAddr(String adr)
	{
		this.addr=adr;
	}
	
	public String getAddr()
	{
		return this.addr;
	}
	
	public ArrayList<String> viewHallNames()
	{
		DAOClass obj=new DAOClass();
		ArrayList<Hall> hlist = new ArrayList<Hall>();
		ArrayList<String> hallnames = new ArrayList<String>();
		try
		{
			
			hlist=obj.fetchHallData();
			for(Hall ob:hlist)
				hallnames.add(ob.hall_name);
				
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return hallnames;
		
	}
	
	public ArrayList<Hall> viewHalls()
	{
		DAOClass obj=new DAOClass();
		ArrayList<Hall> hlist = new ArrayList<Hall>();
		try
		{
			
			hlist=obj.fetchHallData();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return hlist;
		
	}
	
	public ArrayList<String> viewHallNames(String loc)
	{
		
		DAOClass obj=new DAOClass();
		ArrayList<Hall> hlist = new ArrayList<Hall>();
		ArrayList<String> hallnames = new ArrayList<String>();
		try
		{
			
			hlist=obj.fetchHallData();
			for(Hall ob:hlist)
				if(ob.loc==loc)
					hallnames.add(ob.hall_name);
				
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return hallnames;
		
	}
	
	public Hall getHallByAdminId(long id)
	{
		
		DAOClass obj=new DAOClass();
		Hall h=new Hall();
	    try
	    {
	    	h=obj.getHallByAdminId(id);
	    }
		catch(Exception e)
	    {
			e.printStackTrace();
	    }
		
	    return h;
	}
	
	public boolean addHall()
	{
		
		DAOClass obj=new DAOClass();
		boolean f=false;
	    try
	    {
	    	f=obj.addHall(this);
	    }
		catch(Exception e)
	    {
			e.printStackTrace();
	    }
		return f;
		
	}
	
	public boolean deleteHall()
	{
		DAOClass obj=new DAOClass();
		String query="delete from table_hall where hall_id='" + this.getHallId() + "'";
		int nor=obj.InsertUpdateDeleteView(query);
	    if(nor>0)
	    	return true;
	    else
	    	return false;
	}
	
	public boolean updateHall()
	{
		DAOClass obj=new DAOClass();
		String query="update table_hall set hall_name='" + this.getHallName() + "',location='" + this.getLoc()  + "',address='" + this.getAddr() + "',admin_id='" + this.getAdminId() +"' where hall_id='" + this.getHallId() +"'";

		int nor=obj.InsertUpdateDeleteView(query);
	    if(nor>0)
	    	return true;
	    else
	    	return false;
	}
	
	
}
	
