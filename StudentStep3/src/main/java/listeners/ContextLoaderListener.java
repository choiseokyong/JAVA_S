package listeners;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import dao.StudentDao;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;


@WebListener
public class ContextLoaderListener implements ServletContextListener {
//	Connection con;
//	BasicDataSource ds;
    public void contextInitialized(ServletContextEvent sce)  { 
    	ServletContext sc = sce.getServletContext();
		try {
//			Class.forName(sc.getInitParameter("driver"));
//			con = DriverManager.getConnection(sc.getInitParameter("url"), sc.getInitParameter("username"), 
//					sc.getInitParameter("password"));
			
//			 DataSource 사용해서 직접 DB 연결
//    		ds = new BasicDataSource();
//    		ds.setDriverClassName(sc.getInitParameter("driver"));
//			ds.setUrl(sc.getInitParameter("url"));
//			ds.setUsername(sc.getInitParameter("username"));
//			ds.setPassword(sc.getInitParameter("password"));
			
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource)ic.lookup("java:comp/env/jdbc/student");
			StudentDao studentDao = new StudentDao();
			studentDao.setDataSource(ds);
			sc.setAttribute("studentDao", studentDao);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }

	
    public void contextDestroyed(ServletContextEvent sce)  { 
         
    }
	
}
