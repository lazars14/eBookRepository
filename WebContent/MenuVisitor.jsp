<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/overlay.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:setBundle basename="messages.messages"/>
<c:set var="language" value="${sessionScope.language}"/>
<c:if test="${language==null}">
	<c:set var="language" value="en" scope="request"/>
</c:if>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages.messages"/>

<jsp:useBean id="userCategory" type="entities.Category" scope="request"/>
<jsp:useBean id="userCategories" type="java.util.List" scope="request"/>
<jsp:useBean id="userBooksForCategory" type="java.util.List" scope="request"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="naslovProzora"/></title>
<link rel="icon" href="images/icon.png"></link>
</head>
<body>
	<h1><fmt:message key="kategorije"/></h1>
	<table border=1>
		<tr>
			<c:forEach items="${ userCategories }" var="i">
				<th><a href="MenuVisitorServlet?id=${ i.categoryId }"><c:out value="${ i.categoryName }"/></a></th>
			</c:forEach>
		</tr>
	</table>
	
	<h2><fmt:message key="kategorija"/>: <c:out value="${ userCategory.categoryName }"/></h2>
	<table border=1>
		<c:set var="rb3" scope="page" value="${1}"/>
		<tr>
			<th><fmt:message key="redniBroj"/></th>
			<th><fmt:message key="naslov"/></th>
			<th><fmt:message key="autor"/></th>
			<th><fmt:message key="kljucneReci"/></th>
			<th><fmt:message key="godinaIzdavanja"/></th>
			<th><fmt:message key="jezik"/></th>
			<th><fmt:message key="skidanje"/></th>
			<th></th>
		</tr>
	<c:forEach items="${ userBooksForCategory }" var="i">
		<tr>
			<td align="center"><c:out value="${rb3}"></c:out></td>
			<td align="center">${ i.eBooktitle }</td>
			<td align="center">${ i.eBookauthor }</td>
			<td align="center">${ fn:substring(i.eBookkeywords, 0, 50).concat("...") }</td>
			<td align="center">${ i.eBookpublicationyear }</td>
			<td align="center">${ i.eBooklanguage }</td>
			<td align="center"><img src="images/download.png" onclick="openOverlay('banner')"></td>
		</tr>
		<c:set var="rb3" scope="page" value="${rb3 + 1}"/>
	</c:forEach>
	</table>
	
	<div id="banner" class="overlay">
        <a href="javascript:void(0)" class="closebtn" onclick="closeOverlay('banner')">&times;</a>
        <div class="overlay-content">
            <h2>Morate da se registrujete kako biste preuzili sadrzaj!</h2>
            </br>
            <a href="#">Registracija</a>
        </div>
    </div>
	
	
	<a href="ChangeLanguageServlet?lang=en&role=asdf" name="language">ENG</a>
	<a href="ChangeLanguageServlet?lang=sr&role=asdf" name="language">SRP</a>
	<a href="BookSearchPrepareServlet"><fmt:message key="pretragaKnjiga"/></a>
</body>
</html>