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

@WebServlet("/RaterDetail")
public class RaterDetail extends HttpServlet {
    private Connection connection;
    private ResultSet rs;

    public String getRestRate(String id, Connect conn) {
        String rList = "";
        connection = conn.getConnection();
        String name, date, price, food, mood, staff, comeagain, comment;
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery(
                    "select name,date,price,food,mood,staff,comeagain,comment from rating left join restaurant on rating.restaurantid=restaurant.restaurantid where userid="
                            + id);
        } catch (Exception e) {
            System.out.println("Can't read table rating");
        }
        try {
            while (rs.next()) {
                name = rs.getString("name");
                date = rs.getString("date");
                price = rs.getString("price");
                food = rs.getString("food");
                mood = rs.getString("mood");
                staff = rs.getString("staff");
                comeagain = rs.getString("comeagain");
                comment = rs.getString("comment");
                rList += "<tr><td>" + name + "</td><td>" + date + "</td><td>" + price + "</td><td>" + food + "</td><td>"
                        + mood + "</td><td>" + staff + "</td><td>" + comeagain + "</td><td>" + comment + "</td></tr>";
            }
        } catch (Exception e) {
            System.out.println("Error creating table" + e);
        }
        return rList;
    }

    public String getItemRate(String id, Connect conn) {
        String rList = "";
        connection = conn.getConnection();
        String name,type,category,price,itemname,comment,date;
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery(
                    "select distinct userid,tmp1.name as itemname, date,tmp1.type,category,price,restaurant.name,comment from (select distinct * from ratingitem left join menuitem on menuitem.itemid=ratingitem.itemid)as tmp1 left join restaurant on tmp1.restaurantid=restaurant.restaurantid where userid="
                            + id);
        } catch (Exception e) {
            System.out.println("Can't read table rating");
        }
        try {
            while (rs.next()) {
                name = rs.getString("name");
                type = rs.getString("type");
                price = rs.getString("price");
                category = rs.getString("category");
                itemname = rs.getString("itemname");
                comment = rs.getString("comment");
                date = rs.getString("date");
                rList += "<tr><td>" + itemname + "</td><td>" + type + "</td><td>" + category + "</td><td>" + price + "</td><td>"
                        + name + "</td><td>" + date + "</td><td>" + comment + "</td></tr>";
            }
        } catch (Exception e) {
            System.out.println("Error creating table" + e);
        }
        return rList;
    }

    public String getRestLowStaff(String id, Connect conn) {
        String rList = "";
        connection = conn.getConnection();
        String name,address,firstopen;
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery("select distinct name, address, firstopen from(select * from (select date, location.restaurantid, firstopen, address from (select date, restaurantid from rating where restaurantid in(SELECT restaurantid FROM rating WHERE (staff<(SELECT min FROM(SELECT userid, min(staff)FROM rating GROUP BY userid)AS tmp1 WHERE userid="
            +id
            +")))) as tmp1 left join location on tmp1.restaurantid=location.restaurantid)as tmp4 inner join restaurant on restaurant.restaurantid=tmp4.restaurantid order by date) as tmp5");
        } catch (Exception e) {
            System.out.println("Can't read table rating");
        }
        try {
            while (rs.next()) {
                name = rs.getString("name");
                address = rs.getString("address");
                firstopen = rs.getString("firstopen");
                rList += "<tr><td>" + name + "</td><td>" + address + "</td><td>" +firstopen + "</td></tr>";
            }
        } catch (Exception e) {
            System.out.println("Error creating table" + e);
        }
        return rList;
    }

    public String getRaterLower(String id, Connect conn) {
        String rList = "";
        connection = conn.getConnection();
        String name,email;
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery("select name, email from (select * from(select userid, cast(((avg(price)+avg(food)+avg(mood)+avg(staff))/4)as dec(3,2))as avgrate from rating group by userid)as tmp2 where avgrate<(select avgrate from(select userid, cast(((avg(price)+avg(food)+avg(mood)+avg(staff))/4)as dec(3,2))as avgrate from rating group by userid)as tmp1 where userid="
            +id
            +"))as tmp3 left join rater on tmp3.userid=rater.userid");
        } catch (Exception e) {
            System.out.println("Can't read table rating");
        }
        try {
            while (rs.next()) {
                name = rs.getString("name");
                email = rs.getString("email");

                rList += "<tr><td>" + name + "</td><td>" +email + "</td></tr>";
            }
        } catch (Exception e) {
            System.out.println("Error creating table" + e);
        }
        return rList;
    }

}