<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<%@ include file="partials/head.jsp" %>
	</head>
	<body>
		<%@ include file="partials/header.jsp" %>
		
		<div class="ui container">
			<form action="" method="POST" enctype="multipart/form-data">
				<input type="file" name="file" />
				<input type="submit" value="Upload file" />
			</form>
		</div>
		
		<div class="ui container">
			<div class="ui raised segments">
				<c:forEach var="question" items="${questions}">
					<div class="ui segment">
						<h3 class="ui header"><c:out value="${question.id}" /></h3>
						<span><c:out value="${question.content}" /></span>
					</div>
				</c:forEach>
			</div>
		</div>
		
		<%@ include file="partials/footer.jsp" %>
		
		<%@ include file="partials/scripts.jsp" %>
	</body>
</html>