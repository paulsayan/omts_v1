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

import com.movieticket.dao.DAOClass;
import com.movieticket.model.User;

/**
 * Servlet implementation class Edit
 */
@WebServlet("/Edit")
public class Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Edit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session=request.getSession();
		User u = (User) session.getAttribute("user");
		long custID = 0L; long haID = 0L;
		RequestDispatcher rd = null;
		if(u != null){
			//for customer
			if(u.gettype().equals("customer") || u.gettype().equals("halladmin")) {
				try {
					custID = u.getid();
					if(request.getParameterValues("upd")!=null){
						u.setname(request.getParameter("name"));
						u.setmobile(Long.parseLong(request.getParameter("mobile")));
						int r1 = u.updateUser();
						request.setAttribute("nor1", r1);
					}
					if(request.getParameterValues("updPwd") != null) 
					{
						if(request.getParameter("newpwd").toString().equals(request.getParameter("confpwd").toString())==false)
						{
							request.setAttribute("pwdmismatch", 1);
						}
						else if(request.getParameter("oldpwd").toString().equals(u.getpwd())==false)
						{
							
							request.setAttribute("wrongpwd", 1);
						}
						else if(request.getParameter("oldpwd").toString().equals(u.getpwd()))
						{
							u.setpwd(request.getParameter("newpwd"));
							int r2 = u.updateUser();
							request.setAttribute("nor2", r2);
						}
						

					}
					
				}catch(Exception e){	e.printStackTrace();	}
				
				
			}
			//for hall admin
			else if(u.gettype().equals("masteradmin")) {
				try {
					haID = u.getid();
					/*
					 * Add your code
					 */
				}catch(Exception e){	e.printStackTrace();	}
				//rd=request.getRequestDispatcher("custprofile.jsp");
			}
		}
			rd=request.getRequestDispatcher("custprofile.jsp");
		rd.forward(request, response);
	}

}
