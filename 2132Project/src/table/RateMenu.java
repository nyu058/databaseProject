package table;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connection.Connect;

/**
 * Servlet implementation class RateMenu
 */
@WebServlet("/RateMenu")
public class RateMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ResultSet rs;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String iid = request.getParameter("iid");
		Connect db = new Connect();
		db.openConnection();

		request.setAttribute("iid", iid);

		RequestDispatcher dispatcher = request.getRequestDispatcher("ratemenu.jsp");
		dispatcher.forward(request, response);
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

	public String getName(String id, Connect conn) {
		String rname = "";
		Statement st;
		Connection connection = conn.getConnection();
		try {
			st = connection.createStatement();
			rs = st.executeQuery("select name from menuitem where itemid=" + id);
		} catch (Exception e) {
			System.out.println("Cant get item name");
			e.printStackTrace();
		}
		try {
			if (rs != null) {
				while (rs.next()) {
					rname = rs.getString("name");
				}
			}

		} catch (Exception e) {
			System.out.println("Error creating table " + e);
		}

		return rname;
	}

	public String getRating(String id, Connect conn) {
		Connection connection = conn.getConnection();
		String name;
		String date;
		String rating;
		String comment;
		String result = "";
		try {
			Statement st = connection.createStatement();
			rs = st.executeQuery("select name, date, rating, comment from ratingitem left join rater on ratingitem.userid=rater.userid where itemid="+id);
		
	}catch (Exception e) {
		System.out.println("Cant get rating item");
		e.printStackTrace();
	}try {
		while (rs.next()) {
			name = rs.getString("name");
			date = rs.getString("date");
			rating = rs.getString("rating");
			comment = rs.getString("comment");
			result += "<tr><tr><td>" + name + "</td><td>" + date + "</td><td>" + rating + "</td><td>" + comment+ "</td></tr>";
		}
	} catch (Exception e) {
		System.out.println("Error creating table " + e);
		e.printStackTrace();
	}
	return result;

}
}
