<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>

<html>
<head>
<title>RPG</title>
<spring:url value="/resources/css/index.css" var="mainCss" />

	<link href="${mainCss}" rel="stylesheet" />
</head>
<body>
	<div class="container">
		<h2>RPG</h2>
		<p>A small RPG with a Player and as much villains as you want.
			Strike, compliment, boost and much more.</p>
		<a href="/DemoGame/Login"><button class="login">Get
				Started</button></a>
	</div>
</body>
</html>
