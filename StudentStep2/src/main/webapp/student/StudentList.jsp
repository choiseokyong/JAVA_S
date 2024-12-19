<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList,vo.Student"%>
    <jsp:include page="/Header.jsp"/>
    <jsp:useBean id="student" class="java.util.ArrayList" type="java.util.ArrayList<vo.Student>" scope="request" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>학생 목록</h1>
	<a href="add">등록</a>
	<hr>
	<table border="1">
	<%
		/* ArrayList<Student> student = (ArrayList<Student>)request.getAttribute("student"); */
	
		for(Student st : student){
	%>
			<tr>
				<td><%=st.getId() %></td>
				<td><a href="update?id=<%=st.getId()%>"><%=st.getName() %></a></td>
				<td><%=st.getSubject() %></td>
				<td><%=st.getCre_date() %></td>
			</tr>
	<%	} %>
	</table>
</body>
</html>
<jsp:include page="/Tail.jsp"/>