<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
    <c:when test="${sessionScope.admin == null && sessionScope.subscriber == null}">
    	<c:redirect url="MenuVisitorServlet"/>
    </c:when>
    <c:when test="${sessionScope.subscriber != null}">
        <c:set var="firstname" scope="page" value="${sessionScope.subscriber.appUserFirstname}"/>
    	<c:set var="lastname" scope="page" value="${sessionScope.subscriber.appUserLastname}"/>
    	<c:set var="location" scope="page" value="MenuSubscriberServlet"/>	
    </c:when>
    <c:when test="${sessionScope.admin != null}">
    	<c:set var="firstname" scope="page" value="${sessionScope.admin.appUserFirstname}"/>
    	<c:set var="lastname" scope="page" value="${sessionScope.admin.appUserLastname}"/>
    	<c:set var="location" scope="page" value="MenuAdminServlet"/>		
    </c:when>
</c:choose>

<c:set var="language" value="${sessionScope.language}"/>
<c:if test="${language==null}">
	<c:set var="language" value="en" scope="session"/>
</c:if>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="messages.messages"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="naslovProzora"/></title>
<link rel="icon" href="images/icon.png"></link>
</head>
<body>
	<h1><fmt:message key="promenaLicnihPodataka"/></h1>
	<form action="ChangePersonalInfoServlet" method="post">
		<table>
			<tr>
				<td><fmt:message key="ime"/>:</td>
				<td><input type="text" maxlength="30" name="firstname" value="${ firstname }" required="required" oninvalid="this.setCustomValidity('<fmt:message key="unesiteIme"/>')" onchange="this.setCustomValidity('')"></td>
			</tr>
			<tr>
				<td><fmt:message key="prezime"/>:</td>
				<td><input type="text" maxlength="30" name="lastname" value="${ lastname }" required="required" oninvalid="this.setCustomValidity('<fmt:message key="unesitePrezime"/>')" onchange="this.setCustomValidity('')"></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
                <td><button type="submit"><fmt:message key="ok"/></button></td>
			</tr>
		</table>
	</form>
	
	<a href="${ location }"><fmt:message key="glavniMeni"/></a>
	<a href="LogoutServlet"><fmt:message key="odjava"/></a>
</body>
</html>