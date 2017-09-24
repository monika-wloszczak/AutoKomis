
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!Doctype html>
<html>
<head>

<link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet">
<meta charset="UTF-8" />
</head>
<body>
	<header>
		<div>
			<h1>

				<img src="<c:url value="/resources/images/head111.png"/>"
					style="opacity: 0.7"> Auto Komis
			</h1>
			<a href="car/login" style=""right">Admin</a>
		</div>
		
	</header>
	<div id="collection">
		<p class="choice">Find by car shape!</p>
		<div id="combi" class="shape">
			<p>combi</p>
			<a href="car/byShape?shape=combi"><img
				src="<c:url value="/resources/images/combi.jpeg"/>"
				alt="tekst alternatywny" /></a>
		</div>
		<div id="hatchback" class="shape">
			<p>hatchback</p>
			<a href="car/byShape?shape=hatchback"><img
				src="<c:url value="/resources/images/hatchback.jpg"/>"
				alt="tekst alternatywny" /></a>
		</div>
		<div id="cabrio" class="shape">
			<p>cabrio</p>
			<a href="car/byShape?shape=cabrio"><img
				src="<c:url value="/resources/images/cabrio.jpeg"/>"	
				alt="tekst alternatywny" /></a>
		</div>
		<div id="suv" class="shape">
			<p>suv</p>
			<a href="car/byShape?shape=suv"><img
				src="<c:url value="/resources/images/terenowe.jpg"/>"
				alt="tekst alternatywny" /></a>
		</div>
	</div>


	<div id="brand">
		<p class="choice">Find by Brand name
		<p>
		<ul style="list-style:none">
			<li><a href="car/byBrand?name=Audi">Audi</a></li>
			<li><a href="car/byBrand?name=BMW">BMW</a></li>
			<li><a href="car/byBrand?name=Honda">Honda</a></li>
			<li><a href="car/byBrand?name=Hyundai">Hyundai</a></li>
			<li><a href="car/byBrand?name=Mercedes">Mercedes</a></li>
			<li><a href="car/byBrand?name=Opel">Opel</a></li>
			<li><a href="car/byBrand?name=Toyota">Toyota</a></li>
		</ul>
	</div>
	<p class="choice">Find by Engine size and Horsepower</p>
	<form action="car/byHorsePowerOrEngine" method="post">
		<label>Engine</label> <input type="text" name="engineSize" id="engineSize"/>
		<form:errors path="engineSize"></form:errors>
		<label>Horsepower</label> <input type="text" name="horsepower"
			id="horsepower"> <form:errors path="horsepower"></form:errors>
			<input type="submit" name="submitEngine"/>
			
	</form>
	<p class="choice">Find by Price:</p>
	<form action="car/byPrice" method="post">

		<label>Lower than</label> 
		<input type="radio" id="lower" name="how" value="lower" checked/> 
		<label>Higher than</label> 
		<input type="radio"	id="higher" name="how" value="higher"/> 
		<input type="text" name="price" id="price"/> 
		<input type="submit" name="submitPrice"/>

	</form>
	<a href="car/contact">Sell your car!</a>
</body>
</html>