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

@WebServlet("/Restaurant")
public class Restaurant extends HttpServlet {
	private Connection connection;

	private ResultSet rs;
	private String rList = "";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String rname = request.getParameter("rname");
		String type = request.getParameter("type");
		Connect db = new Connect();
		db.openConnection();
		createRestaurant(rname, type, db);
		response.sendRedirect("restaurant.jsp");
	}

	public int getID(Connect conn) {
		int id = 0;
		Statement st;
		Connection connection = conn.getConnection();
		try {
			st = connection.createStatement();
			rs = st.executeQuery("select restaurantid from restaurant order by restaurantid desc limit 1");
		} catch (Exception e) {
			System.out.println("Cant get last record");
		}
		try {
			if (rs != null) {
				while (rs.next()) {
					id = rs.getInt("restaurantid");
				}
			}

		} catch (Exception e) {
			System.out.println("Error creating table " + e);
		}
		id++;
		return id;
	}

	public void createRestaurant(String rname, String type, Connect conn) {
		Statement st;
		Connection connection = conn.getConnection();
		try {
			st = connection.createStatement();
			st.executeUpdate("Insert into restaurant values(" + getID(conn) + ", '" + rname + "', '" + type + "')");
		} catch (Exception e) {
			System.out.println("Cant insert restaurant info");
		}
	}

	public String getRestaurantList(Connect conn) {
		connection = conn.getConnection();
		String name;
		String type;
		String id;
		try {
			Statement st = connection.createStatement();
			rs = st.executeQuery("SELECT  restaurantid, name, type FROM restaurant");
		} catch (Exception e) {
			System.out.println("Cant read table");
		}
		try {
			while (rs.next()) {
				name = rs.getString("name");
				type = rs.getString("type");
				id = rs.getString("restaurantid");
				rList += "<tr><tr><td><form method=\"get\" action=\"RestaurantDetail\"><input type=\"hidden\"name=\"id\" value=\""
						+ id + "\"><input type=\"submit\" class=\"link\" value=\"" + name + "\"></form></td><td>" + type
						+ "</td><td><form method=\"get\" action=\"rate.jsp\"><input type=\"hidden\"name=\"id\" value=\""
						+ id + "\"><input type=\"submit\" class=\"link\" value=\"Rate\"></form></td></tr>";
			}
		} catch (Exception e) {
			System.out.println("Error creating table " + e);
		}
		return rList;
	}

	public String getName(String id, Connect conn) {
		String name = "";
		Statement st;
		Connection connection = conn.getConnection();
		try {
			st = connection.createStatement();
			rs = st.executeQuery("select name from restaurant where restaurantid=" + id);
		} catch (Exception e) {
			System.out.println("Cant get last record");
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

	public String getRatingCount(String id, Connect conn) {
		String result = "";
		String name;
		String count;
		Connection connection = conn.getConnection();
		try {
			Statement st = connection.createStatement();
			rs = st.executeQuery(
					"SELECT name, count(rating.userid) FROM rating  inner join rater on rating.userid= rater.userid WHERE restaurantid="
							+ id + " group by rater.name");
		} catch (Exception e) {
			System.out.println("Cant read table");
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				name = rs.getString("name");
				count = rs.getString("count");
				result += "<tr><tr><td>" + name + "</td><td>" + count + "</td></tr>";
			}
		} catch (Exception e) {
			System.out.println("Error creating table " + e);
			e.printStackTrace();
		}
		return result;

	}

	public String getNotRated(Connect conn) {
		String name;
		String phone;
		String type;
		String result = "";
		Statement st;
		Connection connection = conn.getConnection();
		try {
			st = connection.createStatement();
			rs = st.executeQuery(
					"select restaurant.name, tel, type from restaurant inner join location on restaurant.restaurantid=location.restaurantid where (restaurant.restaurantid not in (select restaurantid from rating where (date_part('month',Date)=1 and date_part('year',date)=2015)))");
		} catch (Exception e) {
			System.out.println("Cant get last record");
		}
		try {

			while (rs.next()) {
				name = rs.getString(1);
				phone = rs.getString("tel");
				type = rs.getString("type");
				result += "<tr><tr><td>" + name + "</td><td>" + phone + "</td><td>" + type + "</td></tr>";
			}

		} catch (Exception e) {
			System.out.println("Error creating table " + e);
		}

		return result;

	}

}