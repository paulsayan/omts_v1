package com.movieticket.model;

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
}
