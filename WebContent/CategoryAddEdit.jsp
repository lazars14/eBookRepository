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

<jsp:useBean id="categoryAddEdit" class="entities.Category" scope="request"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="naslovProzora"/></title>
<link rel="icon" href="images/icon.png"></link>
</head>
<body>
	<c:choose>
		<c:when test="${ requestScope.action == 'add' }">
			<h1><fmt:message key="dodajKategoriju"/></h1>
			<c:set var="servlet" scope="page" value="CategoryAddServlet"/>
		</c:when>
		<c:otherwise>
			<h1><fmt:message key="izmeniKategoriju"/></h1>
			<c:set var="servlet" scope="page" value="CategoryEditServlet"/>
		</c:otherwise>
	</c:choose>
	
	<form action="${ servlet }" method="post">
		<table>
			<tr>
				<td><fmt:message key="naziv"/></td>
				<td><input type="text" maxlength="30" name="name" required="required" oninvalid="this.setCustomValidity('<fmt:message key="unesiteNazivKategorije"/>')" onchange="this.setCustomValidity('')" value="${categoryAddEdit.categoryName}"></td>
			</tr>
			
			<tr>
				<td><input type="hidden" name="id" value="${categoryAddEdit.categoryId}"></td>
				<td><input type="submit" name="submit" value="<fmt:message key="ok"/>"></td>				
			</tr>
		</table>
	</form>
	
	<a href="MenuAdminServlet"><fmt:message key="pocetna"/></a>
	<a href="LogoutServlet"><fmt:message key="odjava"/></a>
</body>
</html>