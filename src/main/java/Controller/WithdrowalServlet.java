package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/withdraw")
public class WithdrowalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//set contenet type
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		
		//get user id or account no using session obj
		HttpSession session=request.getSession(false);		
		int User_id=Integer.parseInt((String)session.getAttribute("customer_id"));		
		String user_bank_account=(String) session.getAttribute("user_account");
		
		//get withdraw amount
		Float withdraw_amount=Float.valueOf(request.getParameter("withdraw_amount"));	
		
		RequestDispatcher rd=null;
		try {
			//get database connection
			ServletContext sc=request.getServletContext();		
			Connection con=(Connection)sc.getAttribute("con");
	
	        Float remain_amount=0f;
	        // get remain amount
	        String user_account_data="Select * from customer_amount_data where account_no=?";
	        	        
	        PreparedStatement remainAmountStatement = con.prepareStatement(user_account_data);
	        remainAmountStatement.setString(1, user_bank_account);
            ResultSet remainAmountResultSet=remainAmountStatement.executeQuery();
              
            while(remainAmountResultSet.next()) {
            	remain_amount=remainAmountResultSet.getFloat("remain_amount"); 
            }
            
            if(remain_amount>=withdraw_amount) {
            	//total amount
            	remain_amount-=withdraw_amount;
            
            	//update amount customer_amount_data table
            	user_account_data="insert into customer_amount_data (amount,trans_type,remain_amount,amount_data_time,account_no) values (?,?,?,?,?)";
	        
            	PreparedStatement insert_data=con.prepareStatement(user_account_data);
	        
            	insert_data.setFloat(1, withdraw_amount);
            	insert_data.setString(2, "Withdraw_Self");
            	insert_data.setFloat(3, remain_amount);	 
            	//get local date and time
            	LocalDateTime myDateObj = LocalDateTime.now();
            	DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            	String account_create_date = myDateObj.format(myFormatObj);

            	insert_data.setString(4, account_create_date);
            	insert_data.setString(5, user_bank_account);            
            
            	int rowsAffected = insert_data.executeUpdate();
            
            	//chack inset data
            	if (rowsAffected > 0) {
            		out.println("<script>\r\n"
            				+ "   alert(\"Amount withdraw SuccessFully!....\");\r\n"
            				+"window.location.href = 'http://localhost:8080/TCG_Bank_Project/loging';"
            				+ "</script>");
                
                
            	} else {
                System.out.println("Failed to insert data.");
            	}
            }
            else {
            	out.println("<script>\r\n"
        				+ "   alert(\"Insufficient funds!!!!!\");\r\n"
        				+"window.location.href = 'http://localhost:8080/TCG_Bank_Project/loging';"
        				+ "</script>");
            }
	        
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
