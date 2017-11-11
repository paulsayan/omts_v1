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

<%
u = (User) session.getAttribute("user");
if(u!=null)
{
	
	if(u.gettype().equals("customer"))
	{
		response.sendRedirect("custhome.jsp");
	}
	else if(u.gettype().equals("halladmin"))
	{
		response.sendRedirect("hahome.jsp");
	}
	else if(u.gettype().equals("masteradmin"))
	{
		response.sendRedirect("mahome.jsp");
	}
	
}
%>
<%@ include file = "header.jsp" %>

<div class="container">
		 <div class="main-content">
		 
		 <div class="main-content">
			<div class="header">
				<div class="logo">
					<a href="index.jsp"><h1>Online Movie Ticketing System</h1></a>
				</div>
				
				<div class="clearfix"></div>
			</div>
	<div class="bootstrap_container">
            <nav class="navbar navbar-default w3_megamenu" role="navigation">
                <div class="navbar-header">
          			<button type="button" data-toggle="collapse" data-target="#defaultmenu" class="navbar-toggle"><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button><a href="index.jsp" class="navbar-brand"><i class="fa fa-home"></i></a>
				</div><!-- end navbar-header -->
        
            <div id="defaultmenu" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="index.jsp">Home</a></li>	

                    <li><a href="about.jsp">About</a></li>
                 
					<!-- end dropdown w3_megamenu-fw -->
					
                </ul><!-- end nav navbar-nav -->
				
			</div><!-- end #navbar-collapse-1 -->
            
			</nav><!-- end navbar navbar-default w3_megamenu -->
		</div><!-- end container -->

	<div class="main-banner">
	
		</div>
		
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.movieticket.model.*" %>
<%@ page import="java.io.*" %>


<%!
Movie mov=null;
ArrayList<Movie> movies=null;
Show sh=null;
String poster=null;

SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
Date now = new Date();
String today= sdfDate.format(now);
%>


<%
mov=new Movie();
movies=new ArrayList<Movie>();
sh=new Show();
movies=sh.getMoviesAvailableForBooking(today);



%>
<!-- General page of Customer Home-->
<br><br>
<div class="container">
	<div class="footer-top-grid">

	<div class="col-md-12 movies-now-playing">				
			<h3 class="m-head">Latest Movies</h3>
			
<%
for(Movie m:movies)
{
	poster="posters"+File.separator+m.getPoster();
	
%>

		<div class="col-md-3 movie-preview">
			
			<a href="" class="mask">
				
				<img src="<%=poster %>" class="img-responsive zoom-img" alt="" />
				
				<div class="m-movie-title">
				
					<a class="m-movie-link" href="" ><%=m.getMovieName() %></a>
					
					<div class="clearfix"></div>
					<div class="m-r-date">
						
						<p>Director : <%=m.getDirector() %></p>
						<p>Cast : <%=m.getCast() %></p>
						<p>Genre : <%=m.getGenre() %></p>
						
						<a href="selectshowdate.jsp?movieid=<%=m.getMovieId()%>">Book Now</a>
					</div>
					
					 <div class="clearfix"></div>
				</div>
			</a>
		</div>
	
<%

}

%>		
		
		
		
		
		
					
		
		
		
		
		
		
		
		<div class="footer-top-grid">
		
			
			<div class="clearfix"></div>			
			</div>
		
		</div>
		
</div>
</div>
<%@ include file = "footer.jsp" %>
