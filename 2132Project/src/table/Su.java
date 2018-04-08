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

@WebServlet("/Su")
public class Su extends HttpServlet {
    private Connection connection;
    private ResultSet rs;

    public String del(String type, String id, Connect conn) {
        String order="";
            if (type.equals("rest"))
                order = "delete from restaurant where restaurantid = " + id;
            if (type.equals("item"))
                order = "delete from menuitem where itemid = " + id;
            if (type.equals("rater"))
                order = "delete from rater where userid = " + id;
        connection = conn.getConnection();
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery(order);
        } catch (Exception e) {
            System.out.println("Failed"+e);
        }
        return "Query sent";
    }

}