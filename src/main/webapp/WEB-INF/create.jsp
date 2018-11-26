<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add a Idea</title>
</head>
<body>
		<h1>Create a new idea</h1>
		<form:form action="/users/create" method="POST" modelAttribute="idea">
    
        <p><form:label path="name"> Content: </form:label></p>
		<p><form:errors path="name"></form:errors> </p>
        <p><form:input path="name"></form:input></p>

        <p><input type="submit" value="Create" /></p>
    </form:form>
</body>
</html>