package servlets;

import java.net.Authenticator.RequestorType;
import java.sql.Connection;
import java.sql.DriverManager;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;



public class AppInitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ServletContext sc = this.getServletContext();
		try {
			Class.forName(sc.getInitParameter("driver"));
			Connection con = DriverManager.getConnection(sc.getInitParameter("url"),sc.getInitParameter("username")
					,sc.getInitParameter("password"));
			
			sc.setAttribute("con", con);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	
	public void destroy() {
		super.destroy();
		
		Connection con = (Connection)this.getServletContext().getAttribute("con");
		
		try {
			if(con != null && con.isClosed() == false) con.close();
		}catch(Exception e) {
			
		}
	}

}
