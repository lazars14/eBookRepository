<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
<title><fmt:message key="naslovProzora"/></title>
<link rel="icon" href="images/icon.png"></link>
</head>
<body>
	<h1><fmt:message key="prijava"/></h1>
	<form action="LoginServlet" method="POST">
    	<table id="loginTable">
        	<tr>
				<td><fmt:message key="korisnickoIme"/>:</td>
                <td><input type="text" maxlength="10" name="username" required="required" oninvalid="this.setCustomValidity('<fmt:message key="unesiteKorisnickoIme"/>')" onchange="this.setCustomValidity('')" autofocus></td>
            </tr>
            <tr>
            	<td><fmt:message key="lozinka"/>:</td>
                <td><input type="password" maxlength="10" name="password" required="required" oninvalid="this.setCustomValidity('<fmt:message key="unesiteLozinku"/>')" onchange="this.setCustomValidity('')"></td>
            </tr>
            <tr>
            	<td>&nbsp;</td>
                <td><button type="submit"><fmt:message key="prijaviSe"/></button></td>
            </tr>
		</table>
	</form>
	<a href="ChangeLanguageServlet?lang=en" name="language">ENG</a>
	<a href="ChangeLanguageServlet?lang=sr" name="language">SRP</a>
	<a href="MenuVisitorServlet"><fmt:message key="nastaviBezPrijave"/></a>
</body>
</html>