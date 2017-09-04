<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<link href="css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/overlay.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:choose>
    <c:when test="${sessionScope.subscriber != null}">
    	<c:set var="location" scope="page" value="MenuSubscriberServlet"/>
    	<c:set var="user" value="subscriber" scope="request"/>
    	<c:set var="subscriberCategory" value="${ sessionScope.subscriber.appUserCategoryId.categoryId }" scope="request"/>	
    </c:when>
    <c:when test="${sessionScope.admin != null}">
    	<c:set var="location" scope="page" value="MenuAdminServlet"/>
    	<c:set var="user" value="admin" scope="request"/>		
    </c:when>
    <c:otherwise>
    	<c:set var="location" scope="page" value="MenuVisitorServlet"/>
    	<c:set var="user" value="visitor" scope="request"/>		
    </c:otherwise>
</c:choose>

<c:set var="language" value="${sessionScope.language}"/>
<c:if test="${language == null}">
	<c:set var="language" value="en" scope="session"/>
</c:if>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="messages.messages"/>

<jsp:useBean id="booksResult" type="java.util.List" scope="request"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="pretragaKnjiga"/></title>
</head>
<body>
	<table border=1>
		<c:set var="rb3" scope="page" value="${1}"/>
		<tr>
			<th><fmt:message key="redniBroj"/></th>
			<th><fmt:message key="naslov"/></th>
			<th><fmt:message key="autori"/></th>
			<th><fmt:message key="kljucneReci"/></th>
			<th><fmt:message key="godinaIzdavanja"/></th>
			<th><fmt:message key="jezik"/></th>
			<th><fmt:message key="kategorija"/></th>
			<th><fmt:message key="skidanje"/></th>
			<th></th>
		</tr>
	<c:forEach items="${ booksResult }" var="i">
		<tr>
			<td align="center"><c:out value="${rb3}"></c:out></td>
			<td align="center">${ i.EBooktitle }</td>
			<td align="center">${ i.EBookauthor }</td>
			<td align="center">${ fn:substring(i.EBookkeywords, 0, 50).concat("...") }</td>
			<td align="center">${ i.EBookpublicationyear }</td>
			<td align="center">${ i.EBooklanguage.languageName }</td>
			<td align="center">${ i.EBookcategory.categoryName }
			
			<c:choose>
				<c:when test="${ user == 'admin' }">
					<td align="center"><a href="BookDownloadServlet?id=${ i.EBookid }"><img src="images/download.png"></a></td>
				</c:when>
				<c:when test="${ user == 'subscriber' }">
					<c:when test="${ subscriberCategory == i.EBookcategory.categoryId }">
						<td align="center"><a href="BookDownloadServlet?id=${ i.EBookid }"><img src="images/download.png"></a></td>
					</c:when>
					<c:otherwise>
						<td align="center"><a href="#"><img src="images/download.png" onclick="openOverlay('banner')"></a></td>
					</c:otherwise>
				</c:when>
				<c:otherwise>
					<td align="center"><a href="#"><img src="images/download.png" onclick="openOverlay('banner')"></a></td>
				</c:otherwise>
				
			</c:choose>
			
		</tr>
		<c:set var="rb3" scope="page" value="${rb3 + 1}"/>
	</c:forEach>
	</table>
	<br/>
	
	<div id="banner" class="overlay">
        <a href="javascript:void(0)" class="closebtn" onclick="closeOverlay('banner')">&times;</a>
        <div class="overlay-content">
            <h2><fmt:message key="registracijaPoruka"/></h2>
            </br>
            <a href="#"><fmt:message key="registracija"/></a>
        </div>
    </div>
	
	
	
	<a href="${ location }"><fmt:message key="pocetna"/></a>
	<c:if test="${sessionScope.admin != null || sessionScope.subscriber != null}">
    	<a href="LogoutServlet"><fmt:message key="odjava"/></a>
	</c:if>
</body>
</html>