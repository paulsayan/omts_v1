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

package com.movieticket.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class DBConnect {
	
	public static Connection getOracleConnection()
	{
		Connection con=null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","omts","movie");
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		System.out.println("Connecting : "+con);
		return con;
		
	}
	public static void closeConnection(Connection con)
	{
		if(con!=null) {
			try
			{
				System.out.println("Disconnecting : "+con);
				con.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	public static void closePreparedStatementConnection(PreparedStatement pst)
	{
		if(pst!=null) {
			try
			{
				pst.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	public static void closeResultSetConnection(ResultSet rs)
	{
		if(rs!=null) {
			try
			{
				rs.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	

}
