<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    

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

<jsp:useBean id="languages" type="java.util.List" scope="page"/>

<c:set var="language" value="${sessionScope.language}"/>
<c:if test="${language==null}">
	<c:set var="language" value="en" scope="session"/>
</c:if>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="messages.messages"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="pretragaKnjiga"/></title>
</head>
<body>

<h1><fmt:message key="pretragaKnjiga"/></h1>
</br>

<form action="BookSearchServlet" method="post">
	<table border="1">
	<tr>
		<td><fmt:message key="pretragaPoNaslovu"/></td>
		<td><input type="text" name="title" maxLength="80"/></td>
	</tr>
	<tr>
		<td><fmt:message key="pretragaPoAutoru"/></td>
		<td><input type="text" name="author" maxLength="120"/></td>
	</tr>
	<tr>
		<td><fmt:message key="pretragaPoKljucnimRecima"/></td>
		<td><input type="text" name="keyword" maxLength="120"/></td>
	</tr>
	<tr>
		<td><fmt:message key="pretragaPoSadrzaju"/></td>
		<td><input type="text" name="content" maxLength="100"/></td>
	</tr>
	<tr>
		<td><fmt:message key="pretragaPoJeziku"/></td>
		<td>
			<select name="languageSelect">
    			<c:forEach var="i" items="${ languages }">
     				<option value="${ i.languageId }">${ i.languageName }</option>
    			</c:forEach>
			</select>
		</td>
	</tr>
	
	</br>
	
	<!-- 
	
	<ovde mora ajax poziv za rezultate, ne mere drugacije
	
	<div id="searchResultDiv">
	
	</div>
	
	
	 -->
	
	<button type="submit"><fmt:message key="pretrazi"/></button>
	
</table>
</form>



<a href="${ location }"><fmt:message key="glavniMeni"/></a>
<c:if test="${sessionScope.admin != null || sessionScope.subscriber != null}">
    <a href="LogoutServlet"><fmt:message key="odjava"/></a>
</c:if>

</body>
</html>