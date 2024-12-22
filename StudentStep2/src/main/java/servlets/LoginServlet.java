package servlets;

import java.io.IOException;
import java.sql.Connection;

import dao.StudentDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vo.Student;


@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/auth/LoginForm.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			ServletContext sc = request.getServletContext();
			StudentDao studentDao = (StudentDao) sc.getAttribute("studentDao");
			
			Student student =studentDao.login(request.getParameter("name"), request.getParameter("pwd"));
			
			if(student != null) {
				HttpSession session = request.getSession();
				session.setAttribute("student", student);
				response.sendRedirect("../student/list");
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("/auth/LoginFail.jsp");
				rd.forward(request, response);
			}
		}catch(Exception e) {
			
		}
	}

}
