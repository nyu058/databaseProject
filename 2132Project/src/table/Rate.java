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
@WebServlet("/Rate")
public class Rate extends HttpServlet{
    private Connection connection;
    private ResultSet rs;
    private String rList = "";



    public String getRating(String id,Connect conn){
        connection=conn.getConnection();
        String name,date,price,food,mood,staff,comment,comeagain;
        try{
            Statement st=connection.createStatement();
            rs=st.executeQuery("select name, date, price, food, mood, staff, comment, comeagain from (select * from rating where restaurantid="
            +id
            +")as tmp1 left join rater on tmp1.userid=rater.userid");
        }
        catch(Exception e){
            System.out.println("Can't read table rating");
        }
        try{
            while(rs.next()){
                name=rs.getString("name");
                date=rs.getString("date");
                price=rs.getString("price");
                food=rs.getString("food");
                mood=rs.getString("mood");
                staff=rs.getString("staff");
                comment=rs.getString("comment");
                comeagain=rs.getString("comeagain");
                rList+="<tr><td>"+name+"</td><td>"+date+"</td><td>"+price+"</td><td>"+food+"</td><td>"+mood
                +"</td><td>"+staff+"</td><td>"+comment+"</td><td>"+comeagain+"</td><tr>";
            }
        }
        catch(Exception e){
            System.out.println("Error creating table"+e);
        }
        return rList;
    }





    

}