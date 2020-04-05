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
			<div class="ui center aligned basic segment">
				<form class="ui form" action="" method="POST" enctype="multipart/form-data">
					<div class="ui input">
						<input type="file" name="files" multiple>
					</div>
					<button class="ui button" type="submit">Upload</button>
				</form>
			</div>
			
			<div class="ui center aligned basic segment">
				<h3 class="ui header">Questions</h3>
			</div>
			
			<table class="ui celled padded table">
				<thead>
					<tr>
						<th class="single line">ID</th>
						<th>Content</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="question" items="${questions}">
						<tr>
							<td class="single line"><c:out value="${question.id}" /></td>
							<td><c:out value="${question.content}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
		<%@ include file="partials/footer.jsp" %>
		
		<%@ include file="partials/scripts.jsp" %>
	</body>
</html>