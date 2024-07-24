package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.xdevapi.Statement;

@WebServlet("/loging")
public class ProfileServlet extends HttpServlet {

  static String account_no=null;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//set content type
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		//create session fatch user_id
		HttpSession session=request.getSession(false);
		
		if(session==null)
		{
			System.out.println("your session is null");
			response.sendRedirect("index.html");
		}
		else {
			int User_id=Integer.parseInt((String)session.getAttribute("customer_id"));
			System.out.println(User_id);
			 try { 
				  
				 //create Servlet context
				 ServletContext context=request.getServletContext();
				 
				 //get DataBase connection
                 Connection connection = (Connection) context.getAttribute("con");
				 
                 
                 //get user deatil with account no
				 String selct_rec="Select * from customer_data where user_id=?";	     
	             
	             PreparedStatement customerData_preparedStatement = connection.prepareStatement(selct_rec);
	             customerData_preparedStatement.setInt(1, User_id);
	             ResultSet customerData_resultSet=customerData_preparedStatement.executeQuery();
	             
	             String first_name,last_name,mail,contact_no;
	             String address,dob,state;
	             String account_type;
	             Float amount_data=0f;
	             String profile_image=null;
	             while(customerData_resultSet.next())
	             {
	            	 first_name=customerData_resultSet.getString("first_name");
	            	 last_name=customerData_resultSet.getString("last_name");
	            	 mail=customerData_resultSet.getString("user_mail");
	            	 contact_no=customerData_resultSet.getString("user_phone");
	            	 
	            	 address=customerData_resultSet.getString("user_add");
	            	 dob=customerData_resultSet.getString("user_dob");
	            	 state=customerData_resultSet.getString("user_state");
	            	 
	            	 account_type=customerData_resultSet.getString("user_account");
	            	 account_no=customerData_resultSet.getString("account_no");	            	 
	            	 profile_image=customerData_resultSet.getString("user_pic");
	            	 
	            	 //setAttribute user account_no and user mail
	            	 session.setAttribute("user_account", account_no);
	            	 session.setAttribute("User_mail", mail);
	            	 
	            	 customerData_preparedStatement.close();
	            	 customerData_resultSet.close();
	            	 
	            	 //get user amount 
	            	 String user_account_data="Select * from customer_amount_data where account_no=?";
	            	 
	            	 PreparedStatement amount_preparedStatement = connection.prepareStatement(user_account_data);
	            	 amount_preparedStatement.setString(1, account_no);
	                 ResultSet amount_resultSet=amount_preparedStatement.executeQuery();
	                
	                 while(amount_resultSet.next())
	                 {
	                	 amount_data=amount_resultSet.getFloat("remain_amount");  
	                		                 
	                 }
	                 
	                 amount_preparedStatement.close();
	                 amount_resultSet.close();
	                 
	            	 out.println("<!DOCTYPE html>\r\n"
	         				+ "<html lang=\"en\">\r\n"
	         				+ "<head>\r\n"
	         				+ "<meta charset=\"UTF-8\">\r\n"
	         				+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
	         				+ "<title>User Profile - Sabka Apna Bank My Bank</title>\r\n"
	         				+ "<style>\r\n"
	         				+ "    body {\r\n"
	         				+ "        font-family: Arial, sans-serif;\r\n"
	         				+ "        background-color: #f4f4f4;\r\n"
	         				+ "        margin:20px 0px;\r\n"
	         				+ "        padding:20px 0px;\r\n"
	         				+ "        display: flex;\r\n"
	         				+ "        justify-content: center;\r\n"
	         				+ "        align-items: center;\r\n"
	         				+ "        height: 100vh;\r\n"
	         				+ "    }\r\n"
	         				+ ""
	         				+ " #password,#eye_pass {\r\n"
	         				+ "    cursor: pointer;\r\n"
	         				+ "}"
	         				+ ""
	         				+ "    .container {\r\n"
	         				+ "        width: 80%;\r\n"
	         				+ "        display: flex;\r\n"
	         				+ "        border-radius: 8px;\r\n"
	         				+ "        overflow: hidden;\r\n"
	         				+ "        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\r\n"
	         				+ "    }\r\n"
	         				+ "    .profile-section {\r\n"
	         				+ "        width: 50%;\r\n"
	         				+ "        background-color: #fff;\r\n"
	         				+ "        padding: 20px;\r\n"
	         				+ "        border-right: 2px solid #ddd;\r\n"
	         				+ "    }\r\n"
	         				+ "    .transaction-section {\r\n"
	         				+ "        width: 50%;\r\n"
	         				+ "        background-color: #f9f9f9;\r\n"
	         				+ "        padding: 20px;\r\n"
	         				+ "    }\r\n"
	         				+ "    .transaction-form {\r\n"
	         				+ "        display: flex;\r\n"
	         				+ "        flex-direction: column;\r\n"
	         				+ "        gap: 10px;\r\n"
	         				+ "    }\r\n"
	         				+ "    .transaction-form label {\r\n"
	         				+ "        margin-bottom: 5px;\r\n"
	         				+ "    }\r\n"
	         				+ "    .transaction-form input[type=\"number\"],\r\n"
	         				+ "    .transaction-form input[type=\"text\"],\r\n"
	         				+ "    .transaction-form input[type=\"submit\"] {\r\n"
	         				+ "        padding: 10px;\r\n"
	         				+ "        border: 1px solid #ccc;\r\n"
	         				+ "        border-radius: 5px;\r\n"
	         				+ "        width: 100%;\r\n"
	         				+ "        box-sizing: border-box;\r\n"
	         				+ "    }\r\n"
	         				+ "    .transaction-form input[type=\"submit\"] {\r\n"
	         				+ "        background:rgb(5,89,104);\r\n"
	         				+ "        color: white;\r\n"
	         				+ "        cursor: pointer;\r\n"
	         				+ "    }\r\n"
	         				+ "    .transaction-form input[type=\"submit\"]:hover {\r\n"
	         				+ "        background:rgb(5,89,104);\r\n"
	         				+ "    }\r\n"
	         				+ "\r\n"
	         				+ "    @media screen and (max-width: 768px) {\r\n"
	         				+ "        .container {\r\n"
	         				+ "            flex-direction: column;\r\n"
	         				+ "        }\r\n"
	         				+ "        .profile-section,\r\n"
	         				+ "        .transaction-section {\r\n"
	         				+ "            width: 100%;\r\n"
	         				+ "        }\r\n"
	         				+ "        .profile-section {\r\n"
	         				+ "            border-right: none;\r\n"
	         				+ "            border-bottom: 2px solid #ddd;\r\n"
	         				+ "        }\r\n"
	         				+ "    }\r\n"
	         				+ ""
	         				+ ""
	         				+ ".button {\r\n"
	         				+ "  display: inline-block;\r\n"
	         				+ "  border-radius: 4px;\r\n"
	         				+ "  background-color: #f4511e;\r\n"
	         				+ "  border: none;\r\n"
	         				+ "  color: #FFFFFF;\r\n"
	         				+ "  text-align: center;\r\n"
	         				+ "  font-size: 14px;\r\n"
	         				+ "  padding: 10px;\r\n"
	         				+ "  width: 70px;\r\n"
	         				+ "  transition: all 0.5s;\r\n"
	         				+ "  cursor: pointer;\r\n"
	         				+ "  margin: 5px;\r\n"
	         				+ "}\r\n"
	         				+ "\r\n"
	         				+ ".button span {\r\n"
	         				+ "  cursor: pointer;\r\n"
	         				+ "  display: inline-block;\r\n"
	         				+ "  position: relative;\r\n"
	         				+ "  transition: 0.5s;\r\n"
	         				+ "}\r\n"
	         				+ "\r\n"
	         				+ ".button span:after {\r\n"
	         				+ "  content: '\\00bb';\r\n"
	         				+ "  position: absolute;\r\n"
	         				+ "  opacity: 0;\r\n"
	         				+ "  top: 0;\r\n"
	         				+ "  right: -10px;\r\n"
	         				+ "  transition: 0.5s;\r\n"
	         				+ "}\r\n"
	         				+ "\r\n"
	         				+ ".button:hover span {\r\n"
	         				+ "  padding-right: 15px;\r\n"
	         				+ "}\r\n"
	         				+ "\r\n"
	         				+ ".button:hover span:after {\r\n"
	         				+ "  opacity: 1;\r\n"
	         				+ "  right: 0;"
	         				+ ""
	         				+ ".show_list {\r\n"
	         				+ "  background-color: #04AA6D; /* Green */\r\n"
	         				+ "  border: none;\r\n"
	         				+ "  color: white;\r\n"
	         				+ "  padding: 10px 20px;\r\n"
	         				+ "  text-align: center;\r\n"
	         				+ "  text-decoration: none;\r\n"
	         				+ "  display: inline-block;\r\n"
	         				+ "  font-size: 14px;\r\n"
	         				+ "  margin: 4px 2px;\r\n"
	         				+ "  cursor: pointer;\r\n"
	         				+ "}"
	         				+ ""
	         				+ ".profile-header {\r\n"
	         				+ "  display: flex;\r\n"
	         				+ "}\r\n"
	         				+ "\r\n"
	         				+ ".image_form {\r\n"
	         				+ "  margin-right: auto; /* This will push the image form to the right */\r\n"
	         				+ "  width: 100px; /* Adjust width as needed */\r\n"
	         				+ "}\r\n"
	         				+ "\r\n"
	         				+ "/* Optional: Adjust styles for the image */\r\n"
	         				+ ".image_form img {\r\n"
	         				+ "  width: 100%; /* Make sure the image fills the container */\r\n"
	         				+ "  height: auto; /* Maintain aspect ratio */\r\n"
	         				+ "}"
	         				+ ""
	         				+ "</style>\r\n"
	         				+ "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">"
	         				+ "</head>\r\n"
	         				+ "<body>\r\n"
	         				+ "\r\n"
	         				+ "<div class=\"container\">\r\n"
	         				+ "    <div class=\"profile-section\">\r\n"
	         				+ "        <div class=\"profile-header\">\r\n"
	         				+ "<div class='image_form' style=''>"
	         				+ "            <img src='Profile_Image/"+profile_image+"' style='width:200px;height:200px' alt=\"Demo pic1\">\r\n"
	         				
	         				+ "   <form action=\"image\" method=\"post\" enctype=\"multipart/form-data\">\r\n"
	         				+ "        Select Profile Image:\r\n"
	         				+ "        <input type=\"file\" name=\"file\" id=\"file\"></br>\r\n"
	         				+ "        <input type=\"submit\" style='margin-left:150px;margin-top:20px' value=\"Upload Image\" name=\"submit\">\r\n"
	         				+ "    </form>"
	         				+ "</div>"
	         				+ ""
	         				+ "            <h1>"+first_name+" "+last_name+"</h1>\r\n"
	         				+ "            <p>Account Number: "+account_no+"</p>\r\n"
	         				+ "        </div>\r\n"
	         				+ "        <div class=\"profile-details\">\r\n"
	         				+ "            <h2>Contact Information</h2>\r\n"
	         				+ "            <p>Email: "+mail+"</p>"
	         								+" Change Password     "
	         						        + "<a href='forgotPassword.html' \r\n"
	         						        + "style='background-color: #04AA6D; r\n"
	         						        + "  border: none;\r\n"
	         						       	+ "  color: white;\r\n"
	         						       	+ "  padding: 10px 20px;\r\n"
	         						        + "  text-align: center;r\n"
	         						       	+ "  text-decoration: none;\r\n"
	         						       	+ "  display: inline-block;\r\n"
	         						      	+ "  font-size: 14px;\r\n"
	         						     	+ "  margin: 4px 2px;\r\n"
	         						     	+ "  cursor: pointer;' >Update</a>"
	         								+ "\r\n"
	         				+ "            <p>Phone: +91"+contact_no+"</p>\r\n"
	         				+ "            <h2>Address</h2>\r\n"
	         				+ "            <p>Address:- "+address+" State:- "+state+"</p>\r\n"         				
	         				+ "            <h2>Banking Details</h2>\r\n"
	         				+ "            <p>Account Type: "+account_type+"</p>\r\n"
	         				+ "            <p>Balance: "+amount_data+" INR</p>\r\n"
	         				+ "<a href='logout_pages' "
	         						+ "style='background-color: red; /* red */\r\n"
	    	         				+ "  border: none;\r\n"
	    	         				+ "  color: white;\r\n"
	    	         				+ "  padding: 10px 20px;\r\n"
	    	         				+ "  text-align: center;\r\n"
	    	         				+ "  text-decoration: none;\r\n"
	    	         				+ "  display: inline-block;\r\n"
	    	         				+ "  font-size: 14px;\r\n"
	    	         				+ "  margin: 4px 2px;\r\n"
	    	         				+ "  cursor: pointer;' >Logout</a>"
	         				        
	         				+ "        </div>\r\n"
	         				+ "    </div>\r\n"
	         				+ "\r\n"
	         				+ "    <div class=\"transaction-section\">\r\n"
	         				+ "        <h2 style='text-align:center'>Transactions</h2>\r\n"
	         				+ "        <div class=\"transaction-form\">\r\n"
	         				+ "            <form action='sendManey' method='post'>\r\n"
	         				+ "                <label for=\"send-amount\">Send Amount:</label>\r\n"
	         				+ "                <input type=\"number\" name='deposit_amount' id=\"send-amount\" placeholder=\"Enter amount to send\" min=\"1\">\r\n"
	         				+ "                <input type=\"text\" name='deposit_account' placeholder=\"Enter Account No\" />\r\n"
	         				+ "                <input type=\"submit\" value=\"Send\" />\r\n"
	         				+ "            </form>\r\n"
	         				+ "            \r\n"
	         				+ "            <form action='withdraw' method='post'>\r\n"
	         				+ "                <label for=\"withdraw-amount\">Withdraw:</label>\r\n"
	         				+ "                <input type=\"number\" name='withdraw_amount' id=\"withdraw-amount\" placeholder=\"Enter amount to withdraw\" min=\"1\">\r\n"
	         				+ "                <input type=\"submit\" value=\"Withdraw\" />\r\n"
	         				+ "            </form>\r\n"
	         				+ "            \r\n"
	         				+ "            <form action='Deposit' method='post'>\r\n"
	         				+ "                <label for=\"deposit-amount\">Deposit Amount:</label>\r\n"
	         				+ "                <input type=\"number\" name='deposit_amount' id=\"deposit-amount\" placeholder=\"Enter amount to deposit\" min=\"1\">\r\n"
	         				+ "                <input type=\"submit\" value=\"Deposit\" />\r\n"
	         				+ "            </form>\r\n"
	         				+ "            "
	         				+ "        </div>\r\n"
	         				+ "<form action='history' method='post'>"
	         				+ " <br>Start Date History : <input type=\"date\" name=\"start_date\" placeholder=\"start-date\" pattern=\"\\d{2}-\\d{2}-\\d{4}\"><br><br>"
	         				+ "  End Date History :  <input type=\"date\" name=\"end_date\" placeholder=\"end-date\" pattern=\"\\d{2}-\\d{2}-\\d{4}\"><br>"
	         				+ " <input type='hidden' name='no' value='1'>"
	         				+ " <br> <input type='submit' style='background-color: #04AA6D; /* Green */\r\n"
	         				+ "  border: none;\r\n"
	         				+ "  color: white;\r\n"
	         				+ "  padding: 10px 20px;\r\n"
	         				+ "  text-align: center;\r\n"
	         				+ "  text-decoration: none;\r\n"
	         				+ "  display: inline-block;\r\n"
	         				+ "  font-size: 14px;\r\n"
	         				+ "  margin: 4px 2px;\r\n"
	         				+ "  cursor: pointer;' value='History' />"
	         				+ "</form>"
	         				+ "    </div>\r\n"
	         				+ "</div>\r\n"
	         				+ "\r\n"
	         				+ "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\r\n"
	         				+ "<script>\r\n"
	         				+ "$(document).ready(function(){\r\n"
	         				+ "    $('#eye_pass').click(function(){\r\n"
	         				+ "        var passwordField = $('#password');\r\n"
	         				+ "        var passwordFieldType = passwordField.attr('type');\r\n"
	         				+ "        if(passwordFieldType == 'password'){\r\n"
	         				+ "            passwordField.attr('type', 'text');\r\n"
	         				+ "            $(this).text('Hide Password');\r\n"
	         				+ "        } else {\r\n"
	         				+ "            passwordField.attr('type', 'password');\r\n"
	         				+ "            $(this).text('Show Password');\r\n"
	         				+ "        }\r\n"
	         				+ "    });\r\n"
	         				+ "$(document).on('click', function(event) {\r\n"
	         				+ "        var passwordField = $('#password');\r\n"
	         				+ "        if (!$(event.target).closest('#eye_pass').length && !$(event.target).is('#password')) {\r\n"
	         				+ "            passwordField.attr('type', 'password');\r\n"
	         				+ "            $('#eye_pass').text('Show Password');\r\n"
	         				+ "        }\r\n"
	         				+ "    });"
	         				+ "});\r\n"
	         				+ "</script>"
	         				+ "</body>\r\n"
	         				+ "</html>\r\n"
	         				+ "");
	             }
			 }
			 catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		
	}

}
