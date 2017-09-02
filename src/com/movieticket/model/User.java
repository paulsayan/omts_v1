package com.movieticket.model;

import com.movieticket.dao.DAOClass;

public class User {

	private long id,mobile;
	private String name,type,email,pwd;
	
	public User()
	{
		id=0L;
		mobile=0L;
		name="";
		type="";
		email="";
		pwd="";
	}
	
	public User(long id,String name,String type,String email,long mobile,String pwd)
	{
		this.id=id;
		this.name=name;
		this.type=type;
		this.email=email;
		this.mobile=mobile;
		this.pwd=pwd;
	}
	
	public long getid()
	{
		return this.id;
	}
	
	public String getname()
	{
		return this.name;
	}
	
	public String gettype()
	{
		return this.type;
	}
	
	public String getemail()
	{
		return this.email;
	}
	
	public long getmobile()
	{
		return this.mobile;
	}
	
	public String getpwd()
	{
		return this.pwd;
	}
	
	public void setid(long id)
	{
		this.id=id;
	}
	
	public void setmobile(long mobile)
	{
		this.mobile=mobile;
	}
	
	public void setname(String name)
	{
		this.name=name;
	}
	
	public void settype(String type)
	{
		this.type=type;
	}
	
	public void setemail(String email)
	{
		this.email=email;
	}
	
	public void setpwd(String pwd)
	{
		this.pwd=pwd;
	}
	
	public User getUserData(long id)
	{
		DAOClass obj=new DAOClass();
		User u=new User();
	    try
	    {
	    	u=obj.fetchUserData(id);
	    }
		catch(Exception e)
	    {
			e.printStackTrace();
	    }
		
	    return u;
	}
	
	public User getUserData(String email)
	{
		DAOClass obj=new DAOClass();
		User u=new User();
	    try
	    {
	    	u=obj.fetchUserData(email);
	    }
		catch(Exception e)
	    {
			e.printStackTrace();
	    }
		
	    return u;
	}
	
	public int addUser()
	{
		DAOClass obj=new DAOClass();
		int r=2;
	    try
	    {
	    	r=obj.addUserData(this);
	    }
		catch(Exception e)
	    {
			e.printStackTrace();
	    }
	    return r;

	}
	
	public int updateUser()
	{
		DAOClass obj=new DAOClass();
		int r=2;
	    try
	    {
	    	r=obj.updateUserData(this);
	    }
		catch(Exception e)
	    {
			e.printStackTrace();
	    }
	    return r;

	}
	
	public boolean deleteCustomer()
	{
		DAOClass obj=new DAOClass();
		String query="delete from table_user where user_id=" + id;
		int nor=obj.InsertUpdateDeleteView(query);
	    if(nor>0)
	    	return true;
	    else
	    	return false;
	}
	
	
	public boolean deleteHallAdmin()
	{
		DAOClass obj=new DAOClass();
		String[] query=new String[3];
		query[0]="delete from table_show where hall_id = (select hall_id from table_hall where admin_id="+id+")";
		query[1]="delete from table_hall where admin_id ="+id;
		query[2]="delete from table_user where user_id='" + id + "'";
		int nor=obj.InsertUpdateDeleteViewMultiple(query);
		if(nor>0)
			return true;
		else
			return false;
	}
}
