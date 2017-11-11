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

<style>
th, td {
    padding: 15px;
    text-align: left;
}

table {
	border-spacing: 25px;
}

</style>

</head>
<body>

<%@ include file = "menubar.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.movieticket.model.*" %>
<%@ page import="java.io.*" %>

<%!
HashSet<String> bids=null;
Ticket t=null;
%>

<%
t=new Ticket();

bids=t.getBookingIdsByCustId(u.getid());

%>
<br><br>
<center>



<table border=3 ">
<thead>

<th>Booking id</th>
<th>Booking Date</th>
<th>Movie Name</th>
<th>Hall Name</th>

</thead>

<%

	for(String bid:bids)
	{
		
		ArrayList<Ticket> tk=t.getTicketsByBookingId(bid);
		t=tk.get(0);
		Hall h=new Hall();
		String hallname=h.viewHalls(t.getHallId()).getHallName();
		
	%>
		<tr>
		<td><a href="bookingsummary.jsp?bookingid=<%=bid %>"><%=bid %></a></td>
		<td><%=t.getBookingDate().split(" ")[0] %></td>
		<td><%=t.getMovieName() %></td>
		<td><%=hallname %></td>
		</tr>
	<%
	
	}
	%>
</table>



</center>


<div class="footer-top-grid">
<div class="clearfix"></div>			
			</div>
		<div class="footer-top-strip">
			
		</div>


</body>
<%@ include file = "footer.jsp" %>
</html>