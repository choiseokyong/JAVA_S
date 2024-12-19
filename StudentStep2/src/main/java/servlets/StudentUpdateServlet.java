package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.StudentDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vo.Student;


@WebServlet("/student/update")
public class StudentUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			ServletContext sc = this.getServletContext();
			con = (Connection)sc.getAttribute("con");
			StudentDao studentDao = new StudentDao();
			studentDao.setConnection(con);
			Student tmp =studentDao.selectOne(id);
			
			if(tmp == null) {
				throw new ServletException("해당정보없음.");
			}else {
				request.setAttribute("student", tmp);
				RequestDispatcher rd = request.getRequestDispatcher("/student/StudentUpdate.jsp");
				rd.forward(request, response);
			}
			
		}catch(Exception e) {
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			ServletContext sc = this.getServletContext();
			con = (Connection)sc.getAttribute("con");
			StudentDao studentDao = new StudentDao();
			studentDao.setConnection(con);
			Student st = new Student();
			st.setId(id);
			st.setName(request.getParameter("name"));
			st.setPwd(request.getParameter("pwd"));
			st.setSubject(request.getParameter("subject"));
			st.setContent(request.getParameter("content"));
			studentDao.update(st);
			
			response.sendRedirect("list");
		}catch(Exception e) {
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		}
	}

}
