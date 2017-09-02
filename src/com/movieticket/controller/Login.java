package com.movieticket.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.movieticket.model.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Unauthenticated Access!!!");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		
		String pwd=request.getParameter("pwd");
		
		User u=new User();
		u.setemail(request.getParameter("email"));
		
		u=u.getUserData(u.getemail());
		
		if(u==null)
		{
			out.println("User doesn't exist!!!<br>");
			out.println("Please Login again using correct credentials <a href=\"index.jsp\">here</a><br>");
		}
		else
		{
			if(pwd.equals(u.getpwd()))
			{
				HttpSession session=request.getSession();
		  		session.setAttribute("user",u);
		  		RequestDispatcher rd=null;
		  		
		  		if(u.gettype().equals("customer"))
		  			rd=request.getRequestDispatcher("custhome.jsp");
		  		else if(u.gettype().equals("halladmin"))
		  			rd=request.getRequestDispatcher("hahome.jsp");
		  		else if(u.gettype().equals("masteradmin"))
		  			rd=request.getRequestDispatcher("mahome.jsp");
		  		
		  		rd.forward(request,response);
			}
			else
			{
				out.println("Password Mismatch!!!<br>");
				out.println("Please Login again using correct credentials <a href=\"index.jsp\">here</a><br>");
			}
		}
		
		out.close();
	}

}
