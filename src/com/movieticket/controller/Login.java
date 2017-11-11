/*

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

*/

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
