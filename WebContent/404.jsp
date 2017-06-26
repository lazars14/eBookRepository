<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:setBundle basename="messages.messages"/>

<c:choose>
    <c:when test="${sessionScope.subscriber != null}">
    	<c:set var="location" scope="page" value="MenuSubscriberServlet"/>	
    </c:when>
    <c:when test="${sessionScope.admin != null}">
    	<c:set var="location" scope="page" value="MenuAdminServlet"/>		
    </c:when>
    <c:otherwise>
    	<c:set var="location" scope="page" value="MenuVisitorServlet"/>		
    </c:otherwise>
</c:choose>

<html>
	<head>
		<title><fmt:message key="naslovProzora"/></title>
		<link rel="icon" href="images/icon.png"></link>
		<meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
		<meta HTTP-EQUIV="Expires" CONTENT="-1">
	</head>
	<body>
		<h1><fmt:message key="stranicaNijePronadjenaNaslov"/></h1>
		<p><fmt:message key="stranicaNijePronadjenaPoruka"/></p>
		<a href="${ location }"><fmt:message key="glavniMeni"/></a>
	</body>
</html>	