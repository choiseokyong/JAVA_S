<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList,vo.Student"%>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>
    <jsp:include page="/Header.jsp"/>
    <%-- <jsp:useBean id="students" class="java.util.ArrayList" type="java.util.ArrayList<vo.Student>" scope="request" /> --%>
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
	<c:forEach var="s" items="${requestScope.students}">
		<tr>
			<td><c:out value="${s.id}"></c:out> </td>
			<td><a href="update?no=${s.id}"><c:out value="${s.name}"></c:out></a></td>
			<td><c:out value="${s.subject}"></c:out></td>
			<td><c:out value="${s.cre_date}"></c:out></td>
		</tr>
	</c:forEach>
	<%-- <%
		/* ArrayList<Student> student = (ArrayList<Student>)request.getAttribute("student"); */
	
		for(Student st : students){
	%>
			<tr>
				<td><%=st.getId() %></td>
				<td><a href="update?id=<%=st.getId()%>"><%=st.getName() %></a></td>
				<td><%=st.getSubject() %></td>
				<td><%=st.getCre_date() %></td>
			</tr>
	<%	} %> --%>
	</table>
</body>
</html>
<jsp:include page="/Tail.jsp"/>