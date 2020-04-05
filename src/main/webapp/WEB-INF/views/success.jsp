<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="scheme" scope="page" value="${pageContext.request.scheme}"/>
<c:set var="serverName" scope="page" value="${pageContext.request.serverName}"/>
<c:set var="serverPort" scope="page" value="${pageContext.request.serverPort}"/>
<c:set var="contextPath" scope="page" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
	<head>
		<%@ include file="partials/head.jsp" %>
	</head>
	<body>
		<%@ include file="partials/header.jsp" %>
		
		<div class="ui center aligned basic segment">
			<h1 class="ui header">
				<a href="<c:url value="/" />">Home</a>
			</h1>
		</div>
		
		<c:forEach var="link" items="${links}">
			<div class="ui center aligned basic segment">
				<a href="<c:url value="${link}" />">${scheme}://${serverName}:${serverPort}${contextPath}${link}</a>
			</div>
		</c:forEach>
		
		<%@ include file="partials/footer.jsp" %>
		
		<%@ include file="partials/scripts.jsp" %>
	</body>
</html>