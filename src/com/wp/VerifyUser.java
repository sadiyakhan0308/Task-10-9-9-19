package com.wp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/VerifyUser")
public class VerifyUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String sql = "SELECT  username  FROM users WHERE userid = ? AND password =?";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		String utype = request.getParameter("utype");
		try {
			if (utype.equals("owner")) {
				if (userid.equals("admin") && password.equals("indore")) {
					response.sendRedirect("adminpage.jsp");
				} else {
					out.println("INVALID CREDENTIALS FOR ADMIN");
				}

			} else {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/booksdata", "root", "root");

				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, userid);
				ps.setString(2, password);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {

					// storing the userid in session
					// step-1 (fetch the session object)
					HttpSession session = request.getSession();
					// step-2 (write the data in session)
					session.setAttribute("user", userid);
                // System.out.println(session.getAttribute(userid));
                 
					// whether user want to save the password
					String choice = request.getParameter("save");
					if (choice != null) {

						Cookie c1 = new Cookie("id", userid);
						Cookie c2 = new Cookie("pw", password);

						c1.setMaxAge(60 * 60 * 24 * 7);
						c2.setMaxAge(60 * 60 * 24 * 7);

						response.addCookie(c1);
						response.addCookie(c2);

					}

					// response.sendRedirect("buyerpage.jsp");
					RequestDispatcher rd = request.getRequestDispatcher("buyerpage.jsp");
					rd.forward(request, response);

				} else {
					out.println("INVALID BUYER CREDENTIALS");
				}
				con.close();
			}
		} catch (Exception e) {
			out.println(e);
		}
	}

}