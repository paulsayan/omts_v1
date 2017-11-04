package com.movieticket.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.movieticket.model.Hall;
import com.movieticket.model.Seat;
import com.movieticket.model.Show;
import com.movieticket.model.Ticket;
import com.movieticket.model.User;

import oracle.sql.TIMESTAMP;

/**
 * Servlet implementation class BookSeats
 */
@WebServlet("/BookSeats")
public class BookSeats extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookSeats() {
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
		
		PrintWriter pw=response.getWriter();
		HttpSession session=request.getSession();
		User u=(User)session.getAttribute("user");
		
		if(u!=null)
		{
			long showid=Long.parseLong(request.getParameter("showid"));
			String showdate=request.getParameter("showdate");
			String seats_str[]=request.getParameterValues("seat");
			
			Seat[] seats=new Seat[seats_str.length];
			Ticket[] tickets=new Ticket[seats_str.length];
			
			//Initialization
			for(int i=0;i<seats_str.length;i++)
			{
				Seat st=new Seat();
				seats[i]=st;
				Ticket t=new Ticket();
				tickets[i]=t;
				
			}
			
			
			String booking_id="";
			Show shw=new Show();
			shw=shw.getShowByShowId(showid);
			Hall hall=shw.getHallByShowId(showid);
			String moviename=shw.getMovieByShowId(showid);
			
			RequestDispatcher rd=request.getRequestDispatcher("bookingsummary.jsp");
			
			
			int nor=0,nor2=0;
			for(int i=0;i<seats_str.length;i++)
			{
				//pw.println(seats_str[i]);
				
				seats[i].setCustId(u.getid());
				seats[i].setShowId(showid);
				seats[i].setShowDate(showdate);
				seats[i].setSeatId(seats_str[i]);
				
				nor+=seats[i].bookSeat();
				//nor+=1;
			}
			
			if(nor==seats.length)
			{
				
				SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMddHHmmss");
				SimpleDateFormat sdfDate2 = new SimpleDateFormat("yyyy-MM-dd");
			    Date now = new Date();
			    String strDate = sdfDate.format(now);
			    String strDate2= sdfDate2.format(now);
			    
				booking_id=strDate+u.getid();
				
				for(int i=0;i<seats.length;i++)
				{
					
					tickets[i].setBookingId(booking_id);
					tickets[i].setSeatId(seats[i].getSeatId());
					tickets[i].setCustId(u.getid());
					tickets[i].setHallId(hall.getHallId());
					tickets[i].setShowId(showid);
					tickets[i].setBookingDate(strDate2);
					tickets[i].setShowDate(showdate);
					tickets[i].setMovieName(moviename);
					tickets[i].setPrice(seats[i].getPrice());
					tickets[i].sethaId(0);
					
					nor2+=tickets[i].generateTicket();
					//nor2+=1;
				}
				
				if(nor2==tickets.length)
				{
					request.setAttribute("bookingid", booking_id);
					rd.forward(request, response);
					//pw.println("Booking Successful!!! Id : "+booking_id);
				}
				else
				{
					pw.println("Booking Unsuccessful!!! Ticket Generation Problem");
				}
				
			}
			else
			{
				pw.println("Booking Unsuccessful!!!");
			}
			
			
			
		}
		else
		{
			pw.println("Unauthorized Access!!!");
		}
		
	}

}
