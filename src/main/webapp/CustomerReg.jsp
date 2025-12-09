<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<center>
			<h1>
				<%
					String data = (String)request.getAttribute("msg");
					out.println("<center><h3>"+data+"</h3></center><br>");
				%>
			</h1>
			
			
			<jsp:include page="CustomerLogin.html"></jsp:include>
</body>
</html>