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
 * Servlet implementation class CreateRestaurant
 */
@WebServlet("/RestaurantDetail")
public class RestaurantDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ResultSet rs;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		Connect db = new Connect();
		db.openConnection();

		request.setAttribute("rname", getName(id, db));
		request.setAttribute("type", getType(id, db));
		RequestDispatcher dispatcher = request.getRequestDispatcher("restaurantDetail.jsp");
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
			rs = st.executeQuery("select name from restaurant where restaurantid=" + id);
		} catch (Exception e) {
			System.out.println("Cant get restaurant name");
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

	public String getType(String id, Connect conn) {
		String type = "";
		Statement st;
		Connection connection = conn.getConnection();
		try {
			st = connection.createStatement();
			rs = st.executeQuery("select type from restaurant where restaurantid=" + id);
		} catch (Exception e) {
			System.out.println("Cant get restaurant type");
			e.printStackTrace();
		}
		try {
			if (rs != null) {
				while (rs.next()) {
					type = rs.getString("type");
				}
			}

		} catch (Exception e) {
			System.out.println("Error creating table " + e);
		}

		return type;
	}

}
