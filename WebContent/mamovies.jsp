<%@ include file = "header.jsp" %>
<body>
<div class="bootstrap_container">
            <nav class="navbar navbar-default w3_megamenu" role="navigation">
                <div class="navbar-header">
          			<button type="button" data-toggle="collapse" data-target="#defaultmenu" class="navbar-toggle"><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button><a href="hahome.jsp" class="navbar-brand"><i class="fa fa-home"></i></a>
				</div><!-- end navbar-header -->
				<div id="defaultmenu" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><a href="mahome.jsp">Home</a></li>
                    <li><a href="mahalls.jsp">View Halls</a></li>
                    <li><a href="addedithall.jsp">Add Hall</a></li>
                    <li><a href="mamovies.jsp">View Movies</a></li>
                    <li><a href="addeditmovie.jsp">Add Movie</a></li>
                    
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
					
				</ul><!-- end nav navbar-nav navbar-right -->
                    </div>
                    </nav>
   			
</div>

<%@ page import="com.movieticket.model.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>

<%!
ArrayList<Movie> mlist=null;
ArrayList<String> mlistnames=null;

Movie m=null;
User usr=null;
String form="";

String movie_id="";
String movie_name="";
String genre="";
String director="";
String cast="";
String duration="";
String reldate="";

File f=null;
String path="";
String poster="";
%>
<%
u=(User)session.getAttribute("user");
if(u!=null)
{
m=new Movie();
mlist=m.viewMovies();

path="posters";
%>

<br><br>
<form action="addeditmovie.jsp" method=post align=center>
<center>
Choose Movie by Id: <select name="movie">
</center>
<% 
for(Movie ob:mlist)
{
	movie_id=Long.toString(ob.getMovieId());
	
%>
<option value="<%=movie_id %>"><%=movie_id %></option>
<%
}
%>
</select>
<input type=hidden name=form value=editdelete>

<input type=submit name=action value=delete>
</form>
<table style="width:80%" align=center border=4>
<tr>
<th>Movie Id</th>
<th>Movie Name</th>
<th>Genre</th>
<th>Release Date</th>
<th>Director</th>
<th>Cast</th>
<th>Duration</th>
<th>Poster</th>


</tr>
<%

for(Movie ob:mlist)
{
	movie_id=Long.toString(ob.getMovieId());
	movie_name=ob.getMovieName();
	genre=ob.getGenre();
	director=ob.getDirector();
	reldate=ob.getRelDate().split(" ")[0];
	cast=ob.getCast();
	duration=Integer.toString(ob.getDuration());
	
	poster=path+File.separator+ob.getPoster();
	%>
	<tr>
	<td><%=movie_id %></td>
	<td><%=movie_name %></td>
	<td><%=genre %></td>
	<td><%=reldate %></td>
	<td><%=director %></td>
	<td><%=cast %></td>
	<td><%=duration %></td>
	<td><img src="<%=poster %>" width=200 height=150></td>
	</tr>
	<%
}
%>
</table>
<%
}
else
{
	out.println("Unauthenticated Access!!!");
}
%>
<%@ include file = "footer.jsp" %>
</body>
</html>