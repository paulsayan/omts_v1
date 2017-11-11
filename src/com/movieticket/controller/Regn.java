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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movieticket.model.User;

/**
 * Servlet implementation class Regn
 */
@WebServlet("/Regn")
public class Regn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Regn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Access Denied!!!");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		
		User u=new User();
		u.setemail(request.getParameter("email"));
		u.setname(request.getParameter("name"));
		u.setpwd(request.getParameter("pwd"));
		u.setmobile(Long.parseLong(request.getParameter("mobile")));
		u.settype("customer");
		
		int r=u.addUser();
		
		if(r==0)
		{
			out.println("Registration Successful.<br>");
			out.println("Name : "+u.getname()+"<br>");
			out.println("Email : "+u.getemail()+"<br>");
			out.println("Please Login <a href=\"index.jsp\">here</a><br>");
		}
		else if(r==1)
		{
			out.println("Check the email id you have provided, there is already an account registered with that email id.");
			out.println("Please Register <a href=\"index.jsp\">here</a><br>");
		}
		else
		{
			out.println("Sorry, We are currently facing some trouble registering you!<br>");
			out.println("Please Register <a href=\"index.jsp\">here</a><br>");
		}
		
	}

}
