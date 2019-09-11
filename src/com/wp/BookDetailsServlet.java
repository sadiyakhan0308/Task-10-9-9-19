package com.wp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/BookDetailsServlet")
public class BookDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int hitCount=1;
		HttpSession session=request.getSession();
		Map<String,Integer> map=(Map)session.getAttribute("map");
		
		String currentuser=request.getParameter("currentuser");
		//System.out.println(currentuser);
		System.out.println(currentuser);
		if(!map.containsKey(currentuser))
			map.put(currentuser, 1);
		else
		{
			 hitCount= map.get(currentuser);
			hitCount+=1;
			map.replace(currentuser, hitCount);
			
		}
		String code = request.getParameter("code");
		PrintWriter out = response.getWriter();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/booksdata", "root", "root");
			String sql = "SELECT * from books where bcode=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(code));
			ResultSet rs = ps.executeQuery();
			int fakeprice=0;
			int fakeprice2;
			out.println("<html>");
			out.println("<html><body>");
			out.println("<h3>Book-Details</h3>");
			out.println("<hr>");
			while(rs.next())
			{
				if(map.get(currentuser)>2 && map.get(currentuser)<=4 )
				{
					fakeprice=(rs.getInt(5));
					fakeprice+=fakeprice*0.1;
				out.println("<tr><td><center>"+rs.getInt(1)+"</center></td>"
						+ "<td><center>"+rs.getString(2)+"</center></td>"
								+ "<td><center>"+rs.getString(3)+"</center></td>"
								+ "<td><center>"+rs.getString(4)+"</center></td>"
								+ "<td><center>"+fakeprice+"</center></td>"
								+ "</tr>");
				}
				if(map.get(currentuser)>4 )
				{
					fakeprice=(rs.getInt(5));
					fakeprice2=(int)(fakeprice+fakeprice*0.2);
				out.println("<tr><td><center>"+rs.getInt(1)+"</center></td>"
						+ "<td><center>"+rs.getString(2)+"</center></td>"
								+ "<td><center>"+rs.getString(3)+"</center></td>"
								+ "<td><center>"+rs.getString(4)+"</center></td>"
								+ "<td><center>"+fakeprice2+"</center></td>"
								+ "</tr>");
				}
				if(map.get(currentuser)<=2)
				{
					out.println("<tr><td><center>"+rs.getInt(1)+"</center></td>"
							+ "<td><center>"+rs.getString(2)+"</center></td>"
									+ "<td><center>"+rs.getString(3)+"</center></td>"
									+ "<td><center>"+rs.getString(4)+"</center></td>"
									+ "<td><center>"+rs.getInt(5)+"</center></td>"
									+ "</tr>");
				}
			out.println("</table>");
			
			}
				}
				
				
				catch(Exception e){e.printStackTrace();}
				
				}
		
		}
