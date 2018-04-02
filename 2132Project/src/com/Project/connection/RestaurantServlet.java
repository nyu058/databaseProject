package com.Project.connection;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class RestaurantServlet extends HttpServlet {




    private Connect db;

    
    private void processAction(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
            //HttpSession s = request.getSession(true);
           // String customer_name=(String)request.getParameter("txtName");
           // String artist_name=(String)request.getParameter("rdArtist");


            
            Restaurant restaurant = new Restaurant();


            db= new Connect();
            db.openConnection();

          

            //s.setAttribute("customerbean", customerbean );


            PrintWriter out = response.getWriter();

            out.println(restaurant.getRestaurantList(db));            
//            s.setAttribute("restaurant", restaurant );
//            s.setAttribute("dataaccess",db);
//            s.setAttribute("db",db);

            db.closeConsult();


            RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/restaurant.jsp");
            rd.forward(request,response);
 }

    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        processAction(request,response);
    }
    
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    processAction(request, response);
    }

    
    public void destroy()
    {       
        super.destroy();
    }
}

