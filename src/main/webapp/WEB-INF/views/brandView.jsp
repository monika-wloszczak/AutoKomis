<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!Doctype html>
<html>
<head>

<link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet">
<meta charset="UTF-8" />
<style>
table, tr, td {
	border: 1px solid black;
	align: center;
}
</style>
<title>Found cars:</title>
</head>
<body>
	<header>
		<div>
			<c:choose>
				<c:when test="${logged}">
					<a href="logout" style="float: right">Log Out</a>
				</c:when>
			</c:choose>

			<h1>

				<img src="<c:url value="/resources/images/head111.png"/>"
					style="opacity: 0.7"> Auto Komis
			</h1>

		</div>
	</header>
	<table>
		<c:forEach items="${findCars}" var="car">
			<tr>
				<td><c:out value="${car.brand}" /></td>
				<td><c:out value="${car.model}" /></td>
				<td><c:out value="${car.shape}" /></td>
				<td><c:out value="${car.engineSize}" /></td>
				<td><c:out value="${car.horsepower}" /></td>
				<td><c:out value="${car.price}" /></td>
				<c:choose>
					<c:when test="${logged}">
						<td><a href="remove/${car.id}">Remove</a></td>
						<td><a href="update/${car.id}">Edit</a>
					</c:when>
				</c:choose>
			</tr>

		</c:forEach>
	</table>
	<a href="/AutoKomis">Back</a>
</body>
</html>