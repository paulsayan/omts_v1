<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<div class="container">
		  <div class="main-content">
		  
			<div class="header">
				<div class="logo">
					<a href="index.jsp"><h1>Online Movie Ticketing System</h1></a>
				</div>
				<div class="search">
					<div class="search2">
					  <form>
						<i class="fa fa-search"></i>
						 <input type="text" value="Search for a movie, play, event, sport or more" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Search for a movie, play, event, sport or more';}"/>
					  </form>
					</div>
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
                    <li class="active"><a href="index.jsp">Home</a></li>	
                    <!-- Mega Menu -->
					<li class="dropdown w3_megamenu-fw"><a href="movies.html" data-toggle="dropdown" class="dropdown-toggle">Movies<b class="caret"></b></a>
                        <ul class="dropdown-menu fullwidth">
                            <li class="w3_megamenu-content">
                                <div class="row">
								<h5 class="movies-page">for movies page - <a href="movies.jsp">Click here</a> </h5>
                                    <div class="col-sm-4">
                                  		<h3 class="title">Now Showing</h3>
										<ul class="mov_list">
						<li>99%</li>
						<li><a href="movie-single.html">Baahubali (Telugu) (U/A)</a></li>
					</ul>
					<ul class="mov_list">
						<li>100%</li>
						<li><a href="movie-single.html">Baahubali (Hindi) (U/A)</a></li>
					</ul>
					<ul class="mov_list">
						<li>98%</li>
						<li><a href="movie-single.html">Baahubali (English) (U/A)</a></li>
					</ul>
					<ul class="mov_list">
						<li>80%</li>
						<li><a href="movie-single.html">Jurassic World (3D Hindi) (U/A)</a></li>
					</ul>
					<ul class="mov_list">
						<li>65%</li>
						<li><a href="movie-single.html">Hamari Adhuri Kahani (U)</a></li>
					</ul>
                                    </div><!-- end col-4 -->
                                    <div class="col-sm-4 movie-dd">
                                  		<h3 class="title">Next Change</h3>
										<p><a href="movie-single.html">ABCD 2 (3D) (4DX)</a><span>... (Tomorrow, 19 Jun)</span></p>
										<p><a href="movie-single.html">ABCD 2 (3D) (4DX)</a><span>... (Tomorrow, 19 Jun)</span></p>
										<p><a href="movie-single.html">ABCD 2 (3D) (4DX)</a><span>... (Tomorrow, 19 Jun)</span></p>
										<p><a href="movie-single.html">ABCD 2 (3D) (4DX)</a><span>... (Tomorrow, 19 Jun)</span></p>
										<p><a href="movie-single.html">ABCD 2 (3D) (4DX)</a><span>... (Tomorrow, 19 Jun)</span></p>
                                    </div><!-- end col-4 -->
                                    <div class="col-sm-4 movie-dd">
                                  		<h3 class="title">Comming Soon</h3>
										<p><a href="movie-single.html">ABCD 2 (3D) (4DX)</a><span>... (Tomorrow, 19 Jun)</span></p>
										<p><a href="movie-single.html">ABCD 2 (3D) (4DX)</a><span>... (Tomorrow, 19 Jun)</span></p>
										<p><a href="movie-single.html">ABCD 2 (3D) (4DX)</a><span>... (Tomorrow, 19 Jun)</span></p>
										<p><a href="movie-single.html">ABCD 2 (3D) (4DX)</a><span>... (Tomorrow, 19 Jun)</span></p>
										<p><a href="movie-single.html">ABCD 2 (3D) (4DX)</a><span>... (Tomorrow, 19 Jun)</span></p>                                    		
                                    </div><!-- end col-4 -->
									<div class="clearfix"></div>
									<div class="menu-featured-movies">
										<h3 class="title">Featured Trailers</h3>
										<div class="col-md-2 menu-featured-movies-img">
											<a href="movie-single.html"><img src="images/mf1.jpg" alt="" /></a>
										</div>
										<div class="col-md-2 menu-featured-movies-img">
											<a href="movie-single.html"><img src="images/mf2.jpg" alt="" /></a>
										</div>
										<div class="col-md-2 menu-featured-movies-img">
											<a href="movie-single.html"><img src="images/mf3.jpg" alt="" /></a>
										</div>
										<div class="col-md-2 menu-featured-movies-img">
											<a href="movie-single.html"><img src="images/mf3.jpg" alt="" /></a>
										</div>
										<div class="col-md-2 menu-featured-movies-img">
											<a href="movie-single.html"><img src="images/mf3.jpg" alt="" /></a>
										</div>
										<div class="col-md-2 menu-featured-movies-img">
											<a href="movie-single.html"><img src="images/mf3.jpg" alt="" /></a>
										</div>
										<div class="clearfix"></div>
									</div>
                                </div><!-- end row -->
                                <hr>
                    
							</li>
                        </ul>
                    </li>
                   
                    <li><a href="cinema.jsp">Cinema</a></li>
                    <li><a href="custbooking.jsp">My Bookings</a></li>
                    <li><a href="custprofile.jsp">My Profile</a></li>
                    <!-- end dropdown w3_megamenu-fw -->
					
                </ul><!-- end nav navbar-nav -->
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#" data-toggle="dropdown" class="dropdown-toggle">Contact Us<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <form id="contact1" action="#" name="contactform" method="post">
                                    <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <input type="text" name="name" id="name1" class="form-control" placeholder="Name"> 
                                        <input type="text" name="email" id="email1" class="form-control" placeholder="Email"> 
                                    </div>
                                    <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <input type="text" name="phone" id="phone1" class="form-control" placeholder="Phone">
                                        <input type="text" name="subject" id="subject1" class="form-control" placeholder="Subject"> 
                                    </div>                 
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                        <textarea class="form-control" name="comments" id="comments1" rows="6" placeholder="Your Message ..."></textarea>
                                    </div>   
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                        <div class="pull-right">
                                            <input type="submit" value="SEND" id="submit1" class="btn btn-primary small">
                                        </div>  
                                    </div>
									<div class="clearfix"></div>  
                                </form>
                            </li>
                        </ul>
					</li>
				</ul><!-- end nav navbar-nav navbar-right -->
			</div><!-- end #navbar-collapse-1 -->
            <%}
		   		else if(us.gettype().equals("halladmin")){
            %>
            	<div id="defaultmenu" class="navbar-collapse collapse">
                 <ul class="nav navbar-nav">
                    <li class="active"><a href="index.jsp">Home</a></li>
                 </ul>
                </div>
            <%
            	}
		   		else{
		   	%>
		   			<div id="defaultmenu" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="index.jsp">Home</a></li>	
                    <!-- Mega Menu -->
					<li class="dropdown w3_megamenu-fw"><a href="movies.html" data-toggle="dropdown" class="dropdown-toggle">Movies<b class="caret"></b></a>
                        <ul class="dropdown-menu fullwidth">
                            <li class="w3_megamenu-content">
                                <div class="row">
								<h5 class="movies-page">for movies page - <a href="movies.jsp">Click here</a> </h5>
                                    <div class="col-sm-4">
                                  		<h3 class="title">Now Showing</h3>
										<ul class="mov_list">
						<li>99%</li>
						<li><a href="movie-single.html">Baahubali (Telugu) (U/A)</a></li>
					</ul>
					<ul class="mov_list">
						<li>100%</li>
						<li><a href="movie-single.html">Baahubali (Hindi) (U/A)</a></li>
					</ul>
					<ul class="mov_list">
						<li>98%</li>
						<li><a href="movie-single.html">Baahubali (English) (U/A)</a></li>
					</ul>
					<ul class="mov_list">
						<li>80%</li>
						<li><a href="movie-single.html">Jurassic World (3D Hindi) (U/A)</a></li>
					</ul>
					<ul class="mov_list">
						<li>65%</li>
						<li><a href="movie-single.html">Hamari Adhuri Kahani (U)</a></li>
					</ul>
                                    </div><!-- end col-4 -->
                                    <div class="col-sm-4 movie-dd">
                                  		<h3 class="title">Next Change</h3>
										<p><a href="movie-single.html">ABCD 2 (3D) (4DX)</a><span>... (Tomorrow, 19 Jun)</span></p>
										<p><a href="movie-single.html">ABCD 2 (3D) (4DX)</a><span>... (Tomorrow, 19 Jun)</span></p>
										<p><a href="movie-single.html">ABCD 2 (3D) (4DX)</a><span>... (Tomorrow, 19 Jun)</span></p>
										<p><a href="movie-single.html">ABCD 2 (3D) (4DX)</a><span>... (Tomorrow, 19 Jun)</span></p>
										<p><a href="movie-single.html">ABCD 2 (3D) (4DX)</a><span>... (Tomorrow, 19 Jun)</span></p>
                                    </div><!-- end col-4 -->
                                    <div class="col-sm-4 movie-dd">
                                  		<h3 class="title">Comming Soon</h3>
										<p><a href="movie-single.html">ABCD 2 (3D) (4DX)</a><span>... (Tomorrow, 19 Jun)</span></p>
										<p><a href="movie-single.html">ABCD 2 (3D) (4DX)</a><span>... (Tomorrow, 19 Jun)</span></p>
										<p><a href="movie-single.html">ABCD 2 (3D) (4DX)</a><span>... (Tomorrow, 19 Jun)</span></p>
										<p><a href="movie-single.html">ABCD 2 (3D) (4DX)</a><span>... (Tomorrow, 19 Jun)</span></p>
										<p><a href="movie-single.html">ABCD 2 (3D) (4DX)</a><span>... (Tomorrow, 19 Jun)</span></p>                                    		
                                    </div><!-- end col-4 -->
									<div class="clearfix"></div>
									<div class="menu-featured-movies">
										<h3 class="title">Featured Trailers</h3>
										<div class="col-md-2 menu-featured-movies-img">
											<a href="movie-single.html"><img src="images/mf1.jpg" alt="" /></a>
										</div>
										<div class="col-md-2 menu-featured-movies-img">
											<a href="movie-single.html"><img src="images/mf2.jpg" alt="" /></a>
										</div>
										<div class="col-md-2 menu-featured-movies-img">
											<a href="movie-single.html"><img src="images/mf3.jpg" alt="" /></a>
										</div>
										<div class="col-md-2 menu-featured-movies-img">
											<a href="movie-single.html"><img src="images/mf3.jpg" alt="" /></a>
										</div>
										<div class="col-md-2 menu-featured-movies-img">
											<a href="movie-single.html"><img src="images/mf3.jpg" alt="" /></a>
										</div>
										<div class="col-md-2 menu-featured-movies-img">
											<a href="movie-single.html"><img src="images/mf3.jpg" alt="" /></a>
										</div>
										<div class="clearfix"></div>
									</div>
                                </div><!-- end row -->
                                <hr>
                    
							</li>
                        </ul>
                    </li>
                   
                    <li><a href="cinema.jsp">Cinema</a></li>
                    <!-- end dropdown w3_megamenu-fw -->
					
                </ul><!-- end nav navbar-nav -->
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#" data-toggle="dropdown" class="dropdown-toggle">Contact Us<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <form id="contact1" action="#" name="contactform" method="post">
                                    <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <input type="text" name="name" id="name1" class="form-control" placeholder="Name"> 
                                        <input type="text" name="email" id="email1" class="form-control" placeholder="Email"> 
                                    </div>
                                    <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <input type="text" name="phone" id="phone1" class="form-control" placeholder="Phone">
                                        <input type="text" name="subject" id="subject1" class="form-control" placeholder="Subject"> 
                                    </div>                 
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                        <textarea class="form-control" name="comments" id="comments1" rows="6" placeholder="Your Message ..."></textarea>
                                    </div>   
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                        <div class="pull-right">
                                            <input type="submit" value="SEND" id="submit1" class="btn btn-primary small">
                                        </div>  
                                    </div>
									<div class="clearfix"></div>  
                                </form>
                            </li>
                        </ul>
					</li>
				</ul><!-- end nav navbar-nav navbar-right -->
			</div><!-- end #navbar-collapse-1 -->		
		   	<%
		   		}
			}
			%>	
            
            
			</nav><!-- end navbar navbar-default w3_megamenu -->
		</div><!-- end container -->
	</div>
</div>	
