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

	public String getMostExpItem(String id, Connect conn) {
		connection = conn.getConnection();
		String name, price, managername, houropen, url;
		try {
			Statement st = connection.createStatement();
			rs = st.executeQuery(
					"select distinct name,price,managername,houropen,url from (select distinct restaurant.restaurantid,managername,houropen,url from restaurant left join location on restaurant.restaurantid=location.restaurantid)as tmp1 left join menuitem on tmp1.restaurantid=menuitem.restaurantid where (menuitem.restaurantid="
							+ id + ") and (price=(select max(price) from menuitem where restaurantid=" + id + "))");
		} catch (Exception e) {
			System.out.println("Cant read table");
		}
		try {
			while (rs.next()) {
				name = rs.getString("name");
				price = rs.getString("price");
				managername = rs.getString("managername");
				houropen = rs.getString("houropen");
				url = rs.getString("url");
				rList += "<tr><td>" + name + "</td><td>" + price + "</td><td>" + managername + "</td><td>" + houropen
						+ "</td><td>" + url + "</td></tr>";
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

	public String getRating(String id, Connect conn) {
		Connection connection = conn.getConnection();
		String uname;
		String date;
		String price;
		String food;
		String mood;
		String staff;
		String comment;
		String comeagain;
		String result = "";
		boolean b;
		try {
			Statement st = connection.createStatement();
			rs = st.executeQuery(
					"select name, date, price, food, mood, staff, comment, comeagain from rating left join rater on rating.userid=rater.userid where restaurantid="
							+ id);
		} catch (Exception e) {
			System.out.println("Cant get rating");
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				uname = rs.getString("name");
				
				date = rs.getString("date");
				price = rs.getString("price");
				food = rs.getString("food");
				mood = rs.getString("mood");
				staff = rs.getString("staff");
				comment = rs.getString("comment");
				b = rs.getBoolean("comeagain");
				if (b) {
					comeagain = "Yes";
				} else {
					comeagain = "No";
				}
				result += "<tr><tr><td>" + uname + "</td><td>" + date + "</td><td>" + price + "</td><td>" + food
						+ "</td><td>" + mood + "</td><td>" + staff + "</td><td>" + comment + "</td><td>" + comeagain
						+ "</td></tr>";

			}
		} catch (Exception e) {
			System.out.println("Error creating table " + e);
			e.printStackTrace();
		}
		return result;

	}

	
}