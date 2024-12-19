package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vo.Student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import dao.StudentDao;


@WebServlet("/student/add")
public class StudentAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/student/StudentInsert.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		
		try {
			ServletContext sc = this.getServletContext();
			con = (Connection)sc.getAttribute("con");
			StudentDao studentDao = new StudentDao();
			studentDao.setConnection(con);
			
			Student st = new Student();
			st.setName(request.getParameter("name"));
			st.setPwd(request.getParameter("pwd"));
			st.setSubject(request.getParameter("subject"));
			st.setContent(request.getParameter("content"));
			studentDao.insert(st);
			response.sendRedirect("list");
		}catch(Exception e) {
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		}
	}

}
