package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/updatePassword")
public class UpdatePassword extends HttpServlet{

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			doPost(request, response);
			
		}

		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     
		  //set content type
		  response.setContentType("text/html");
	      PrintWriter wr=response.getWriter();
	      
	      //get user mail and password
	      String user_pass=null;
	      HttpSession session=request.getSession(false);
	      String user_mail=(String) session.getAttribute("User_mail");	      
		  user_pass=request.getParameter("user_pass");
		  
		  //get database connection
		  ServletContext sc=request.getServletContext();		
		  Connection connection=(Connection)sc.getAttribute("con");
		  
		  if(user_pass!=null)
		  {
			  //password update
			  String update_pass = "UPDATE customer_login_data SET c_pass = ? WHERE c_mail = ?";
			  
			  try {
				PreparedStatement updatrePreparedStatement=connection.prepareStatement(update_pass);
				updatrePreparedStatement.setString(1, user_pass);
				updatrePreparedStatement.setString(2, user_mail);
				wr.println(updatrePreparedStatement);
				int check=updatrePreparedStatement.executeUpdate();
				wr.println(check);
					if(check>0)
					{
						wr.println("<script>\r\n"
								+ "   alert(\"Update Password SuccessFully!....\");\r\n"
								+"window.location.href = 'http://localhost:8080/TCG_Bank_Project/loging';"
								+ "</script>");
					}else {
					 
				}
			  	} catch (SQLException e) {
			  		e.printStackTrace();
			  	}			  
		 }
	}
}
