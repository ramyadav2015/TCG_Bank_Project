package Model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/singupuser")
public class CheckEmailDuplicateFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

    	//set content type
    	response.setContentType("text/html");
    	PrintWriter out=response.getWriter();
    	
        // Get the connection object from ServletContext
        Connection connection = (Connection) request.getServletContext().getAttribute("con");

        try {
            // Query to retrieve all emails from the database
            String query = "SELECT user_mail FROM customer_data";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            // Get the email entered by the user
            String email = request.getParameter("UserEmail");

            // Check for duplicate emails
            boolean isDuplicate = false;
            while (rs.next()) {
                String dbEmail = (String) rs.getString("user_mail");
 
                if (email.equals(dbEmail)) {
                    
                    isDuplicate = true;

                    break;
                }
            }

            // If a duplicate email is found, forward the request to an error page
            if (isDuplicate) {
            	out.println("<script>\r\n"
						+ "   alert(\"Email address already exists. Please choose another one.!....\");\r\n"
						+"window.location.href = 'http://localhost:8080/TCG_Bank_Project/singup.html';"
						+ "</script>");               
            }
            else {        
                chain.doFilter(request, response);
            }

        } catch (SQLException e) {
            e.printStackTrace();            
        }
    }
    
    

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code goes here (if needed)
    }

    @Override
    public void destroy() {
        // Cleanup code goes here (if needed)
    }
}
