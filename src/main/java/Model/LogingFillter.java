package Model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
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
import javax.swing.text.ChangedCharSetException;

import org.apache.catalina.valves.rewrite.Substitution.StaticElement;

@WebFilter("/chack_loging")
public class LogingFillter implements Filter{

	static String email= null;
	static String password = null;
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException {
		//set content type
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		//get database all email or password
			// Get the connection object from ServletContext
			ServletContext context = request.getServletContext();
        	Connection connection = (Connection) context.getAttribute("con");
        	
        	
        	try {
        		// Query to retrieve all emails or password from the database
        		String query = "SELECT customer_id,c_mail, c_pass FROM customer_login_data";
        		PreparedStatement statement = connection.prepareStatement(query);
        		ResultSet resultSet = statement.executeQuery();

        		//get user email and password
        		email = request.getParameter("username");
        		password = request.getParameter("pass");        		
       
        	       	
        	
            	// Check for Loging details 
        		boolean chak = false;
        		String customerId=null,dbEmail, dbPass=null;
        		Cookie passCookie;
        		
            	while (resultSet.next()) {
            		customerId = String.valueOf(resultSet.getInt("customer_id"));
            		dbEmail = resultSet.getString("c_mail");
            		dbPass = resultSet.getString("c_pass");                
            
                	if (email.equals(dbEmail)&&password.equals(dbPass)) {                    
                		chak=true;                   
                		break;
                	}                
            	}
            	
            	//create Http Session
            	HttpServletRequest req = (HttpServletRequest)  request;
            	HttpServletResponse resp = (HttpServletResponse)  response;

            	HttpSession session = req.getSession();
            	//set customer id to servletContext
        		session.setAttribute("customer_id", customerId);
        		
            	if(chak) {  
            		chain.doFilter(request, response);           		
            		
            	}
            	else {
            		out.println("<h3 style='text-align:center;color:red'>Please Enter Correct User Details</h3>");
            		RequestDispatcher rd=request.getRequestDispatcher("loging.html");
            		rd.include(request, response);
            	}

           

        } catch (SQLException e) {
            e.printStackTrace();            
        }
		
		
	}
	
	
	 public void init(FilterConfig filterConfig) throws ServletException {
	       
	    }

	    @Override
	    public void destroy() {
	       
	    }
	

}
