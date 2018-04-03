package connection;

import java.sql.*;

public class Connect {


    private Connection connection;
    private Statement st;
    private ResultSet rs;

    public Connect()
    {
    }
    
    public Connection getConnection()
    {
        return connection;
    }    
    

    public void openConnection() {
        try
        {
           // Password password = new Password();

            Class.forName("org.postgresql.Driver");
            
            connection = DriverManager.getConnection("jdbc:postgresql://35.184.199.76:5432/postgres",
                                                     "postgres","abcd123456");
            System.out.println("Connection Established");
        }catch(Exception e){
            System.out.println("No connection established: "+e.toString());
        }
    }


    

    public boolean siguiente() {
        try {
            return (rs.next());
        } catch(Exception e){
            System.out.println("Error moving to the next one");
            return false;
        }
    }


    public String getField(String name){
        try {
            return (rs.getString(name));
        } catch(Exception e){
            System.out.println("Error getting the field");
            return "";
        }
    }
    

   public void closeConsult(){
        try {
            rs.close();
            st.close();
        } catch(Exception e){}
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (Exception e){}
    }
    

   
}



