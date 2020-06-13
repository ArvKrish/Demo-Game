<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>RPG - Welcome ${User}</title>
<spring:url value="/resources/css/index.css" var="mainCss" />
<link href="${mainCss}" rel="stylesheet" />
</head>
<body>
	<div class="header">
		<h3>Welcome ${User}</h3>
	</div>
	<div class="form">
		<form action="Action" method="post">
			<label for="oname">Villain Name&emsp;</label> <input name="oname"
				type="text" placeholder="Enter here" size="20"
				minlength="3" maxlength="10" required> <br /> <br /> <label for="key">Code
				&ensp; &ensp; &ensp; &ensp; &ensp;&ensp; </label> <input name="key"
				type="number" min="1" max="100" size="20" placeholder="Enter a code"
				required> <input type="submit" value="Submit">

		</form>
	</div>
</body>
</html>