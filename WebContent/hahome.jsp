<%@ include file = "header.jsp" %>
<div class="container">
		  <div class="main-content">
			<div class="header">
				<div class="logo">
					<a href="hahome.jsp"><h1>Online Movie Ticketing System</h1> <h3>HALL ADMIN PAGE</h3></a>
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
                    <li><a href="custprofile.jsp">Edit Credentials</a></li>
                    <li><a href="addeditshow.jsp">Add Show</a></li>
                    <li><a href="deleteshow.jsp">Delete Show</a></li>
                    <li><a href="viewshow.jsp">View Show</a></li>
                    <li><a href="confirmbookings.jsp">Confirm Ticket Bookings</a></li>
                    </ul>
                    </div>
                    </nav>
   		<br><br><br><br><br><br><br><br>   		
</div>
</div>
<%@ include file = "footer.jsp" %>