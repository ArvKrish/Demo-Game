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
	${Message}
	<br />
	<div class="form">
		<form action="get-opponent" method="post">
			<label for="oname">Name </label> <input name="oname" type="text"
				max="10" placeholder="Enter here" size="15" maxlength="11" required>
			<input type="submit" name="submit" value="value">
		</form>

		<br /> (or) <br /> <br />

		<form action="get-opponent" method="post">
			<label for="key">Code </label> <input name="key" type="number"
				min="1" max="100" required> <input type="submit"
				name="submit" value="key">
		</form>

	</div>

</body>
</html>