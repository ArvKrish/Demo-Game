<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
		<form:form action="register-user" class="formRegister"
			modelAttribute="user">
			<br>Username : <form:input path="username" />
			<br>
			<form:errors path="username" cssClass="error" />

			<br>Email : &ensp; &ensp; <form:input
				path="email" type="email" />
			<br>
			<form:errors path="email" cssClass="error" />
			<br>

			<br>Password : <form:input path="password"
				type="password" />
			<br>
			<form:errors path="password" cssClass="error" />
			<br>
			<input type="submit" value="Register" />
		</form:form>
		<br>&ensp; &ensp; <a href="/DemoGame/login">Back to login</a><br> <br>
		<br> <br>
	</div>
</body>
</html>
