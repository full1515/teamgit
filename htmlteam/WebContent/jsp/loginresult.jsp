<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
<%request.setCharacterEncoding("UTF-8"); %>
<%
String id=request.getParameter("id");
String pw=request.getParameter("pw");
%>

<h2>아이디는 <%=id %>이고, 비밀번호는 <%=pw %>입니다.</h2>
</body>
</html>