package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.mail.Session;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.x.protobuf.MysqlxNotice.SessionStateChangedOrBuilder;

import Model.CountTotalRow;


@WebServlet("/history")
public class TansectionHistory extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		//set date in session
		HttpSession session=request.getSession(false);
		session.setAttribute("startDate",request.getParameter("start_date"));
		session.setAttribute("endDate", request.getParameter("end_date"));
		
		
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//set content type
		response.setContentType("text/html");
		PrintWriter write=response.getWriter();
		
		//get session obj
		HttpSession session=request.getSession(false);
		
		String account_no=(String)session.getAttribute("user_account");
		String start_date=(String)session.getAttribute("startDate");
		String end_date=(String)session.getAttribute("endDate");
		
		
		//get database connection
		ServletContext context=request.getServletContext();		
		Connection con=(Connection)context.getAttribute("con");
		
		
		//paginetion 
		int total=5,pageid=0,count=0;
		
		//get page number
		int page_no = Integer.parseInt(request.getParameter("no"));
		
		if(page_no==1){
			//serial number
			count=1;
		}		
        else{  
            pageid=page_no-1;  
            pageid=pageid*total+1;  
            
            //serial number
            count=pageid;
        } 
	
		
		//get user account history
		String record = "SELECT * FROM customer_amount_data WHERE account_no = ?  AND STR_TO_DATE(amount_data_time, '%d-%m-%Y %H:%i:%s') BETWEEN ? AND ? ORDER BY customer_id DESC LIMIT ? OFFSET ?";
		
		try {
			PreparedStatement historyStatement=con.prepareStatement(record);
			
			historyStatement.setString(1,account_no);
			historyStatement.setString(2, start_date+" 00:00:00");
			historyStatement.setString(3, end_date+" 23:59:59");
			historyStatement.setInt(4, total);
			historyStatement.setInt(5, pageid);
		
			ResultSet historyResultSet=historyStatement.executeQuery();

		
			//print history			
			write.println("<!DOCTYPE html>\r\n"
						+ "<html lang=\"en\">\r\n"
						+ "<head>\r\n"
						+ "  <title>My bank Statement</title>\r\n"
						+ "  <meta charset=\"utf-8\">\r\n"
						+ "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
						+ "  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">\r\n"
						+ "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js\"></script>\r\n"
						+ "  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script>\r\n"
						+ "</head>\r\n"
						+ "<body>");
			
			write.println("<table class=\"table table-striped\">"
						+ "<thead>"
						+ "<tr><th>S.no</th><th>Amount</th><th>Transection Type</th><th>Remain Amount</th><th>Date and Time</th><th>Account No</th></tr>"
						+ "</thead>"
						+ "<tbody>");

								 
			while(historyResultSet.next())
			{
				write.println("<tr>");
				write.println("<td>"+count+"</td>");
				write.println("<td>"+historyResultSet.getFloat("amount")+" Rs</td>");
				write.println("<td>"+historyResultSet.getString("trans_type")+"</td>");
				write.println("<td>"+historyResultSet.getFloat("remain_amount")+" Rs</td>");
				write.println("<td>"+historyResultSet.getString("amount_data_time")+"</td>");
				write.println("<td>"+historyResultSet.getString("account_no")+"</td>");
				write.println("<tr>");
				count++;

			}
			
			write.println("</tbody>"
						+ "</table>");
		
			//create page number
				//get count row
			    int countRow = CountTotalRow.countRow(context,session);
			for(int i=1;i<=countRow/total;i++) {
        		String pageNo = String.valueOf(i);
        		write.print("<a href='history?no=" + pageNo + "' style='background-color: blue; \r\n"
        				+ "  border: none;\r\n"
						+ "  color: white;\r\n"
						+ "  padding: 3px 6px;\r\n"
						+ "  text-align: center;\r\n"
						+ "  text-decoration: none;\r\n"
						+ "  display: inline-block;\r\n"
						+ "  font-size: 10px;\r\n"
						+ "  margin: 1px .5px;\r\n"
        				+ "  cursor: pointer;' >" + pageNo + "</a> ");

        	}
			
			write.println("<br><br>");
			write.println("<a href='loging' style='background-color: Green; \r\n"
						+ "  border: none;\r\n"
						+ "  color: white;\r\n"
						+ "  padding: 15px 32px;\r\n"
						+ "  text-align: center;\r\n"
						+ "  text-decoration: none;\r\n"
						+ "  display: inline-block;\r\n"
						+ "  font-size: 16px;\r\n"
						+ "  margin: 4px 2px;\r\n"
						+ "  cursor: pointer;' >Profile Page</a>");
			
			write.println("<a href='history_pdf' style='background-color: Green; \r\n"
						+ "  border: none;\r\n"
						+ "  color: white;\r\n"
						+ "  padding: 15px 32px;\r\n"
						+ "  text-align: center;\r\n"
						+ "  text-decoration: none;\r\n"
						+ "  display: inline-block;\r\n"
						+ "  font-size: 16px;\r\n"
						+ "  margin: 4px 2px;\r\n"
						+ "  cursor: pointer;' >Pdf Record</a>");
				
			write.println("</body></html>");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			 
		
		}
}
