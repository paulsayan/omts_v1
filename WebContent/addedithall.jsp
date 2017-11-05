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
int r;
boolean r2=false;
Long hallid;
String hallname="";
String hallloc="";
String halladdr="";
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
<h2>Add Hall</h2>
<br>
 <form action=addedithall.jsp method="post">
  Hall Name:<input type="text" name="hall_name"><br>
  Location:<input type="text" name="loc"><br>
  Address:<input type="text" name="addr"><br>
  Admin Details:-<br>
  Name:<input type="text" name="name"><br>
  Email:<input type="text" name="email"><br>
  Mobile:<input type="text" name="mobile"><br>
  Password:<input type="password" name="pwd"><br>
  <input type=hidden name="type" value="halladmin"><br>
  <input type=hidden name="form" value="createhall"><br>
  <input type="submit" value="Submit">
</form>
</center>
<%
	}
	else if(form.equals("createhall"))
	{
		usr=new User();
		h=new Hall();
		r=2;
		
		usr.setemail(request.getParameter("email"));
		usr.setname(request.getParameter("name"));
		usr.setmobile(Long.parseLong(request.getParameter("mobile")));
		usr.setpwd(request.getParameter("pwd"));
		usr.settype("halladmin");
		
		r=usr.addUser();
		usr=usr.getUserData(usr.getemail());
		
		h.setHallName(request.getParameter("hall_name"));
		h.setLoc(request.getParameter("loc"));
		h.setAddr(request.getParameter("addr"));
		h.setAdminId(usr.getid());
		
		r2=h.addHall();
		h=h.getHallByAdminId(usr.getid());
		//hallname=h.getHallName();
		
		if(r==0 && r2==true)
		{
		%>
	<center><h3>Hall Created Successfully.</h3></center>
	<% 
		}
		else
		{
	%>
	<center><h3>Hall Creation Unsuccessful!!!</h3></center>
	<% 
		}
	%>
	<br>
	
		<%
		
	}
	
else if(form.equals("editdelete") && action.equals("edit"))
{
	h=new Hall();
	h=h.viewHalls(Long.parseLong(request.getParameter("hall")));
	hallid=h.getHallId();
	hallname=h.getHallName();
	hallloc=h.getLoc();
	halladdr=h.getAddr();
	%>
	<center>
	<h2>Edit Hall</h2>
	<br>
 <form action=addedithall.jsp method="post">
  <input type=hidden name="hall" value="<%=hallid %>"><br>
  Hall Name:<input type="text" name="hall_name" value="<%=hallname %>"><br>
  Location:<input type="text" name="loc" value="<%=hallloc %>"><br>
  Address:<input type="text" name="addr" value="<%=halladdr %>"><br>
  <input type=hidden name="form" value="updatehall"><br>
  <input type="submit" value="Update">
</form>
</center>
	<%
}
else if(form.equals("editdelete") && action.equals("delete"))
{
	h=new Hall();
	hallid=Long.parseLong(request.getParameter("hall"));
	h=h.viewHalls(hallid);
	hallname=h.getHallName();
	nor=h.deleteHall();
	if(nor)
	{
		%>
		<center><h3>Hall <%=hallid %> Deleted Successfully</h3></center>
		<% 
	}
	else
	{
		%>
		<center><h3>Hall Deletion Unsuccessful!!!</h3></center>
		<% 
	}
}
else if(form.equals("updatehall"))
{
	h=new Hall();
	hallid=Long.parseLong(request.getParameter("hall"));
	h=h.viewHalls(hallid);
	h.setHallName(request.getParameter("hall_name"));
	h.setLoc(request.getParameter("loc"));
	h.setAddr(request.getParameter("addr"));
	
	nor=h.updateHall();
	if(nor)
	{
		%>
		<center><h3>Hall <%=hallid %> Updated Successfully</h3></center>
		<% 
	}
	else
	{
		%>
		<center><h3>Hall Updation Unsuccessful!!!</h3></center>
		<% 
	}
}
	
}
else
{
	out.println("Unauthenticated Access!!!");
}
%>
<%@ include file = "footer.jsp" %>
</body>
</html>