<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>요청 처리 중 문제가 발생하였습니다. 
	잠시 후에 다시 요청하기 바랍니다.</p>
	<p>
		<% Exception e = (Exception)request.getAttribute("error"); %>
		<%=e.getMessage() %>
	</p>
</body>
</html>