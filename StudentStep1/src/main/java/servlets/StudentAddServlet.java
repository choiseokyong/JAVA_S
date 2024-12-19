package servlets;

import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Authenticator.RequestorType;
import java.sql.Connection;
import java.sql.PreparedStatement;


@WebServlet("/student/add")
public class StudentAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/student/StudentInsert.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		PreparedStatement pst = null;
		String query = null;
		PrintWriter out = response.getWriter();
		
		try {
			ServletContext sc = this.getServletContext();
			con = (Connection)sc.getAttribute("con");
			query = "INSERT INTO student SET name=?,password=?,subject=?,content=?,cre_date=now(),mod_date=now()";
			pst = con.prepareStatement(query);
			pst.setString(1,request.getParameter("name"));
			pst.setString(2, request.getParameter("pwd"));
			pst.setString(3,request.getParameter("subject") );
			pst.setString(4,request.getParameter("content") );
			
			pst.executeUpdate();
			response.sendRedirect("list");
		}catch(Exception e) {
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		}
	}

}
