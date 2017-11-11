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

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<div class="container">
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
        
	        <%@page import="com.movieticket.model.*" %>
			<%@page import="com.movieticket.controller.*" %>
			<%! User us=null;  %>
  
		    <%
		     us = (User) session.getAttribute("user");
		   	 if(us != null){
		   		String name= us.getname();
		   		if(us.gettype().equals("customer")){   			
	   		%>   
        
            <div id="defaultmenu" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="custhome.jsp">Home</a></li>	
                    <li><a href="custbookings.jsp">My Bookings</a></li>
                    <li><a href="custprofile.jsp">My Profile</a></li>
                
					
                </ul><!-- end nav navbar-nav -->
			
			</div><!-- end #navbar-collapse-1 -->
			
            <%}
		   		else if(us.gettype().equals("halladmin")){
            %>
            	
            <%
            	}
		   		else{
		   	%>
		   			<div id="defaultmenu" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="index.jsp">Home</a></li>	
     
                </ul><!-- end nav navbar-nav -->
			
			</div><!-- end #navbar-collapse-1 -->		
		   	<%
		   		}
			}
			%>	
            
            
			</nav><!-- end navbar navbar-default w3_megamenu -->
		</div><!-- end container -->
	</div>
</div>	
