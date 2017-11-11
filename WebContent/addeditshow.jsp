<%--

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

--%>

<%@ include file = "header.jsp" %>


<div class="container">
		  <div class="main-content">
			<div class="header">
				<div class="logo">
					<a href="hahome.jsp"><h1>Online Movie Ticketing System</h1> </a>
				</div>
				</div>
				<div class="clearfix"></div>
			</div>
			
			<div class="bootstrap_container">
            <nav class="navbar navbar-default w3_megamenu" role="navigation">
                <div class="navbar-header">
          			<button type="button" data-toggle="collapse" data-target="#defaultmenu" class="navbar-toggle"><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button><a href="hahome.jsp" class="navbar-brand"><i class="fa fa-home"></i></a>
				</div><!-- end navbar-header -->
				<div id="defaultmenu" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><a href="hahome.jsp">Home</a></li>
                    
                    
                    <li><a href="viewshow.jsp">View Shows</a></li>
                    <li><a href="addeditshow.jsp">Add Show</a></li>
                  
                    <li><a href="confirmbookings.jsp">Confirm Ticket Bookings</a></li>
                    
                    <li><a href="custprofile.jsp">My Profile</a></li>
                    
                    
                    </ul>
                    </div>
                    </nav>
   		 		
			</div>
</div>


<%@ page import="com.movieticket.*" %>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.text.*"%>
<%!
Show show=null;
Long hallid=null;
Hall h=null;
Movie m=null;
String mov=null;
boolean nor=false;
String action="";
String form="";
ArrayList<Movie> mlist=new ArrayList<Movie>();
int i=0;

String start_time=null;
%>
<%
u=(User)session.getAttribute("user");
h=new Hall();
m=new Movie();
h=h.getHallByAdminId(u.getid());

if(u!=null)
{
	form=request.getParameter("form");
	action=request.getParameter("action");

	if(form==null)
	{
%>
<h2 align=center>Add Show</h2>
<br><br>
<center>

 <form action=addeditshow.jsp method="post" align=center>
  Select Movie:<select name="movie">
  <% mlist=m.viewMovies();
  for(Movie obj:mlist)
  {
  	String name=obj.getMovieName();
  	
  	if(request.getParameter("name")!=null)
  	{
  		String selectedname=request.getParameter("name");
  		if(selectedname.equals(name))
  			out.println("<option selected>"+name+"</option>");
  		else
  			out.println("<option>"+name+"</option>");
  	}
  	else
  		out.println("<option>"+name+"</option>");
  	
  	}
  %>
  
  </select>
  Date: <input type="date" name="userdate" placeholder="YYYY-MM-DD">
  Select Time:<select name="time">
  <option>9:00:00.000000</option>
  <option>12:30:00.000000</option>
  <option>15:00:00.000000</option>
  <option>18:00:00.000000</option>
  <option>20:30:00.000000</option>
  </select>
  Select Language:<select name="lang">
  <option>English</option><option>Bengali</option><option>Hindi</option>
  </select>
  <input type=hidden name="type" value="halladmin"><br>
  <input type=hidden name="form" value="addshow"><br>
  <input type="submit" value="Submit">
</form>

</center>
<%
}
	else if(form.equals("addshow"))
	{
		show=new Show();
		show.setHallId(h.getHallId());
		show.setMovieId(m.getMovieIdByName(request.getParameter("movie").toString()).getMovieId());
		show.setLanguage(request.getParameter("lang"));
		start_time=request.getParameter("userdate")+request.getParameter("time");
		show.setStartTime(start_time);
		//out.println("Hall Id="+show.getHallId()+"Movie Name="+request.getParameter("movie")+"Movie Id="+show.getMovieId()+"Start Time="+show.getStartTime()+"Language="+show.getLanguage());
		nor=show.addShow();
		
		if(nor==true)
		{
		%>
	<center><h3>Show Created Successfully.</h3></center>
	<% 
		}
		else
		{
	%>
	<center><h3>Show Creation Unsuccessful!!!</h3></center>
	<% 
		}
	}
	
	
	
	
	
	
	
	
	
}
else
{
	out.println("Unauthenticated Access!!!");
}
	%>
	<br>
	
<%@ include file = "footer.jsp" %>