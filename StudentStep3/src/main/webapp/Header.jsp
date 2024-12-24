<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="student" class="vo.Student" scope="session"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	div#header{
		background-color: skyblue;
		color:#fff;
		height: 20px;
		padding: 5px;
	}
	span{
		float: right;
		color:black;
	}
</style>
</head>
<body>
	<div id="header">
		WPMS(Web Project Management System)
		<span>
			<% if(student.getName() == null){  %>
			<a href="<%=request.getContextPath() %>/auth/login">Login</a>
			<% }else{ %>
			<a href="<%=request.getContextPath() %>/auth/logout">Logout</a>
			<% } %>
		</span>
	</div>
</body>
</html>