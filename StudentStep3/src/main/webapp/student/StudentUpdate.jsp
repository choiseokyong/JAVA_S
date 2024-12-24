<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="vo.Student"%>
    <jsp:include page="/Header.jsp"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	table,tr,th,td{
		border:1px solid black;
	}
</style>
</head>
<body>
	<%
		Student student = (Student)request.getAttribute("student");
	%>
	<h1>학생 등록</h1>
	<hr>
	<form action="update" method="post">
		<table>
			<tr><th>번호</th><td><input type="text" name="id" value="<%=student.getId()%>" readonly></td></tr>
			<tr><th>이름</th><td><input type="text" name="name" value="<%=student.getName()%>"></td></tr>
			<tr><th>비밀번호</th><td><input type="password" name="pwd" value="<%=student.getPwd()%>"></td></tr>
			<tr><th>제목</th><td><input type="text" name="subject" value="<%=student.getSubject()%>"></td></tr>
			<tr><th>내용</th><td><textarea rows="3" cols="20" name="content"><%=student.getContent()%></textarea></td></tr>
			<tr>
				<th colspan="2">
					<input type="submit" value="수정">
					<input type="reset" value="취소">
					<input type="button" value="삭제" onclick="location.href='delete?id=<%=student.getId()%>'">
				</th>			
			</tr>
		</table>
	</form>
</body>
</html>
<jsp:include page="/Tail.jsp"/>