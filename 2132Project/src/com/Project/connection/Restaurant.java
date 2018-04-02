package com.Project.connection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class Restaurant {
	private Connection connection;
    private Statement st;
    private Connect dataaccess;
    private ResultSet rs;
    private String rList ="";
    
    
    public String getRestaurantList(Connect conn)
    {
        connection = conn.getConnection();
        String cname;
        String aname;

        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT  name, type FROM restaurant");
        } catch(Exception e){
            System.out.println("Cant read likeartist table");
        }
        try{
            while (rs.next())
            {
                cname=rs.getString("name");
                aname=rs.getString("type");
                rList+="<tr><tr><td>"
                               + cname
                               + "</td><td>"
                               + aname
                               +"</td></tr>";
            }
        }catch(Exception e){
            System.out.println("Error creating table "+e);
        }
        return rList;
    }
}
