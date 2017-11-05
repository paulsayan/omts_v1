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
ArrayList<Show> shows=null;
HashSet<String> showdates=null;

Show sh=null;
long movieid;


SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
Date now = new Date();
String today= sdfDate.format(now);
%>

<%

movieid=Long.parseLong(request.getParameter("movieid"));

sh=new Show();
shows=sh.getShowsAvailableForBooking(today, movieid);

showdates=new HashSet<String>();

for(Show s:shows)
{
	String[] str = s.getStartTime().split("\\.");
	
	System.out.println(str.length);
	
	showdates.add(s.getStartTime().split("\\.")[0]);
}

%>
<br><br>

<center>

<form action=selectshow.jsp method=get>
<table>
<tr>

<td><h3>Show Dates available for booking: &nbsp; &nbsp;</h3></td>

<td>
<select name="showdate">
<%
for(String date:showdates)
{
	
%>
 <option value="<%=date %>"><%=date %></option>
<%

}
%>
</select>
</td>
<td>

<input type=submit value="Proceed">

</td>
</tr>
</table>

<input type=hidden name=movieid value=<%=movieid %>>

</form>
</center>

<div class="container">
	<div class="footer-top-grid">
	<div class="clearfix"></div>
	</div>
		
		
			
	<div class="clearfix"></div>			
</div>


<div class="footer-top-strip">
</div>



</body>
<%@ include file = "footer.jsp" %>
</html>