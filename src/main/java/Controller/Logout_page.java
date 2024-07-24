package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout_pages")
public class Logout_page extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//invalid the session
		HttpSession session = req.getSession(false);
		 if (session != null) {
	            session.invalidate();
	     }
		 
		 //Redirect index page
	     resp.sendRedirect("index.html"); 
	}	

}
