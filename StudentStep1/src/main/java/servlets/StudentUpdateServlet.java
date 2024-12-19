package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		PreparedStatement pst = null;
		PrintWriter out = response.getWriter();
		String query = null;
		ResultSet rs = null;
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			ServletContext sc = this.getServletContext();
			con = (Connection)sc.getAttribute("con");
			query = "SELECT * FROM student WHERE id=?";
			pst = con.prepareStatement(query);
			pst.setInt(1,id);
			rs = pst.executeQuery();
			
			if(!rs.next()) {
				throw new ServletException("해당정보없음.");
			}else {
				Student tmp = new Student();
				tmp.setId(rs.getInt("id"));
				tmp.setName(rs.getString("name"));
				tmp.setPwd(rs.getString("password"));
				tmp.setSubject(rs.getString("subject"));
				tmp.setContent(rs.getString("content"));
				tmp.setCre_date(rs.getDate("cre_date"));
				tmp.setMod_date(rs.getDate("mod_date"));
				
				request.setAttribute("student", tmp);
				RequestDispatcher rd = request.getRequestDispatcher("/student/StudentUpdate.jsp");
				rd.forward(request, response);
			}
			
		}catch(Exception e) {
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		}finally {
			try {
				if(rs != null) rs.close();
				if(pst != null) pst.close();
			}catch(SQLException e) {
				request.setAttribute("error", e);
				RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
				rd.forward(request, response);
			}
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		PreparedStatement pst = null;
		PrintWriter out = response.getWriter();
		String query = null;
		ResultSet rs = null;
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			ServletContext sc = this.getServletContext();
			con = (Connection)sc.getAttribute("con");
			query = "UPDATE student SET name=?,password=?,subject=?,content=?,mod_date=now() WHERE id=?";
			pst = con.prepareStatement(query);
			pst.setString(1,request.getParameter("name"));
			pst.setString(2,request.getParameter("pwd"));
			pst.setString(3,request.getParameter("subject"));
			pst.setString(4,request.getParameter("content"));
			pst.setInt(5,id);
			pst.executeUpdate();
			
			response.sendRedirect("list");
		}catch(Exception e) {
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
			
		}finally {
			try {
				if(rs != null) rs.close();
				if(pst != null) pst.close();
			}catch(SQLException e) {
				request.setAttribute("error", e);
				RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
				rd.forward(request, response);
			}
		}
	}

}
