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
 * Servlet implementation class AddItemRate
 */
@WebServlet("/AddItemRate")
public class AddItemRate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ResultSet rs;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connect db = new Connect();
		db.openConnection();
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("username");
		
		String uid = getUID(email, db);
		createRating(uid, request.getParameter("iid"),  request.getParameter("rating"),  request.getParameter("comment"),db);
		response.sendRedirect("restaurant.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public void createRating(String uid, String iid, String rating, String comment, Connect conn) {
		Statement st;
		Connection connection = conn.getConnection();
		
		try {
			st = connection.createStatement();
			st.executeUpdate("insert into ratingitem values(" + uid + ", '" + getDate() + "', "+iid+"," + rating + ", '" + comment + "')");
		} catch (Exception e) {
			System.out.println("Cant insert rating item info");
			e.printStackTrace();
		}
	}

		
	
	public String getDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		String date = dtf.format(now);
		return date;
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
}
