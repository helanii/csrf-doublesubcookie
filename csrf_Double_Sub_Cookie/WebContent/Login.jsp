<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<center><h2>Login page</h2>
	<br/>
	<form method="post" action="MainController">
		<label>User name : </label><input type="text" name="username" /> <br> <br>
		<label>Password  : </label><input type="password" name="password"/> <br><br>
		<c:if test="${not empty sessionScope.invalidCredentials}">
			<div id="message" style="color:red;">Incorrect User name And Password!</div>
		</c:if>
		<input type="submit" value="Login">
	</form></center>
</body>
</html>