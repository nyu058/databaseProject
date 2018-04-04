package table;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connection.Connect;

@WebServlet("/Register")
public class Register extends HttpServlet {
	private ResultSet rs;
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String uname = request.getParameter("username");
		String pswd = request.getParameter("pswd");
		String type = request.getParameter("type");

		Connect db = new Connect();
		db.openConnection();

		createUser(email, uname, type, db);
		createLogin(email, pswd, db);

		response.sendRedirect("RegisterSuccess.jsp");
	}

	public void createUser(String email, String uname, String type, Connect conn) {
		Statement st;
		Connection connection = conn.getConnection();
		try {
			st = connection.createStatement();
			st.executeUpdate("Insert into rater values(" + getID(conn) + ", '" + email + "', '" + uname + "', '"
					+ getDate() + "', '" + type + "')");

		} catch (Exception e) {
			System.out.println("Cant insert user info");
		}
	}

	public void createLogin(String email, String pswd, Connect conn) {
		Statement st;
		Connection connection = conn.getConnection();
		int id = getID(conn) - 1;
		try {
			st = connection.createStatement();
			st.executeUpdate("Insert into login values(" + id + ", '" + email + "', '" + pswd + "')");
		} catch (Exception e) {
			System.out.println("Cant insert login info");
		}

	}

	public int getID(Connect conn) {
		int id = 0;
		Statement st;
		Connection connection = conn.getConnection();
		try {
			st = connection.createStatement();
			rs = st.executeQuery("select userid from rater  order by  userid desc limit 1");
		} catch (Exception e) {
			System.out.println("Cant get last record");
		}
		try {
			if (rs != null) {
				while (rs.next()) {
					id = rs.getInt("userid");
				}
			}

		} catch (Exception e) {
			System.out.println("Error creating table " + e);
		}
		id++;
		return id;
	}

	public String getDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		String date = dtf.format(now);
		return date;
	}

}
