<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>RPG</title>
<spring:url value="/resources/css/index.css" var="mainCss" />
<link href="${mainCss}" rel="stylesheet" />
</head>
<body>
	<div class="loginbox">
		<form action="login-user" class="formLogin" method="post"><br><br>
			<label for="username">&emsp; E-mail &emsp; </label> <input type="text" name="email"
				placeholder="Enter E-mail" minlength="8" required><br><br><br> <label
				for="password">&emsp; Password</label> <input type="password"
				name="password" placeholder="Enter password" minlength="8" required><br><br><br>
			&emsp; <input type="submit" value="Login">
		</form><br><br>
		&emsp; <a href="/DemoGame/register">New to this game? Register here</a>
	</div>
</body>
</html>
