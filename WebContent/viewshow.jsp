<%@ include file = "header.jsp" %>
<body>
<div class="container">
		  <div class="main-content">
			<div class="header">
				<div class="logo">
					<a href="hahome.jsp"><h1>Online Movie Ticketing System</h1> <h3>HALL ADMIN PAGE</h3></a>
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
                    <li><a href="custprofile.jsp">Edit Credentials</a></li>
                    <li><a href="addeditshow.jsp">Add Show</a></li>
                    <li><a href="deleteshow.jsp">Delete Show</a></li>
                    <li><a href="viewshow.jsp">View Show</a></li>
                    <li><a href="confirmbookings.jsp">Confirm Ticket Bookings</a></li>
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
String showid="";
String hallid="";
String movieid="";
String start_time="";
String lang="";



ArrayList<String> alist=new ArrayList<String>();
ArrayList<Show> slist=null;
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
	
	
	
	%>
	<form action="viewshow.jsp"method=post align=center>
	Choose Show by Movie: <select name="movie">
<% 	alist=m.getMovies();
	out.println("<option>Select</option>");
  	for(Object obj:alist)
  	{
  		String name=(String)obj;
  	
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
  	for(Show obj:slist)
 	 {
  		String date=(String)obj.getStartTime();
  		
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
%>
	</select>
Choose Show by Language:<select name="language">
<option selected>Select</option>
<option>English</option>
<option>Bengali</option>
<option>Hindi</option>
</select>
<input type=hidden name=form value=viewshow>
<input type=submit name=action value=show>
<input type=submit name=action value=edit>
<input type=submit name=action value=delete>
</form>
<table style="width:80%" align=center border=4>

	<% form=request.getParameter("form");
	action=request.getParameter("action");
	if(form==null)
	{
%>
<tr>
<th>Check</th>
<th>Show Id</th>
<th>Movie Name</th>
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
		 start_time=ob.getStartTime();
		 lang=ob.getLanguage();
		 
		 //if(getmovid==movieid || getlang==lang || gettime==start_time)
		 {%>
		    
			<tr>
			<td><input id="checkBox" type="checkbox"></td>
			<td><%=showid %></td>
			<td><%=m.getMovieByMovieId(ob.getMovieId()) %></td>
			<td><%=start_time%></td>
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
				<tr>
				<th>Check</th>
				<th>Show Id</th>
				<th>Movie Name</th>
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
		 for(Show ob:slist)
		 {
			
			 
			 showid=Long.toString(ob.getShowId());
			 movieid=Long.toString(ob.getMovieId());
			 start_time=ob.getStartTime();
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
				<td><input type="checkbox" name="check<%=ob.getShowId()%>"></td>
				<td><%=showid %></td>
				<td><%=m.getMovieByMovieId(ob.getMovieId()) %></td>
				<td><%=start_time%></td>
				<td><%=lang %></td>
				</tr>
				
			 <% 
			 }
			  
		 }
		 %>
		</table>
		
	<% 
	}%>
	<%-- else if(form.equals("viewshow") && action.equals("delete"))
	{
		ArrayList<String> list=new ArrayList<String>();
		for(Show ob:slist)
			list.add(request.getParameter("check"+ob.getShowId()));
		
		
		s=new Show();
		for(String str:list)
			s.deleteShowByShowId(Long.parseLong(str));
		
		out.println("<font align=center>Records deleted !!!</font>");
	} --%>
<% }

else
{
	out.println("Unauthenticated Access!!!");
}

%>

<br>

<%@ include file = "footer.jsp" %>
</body>
</html>