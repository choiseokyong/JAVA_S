package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/auth/login.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String query = null;
		
		try {
			ServletContext sc = this.getServletContext();
			con = (Connection)sc.getAttribute("con");
			query = "SELECT * FROM student WHERE name=?,password=?";
			pst = con.prepareStatement(query);
			pst.setString(1, request.getParameter("name"));
			pst.setString(2, request.getParameter("pwd"));
			rs = pst.executeQuery();
			
			if(!rs.next()) {
				
			}else {
				
			}
		}catch(Exception e) {
			
		}finally {
			try {
				if(rs != null) rs.close();
				if(pst != null) pst.close();
				if(con != null) con.close();
			}catch(SQLException e) {
				
			}
		}
	}

}
