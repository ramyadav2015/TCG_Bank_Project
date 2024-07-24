package Controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.cj.jdbc.result.ResultSetMetaData;

import Model.CountTotalRow;

@WebServlet("/history_pdf")
public class AccountHistoryPdf extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //set content type to pdf
        response.setContentType("application/pdf"); 
        //response.setHeader("Content-Disposition", "attachment; filename=statement");
        
        //get account no with session obj 
        HttpSession session=request.getSession(false);                
        String account_no=(String)session.getAttribute("user_account");        
        
        //get database connection
        ServletContext context=request.getServletContext();       
        Connection con=(Connection)context.getAttribute("con");
        
        //count total row Account history
        int count_row = CountTotalRow.countRow(context,session);
              
        try {
            //get user account history
            String record = "SELECT * FROM customer_amount_data WHERE account_no=? ORDER BY customer_id DESC";
            
            PreparedStatement history_Pdf_Statement=con.prepareStatement(record);                   
            history_Pdf_Statement.setString(1,account_no);        
            ResultSet history_pfd_ResultSet=history_Pdf_Statement.executeQuery();
            
            //create pdf file
            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream()); // Write directly to the response output stream
            document.open();
            
            //create table with 6 columns
            PdfPTable table = new PdfPTable(6);
            // Add column headers
            table.addCell("Sr.No");
            table.addCell("Amount");
            table.addCell("Transaction Type");
            table.addCell("Remaining Amount");
            table.addCell("Date/Time");
            table.addCell("Acc. Number");
            
            int count=0;
            //document add new paragraph
            while(history_pfd_ResultSet.next()) {
                count++;
                table.addCell(String.valueOf(count)); 
                table.addCell(String.valueOf(history_pfd_ResultSet.getFloat("amount")));
                table.addCell(history_pfd_ResultSet.getString("trans_type"));
                table.addCell(String.valueOf(history_pfd_ResultSet.getFloat("remain_amount")) + " Rs");
                table.addCell(history_pfd_ResultSet.getString("amount_data_time"));
                table.addCell(history_pfd_ResultSet.getString("account_no"));
            }
            
            document.add(table);
            document.close();
            
        } catch (SQLException | DocumentException e) {
            e.printStackTrace();
            response.getWriter().write("Error generating PDF: " + e.getMessage());
        }       
    }   
    
}
