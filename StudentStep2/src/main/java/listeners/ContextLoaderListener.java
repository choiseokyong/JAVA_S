package listeners;

import java.sql.Connection;
import java.sql.DriverManager;

import dao.StudentDao;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;


@WebListener
public class ContextLoaderListener implements ServletContextListener {
	Connection con;
    public void contextInitialized(ServletContextEvent sce)  { 
    	ServletContext sc = sce.getServletContext();
		try {
			Class.forName(sc.getInitParameter("driver"));
			con = DriverManager.getConnection(sc.getInitParameter("url"), sc.getInitParameter("username"), 
					sc.getInitParameter("password"));
			StudentDao studentDao = new StudentDao();
			studentDao.setConnection(con);
			sc.setAttribute("studentDao", studentDao);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }

	
    public void contextDestroyed(ServletContextEvent sce)  { 
         
    }
	
}
