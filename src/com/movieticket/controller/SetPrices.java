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
