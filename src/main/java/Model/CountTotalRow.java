package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpSession;

public class CountTotalRow {
	public static int countRow(ServletContext context,HttpSession session) {
		
		//get database connection
		Connection connection = (Connection) context.getAttribute("con");
		
		//get account no
		String account_no = (String) session.getAttribute("user_account");
		
		//count total record
    	String totalRecord = "SELECT COUNT(customer_id) as count_row FROM customer_amount_data 	WHERE account_no=? ";
    	PreparedStatement totalRowStatemnet;
    	
    	
    	int total_row=0;
		try {
			//pass the account no in query
			totalRowStatemnet = connection.prepareStatement(totalRecord);
			totalRowStatemnet.setString(1, account_no);
			
			ResultSet totalRs = totalRowStatemnet.executeQuery();
			if(totalRs.next())
	    	total_row= totalRs.getInt("count_row");
	    	
		} catch (SQLException e) {			
			e.printStackTrace();
		}
    	
		return total_row;
		
	}
}
