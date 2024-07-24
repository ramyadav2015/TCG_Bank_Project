package Controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet("/image")
@MultipartConfig
public class SetUserImage extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Set content type
            response.setContentType("application/json");
           

            // Get file information
            Part part = request.getPart("file");
            String fileName = part.getSubmittedFileName();

            // Image conversion to byte stream
            try (InputStream inputStream = part.getInputStream()) {
                byte[] image = inputStream.readAllBytes();

                // Get the real path of the web application
                ServletContext context = request.getServletContext();
                String realPath = context.getRealPath("/");

                // Save profile image
                String path = realPath + "Profile_Image\\" + fileName;
                try (FileOutputStream fileOutputStream = new FileOutputStream(path)) {
                    fileOutputStream.write(image);
                }
               
                // Get database connection from ServletContext
                Connection con = (Connection) context.getAttribute("con");

                // Get user email
                HttpSession session = request.getSession(false);
                String userMail = (String) session.getAttribute("User_mail");
               
                // Update user profile image in the database
                String updateImageQuery = "UPDATE customer_data SET user_pic=? WHERE user_mail=?";
                try (PreparedStatement ps = con.prepareStatement(updateImageQuery)) {
                    ps.setString(1, fileName);
                    ps.setString(2, userMail);
                    int check = ps.executeUpdate();

                    if (check > 0) {                        
                        PrintWriter out = response.getWriter();
                        out.println("<script>\r\n"
    							+ "   alert(\"Image set seccessfully!....\");\r\n"
    							+"window.location.href = 'http://localhost:8080/TCG_Bank_Project/loging';"
    							+ "</script>");
                    }
                }
            }
        } 
        catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
