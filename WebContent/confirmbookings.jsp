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

<%@ include file = "header.jsp" %>
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
			
			<center>
			
			<%!
			String bid=null;
			int nor;
			Ticket t=null;
			%>
			
			<%
			bid=request.getParameter("bid");
			t=new Ticket();
			
			if(bid==null)
			{
				
			
			%>
			
			<form action=confirmbookings.jsp method=post>
			
			<input type=text name=bid placeholder="Booking Id">
			
			<input type=submit value="Confirm">
			
			
			</form>
			
			<%
			}
			else
			{
				t.setBookingId(bid);
				t.sethaId(u.getid());
				
				nor=t.confirmTicket();
				if(nor!=0)
				{
				%>
					<h3>Booking Confirmed!!!</h3>
				<%
				}
				else
				{
				
				%>
					<h3>Invalid Booking Id or Booking already confirmed!!!</h3>
				
				<%
				}
				
			
			}
			%>
			
			</center>
			
			</div>
				


<%@ include file = "footer.jsp" %>
