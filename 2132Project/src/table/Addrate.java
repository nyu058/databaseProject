package table;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import connection.Connect;
@WebServlet("/Addrate")
public class Addrate extends HttpServlet{
    private ResultSet rs;


    public void createRate(String userid,String price,String food,String mood,String staff,String comment,String restid,Connect conn){
        Statement st;
        String date=getDate();
        Connection connection=conn.getConnection();
        try{
            st=connection.createStatement();
            st.executeUpdate("insert into rating values(")

        }
    }

    public String getDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		String date = dtf.format(now);
		return date;
	}


}