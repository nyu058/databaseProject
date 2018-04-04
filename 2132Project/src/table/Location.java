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
 * Servlet implementation class Location
 */
@WebServlet("/Location")
public class Location extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ResultSet rs;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connect db = new Connect();
		db.openConnection();
		String id = request.getParameter("rid");

		createLocation(request.getParameter("odate"), request.getParameter("mname"), request.getParameter("phone"),
				request.getParameter("address"), request.getParameter("city"), request.getParameter("otime"),
				request.getParameter("ctime"), id, db);
		response.sendRedirect("restaurant.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	public void createLocation(String odate, String mname, String phone, String address, String city, String otime,
			String ctime, String rid, Connect conn) {

		Statement st;
		Connection connection = conn.getConnection();
		try {
			st = connection.createStatement();
			st.executeUpdate("insert into location values(" + getID(conn) + ", '" + odate + "', '" + mname + "', '"
					+ phone + "', '" + address + "', '" + city + "', '" + otime + "', '" + ctime + "', " + rid + ")");
		} catch (Exception e) {
			System.out.println("Cant insert location info");
			e.printStackTrace();
		}
	}

	public int getID(Connect conn) {
		int id = 0;
		Statement st;
		Connection connection = conn.getConnection();
		try {
			st = connection.createStatement();
			rs = st.executeQuery("select locationid from location order by locationid desc limit 1");
		} catch (Exception e) {
			System.out.println("Cant get last record");
		}
		try {
			if (rs != null) {
				while (rs.next()) {
					id = rs.getInt("locationid");
				}
			}

		} catch (Exception e) {
			System.out.println("Error creating table " + e);
		}
		id++;
		return id;
	}

	public String getLocationList(String id, Connect conn) {
		Connection connection = conn.getConnection();
		String odate;
		String mname;
		String phone;
		String address;
		String city;
		String otime;
		String ctime;
		String result = "";
		try {
			Statement st = connection.createStatement();
			rs = st.executeQuery(
					"SELECT firstOpen, managerName, tel, address, city, houropen, hourclose FROM location where restaurantid="
							+ id);
		} catch (Exception e) {
			System.out.println("Cant read table");
		}
		try {
			while (rs.next()) {
				odate = rs.getString("firstOpen");
				mname = rs.getString("managerName");
				phone = rs.getString("tel");
				address = rs.getString("address");
				city = rs.getString("city");
				otime = rs.getString("houropen");
				ctime = rs.getString("hourclose");
				result += "<tr><tr><td>" + odate + "</td><td>" + mname + "</td><td>" + phone + "</td><td>" + address
						+ "</td><td>" + city + "</td><td>" + otime + "</td><td>" + ctime + "</td></tr>";
			}
		} catch (Exception e) {
			System.out.println("Error creating table " + e);
			e.printStackTrace();
		}
		return result;
	}

}
