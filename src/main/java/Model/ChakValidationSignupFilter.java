package Model;


import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/singupuser")
public class ChakValidationSignupFilter implements Filter {    
		
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
       
    	//set content type
    	response.setContentType("text/html");
    	PrintWriter out=response.getWriter();
    	
        // Get user email address and date of birth
        String email = request.getParameter("UserEmail");
        String dob = request.getParameter("dob");
        LocalDate date = LocalDate.parse(dob);
        
        // Validate age
        if (ageValid(date)) {
            // Validate email
            if (emailValid(email)) {               	
                chain.doFilter(request, response);               
            } else {
            	out.println("<script>\r\n"
						+ "   alert(\"EmailError, Your email is not valid!....\");\r\n"
						+"window.location.href = 'http://localhost:8080/TCG_Bank_Project/singup.html';"
						+ "</script>");
               
            }
        } else { 
        	out.println("<script>\r\n"
					+ "   alert(\"AgeError, You must be at least 18 years old to sign up!....\");\r\n"
					+"window.location.href = 'http://localhost:8080/TCG_Bank_Project/singup.html';"
					+ "</script>");
           
        }
    }
    
    // Email validation 
    static boolean emailValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    // Age validation
    static boolean ageValid(LocalDate dob) {        
        LocalDate currentDate = LocalDate.now();
        Period ages = Period.between(dob, currentDate);
        return ages.getYears() >= 18;
    }
    
   
}
