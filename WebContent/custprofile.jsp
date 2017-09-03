<%@ include file = "header.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file = "menubar.jsp" %>

<script >
window.onload = function() {
    document.getElementById("name").disabled = true;
    document.getElementById("email").disabled = true;
    document.getElementById("mobile").disabled = true;
    document.getElementById("pwd").disabled = true;
}
</script>
</head>
<body>
<%! String name = null; String email = null; String mobile = null; String password = null; %>
<%if(u != null){
	name = u.getname();
	email = u.getemail();
	Long mobObj = u.getmobile();
	mobile = mobObj.toString();
	password = u.getpwd();
} 
else{
	RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
	 rd.forward(request,response);
}%>
	


<div class="container">
	<div class="main-content">
	<br><br>
	
		 <form role="form" class="form-horizontal" action="Edit" method="post">
                                <div class="form-group">
                                    <label for="name" class="col-sm-2 control-label">
                                        Name</label>
                                    <div class="col-sm-5">
                                        <input type="text" class="form-control" id="name" name="name" placeholder="Name" value="<%=name%>" />
                                    </div>
                                </div> 
                                 <div class="form-group">
                                    <label for="email" class="col-sm-2 control-label">
                                        Email</label>
                                    <div class="col-sm-5">
                                        <input type="email" class="form-control" id="email" name="email" placeholder="Email" value="<%=email%>" />
                                    </div>
                                </div>  
                                <div class="form-group">
                                    <label for="mobile" class="col-sm-2 control-label">
                                        Mobile</label>
                                    <div class="col-sm-5">
                                        <input type="text" class="form-control" id="mobile" name="mobile" placeholder="Mobile" value="<%=mobile%>" />
                                    </div>
                                </div>
                           
                                <div class="row">
                                    <div class="col-sm-2">
                                    </div>
                                    </div>
                                   
		<button  type="button" class="btn btn-primary btn-sm" onclick="EFunc()" style="margin-left: 200px;">Edit Credentials</button>
		<script>
			function EFunc() {
				document.getElementById("name").disabled = false;
				document.getElementById("mobile").disabled = false;
				
			}
		</script>
		<button type="submit" value="Update" name="upd" class="btn btn-primary btn-sm">Update</button>
		
		<button  type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target=".myModal">Change Password</button>
		<%
		if(request.getAttribute("nor1")!=null){
			
			out.println("<br><h3 align=center><font color=blue>Record Updated !!</font></h3>");
		}
		
			
		%>
		</form>
		<div class="modal fade myModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
		    aria-hidden="true">
		    <div class="modal-dialog modal-lg">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		                    &times;</button>
		                <h4 class="modal-title" id="myModalLabel">
		                    Change your password</h4>
		                    
		       <div class="modal-body">
                <div class="row">
                    <div class="col-md-20" >
                      <form role="form" class="form-horizontal" action=Edit method="post">
                                <div class="form-group">
                                    <label for="oldpwd" class="col-sm-3 control-label">
                                        Old Password</label>
                                    <div class="col-sm-5">
                                        <input type="password" name="oldpwd" class="form-control" id="oldpwd" placeholder="Enter Old Password" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="newpwd1" class="col-sm-3 control-label">
                                       New Password</label>
                                        <div class="col-sm-5">
                                        	<input type="password" name="newpwd"class="form-control" id="newpwd" placeholder="Enter New Password" />
                                    	</div>
                            	</div>
                            <div class="form-group">
                                    <label for="newpwd1" class="col-sm-3 control-label">
                                       Confirm New Password</label>
                                        <div class="col-sm-5">
                                        <input type="password" name="confpwd"class="form-control" id="confpwd" placeholder="Confirm New Password"/>
                                    </div>
                            </div>
                            
                            
                            <button type="submit" value="changepwd" name="updPwd" class="btn btn-primary btn-sm" >Change Password</button>
                            
							
							
								  
					</form>
					</div>
					</div>
					</div>	
						
            </div>
		</div>
		</div>
		</div>
		<%if(request.getAttribute("nor2")!=null)
				out.println("<br><h3 align=center><font color=blue>Password Updated !!</font></h3>");
									
		if(request.getAttribute("wrongpwd")!=null)
			out.println("<br><h3 align=center><font color=blue>Wrong Old Password !!</font></h3>"); 
		
		if(request.getAttribute("pwdmismatch")!=null)
			out.println("<br><h3 align=center><font color=blue>Passwords Mismatch !!!</font></h3><br><h4 align=center><font color=blue>Please re-enter passwords carefully!!!</font></h4>"); 
							%>
		
		</div>
		</div>
		
		

</body>
<%@ include file = "footer.jsp" %>
</html>