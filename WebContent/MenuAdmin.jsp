<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page import="entities.Category" %>
<%@ page import="entities.BookFile" %>
<%@ page import="java.util.List" %>

<c:if test="${sessionScope.admin == null}">
	<c:redirect url="MenuVisitorServlet"/>
</c:if>

<c:set var="language" value="${sessionScope.language}"/>
<c:if test="${language==null}">
	<c:set var="language" value="en" scope="session"/>
</c:if>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="messages.messages"/>

<jsp:useBean id="adminCategory" type="entities.Category" scope="request"/>
<jsp:useBean id="adminCategories" type="java.util.List" scope="request"/>
<jsp:useBean id="adminBooks" type="java.util.List" scope="request"/>

<script type="text/javascript">
function delMovie(message){
	if(confirm(message)){
        return true;
    }
    else{
        return false;
    }
}
</script>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="naslovProzora"/></title>
<link rel="icon" href="images/icon.png"></link>
</head>
<body>
	<h1><fmt:message key="adminMeni"/></h1>
	
	<h1><fmt:message key="izmenaKategorija"/></h1>
	<table border=1>
		<c:set var="rb3" scope="page" value="${1}"/>
		<tr>
			<th><fmt:message key="redniBroj"/></th>
			<th><fmt:message key="naziv"/></th>
			<th><fmt:message key="obrisano"/></th>
			<th colspan="3"><fmt:message key="funkcije"/></th>
		</tr>
		<c:forEach items="${ adminCategories }" var="i">
			<tr>
				<td align="center"><c:out value="${rb3}"></c:out></td>
				<td align="center">${ i.categoryName }</td>
				<td align="center">${ i.categoryDeleted }</td>
				<td align="center"><a href="CategoryPrepareAddEditServlet?id=${ i.categoryId }"><fmt:message key="izmeni"/></a></td>
				<td align="center"><a href="CategoryDeleteServlet?id=${i.categoryId}" onclick="return delMovie('<fmt:message key="daLiSteSigurniKategorija"/>')"><fmt:message key="obrisi"/></a></td>
				<td align="center"><a href="MenuAdminServlet?id=${ i.categoryId }"><fmt:message key="prikazi"/></a></td>
			</tr>
			<c:set var="rb3" scope="page" value="${rb3 + 1}"/>
		</c:forEach>
	</table>
	<br/>
	<a href="CategoryPrepareAddEditServlet"><input type="submit" value='<fmt:message key="dodajKategoriju"/>'></input></a>
	
	<h2><fmt:message key="izmenaKnjiga"/>: <c:out value="${ adminCategory.categoryName }"/></h2>
	<table border=1>
		<c:set var="rb3" scope="page" value="${1}"/>
		<tr>
			<th><fmt:message key="redniBroj"/></th>
			<th><fmt:message key="naslov"/></th>
			<th><fmt:message key="autori"/></th>
			<th><fmt:message key="kljucneReci"/></th>
			<th><fmt:message key="godinaIzdavanja"/></th>
			<th><fmt:message key="jezik"/></th>
			<th colspan="3"><fmt:message key="funkcije"/></th>
		</tr>
	<c:forEach items="${ adminBooks }" var="i">
		<tr>
			<td align="center"><c:out value="${rb3}"></c:out></td>
			<td align="center">${ i.EBooktitle }</td>
			<td align="center">${ i.EBookauthor }</td>
			<td align="center">${ fn:substring(i.EBookkeywords, 0, 50).concat("...") }</td>
			<td align="center">${ i.EBookpublicationyear }</td>
			<td align="center">${ i.EBooklanguage.languageName }</td>
			<td align="center"><a href="BookPrepareAddEditServlet?id=${ i.EBookid }"><fmt:message key="izmeni"/></a></td>
			<td align="center"><a href="BookDeleteServlet?id=${i.EBookid}" onclick="return delMovie('<fmt:message key="daLiSteSigurniKnjiga"/>')"><fmt:message key="obrisi"/></a></td>
			<td align="center"><a href="BookDownloadServlet?id=${i.EBookid}"><fmt:message key="skidanje"/></a></td>
		</tr>
		<c:set var="rb3" scope="page" value="${rb3 + 1}"/>
	</c:forEach>
	</table>
	<br/>
	<a href="BookPrepareAddEditServlet"><input type="submit" value='<fmt:message key="dodajKnjigu"/>'></input></a>
	
	<a href="LogoutServlet"><fmt:message key="odjava"/></a>
	<a href="ChangePasswordPrepareServlet"><fmt:message key="promenaLozinke"/></a>
	<a href="ChangePersonalInfoPrepareServlet"><fmt:message key="promenaLicnihPodataka"/></a>
	<a href="BookSearchPrepareServlet"><fmt:message key="pretragaKnjiga"/></a>
</body>
</html>