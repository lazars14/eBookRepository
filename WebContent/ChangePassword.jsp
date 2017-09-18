<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
    <c:when test="${sessionScope.admin == null && sessionScope.subscriber == null}">
    	<c:redirect url="MenuVisitorServlet"/>
    </c:when>
    <c:when test="${sessionScope.subscriber != null}">
    	<c:set var="location" scope="page" value="MenuSubscriberServlet"/>	
    </c:when>
    <c:when test="${sessionScope.admin != null}">
    	<c:set var="location" scope="page" value="MenuAdminServlet"/>		
    </c:when>
</c:choose>

<c:set var="language" value="${sessionScope.language}"/>
<c:if test="${language==null}">
	<c:set var="language" value="en" scope="session"/>
</c:if>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="messages.messages"/>

<script type="text/javascript">
function passwordValidation(message){
	var password1 = document.getElementById("newPassword").value;
	var password2 = document.getElementById("repeatNewPassword").value;

	if (password1 == password2) {
		return true;
	} else {
		alert(message);
		return false;
	}
}
</script>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="naslovProzora"/></title>
<link rel="icon" href="images/icon.png"></link>
</head>
<body>
	<h1><fmt:message key="promenaLozinke"/></h1>
	<form action="ChangePasswordServlet" onsubmit="return passwordValidation('<fmt:message key="lozinkeNejednake"/>')" method="post">
		<table>
			<tr>
				<td><fmt:message key="novaLozinka"/>:</td>
				<td><input type="password" maxlength="10" id="newPassword" name="newPassword" required="required" oninvalid="this.setCustomValidity('<fmt:message key="unesiteNovuLozinku"/>')" onchange="this.setCustomValidity('')"></td>
			</tr>
			<tr>
				<td><fmt:message key="ponoviNovuLozinku"/>:</td>
				<td><input type="password" maxlength="10" id="repeatNewPassword" name="repeatNewPassword" required="required" oninvalid="this.setCustomValidity('<fmt:message key="ponoviteNovuLozinku"/>')" onchange="this.setCustomValidity('')"></td>
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