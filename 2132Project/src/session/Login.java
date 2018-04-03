package session;

import java.io.IOException;
import java.sql.Connection;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connection.Connect;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private ResultSet rs;
	private String cemail;
	private String cpswd;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uname = request.getParameter("uname");
		String pswd = request.getParameter("pswd");
		System.out.println(uname + " " + pswd);
		Connect db = new Connect();
		db.openConnection();
		if (checkLogin(uname, pswd, db)) {
			response.sendRedirect("home.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uname = request.getParameter("uname");
		String pswd = request.getParameter("pswd");
		Connect db = new Connect();
		db.openConnection();
		if (checkLogin(uname, pswd, db) == true) {
			HttpSession session = request.getSession();
			session.setAttribute("username", uname);
			response.sendRedirect("home.jsp");
		} else {
			response.sendRedirect("Login.jsp");
		}
	}

	public boolean checkLogin(String email, String pswd, Connect conn) {
		Statement st;

		Connection connection = conn.getConnection();
		try {
			st = connection.createStatement();
			rs = st.executeQuery("Select email, pswd from login where email='" + email + "'");
		} catch (Exception e) {
			System.out.println("Cant check login info");
		}
		try {
			if (rs != null) {
				while (rs.next()) {
					cemail = rs.getString("email");
					cpswd = rs.getString("pswd");
				}
			}

		} catch (Exception e) {
			System.out.println("Error creating table " + e);
		}

		if (pswd.equals(cpswd)) {
			return true;
		} else {
			return false;
		}

	}
}
