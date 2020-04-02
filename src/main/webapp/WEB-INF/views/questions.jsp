<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Home</title>
	</head>
	<body>
		<a href="">Test</a>
		
		<form:form action="" method="POST" enctype="multipart/form-data">
			<table>
				<tr>
					<td>Select a file to upload</td>
					<td><input type="file" name="files" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="Submit" /></td>
				</tr>
			</table>
		</form:form>
		
		<c:forEach var="question" items="${questions}">
			<div>
				<h3><c:out value="${question.id}" /></h3>
				<span><c:out value="${question.content}" /></span>
			</div>
		</c:forEach>
	</body>
</html>