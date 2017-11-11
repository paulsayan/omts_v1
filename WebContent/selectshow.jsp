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

<%@ include file = "header.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ include file = "menubar.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.movieticket.model.*" %>
<%@ page import="java.io.*" %>

<%!
Movie mov=null;
long movieid;
String showdate=null;
String poster=null;
Show sh=null;
ArrayList<Show> shows=null;

HashSet<Hall> halls=null;
HashSet<Long> hallids=null;

%>

<%
movieid=Long.parseLong(request.getParameter("movieid"));
showdate=request.getParameter("showdate");

mov=new Movie();
mov=mov.getMovieIdById(movieid);

sh=new Show();
shows=sh.getShowsAvailableForBooking(movieid, showdate);

halls=new HashSet<Hall>();
hallids=new HashSet<Long>();

System.out.println(shows.size());


for(Show s:shows)
{
	halls.add(s.getHallByShowId(s.getShowId()));
}

for(Hall h:halls)
{
	hallids.add(h.getHallId());
}

poster="posters"+File.separator+mov.getPoster();

%>
<div class="container">

<div class="header">
</div>

<div class="main-content">
<br>
	<div class="now-showing-list">
		<div class="col-md-4 movies-by-category movie-booking">

			<div class="movie-ticket-book">
					
					<div class="clearfix"></div>
					<h3><%=mov.getMovieName() %></h3>
					<br>
					<img src="<%=poster %>" width=240 height=320>
					
					<div class="bahubali-details">
					
						<h4>Release Date:</h4>
						<p><%=mov.getRelDate().split(" ")[0] %></p>
						<h4>Duration:</h4>
						<p><%=mov.getDuration() %> mins</p>
						
						<h4>Genre:</h4>
						<p><%=mov.getGenre() %></p>
						
						<h4>Director:</h4>
						<p><%=mov.getDirector() %></p>
						<h4>Cast & Crew:</h4>
						<p><%=mov.getCast() %></p>
						
					</div>
				</div>
			</div>
			
			
		<div class="col-md-8 movies-dates" data-toggle="modal" data-target="#mymodal">	
		
		<%
			for(Long hid : hallids)
			{
		
				Hall h=new Hall();
				h=h.viewHalls(hid);
				
		%>
			<div class="movie-date-selection" data-toggle="modal" data-target="#mymodal">
				<ul>
				
				
					<li class="location">
						<a href="pic-a-movie.html"><i class="fa fa-map-marker"></i>Theater Name: <%=h.getHallName() %></a>
					</li>
					
					<%
						for(Show s:shows)
						{
							if(s.getHallId()==hid)
							{
								String showtime = s.getShowTimeByShowId(s.getShowId());
								showtime=showtime.split(" ")[1];
								
								String temp[]=showtime.split(":");
								showtime=temp[0]+":"+temp[1];
								
								long sid=s.getShowId();
								//System.out.println("SID:"+sid);
								
					%>
					
					<li class="time">
						<a href="seatbooking.jsp?showid=<%=sid %>"><%=showtime %></a>
						
					</li>
					
					<%
					
					
							}
						}
					%>
					
				</ul>
			</div>
			
		<%
			}
		
		%>
			
			
		</div>
		<div class="clearfix"></div>
	</div>
	
	
</div>
</div>



	<div class="footer-top-grid">
	<div class="clearfix"></div>
</div>
		
		
			
	<div class="clearfix"></div>			



<div class="footer-top-strip">
</div>



</body>
<%@ include file = "footer.jsp" %>
</html>