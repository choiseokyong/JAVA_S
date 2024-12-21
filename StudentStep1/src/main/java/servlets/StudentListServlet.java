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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/student/list")
public class StudentListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		PreparedStatement pst = null;
		String query = null;
		ResultSet rs = null;
		PrintWriter out = response.getWriter();
		
		try {
			ServletContext sc = request.getServletContext();
			con = (Connection)sc.getAttribute("con");
			query = "SELECT * FROM student";
			pst = con.prepareStatement(query);
			rs = pst.executeQuery();
			
			ArrayList<Student> student = new ArrayList<Student>();
			while(rs.next()) {
				Student tmp = new Student();
				tmp.setId(rs.getInt("id"));
				tmp.setName(rs.getString("name"));
				tmp.setPwd(rs.getString("password"));
				tmp.setSubject(rs.getString("subject"));
				tmp.setContent(rs.getString("content"));
				tmp.setCre_date(rs.getDate("cre_date"));
				tmp.setMod_date(rs.getDate("mod_date"));
				student.add(tmp);
			}
			
			request.setAttribute("student", student);
			RequestDispatcher rd = request.getRequestDispatcher("/student/StudentList.jsp");
			rd.forward(request, response);
				
			
		}catch(Exception e) {
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		}finally {
			try {
				if(rs != null) rs.close();
				if(pst != null) pst.close();
			}catch(SQLException e) {
				out.print(e);
			}
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
