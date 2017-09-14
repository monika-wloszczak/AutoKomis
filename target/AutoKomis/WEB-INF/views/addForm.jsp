<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Add a car:</title>
</head>
<body>

	<form:form method="post" modelAttribute="Car">
		<form:errors path="*"></form:errors>
		<p><form:label path="brand">brand</form:label>
		<form:select path="brand.id">

			<form:options items="${brand}" itemLabel="name" itemValue="id" />
		</form:select></p>
		<p><form:label path="model">model</form:label>
		<form:input path="model" id="model" /></p>
		<p><form:label path="engineSize">engine Size</form:label>
		<form:input path="engineSize" id="engineSize" /></p>
		<p><form:label path="horsepower">horsepower</form:label>
		<form:input type="horsepower" path="horsepower" id="horsepower" /></p>
		<p><form:label path="price">price</form:label>
		<form:input type="price" path="price" id="price" /></p>
		Combi<form:radiobutton path="shape" value="combi" />
		Hatchback<form:radiobutton path="shape" value="hatchback" />
		Cabrio<form:radiobutton path="shape" value="cabrio" />
		SUV<form:radiobutton path="shape" value="suv" />
		<input type="submit"></input>
	</form:form>
</body>
</html>