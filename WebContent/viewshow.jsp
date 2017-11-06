<%@ include file = "header.jsp" %>
<body>
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

<%@ page import="com.movieticket.model.*" %>
<%@ page import="com.movieticket.dao.*" %>
<%@ page import="java.util.*" %>
<%!

Hall h=null;
User usr=null;
Movie m=null;
Show s=null;
String form="";
String action="";
String showid=null;
String hallid="";
String movieid="";
String start_time="";
String lang="";
String str=null;
boolean flag1=false;
Price p=null;

ArrayList<Movie> mlist=new ArrayList<Movie>();


ArrayList<Show> slist=null;
HashSet<String> sdates=null;

%>
<%
u=(User)session.getAttribute("user");
if(u!=null)
{
	h=new Hall();
	s=new Show();
	m=new Movie();
	
	
	slist=new ArrayList<Show>();
	h=h.getHallByAdminId(u.getid());
	slist=s.getShowByHallId(h.getHallId());
	
	
	
	form=request.getParameter("form");
	action=request.getParameter("action");
	if(form!=null)
	{
	if(form.equals("viewshow") && action.equals("Manage Prices"))
	{
		System.out.println("Entered Manage Prices..");
		flag1=true;
		
		s=new Show();
		str=request.getParameter("checkbox");
		s=s.getShowByShowId(Long.parseLong(str));
		
		%>
		<script type=javascript>
		
		</script>
		<table style="width:80%" align=center border=4>
		<tr>
		<th>Show Id</th>
		<th>Movie Name</th>
		<th>Start Time</th>
		<th>Language</th>
		</tr>
		<tr>
		<td><%=s.getShowId() %></td>
		<td><%=s.getMovieByShowId(s.getShowId()) %></td>
		<td><%=s.getStartTime() %></td>
		<td><%=s.getLanguage() %></td>
		</tr>
		</table>
		<br><br>
		
		<center>
		<h3>Ticket Prices (in Rupees):-</h3>
		<%
		 p=new Price();
		 double prices[]=p.viewPrices(s.getShowId());
		 if(prices==null)
		 {
			 System.err.println("NULL Prices");
		%>
		<form action=SetPrices method=post>
		Normal:	<input type=text name=normalprice>
		Executive: <input type=text name=executiveprice>
		Premium: <input type=text name=premiumprice>
		<br>
		<input type=hidden name=showid value=<%=s.getShowId() %>>
		<input type=hidden name=action value="setprice">
		<br>
		<input type=submit value="Set Prices">
		</form>
		</center>
		<div id="restofthedoc" style="display: none;">
		<%
		 }
		 else
		 {
			 System.out.println(prices[0]+prices[1]+prices[2]);
		 %>
		 
		<form action=SetPrices method=post>
		Normal:	<input type=text name=normalprice value=<%=prices[0] %>>
		Executive: <input type=text name=executiveprice value=<%=prices[1] %>>
		Premium: <input type=text name=premiumprice value=<%=prices[2] %>>
		<br>
		<input type=hidden name=showid value=<%=s.getShowId() %>>
		<input type=hidden name=action value="updateprice">
		<input type=submit value="Update Prices">
		</form>
		
		<div id="restofthedoc" style="display: none;">
		  
		 <%
		 }
	}
	}
	
	
	%>
	<center>
	<form action="viewshow.jsp" method=post align=center>
	Choose Show by Movie: <select name="movie">
<% 	mlist=m.viewMovies();
	out.println("<option>Select</option>");
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
	
	
	Choose Show by Date: <select name="date">
<% 	out.println("<option>Select</option>");
	sdates=new HashSet<String>();
	
	 for(Show obj:slist)
 	 {
  		String date=(String)obj.getStartTime().split(" ")[0];
  		
  		if(!sdates.contains(date))
  		{
  			
  		if(request.getParameter("date")!=null)
  		{
  			String selecteddate=request.getParameter("date");
  			if(selecteddate.equals(date))
  				out.println("<option selected>"+date+"</option>");
  			else
  				out.println("<option>"+date+"</option>");
  		}
  		else
  			out.println("<option>"+date+"</option>");
  		
  		}
  		
  		sdates.add(date);
  		
  	}
%>
	</select>
Choose Show by Language:<select name="language">
<option selected>Select</option>
<option>English</option>
<option>Bengali</option>
<option>Hindi</option>
</select>
<br><br>
<input type=hidden name=form value=viewshow>
<input type=submit name=action value=show>

<input type=submit name=action value=delete>
<input type=submit name=action value="Manage Prices">
</center>
<table style="width:80%" align=center border=4>

	<% form=request.getParameter("form");
	action=request.getParameter("action");
	if(form==null)
	{
%>
<tr>
<th>Select</th>
<th>Show Id</th>
<th>Movie Name</th>
<th>Date</th>
<th>Start Time</th>
<th>Language</th>
</tr>
	
<%
		

	 /*String getmovid=Long.toString(m.getMovieIdByName(request.getParameter("movie")).getMovieId());
	 String getlang=request.getParameter("language");
	 String gettime=request.getParameter("date");
	 out.println("MovieId="+getmovid+"Language="+getlang+"Time="+gettime);*/
	 for(Show ob:slist)
	 {
		 showid=Long.toString(ob.getShowId());
		 movieid=Long.toString(ob.getMovieId());
		 
		 //Edit
		 start_time=ob.getStartTime().split(" ")[0];
		 
		 
		 lang=ob.getLanguage();
		 
		 //if(getmovid==movieid || getlang==lang || gettime==start_time)
		 {%>
		    
			<tr>
			<td><input type="radio" name="checkbox" value=<%=showid %>></td>
			<td><%=showid %></td>
			<td><%=m.getMovieByMovieId(ob.getMovieId()) %></td>
			<td><%=start_time%></td>
			<td><%=ob.getStartTime().split(" ")[1] %></td>
			<td><%=lang %></td>
			</tr>
		   
		 <%
		 }
		 
	 }
	 

%></table>
	<%  			
}
	else if(form.equals("viewshow") && action.equals("show"))
	{%>
				<table style="width:80%" align=center border=4>
				<tr>
				<th>Select</th>
				<th>Show Id</th>
				<th>Movie Name</th>
				<th>Date</th>
				<th>Start Time</th>
				<th>Language</th>
				</tr>
		<% 
		String getmovid="Select";
		String getlang="Select";
		String gettime="Select";
		if(request.getParameter("movie").equals("Select")==false) 
			getmovid=Long.toString(m.getMovieIdByName(request.getParameter("movie")).getMovieId());
		if(request.getParameter("language").equals("Select")==false)
	 		getlang=request.getParameter("language");
		if(request.getParameter("date").equals("Select")==false)
	 		gettime=request.getParameter("date");
		int i=0;
		 for(Show ob:slist)
		 {
			
			 i++;
			 showid=Long.toString(ob.getShowId());
			 movieid=Long.toString(ob.getMovieId());
			 
			 //Edit
			 start_time=ob.getStartTime().split(" ")[0];
			 
			 
			 lang=ob.getLanguage();
			 
			 
			 
			 if((getmovid.equals(movieid)  && getlang.equals("Select") && gettime.equals("Select"))
					||  (getmovid.equals("Select") && getlang.equals(lang) && gettime.equals("Select"))
					||  (getmovid.equals("Select") && getlang.equals("Select") && gettime.equals(start_time))
					||  (getmovid.equals(movieid) && getlang.equals(lang) && gettime.equals("Select"))
					||  ((getmovid.equals(movieid) && getlang.equals(lang) && gettime.equals(start_time)))
					||  ((getmovid.equals(movieid) && getlang.equals("Select") && gettime.equals(start_time)))
					 )
				 
			 {%>
			 
				<tr>
				<td><input type="radio" name="checkbox" value=<%=showid %>></td>
				<td><%=showid %></td>
				<td><%=m.getMovieByMovieId(ob.getMovieId()) %></td>
				<td><%=start_time%></td>
				<td><%=ob.getStartTime().split(" ")[1] %></td>
				<td><%=lang %></td>
				</tr>
				
			 <% 
			 }
			  
		 }
		 
		 %>

	</table>
	<% 
	}
	else if(form.equals("viewshow") && action.equals("delete"))
	{
		str=request.getParameter("checkbox");
		boolean b=s.deleteShowByShowId(Long.parseLong(str));
		if(b==true)
			out.println("<br><font align=center>Records deleted !!!</font>");
		else
			out.println("<br><font align=center>Records not deleted !!!</font>");
	}
	
	else if(form.equals("viewshow") && action.equals("edit"))
	{
		 
	}



%>

</form>

<%	
}

else
{
	out.println("Unauthenticated Access!!!");
}

%>

<br>
<%
if(flag1)
{
%>
</div>
<%
}
%>
<%@ include file = "footer.jsp" %>
</body>
</html>