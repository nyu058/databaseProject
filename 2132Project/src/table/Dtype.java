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

@WebServlet("/Dtype")
public class Dtype extends HttpServlet {
    private Connection connection;
    private ResultSet rs;

    public String getAvgPrice(String dtype,Connect conn){
        String rList = "";
        connection = conn.getConnection();
        String price="";
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery("select type,(case when avg is null then 0.00 else avg end)from(select resttype as type,cast((avg(cast((price)as dec(10,2)))) as dec(10,2)) from(select menuitem.type as itemtype,restaurant.type as resttype,price from restaurant left join menuitem on menuitem.restaurantid=restaurant.restaurantid)as tmp1 group by resttype union select itemtype as type,cast((avg(cast((price)as dec(10,2)))) as dec(10,2)) from (select menuitem.category as itemtype,restaurant.type as resttype,price from restaurant left join menuitem on menuitem.restaurantid=restaurant.restaurantid)as tmp2 group by itemtype HAVING itemtype IS NOT NULL)as tmp7 where type='"
            +dtype+"'");
        } catch (Exception e) {
            System.out.println("Can't read table rating"+e);
        }
        try {
            if(rs==null){
                rList="0.00";
            }
            else{
                rs.next();
                rList=rs.getString("avg");
            }
        } catch (Exception e) {
            System.out.println("Error creating table" + e);
        }
        return "Average Price is: "+rList;
    }

    public String getHighestRate(String dtype,Connect conn){
        String rList = "";
        connection = conn.getConnection();
        String restname,name;
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery(
                "select tmp2.name as restname, rater.name from ( select name, tmp1.userid from (select userid, tmptba.restaurantid, name from (select userid, restaurantid from rating where ((restaurantid in (select restaurantid from (select restaurantid, most From (SELECT restaurantid, MAX(food) as most FROM rating where restaurantid in (select restaurantid from restaurant where type='"
+dtype+"') GROUP BY restaurantid) as tmp1 inner join (SELECT MAX(food) FROM rating where restaurantid in (select restaurantid from restaurant where type='"
+dtype+"')) as tmp2 on tmp1.most=tmp2.max) as tmppp))and(food in(select most from (select restaurantid, most From (SELECT restaurantid, MAX(food) as most FROM rating where restaurantid in (select restaurantid from restaurant where type='"
+dtype+"') GROUP BY restaurantid) as tmp1 inner join (SELECT MAX(food) FROM rating where restaurantid in (select restaurantid from restaurant where type='"
+dtype+"')) as tmp2 on tmp1.most=tmp2.max)as tmpppa))))as tmptba inner join restaurant on restaurant.restaurantid=tmptba.restaurantid)as tmp1 left join rating on ((tmp1.userid=rating.userid)and(tmp1.restaurantid=rating.restaurantid)) where food=(select most from (select restaurantid, most From (SELECT restaurantid, MAX(food) as most FROM rating where restaurantid in (select restaurantid from restaurant where type='"
+dtype+"') GROUP BY restaurantid) as tmp1 inner join (SELECT MAX(food) FROM rating where restaurantid in (select restaurantid from restaurant where type='"
+dtype+"')) as tmp2 on tmp1.most=tmp2.max)as tmpppb))as tmp2 inner join rater on tmp2.userid=rater.userid"
            );
        } catch (Exception e) {
            System.out.println("Can't read table rating"+e);
        }
        try {
            while (rs.next()) {
                name = rs.getString("name");
                restname=rs.getString("restname");
                rList+="<tr><td>"+restname+"</td><td>"+name+"</td></tr>";
            }
        } catch (Exception e) {
            System.out.println("Error creating table" + e);
        }
        return rList;
    }

}