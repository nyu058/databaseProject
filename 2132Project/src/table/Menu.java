package table;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connection.Connect;

/**
 * Servlet implementation class Menu
 */
@WebServlet("/Menu")
public class Menu extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ResultSet rs;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connect db = new Connect();
		db.openConnection();
		String id = request.getParameter("rid");
		createItem(request.getParameter("name"), request.getParameter("type"), request.getParameter("category"),
				request.getParameter("descript"), request.getParameter("price"), id, db);
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

	public String getStarter(String id, Connect conn) {
		String result = "";
		Statement st;
		Connection connection = conn.getConnection();
		try {
			st = connection.createStatement();
			rs = st.executeQuery(
					"select itemid, name, description, price from menuitem where category='starter'and type='food' and restaurantid="
							+ id);
		} catch (Exception e) {
			System.out.println("Cant get starter");
			e.printStackTrace();
		}
		try {
			if (rs != null) {
				while (rs.next()) {
					
					result += "<tr><tr><td><form method=\"get\" action=\"RateMenu\"><input type=\"hidden\"name=\"iid\" value=\""
							+ rs.getString("itemid") + "\"><input type=\"submit\" class=\"link\" value=\"" + rs.getString("name") + "\">&nbsp;&nbsp;&nbsp;&nbsp;</form></td><td>" + rs.getString("description")
							+ "</td><td>&nbsp;&nbsp;&nbsp;&nbsp;"+rs.getString("price")+"</td></tr>";
//					result += "<tr><tr><td>" + rs.getString("name") + "&nbsp;&nbsp;&nbsp;&nbsp;</td><td>"
//							+ rs.getString("description") + "&nbsp;&nbsp;&nbsp;&nbsp;</td><td>$" + rs.getString("price")
//							+ "</td></tr>";
				}
			}

		} catch (Exception e) {
			System.out.println("Error creating table " + e);
		}

		return result;
	}

	public String getMain(String id, Connect conn) {
		String result = "";
		Statement st;
		Connection connection = conn.getConnection();
		try {
			st = connection.createStatement();
			rs = st.executeQuery(
					"select itemid, name, description, price from menuitem where category='main'and type='food' and restaurantid="
							+ id);
		} catch (Exception e) {
			System.out.println("Cant get main");
			e.printStackTrace();
		}
		try {
			if (rs != null) {
				while (rs.next()) {
					result += "<tr><tr><td><form method=\"get\" action=\"RateMenu\"><input type=\"hidden\"name=\"iid\" value=\""
							+ rs.getString("itemid")  + "\"><input type=\"submit\" class=\"link\" value=\"" + rs.getString("name") + "\">&nbsp;&nbsp;&nbsp;&nbsp;</form></td><td>" + rs.getString("description")
							+ "</td><td>&nbsp;&nbsp;&nbsp;&nbsp;"+rs.getString("price")+"</td></tr>";
				}
			}

		} catch (Exception e) {
			System.out.println("Error creating table " + e);
		}

		return result;
	}

	public String getDesert(String id, Connect conn) {
		String result = "";
		Statement st;
		Connection connection = conn.getConnection();
		try {
			st = connection.createStatement();
			rs = st.executeQuery(
					"select itemid, name, description, price from menuitem where category='desert'and type='food' and restaurantid="
							+ id);
		} catch (Exception e) {
			System.out.println("Cant get desert");
			e.printStackTrace();
		}
		try {
			if (rs != null) {
				while (rs.next()) {
					result += "<tr><tr><td><form method=\"get\" action=\"RateMenu\"><input type=\"hidden\"name=\"iid\" value=\""
							+ rs.getString("itemid")  + "\"><input type=\"submit\" class=\"link\" value=\"" + rs.getString("name") + "\">&nbsp;&nbsp;&nbsp;&nbsp;</form></td><td>" + rs.getString("description")
							+ "</td><td>&nbsp;&nbsp;&nbsp;&nbsp;"+rs.getString("price")+"</td></tr>";
				}
			}

		} catch (Exception e) {
			System.out.println("Error creating table " + e);
		}

		return result;
	}

	public String getDrink(String id, Connect conn) {
		String result = "";
		Statement st;
		Connection connection = conn.getConnection();
		try {
			st = connection.createStatement();
			rs = st.executeQuery(
					"select itemid, name, description, price from menuitem where type='beverage' and restaurantid=" + id);
		} catch (Exception e) {
			System.out.println("Cant get drink");
			e.printStackTrace();
		}
		try {
			if (rs != null) {
				while (rs.next()) {
					result += "<tr><tr><td><form method=\"get\" action=\"RateMenu\"><input type=\"hidden\"name=\"iid\" value=\""
							+ rs.getString("itemid")  + "\"><input type=\"submit\" class=\"link\" value=\"" + rs.getString("name") + "\">&nbsp;&nbsp;&nbsp;&nbsp;</form></td><td>" + rs.getString("description")
							+ "</td><td>&nbsp;&nbsp;&nbsp;&nbsp;"+rs.getString("price")+"</td></tr>";
				}
			}

		} catch (Exception e) {
			System.out.println("Error creating table " + e);
		}

		return result;
	}

	public void createItem(String name, String type, String category, String descript, String price, String rid,
			Connect conn) {
		Statement st;
		Connection connection = conn.getConnection();
		try {
			st = connection.createStatement();
			st.executeUpdate("insert into menuitem values(" + getID(conn) + ", '" + name + "', '" + type + "', '"
					+ category + "', '" + descript + "', '" + price + "', " + rid + ")");
		} catch (Exception e) {
			System.out.println("Cant insert item info");
			e.printStackTrace();
		}
	}

	public int getID(Connect conn) {
		int id = 0;
		Statement st;
		Connection connection = conn.getConnection();
		try {
			st = connection.createStatement();
			rs = st.executeQuery("select itemid from menuitem order by itemid desc limit 1");
		} catch (Exception e) {
			System.out.println("Cant get last record");
		}
		try {
			if (rs != null) {
				while (rs.next()) {
					id = rs.getInt("itemid");
				}
			}

		} catch (Exception e) {
			System.out.println("Error creating table " + e);
		}
		id++;
		return id;
	}
}
