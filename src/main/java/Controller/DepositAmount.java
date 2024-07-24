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

import org.apache.jasper.tagplugins.jstl.core.Out;

@WebServlet("/Deposit")
public class DepositAmount extends HttpServlet {
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//set content type
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		
		//get session obj
		HttpSession session=request.getSession(false);
		
		//get id and account no using session
		int User_id=Integer.parseInt((String)session.getAttribute("customer_id"));		
		String user_bank_account=(String) session.getAttribute("user_account");
		
		//get amount 
		Float amount=Float.valueOf(request.getParameter("deposit_amount"));
		
		RequestDispatcher rd=null;
		try {
			
			//get database connectioin
			ServletContext sc=request.getServletContext();		
			Connection con=(Connection)sc.getAttribute("con");
			
			//get data for costomer_amount_data table 
			Float remain_amount=0f;
	        String user_account_data="Select * from customer_amount_data where account_no=?";
	        PreparedStatement amountStatement = con.prepareStatement(user_account_data);
	        amountStatement.setString(1, user_bank_account);
            ResultSet amountResultSet=amountStatement.executeQuery();
            
            //get remain amount 
            while(amountResultSet.next())
            {
            	remain_amount=amountResultSet.getFloat("remain_amount");            
            }
            
            //total amount
            remain_amount+=amount;
            amountResultSet.close();
            amountStatement.close();
            
            //amount insert customer_amount_data table
	        user_account_data="insert into customer_amount_data (amount,trans_type,remain_amount,amount_data_time,account_no) values (?,?,?,?,?)";
	        
	        PreparedStatement insetStatement=con.prepareStatement(user_account_data);	        
	        insetStatement.setFloat(1, amount);
	        insetStatement.setString(2, "Self_Deposit");
	        insetStatement.setFloat(3, remain_amount);
	        //get local data time
	        LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String account_create_date = myDateObj.format(myFormatObj);
            
            insetStatement.setString(4, account_create_date);
            insetStatement.setString(5, user_bank_account);
            
            
            int rowsAffected = insetStatement.executeUpdate();
            insetStatement.close();
            //check inset data
            if (rowsAffected > 0) {
            	out.println("<script>\r\n"
						+ "   alert(\"Amount Deposit SuccessFully!....\");\r\n"
						+"window.location.href = 'http://localhost:8080/TCG_Bank_Project/loging';"
						+ "</script>");
                
            } else {
                System.out.println("Failed to insert data.");
            }
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        
	}

}
