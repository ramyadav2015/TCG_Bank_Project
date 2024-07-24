package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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

@WebServlet("/sendManey")
public class SendManeyAnotherAccount extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//set content type
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		
		//create session
		HttpSession session=request.getSession(false);
		
		//get id and account no using session
		int User_id=Integer.parseInt((String)session.getAttribute("customer_id"));		
		String user_bank_account=(String) session.getAttribute("user_account");
		
		//get send account no and amount
		String deposit_account=request.getParameter("deposit_account");
		Float user_amount=Float.valueOf(request.getParameter("deposit_amount"));		
		
		
		RequestDispatcher rd=null;
		try {
			
			//get database connection
			ServletContext sc=request.getServletContext();		
			Connection con=(Connection)sc.getAttribute("con");
			
	        Float remain_amount=0f;
	        Float deposit_remain_amount=0f;
	        
	        //get user_data customer_amount_data table
	        String user_account_data="Select * from customer_amount_data where account_no=?";
	        PreparedStatement accountStatement = con.prepareStatement(user_account_data);
	        accountStatement.setString(1, user_bank_account);
            ResultSet accountResultSet=accountStatement.executeQuery();
            
            //get remain_amount Sender
            while(accountResultSet.next())
            {
            	remain_amount=accountResultSet.getFloat("remain_amount");             
            }
            
            accountStatement.close();
            accountResultSet.close();
            
            if(remain_amount>=user_amount) {
            	//total amount Sender
            	remain_amount-=user_amount;
            
            	//get Reciver data customer_amount_data table 
            	String user_account_data3="Select * from customer_amount_data where account_no=?";
            	PreparedStatement senderStatement = con.prepareStatement(user_account_data3);
            	senderStatement.setString(1, deposit_account);
            	ResultSet senderResultSet=senderStatement.executeQuery();
            
            	//get Reciver ramain_amount
            	while(senderResultSet.next())
            	{
            		deposit_remain_amount=senderResultSet.getFloat("remain_amount");              
            	}
            
            	//get Reciver total amount
            	deposit_remain_amount+=user_amount;
            
            	senderStatement.close();
            	senderResultSet.close();
            
            	//insert data Sender account
            	user_account_data="insert into customer_amount_data (amount,trans_type,remain_amount,amount_data_time,account_no) values (?,?,?,?,?)";
	        
            	PreparedStatement userInsertStatement=con.prepareStatement(user_account_data);	        
            	userInsertStatement.setFloat(1, user_amount);
            	userInsertStatement.setString(2, "Send By "+user_bank_account+"");
            	userInsertStatement.setFloat(3, deposit_remain_amount);
            	//get local time and date
            	LocalDateTime myDateObj = LocalDateTime.now();
            	DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            	String account_create_date = myDateObj.format(myFormatObj);

            	userInsertStatement.setString(4, account_create_date);
            	userInsertStatement.setString(5, deposit_account);                   
            
            	int rowsAffected = userInsertStatement.executeUpdate();
            	
            	userInsertStatement.close();
            	
            	//chack insert Sender data 
            	if (rowsAffected > 0) {
            	
            		//insert data Recever account	 
            		PreparedStatement insert_data2=con.prepareStatement(user_account_data);
    	        
            		insert_data2.setFloat(1, user_amount);
            		insert_data2.setString(2, "Send to "+deposit_account+"");
            		insert_data2.setFloat(3, remain_amount);
    	        
            		LocalDateTime myDateObj2 = LocalDateTime.now();
            		DateTimeFormatter myFormatObj2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            		String account_create_date2 = myDateObj2.format(myFormatObj2);

            		insert_data2.setString(4, account_create_date2);
            		insert_data2.setString(5, user_bank_account);
            		rowsAffected = insert_data2.executeUpdate();
                
            		
            		insert_data2.close();
            		
            		//chack insert Recever data
            		if(rowsAffected>0)
            		{
            			out.println("<script>\r\n"
            					+ "   alert(\"Amount send another account seccessfully!....\");\r\n"
            					+"window.location.href = 'http://localhost:8080/TCG_Bank_Project/loging';"
            					+ "</script>");
            		}
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
