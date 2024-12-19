package dao;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import vo.Student;

public class StudentDao {
	Connection con;
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	public int update(Student st) throws Exception {
		PreparedStatement pst = null;
		String query = null;
		ResultSet rs = null;
		try {
			query = "UPDATE student SET name=?,password=?,subject=?,content=?,mod_date=now() WHERE id=?";
			pst = con.prepareStatement(query);
			pst.setString(1,st.getName());
			pst.setString(2,st.getPwd());
			pst.setString(3,st.getSubject());
			pst.setString(4,st.getContent());
			pst.setInt(5,st.getId());
			return pst.executeUpdate();
		}catch(Exception e) {
			throw e;
		}finally {
			try {
				if(rs != null) rs.close();
				if(pst != null) pst.close();
			}catch(SQLException e) {
				
			}
		}
	}
	
	public Student selectOne(int id) throws Exception {
		PreparedStatement pst = null;
		String query = null;
		ResultSet rs = null;
		try {
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
				return tmp;
			}
			
		}catch(Exception e) {
			throw e;
		}finally {
			try {
				if(rs != null) rs.close();
				if(pst != null) pst.close();
			}catch(SQLException e) {
				
			}
		}
	}
	public int delete(int id) throws Exception {
		PreparedStatement pst = null;
		String query = null;
		
		try {
			query = "DELETE FROM student WHERE id=?";
			pst = con.prepareStatement(query);
			pst.setInt(1, id);
			return pst.executeUpdate();
		}catch(Exception e) {
			throw e;
		}finally {
			try {
				if(pst != null) pst.close();
			}catch(SQLException e) {
				
			}
		}
	}
	
	public int insert(Student st) throws Exception {
		PreparedStatement pst = null;
		String query = null;
		
		try {
			query = "INSERT INTO student SET name=?,password=?,subject=?,content=?,cre_date=now(),mod_date=now()";
			pst = con.prepareStatement(query);
			pst.setString(1,st.getName());
			pst.setString(2, st.getPwd());
			pst.setString(3,st.getSubject());
			pst.setString(4,st.getContent());
			
			return pst.executeUpdate();
		}catch(Exception e) {
			throw e;
		}finally {
			try {
				if(pst != null) pst.close();
			}catch(SQLException e) {
				
			}
		}
	}
	
	public ArrayList<Student> selectAll() throws Exception{
		PreparedStatement pst = null;
		String query = null;
		ResultSet rs = null;
		
		try {
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
			return student;
		}catch(Exception e) {
			throw e;
		}finally {
			try {
				if(rs != null) rs.close();
				if(pst != null) pst.close();
			}catch(SQLException e) {
				
			}
		}
	}
}
