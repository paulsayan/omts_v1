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
<%!
Hall h=null;
User usr=null;

Movie m=null;
String form="";
String action="";
boolean nor=false;
%>
<% 
u=(User)session.getAttribute("user");
if(u!=null)
{
	form=request.getParameter("form");
	action=request.getParameter("action");
	if(form==null)
	{
%>

<center>
<h2>Add Movie</h2>
<br>

 <form action=AddEditMovie method="post" enctype="multipart/form-data">
 
 <table>
  <tr><td>Movie Name:</td><td><input type="text" name="movie_name"></td></tr>
  <tr><td>Genre:</td><td><input type="text" name="genre"></td></tr>
  <tr><td>Release Date:</td><td><input type="text" name="reldate" placeholder="DD-MM-YYYY"></td></tr>
  
  <tr><td>Director:</td><td><input type="text" name="director"></td></tr>
  <tr><td>Cast:</td><td><input type="text" name="cast"></td></tr>
  <tr><td>Duration (in mins):</td><td><input type="text" name="duration"></td></tr>
  <tr><td>Poster:</td><td><input type="file" name="poster"></td></tr>
  
  </table>
  
  <input type=hidden name="form" value="addmovie"><br>
  <input type="submit" value="Submit">
  
  
</form>
 </center>
<%
}
else if(form.equals("addmovie"))
{
		m=new Movie();
		m.setMovieName(request.getParameter("movie_name"));
		m.setGenre(request.getParameter("genre"));
		m.setRelDate(request.getParameter("reldate"));
		m.setDirector(request.getParameter("director"));
		m.setCast(request.getParameter("cast"));
		m.setDuration(Integer.parseInt(request.getParameter("duration")));
		
}
	
else if(form.equals("editdelete") && action.equals("edit"))
{
	
}
else if(form.equals("editdelete") && action.equals("delete"))
{
	m=new Movie();
	nor=m.deleteMovie(Long.parseLong(request.getParameter("movie")));
	if(nor)
	{
		%>
		<h3>Movie Deleted Successfully!!!</h3>
		<%
	}
	else
	{
		%>
		<h3>Movie Deletion Unsuccessful!!!</h3>
		<%
		
	}
}
else if(form.equals("updatemovie"))
{
	
}
	
}
else
{
	out.println("Unauthenticated Access!!! ");
	out.println("Please Login again using correct credentials <a href=\"index.jsp\">here</a><br>");
}
%>
<%@ include file = "footer.jsp" %>
</body>
</html>