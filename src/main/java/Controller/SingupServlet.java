package Controller;

import java.awt.Color;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.AccountNoGeneret;
import Model.SendMail;
import Model.Entity;


@WebServlet("/singupuser") 
public class SingupServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//set content type
		resp.setContentType("text/html");
		PrintWriter write=resp.getWriter();

		//get details for singup form
		String first_name,last_name,ft_name,mo_name,user_add,user_dob,user_state,user_phone,user_mail="",
                user_pan,user_account,gender,nominee,user_aadhar;
		
		//get singup data
        first_name=req.getParameter("FirstName");
        last_name=req.getParameter("LastName");
        ft_name=req.getParameter("father_name");
        mo_name=req.getParameter("mother_name");
        user_add=req.getParameter("UserAddress");
        user_dob=req.getParameter("dob");
        user_state=req.getParameter("state_name");
        gender=req.getParameter("gender");
        user_account=req.getParameter("AccountType");
        nominee=req.getParameter("Nominee");      
        user_pan=req.getParameter("UserPan");
        user_aadhar=req.getParameter("AadharNo");
        user_phone=req.getParameter("ContactNo");
        user_mail=req.getParameter("UserEmail");
        
        //set singup data setter method
        Entity entity = new Entity();
        entity.setFirstName(first_name);
        entity.setLastName(last_name);
        entity.setFather_name(ft_name);
        entity.setMother_name(mo_name);
        entity.setUserAddress(user_add);
        entity.setDob(user_dob);
        entity.setState_name(user_state);
        entity.setGender(gender);
        entity.setAccountType(user_account);
        entity.setNominee(nominee);
        entity.setUserPan(user_pan);
        entity.setAadharNo(user_aadhar);
        entity.setContactNo(user_phone);
        entity.setUserEmail(user_mail);
        
        
        
                try{
                    //get Account no
                		//create obj ServletContext
                	ServletContext context = req.getServletContext();
                    AccountNoGeneret ad=new AccountNoGeneret();
                    String account_no=ad.account_rec(context);
                    
                    //get acount opning data time                      
                    LocalDateTime myDateObj = LocalDateTime.now();
                    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                    String account_create_date = myDateObj.format(myFormatObj);
                    
                    //get DataBase connection
                    Connection connection = (Connection) context.getAttribute("con");
                  	
                //Create queary and statament for customer_data table
                     String personal_data = "INSERT INTO customer_data (first_name, last_name, ft_name, mo_nome, user_add, user_dob, user_state, user_phone, user_mail, user_pan, user_account, gender, nominee, user_aadhar, account_no) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                     PreparedStatement pstmt = connection.prepareStatement(personal_data);
                        
                     												
                        pstmt.setString(1, entity.getFirstName());
                        pstmt.setString(2, entity.getLastName());
                        pstmt.setString(3, entity.getFather_name());
                        pstmt.setString(4, entity.getMother_name());
                        pstmt.setString(5, entity.getUserAddress());
                        pstmt.setString(6, entity.getDob());
                        pstmt.setString(7, entity.getState_name());
                        pstmt.setString(8, entity.getContactNo());
                        pstmt.setString(9, entity.getUserEmail());
                        pstmt.setString(10, entity.getUserPan());
                        pstmt.setString(11, entity.getAccountType());
                        pstmt.setString(12, entity.getGender());
                        pstmt.setString(13, entity.getNominee());
                        pstmt.setString(14, entity.getAadharNo());
                        pstmt.setString(15, account_no);
                        
                        
                        int count=0;
                        // Execute the query
                        int rowsAffected = pstmt.executeUpdate();

                        // Check if the insertion was successful
                        if (rowsAffected > 0) {
                            System.out.println("Data inserted successfully customer_data table ");
                            count++;
                        } else {
                            System.out.println("Failed to insert data.");
                        }
                       
         // data insert from customer_login_data table                        
                //Create user password
                Random random = new Random();
                int user_pass = random.nextInt(999999);
                        
                //create queary and stetament customer_loging_data table      
                String user_data = "Insert into customer_login_data (c_mail,c_pass) values(?,?)";
                PreparedStatement pst = connection.prepareStatement(user_data);
                pst.setString(1, entity.getUserEmail());
                pst.setInt(2, user_pass);
                        
                //excute the query       
                int rowsAffected2 = pst.executeUpdate();
                // Check if the insertion was successful
                if (rowsAffected2 > 0) {
                	System.out.println("Data inserted successfully customer_loging_data.");
                    count++;
                } else {
                     System.out.println("Failed to insert data customer_loging_data.");
                }
                        
                    
                
         //data insert from customer_login_data table

                float start_amount = 0;
                float remain_amount=0;
                String amount_type = "account_open";
                
                String user_amount_data ="Insert into customer_amount_data (amount, trans_type, remain_amount, amount_data_time, account_no) values(?,?,?,?,?)"; 
				PreparedStatement pst3 = connection.prepareStatement(user_amount_data);
				pst3.setFloat(1, start_amount); 
				pst3.setString(2, amount_type);
				pst3.setFloat(3, remain_amount);
				pst3.setString(4, account_create_date);
				pst3.setString(5, account_no);
					
				//excute the query  
				int rowsAffected3 = pst3.executeUpdate(); 
				
				// Check if the insertion was successful
				if (rowsAffected3 > 0){
					System.out.println("Data inserted successfully customer_data table.");
					count++;
				} 
				else { System.out.println("Failed to insert data customer_data table."); }
						 
                        
				//chack all data inset successfully
	            if(count==3)
	            {
	            	write.println("<h3 style='text-align:center;color:red'> Data Successfully Inserted</h3>");
	            }
	             
	       //send email user_mail and Password
	            String passString = String.valueOf(user_pass);
	            String subject = "Your TCG Bank Loging Details";
	            String massege = "Your Email is "+user_mail+"   And Your Password is "+passString+"";
	            SendMail.send(user_mail,subject , massege);                    
	            
	           
	            write.println("<script>\r\n"
						+ "   alert(\"Account Open SuccessFully!.....   Your User Name and Password send your Register Email\");\r\n"
						+"window.location.href = 'http://localhost:8080/TCG_Bank_Project/loging';"
						+ "</script>");
	         

            }
            catch (Exception es)
            {
               es.printStackTrace();
            }
	}
}

