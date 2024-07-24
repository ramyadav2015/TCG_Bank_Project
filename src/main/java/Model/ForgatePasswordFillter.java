package Model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/forgot")
public class ForgatePasswordFillter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException,ServletException {
		
		//set content type
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
				
		//get all email or dob in database
			// Get the connection object from ServletContext
			ServletContext context = request.getServletContext();
		    Connection connection = (Connection) context.getAttribute("con");
		        	
		        	
		    try {
		    // Query to retrieve all emails or password from the database
		        String query = "SELECT user_id,user_mail, user_dob FROM customer_data";
		        PreparedStatement statement = connection.prepareStatement(query);
		        ResultSet resultSet = statement.executeQuery();

		        //get user email and password
		        String name = request.getParameter("username");
		        String date = request.getParameter("date"); 
		      	
		        //Check for Loging details 
		        boolean chak = false;
		        String customer_id=null,dbEmail, dbPass=null;
		        Cookie passCookie;
		        		
		        while (resultSet.next()) {
		        	customer_id = String.valueOf(resultSet.getInt("user_id"));
		            dbEmail = resultSet.getString("user_mail");
		            dbPass = resultSet.getString("user_dob");                
		            
		                if (name.equals(dbEmail)&& date.equals(dbPass)) {                    
		                	chak=true;                   
		                	break;
		                }                
		        }        	
		        
		           
		        if(chak) {          	
		           chain.doFilter(request, response);
		       }
		       else {
		           out.println("<h3 style='text-align:center;color:red'>Please Enter Correct User Details</h3>");
		           RequestDispatcher rd=request.getRequestDispatcher("forgotPassword.html");
		           rd.include(request, response);
		      }

		           

		   } catch (SQLException e) {
		            e.printStackTrace();            
		   }
				
				
	}
}


