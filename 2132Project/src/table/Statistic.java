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

@WebServlet("/Statistic")
public class Statistic extends HttpServlet {
    private Connection connection;
    private ResultSet rs;

    public String getRestList(Connect conn) {
        String rList = "";
        connection = conn.getConnection();
        String name, id;
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery("select restaurantid,name from restaurant");
        } catch (Exception e) {
            System.out.println("Can't read table rating");
        }
        try {
            while (rs.next()) {
                name = rs.getString("name");
                id = rs.getString("restaurantid");
                rList += "<option value=" + id + ">" + name + "</option>";
            }
        } catch (Exception e) {
            System.out.println("Error creating table" + e);
        }
        return rList;
    }

    public String getUserList(Connect conn) {
        String rList = "";
        connection = conn.getConnection();
        String name, id;
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery("select userid,name from rater");
        } catch (Exception e) {
            System.out.println("Can't read table rating");
        }
        try {
            while (rs.next()) {
                name = rs.getString("name");
                id = rs.getString("userid");
                rList += "<option value=" + id + ">" + name + "</option>";
            }
        } catch (Exception e) {
            System.out.println("Error creating table" + e);
        }
        return rList;
    }

    public String getPopular(Connect conn) {
        String rList = "";
        connection = conn.getConnection();
        String type, count;
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery(
                    "select type, count(type) from (select restaurantid from rating)as tmp1 left join restaurant on tmp1.restaurantid=restaurant.restaurantid group by type order by count desc");
        } catch (Exception e) {
            System.out.println("Can't read table rating");
        }
        try {
            while (rs.next()) {
                type = rs.getString("type");
                count = rs.getString("count");
                rList += "<tr><td>" + type + "</td><td>" + count + "</td></tr>";
            }
        } catch (Exception e) {
            System.out.println("Error creating table" + e);
        }
        return rList;
    }

    public String getRaterOverall(Connect conn) {
        String rList = "";
        connection = conn.getConnection();
        String ratername, joindate, reputation, restname, date;
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery("select tmp5.name as ratername, joindate, reputation, restaurant.name as restaurant_name, date from(select name, tmp4.userid, joindate, reputation, date, restaurantid from(select name, tmp3.userid, joindate, reputation from(select userid from (select userid, cast((avg(food+mood)/2)as dec(3,2)) as rating from rating group by userid) as tmp1 where rating=(select max(rating)from (select userid, cast((avg(food+mood)/2)as dec(3,2)) as rating from rating group by userid) as tmp2))as tmp3 left join rater on tmp3.userid=rater.userid) as tmp4 left join rating on tmp4.userid=rating.userid) as tmp5 left join restaurant on tmp5.restaurantid=restaurant.restaurantid");
        } catch (Exception e) {
            System.out.println("Can't read table rating");
        }
        try {
            while (rs.next()) {
                ratername = rs.getString("ratername");
                joindate = rs.getString("joindate");
                reputation = rs.getString("reputation");
                restname = rs.getString("restaurant_name");
                date = rs.getString("date");
                rList += "<tr><td>" + ratername + "</td><td>" + joindate + "</td><td>" + reputation + "</td><td>"
                        + restname + "</td><td>" + date + "</td></tr>";
            }
        } catch (Exception e) {
            System.out.println("Error creating table" + e);
        }
        return rList;
    }

    public String getRaterAlt(Connect conn) {
        String rList = "";
        connection = conn.getConnection();
        String ratername, joindate, reputation, restname, date;
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery("select tmp5.name as ratername, joindate, reputation, restaurant.name as restaurant_name, date from(select name, tmp4.userid, joindate, reputation, date, restaurantid from (select name, tmp3.userid, joindate, reputation from(select userid from (select userid, foodavg as rating from (select userid, cast((avg(food))as dec(3,2)) as foodavg, cast((avg(mood))as dec(3,2)) as moodavg from rating group by userid)as tmpp1 where foodavg>moodavg union select userid, moodavg as rating from (select userid, cast((avg(food))as dec(3,2)) as foodavg, cast((avg(mood))as dec(3,2)) as moodavg from rating group by userid)as tmpp2 where foodavg<=moodavg) as tmp1 where rating=(select max(rating) from (select userid, foodavg as rating from (select userid, cast((avg(food))as dec(3,2)) as foodavg, cast((avg(mood))as dec(3,2)) as moodavg from rating group by userid)as tmpp1 where foodavg>moodavg union select userid, moodavg as rating from (select userid, cast((avg(food))as dec(3,2)) as foodavg, cast((avg(mood))as dec(3,2)) as moodavg from rating group by userid)as tmpp2 where foodavg<=moodavg) as tmp2))as tmp3 left join rater on tmp3.userid=rater.userid) as tmp4 left join rating on tmp4.userid=rating.userid) as tmp5 left join restaurant on tmp5.restaurantid=restaurant.restaurantid");
        } catch (Exception e) {
            System.out.println("Can't read table rating");
        }
        try {
            while (rs.next()) {
                ratername = rs.getString("ratername");
                joindate = rs.getString("joindate");
                reputation = rs.getString("reputation");
                restname = rs.getString("restaurant_name");
                date = rs.getString("date");
                rList += "<tr><td>" + ratername + "</td><td>" + joindate + "</td><td>" + reputation + "</td><td>"
                + restname + "</td><td>" + date + "</td></tr>";
            }
        } catch (Exception e) {
            System.out.println("Error creating table" + e);
        }
        return rList;
    }

}