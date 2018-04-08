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
            rs = st.executeQuery(
                    "select tmp5.name as ratername, joindate, reputation, restaurant.name as restaurant_name, date from(select name, tmp4.userid, joindate, reputation, date, restaurantid from(select name, tmp3.userid, joindate, reputation from(select userid from (select userid, cast((avg(food+mood)/2)as dec(3,2)) as rating from rating group by userid) as tmp1 where rating=(select max(rating)from (select userid, cast((avg(food+mood)/2)as dec(3,2)) as rating from rating group by userid) as tmp2))as tmp3 left join rater on tmp3.userid=rater.userid) as tmp4 left join rating on tmp4.userid=rating.userid) as tmp5 left join restaurant on tmp5.restaurantid=restaurant.restaurantid");
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
            rs = st.executeQuery(
                    "select tmp5.name as ratername, joindate, reputation, restaurant.name as restaurant_name, date from(select name, tmp4.userid, joindate, reputation, date, restaurantid from (select name, tmp3.userid, joindate, reputation from(select userid from (select userid, foodavg as rating from (select userid, cast((avg(food))as dec(3,2)) as foodavg, cast((avg(mood))as dec(3,2)) as moodavg from rating group by userid)as tmpp1 where foodavg>moodavg union select userid, moodavg as rating from (select userid, cast((avg(food))as dec(3,2)) as foodavg, cast((avg(mood))as dec(3,2)) as moodavg from rating group by userid)as tmpp2 where foodavg<=moodavg) as tmp1 where rating=(select max(rating) from (select userid, foodavg as rating from (select userid, cast((avg(food))as dec(3,2)) as foodavg, cast((avg(mood))as dec(3,2)) as moodavg from rating group by userid)as tmpp1 where foodavg>moodavg union select userid, moodavg as rating from (select userid, cast((avg(food))as dec(3,2)) as foodavg, cast((avg(mood))as dec(3,2)) as moodavg from rating group by userid)as tmpp2 where foodavg<=moodavg) as tmp2))as tmp3 left join rater on tmp3.userid=rater.userid) as tmp4 left join rating on tmp4.userid=rating.userid) as tmp5 left join restaurant on tmp5.restaurantid=restaurant.restaurantid");
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

    public String getItemByRater(String id, Connect conn) {
        String rList = "";
        connection = conn.getConnection();
        String ratername, name, reputation, date, price, comment;
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery(
                    "select ratername, reputation, name, date, comment, price from(select name as ratername, reputation, date, itemid, comment from (select tmp3.userid, name, reputation from (select * from (SELECT userid, count(userid) FROM rating WHERE restaurantid="
                            + id
                            + " group by userid)as tmp2 where count= (select max(count) from (SELECT userid, count(userid) FROM rating WHERE restaurantid="
                            + id
                            + " group by userid)as tmp1))as tmp3 left join rater on tmp3.userid=rater.userid)as tmp4 left join ratingitem on tmp4.userid=ratingitem.userid)as tmp5 left join menuitem on tmp5.itemid=menuitem.itemid where restaurantid="
                            + id);
        } catch (Exception e) {
            System.out.println("Can't read table getItemByRater");
        }
        try {
            while (rs.next()) {
                ratername = rs.getString("ratername");
                reputation = rs.getString("reputation");
                name = rs.getString("name");
                date = rs.getString("date");
                comment = rs.getString("comment");
                price = rs.getString("price");
                rList += "<tr><td>" + ratername + "</td><td>" + reputation + "</td><td>" + name + "</td><td>" + date
                        + "</td><td>" + comment + "</td><td>" + price + "</td></tr>";
            }
        } catch (Exception e) {
            System.out.println("Error creating table" + e);
        }

        return rList;
    }

    public String getDiversedRater(String id, Connect conn) {
        String rList = "";
        connection = conn.getConnection();
        String ratername, type, email, price, food, mood, staff;
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery(
                    "select ratername, tmpa.type, email, price, food, mood, staff from (select tmp6.name as ratername, type, email, restid, price, food, mood, staff from((select distinct userid, restaurantid as restid from (select tmp2.userid, tmp2.restaurantid, ((avgrate-avg)*(avgrate-avg)/(count))as variance from (select userid, cast(((price+food+mood+staff)/4)as dec(3,2))as avgrate, restaurantid from rating)as tmp2 left join (select userid, restaurantid, avg(avgrate), count(userid) from (select userid, cast(((price+food+mood+staff)/4)as dec(3,2))as avgrate, restaurantid from rating)as tmpa group by userid,restaurantid) as tmp1 on ((tmp2.userid=tmp1.userid) and (tmp2.restaurantid=tmp1.restaurantid)))as tmptb where restaurantid= "
                            + id
                            + " and variance=(select max(variance) from (select distinct * from (select tmp2.userid, tmp2.restaurantid, ((avgrate-avg)*(avgrate-avg)/(count))as variance from (select userid, cast(((price+food+mood+staff)/4)as dec(3,2))as avgrate, restaurantid from rating)as tmp2 left join(select userid, restaurantid, avg(avgrate), count(userid) from (select userid, cast(((price+food+mood+staff)/4)as dec(3,2))as avgrate, restaurantid from rating)as tmpa group by userid,restaurantid) as tmp1 on ((tmp2.userid=tmp1.userid) and (tmp2.restaurantid=tmp1.restaurantid))) as tmptc where restaurantid= "
                            + id
                            + ")as tmp7))as tmp5 left join rater on rater.userid=tmp5.userid)as tmp6 left join rating on restid=restaurantid)as tmpa left join restaurant on restid=restaurantid");
        } catch (Exception e) {
            System.out.println("Can't read table getDiversedRater" + e);
            e.printStackTrace();
        }
        try {
            while (rs.next()) {
                ratername = rs.getString("ratername");
                type = rs.getString("type");
                email = rs.getString("email");
                
                price = rs.getString("price");
                food = rs.getString("food");
                mood = rs.getString("mood");
                staff = rs.getString("staff");
                rList += "<tr><td>" + ratername + "</td><td>" + type + "</td><td>" + email + "</td><td>" 
                         + price + "</td><td>" + food + "</td><td>" + mood + "</td><td>" + staff
                        + "</td></tr>";
            }
        } catch (Exception e) {
            System.out.println("Error creating table" + e);
        }

        return rList;
    }
}