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
<%@ include file = "menubar.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import="com.movieticket.model.*" %>

<body>
<center>
<%!
String bookingid="";
ArrayList<Ticket> tickets=null;
Ticket t=null;
Hall hall=null;
Show sh=null;

ArrayList<String> seats=null;
ArrayList<Double> prices=null;
double totalprice=0;
String paymentstatus="";
%>

<%
System.out.println("Entering BS");

u=(User)session.getAttribute("user");
System.out.println(">"+1);


bookingid=(String)request.getAttribute("bookingid");
if(bookingid==null)
{
	bookingid=request.getParameter("bookingid");
}

System.out.println(">"+2);

t=new Ticket();
tickets=t.getTicketsByBookingId(bookingid);
System.out.println(">"+3);

t=tickets.get(0);
System.out.println(">"+4);

sh=new Show();
sh=sh.getShowByShowId(t.getShowId());
System.out.println(">"+5);

hall=sh.getHallByShowId(t.getShowId());
System.out.println(">"+6);

seats=new ArrayList<String>();
prices=new ArrayList<Double>();
System.out.println(">"+6.5);

for(Ticket tk:tickets)
{
	seats.add(tk.getSeatId());
	prices.add(tk.getPrice());
	totalprice+=tk.getPrice();
}

System.out.println(">"+7);

if(tickets.get(0).gethaId()==0)
{
	paymentstatus="To be Paid";
}
else
{
	paymentstatus="Paid";
}

System.out.println(">"+8);

%>
<br><br>
<table>
<thead>
<th><h3>Booking Summary</h3></th>

</thead>
<tr><td>&nbsp;&nbsp;</td><td>&nbsp;&nbsp;</td></tr>

<tr><td><b>Booking Id:</b></td><td><%=bookingid %></td></tr>
<tr><td><b>Movie Name:</b></td><td><%=t.getMovieName() %></td></tr>
<tr><td><b>Hall Name:</b></td><td><%=hall.getHallName() %></td></tr>

<tr><td><b>Booking Date:</b></td><td><%=t.getBookingDate().split(" ")[0] %></td></tr>
<tr><td><b>Show Date:</b></td><td><%=t.getShowDate().split(" ")[0] %></td></tr>
<tr><td><b>Show Time:</b></td><td><%=sh.getStartTime().split(" ")[1] %></td></tr>

<tr><td><b>Seats booked:</b></td><td><%=seats.toString().substring(1, seats.toString().length()-1) %></td></tr>
<tr><td><b>Total Price:</b></td><td><%=totalprice %></td></tr>

<tr><td><b>Payment Status:</b></td><td><%=paymentstatus %></td></tr>



</table>


</center>
</body>
<%@ include file = "footer.jsp" %>
</html>