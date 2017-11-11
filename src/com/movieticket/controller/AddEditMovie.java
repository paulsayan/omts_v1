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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.movieticket.model.*;

/**
 * Servlet implementation class AddEditMovie
 */
@WebServlet("/AddEditMovie")

@MultipartConfig(fileSizeThreshold=1024*1024*2, 
maxFileSize=1024*1024*10, 
maxRequestSize=1024*1024*50)

public class AddEditMovie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEditMovie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		processRequest(request,response);
		
	}
	
	private String getFileName(final Part part) {
	    final String partHeader = part.getHeader("content-disposition");
	    
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(
	                    content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        response.setContentType("text/html;charset=UTF-8");
	        try (PrintWriter out1 = response.getWriter()) {
	            HttpSession session=request.getSession();
	            
	            Movie m=new Movie();
	    		m.setMovieName(request.getParameter("movie_name"));
	    		m.setGenre(request.getParameter("genre"));
	    		m.setRelDate(request.getParameter("reldate"));
	    		m.setDirector(request.getParameter("director"));
	    		m.setCast(request.getParameter("cast"));
	    		m.setDuration(Integer.parseInt(request.getParameter("duration")));
	    		
	            //String name=request.getParameter("unname");
	            
	    		Part filePart = request.getPart("poster");
	            
	          
	          String poster="";
	          String path=this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
	          File gp=new File(path);
	          path=gp.getParentFile().getParentFile().getPath();
	          path=path+File.separator+"posters";
	          System.out.println(path);
	          
	          //String path="/home/sayan/omts/omtsjee_v1/WebContent/posters";
	          
	          File file=new File(path);
	          file.mkdir();
	          String fileName = getFileName(filePart);
	          
	          OutputStream out = null;
	          
	            InputStream filecontent = null;
	            
	            PrintWriter writer = response.getWriter();
	            try {
	        out = new FileOutputStream(new File(path + File.separator
	                + fileName));
	        
	        filecontent = filePart.getInputStream();
	     
	 
	        int read = 0;
	        final byte[] bytes = new byte[1024];
	 
	        while ((read = filecontent.read(bytes)) != -1) {
	            out.write(bytes, 0, read);
	           
	            poster=path+"/"+fileName;
	            
	            
	        }
	        
	       
	        m.setPoster(fileName);
	        boolean nor=m.addMovie();
	        if(!nor)
	        {
	        	out1.println ("<html><body>Database Error!!!</body></html>");
	        	File f=new File(path + File.separator + fileName);
	        	boolean filedel=f.delete();
	        	//System.out.println("File Delete:"+ filedel);
	        }
	       
	     }
	     catch(Exception e)
	     {
	         
	     }
	     out1.println ("<html><body>Added Successfully!!!</body></html>");
	}

	
	
}
}
