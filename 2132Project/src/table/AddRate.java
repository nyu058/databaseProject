package table;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connection.Connect;

/**
 * Servlet implementation class AddRate
 */
@WebServlet("/AddRate")
public class AddRate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ResultSet rs;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connect db = new Connect();
		db.openConnection();
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("username");
		System.out.println(email);
		String uid = getUID(email, db);
		createRating(uid, request.getParameter("food"), request.getParameter("mood"), request.getParameter("price"),
				request.getParameter("staff"), request.getParameter("comeagain"), request.getParameter("comment"),
				request.getParameter("rid"), db);
		response.sendRedirect("restaurant.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public String getDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		String date = dtf.format(now);
		return date;
	}

	public void createRating(String uid, String food, String mood, String price, String staff, String again,
			String comment, String id, Connect conn) {
		Statement st;
		Connection connection = conn.getConnection();
		System.out.println("insert into rating values(" + uid + ", '" + getDate() + "', " + price + ", " + food
					+ ", " + mood + ", " + staff + ", '" + comment + "', " + again + ", " + id + ")");
		try {
			st = connection.createStatement();
			st.executeUpdate("insert into rating values(" + uid + ", '" + getDate() + "', " + price + ", " + food
					+ ", " + mood + ", " + staff + ", '" + comment + "', " + again + ", " + id + ")");
		} catch (Exception e) {
			System.out.println("Cant insert rating info");
			e.printStackTrace();
		}
	}

	public String getUID(String email, Connect conn) {
		String id = "";
		Statement st;
		Connection connection = conn.getConnection();
		try {
			st = connection.createStatement();
			rs = st.executeQuery("select userid from rater where email ='" + email+"'");
		} catch (Exception e) {
			System.out.println("Cant get last record");
		}
		try {
			if (rs != null) {
				while (rs.next()) {
					id = rs.getString("userid");
				}
			}

		} catch (Exception e) {
			System.out.println("Error creating table " + e);
			e.printStackTrace();
		}

		return id;
	}

	public String getUName(int id, Connect conn) {
		String name = "";
		Statement st;
		Connection connection = conn.getConnection();
		try {
			st = connection.createStatement();
			rs = st.executeQuery("select name from rater where userid=" + id);
		} catch (Exception e) {
			System.out.println("Cant get name record");
		}
		try {
			if (rs != null) {
				while (rs.next()) {
					name = rs.getString("name");
				}
			}

		} catch (Exception e) {
			System.out.println("Error creating table " + e);
		}

		return name;
	}
}
