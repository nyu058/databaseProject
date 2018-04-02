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
        String name;
        String type;
        String link;
        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT  name, type, url FROM restaurant");
        } catch(Exception e){
            System.out.println("Cant read table");
        }
        try{
            while (rs.next())
            {
                name=rs.getString("name");
                type=rs.getString("type");
                link=rs.getString("url");
                rList+="<tr><tr><td>"
                               +name
                               + "</td><td>"
                               + type
                               +"</td></tr>"
                               + link
                               +"</td><td>";
            }
        }catch(Exception e){
            System.out.println("Error creating table "+e);
        }
        return rList;
    }
}
