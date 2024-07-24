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

import Model.SendMail;

@WebServlet("/forgot")
public class ForgotPasswordServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doPost(req, resp);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException{
		
		//set content type
		  response.setContentType("text/html");
	      PrintWriter out=response.getWriter();
	      
	      //get user mail and password
	      String user_pass=null;	      
	      String user_mail = request.getParameter("username");
	      user_pass = request.getParameter("new_password");
		 
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
				
				out.println(updatrePreparedStatement);
				int check=updatrePreparedStatement.executeUpdate();	
				//close the preparedStatement
				updatrePreparedStatement.close();
					if(check>0)
					{
						//send email user_mail and Password
			            String subject = "Reset Password Details";
			            String massege = "Your TCG Bank Account Reset Password is "+ user_pass+"";
			             SendMail.send(user_mail,subject , massege);
			            
			            
						out.println("<script>\r\n"
								+ "   alert(\"Password Reset SuccessFully!....\");\r\n"
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
