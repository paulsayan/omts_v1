package com.movieticket.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.movieticket.model.*;

/**
 * Servlet implementation class SetPrices
 */
@WebServlet("/SetPrices")
public class SetPrices extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetPrices() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		
		final String[] rowidset= {"A","B","C","D","E","F","G","H","J","K","L","N","P","Q","R"};
		String rowclass=null;
		Long showid=Long.parseLong(request.getParameter("showid"));
		double normalprice=Double.parseDouble(request.getParameter("normalprice"));
		double executiveprice=Double.parseDouble(request.getParameter("executiveprice"));
		double premiumprice=Double.parseDouble(request.getParameter("premiumprice"));
		String action=request.getParameter("action");
		int nor=0; 
		
		rowclass="Normal";
		for(int i=0;i<5;i++)
		{
			Price p=new Price();
			p.setShowId(showid);
			p.setRowId(rowidset[i]);
			p.setRowClass(rowclass);
			p.setRowPrice(normalprice);
			if(action.equals("setprice"))
				nor+=p.addPrice();
			else if(action.equals("updateprice"))
				nor+=p.updatePrice();
		}
		
		rowclass="Executive";
		for(int i=5;i<12;i++)
		{
			Price p=new Price();
			p.setShowId(showid);
			p.setRowId(rowidset[i]);
			p.setRowClass(rowclass);
			p.setRowPrice(executiveprice);
			if(action.equals("setprice"))
				nor+=p.addPrice();
			else if(action.equals("updateprice"))
				nor+=p.updatePrice();
	
		}
		
		rowclass="Premium";
		for(int i=12;i<15;i++)
		{
			Price p=new Price();
			p.setShowId(showid);
			p.setRowId(rowidset[i]);
			p.setRowClass(rowclass);
			p.setRowPrice(premiumprice);
			if(action.equals("setprice"))
				nor+=p.addPrice();
			else if(action.equals("updateprice"))
				nor+=p.updatePrice();
	
		}
		
		
		if(nor==15)
		{
			pw.println("Prices Set Successfully!!!");
		}
		else
		{
			pw.println("Unsuccessful!!!");
		}
		
		pw.println("<br><br><a href=\"hahome.jsp\">Go Back to Home</a>");
	}

}
