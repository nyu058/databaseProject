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

@WebServlet("/Rater")
public class Rater extends HttpServlet{
    private Connection connection;
    private ResultSet rs;

    public String getUserList(Connect conn) {
        String rList = "";
        connection = conn.getConnection();
        String name,reputation,date,type,email,id;
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery("select * from rater");
        } catch (Exception e) {
            System.out.println("Can't read table rating");
        }
        try {
            while (rs.next()) {
                name = rs.getString("name");
                id = rs.getString("userid");
                reputation = rs.getString("reputation");
                date = rs.getString("joindate");
                type = rs.getString("type");
                email = rs.getString("email");
                rList += "<tr><td><form method=get action=raterDetail.jsp><input type=hidden name=id value="
                +id+"><input type=hidden name=name value="
                +name+"><input type=submit class=link value="
                +name+"></form></td><td>"
                +reputation+"</td><td>"
                +date+"</td><td>"
                +type+"</td><td>"
                +email+"</td></tr>";
            }
        } catch (Exception e) {
            System.out.println("Error creating table" + e);
        }
        return rList;
    }




}