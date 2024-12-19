<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<h1>학생 등록</h1>
	<hr>
	<form action="add" method="post">
		<table>
			<tr><th>이름</th><td><input type="text" name="name"></td></tr>
			<tr><th>비밀번호</th><td><input type="password" name="pwd"></td></tr>
			<tr><th>제목</th><td><input type="text" name="subject"></td></tr>
			<tr><th>내용</th><td><textarea rows="3" cols="20" name="content"></textarea></td></tr>
			<tr>
				<th colspan="2">
					<input type="submit" value="등록">
					<input type="reset" value="취소">
				</th>			
			</tr>
		</table>
	</form>
</body>
</html>
<jsp:include page="/Tail.jsp"/>