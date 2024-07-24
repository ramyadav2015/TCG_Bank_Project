package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;

public class AccountNoGeneret {

	public String account_rec(ServletContext context){ 
		
		String get_account_no=null,fetch_acc="";
		
		try {		  
		    String fetch_rec="Select * from Customer_Data";
		    
		    //get database connection
		    Connection connection =(Connection) context.getAttribute("con");
		    //create statement and ResultSet 
		    Statement sm=connection.createStatement();
		    ResultSet rs=sm.executeQuery(fetch_rec);
		    
		    int get_last_id=0;
		           
		    while(rs.next()){ 
		    	get_last_id=rs.getInt("user_id");
		    	get_account_no=rs.getString("account_no");
		    }
		    
		    	//Generet Account No
		        get_last_id+=1;
		        String first_digit="TCG";
		        
		        if(get_last_id==1){
		        	int last_digit=100001; 
		        	get_account_no=first_digit+last_digit;
		        }
		         else {
		        	 fetch_acc=get_account_no.substring(3);
		        	 System.out.println(fetch_acc);
		        	 int conver_acc=Integer.parseInt(fetch_acc);
		        	 conver_acc+=1;
		        	 System.out.println(conver_acc);
		             get_account_no=first_digit+String.valueOf(conver_acc);
		  
		        }
		          
		  } catch (Exception e) { e.printStackTrace(); }
		  
		  
		    return get_account_no;
	   } 
}
