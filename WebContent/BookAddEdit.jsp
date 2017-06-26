<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${sessionScope.admin == null}">
	<c:redirect url="MenuVisitorServlet"/>
</c:if>


<c:set var="language" value="${sessionScope.language}"/>
<c:if test="${language==null}">
	<c:set var="language" value="en" scope="session"/>
</c:if>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="messages.messages"/>

<jsp:useBean id="eBookAddEdit" class="entities.Ebook" scope="request"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="naslovProzora"/></title>
<link rel="icon" href="images/icon.png"></link>
</head>
<body>
	<c:choose>
		<c:when test="${ requestScope.action == 'add' }">
			<h1><fmt:message key="dodajKnjigu"/></h1>
			<c:set var="servlet" scope="page" value="BookAddServlet"/> 
		</c:when>
		<c:otherwise>
			<h1><fmt:message key="izmeniKnjigu"/></h1>
			<c:set var="servlet" scope="page" value="BookEditServlet"/>
		</c:otherwise>
	</c:choose>
	
	<form action="${ servlet }" method="post">
		<table>
			<tr>
				<td><fmt:message key="naslov"/></td>
				<td><input type="text" maxlength="30" name="name" required="required" oninvalid="this.setCustomValidity('<fmt:message key="unesiteNazivKnjige"/>')" onchange="this.setCustomValidity('')" value="${eBookAddEdit.eBooktitle}"></td>
			</tr>
			<tr>
				<td><fmt:message key="autor"/></td>
				<td><input type="text" maxlength="30" name="name" required="required" oninvalid="this.setCustomValidity('<fmt:message key="unesiteNazivKnjige"/>')" onchange="this.setCustomValidity('')" value="${eBookAddEdit.eBooktitle}"></td>
			</tr>
			<tr>
				<td><fmt:message key="kljucneReci"/></td>
				<td><input type="text" maxlength="30" name="name" required="required" oninvalid="this.setCustomValidity('<fmt:message key="unesiteNazivKnjige"/>')" onchange="this.setCustomValidity('')" value="${eBookAddEdit.eBooktitle}"></td>
			</tr>
			<tr>
				<td><fmt:message key="godinaIzdavanja"/></td>
				<td><input type="text" maxlength="30" name="name" required="required" oninvalid="this.setCustomValidity('<fmt:message key="unesiteNazivKnjige"/>')" onchange="this.setCustomValidity('')" value="${eBookAddEdit.eBooktitle}"></td>
			</tr>
			<tr>
				<td><fmt:message key="jezik"/></td>
				<td><input type="text" maxlength="30" name="name" required="required" oninvalid="this.setCustomValidity('<fmt:message key="unesiteNazivKnjige"/>')" onchange="this.setCustomValidity('')" value="${eBookAddEdit.eBooktitle}"></td>
			</tr>
			<tr>
				<td><fmt:message key="fajl"/></td>
				<td></td>
			</tr>

			<tr>
				<td><input type="hidden" name="id" value="${eBookAddEdit.eBookcategory.categoryId}"></td>
				<td><input type="submit" name="submit" value="<fmt:message key="ok"/>"></td>				
			</tr>
		</table>
	</form>
	
	<a href="MenuAdminServlet"><fmt:message key="glavniMeni"/></a>
	<a href="LogoutServlet"><fmt:message key="odjava"/></a>
</body>
</html>