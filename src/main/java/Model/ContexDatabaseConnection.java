package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContexDatabaseConnection implements ServletContextListener {
	
	//Create instance of Connection
	Connection conn=null;
			
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		try {
			//Create sql Dirver
            Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Create Connection
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/TCG_BANK","root","12345");
			
			// Set Attribute in connection 
			ServletContext context = sce.getServletContext();
			context.setAttribute("con", conn);			
	
		}
		catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		 
		//close the jdbc connection
		try {
		conn.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

}

