<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Task</title>
</head>
<body>
	<!-- To display the Idea -->
		<h1>${ idea.name }</h1>
		<p> <a href="/users/dashboard" >Home</a></p>
		<p> <a href="/users" >Logout</a></p>
		
		
		<p>Created by:  ${ idea.user.name }</p>
		
		
		<h2>Users who liked this idea</h2>
	
		<button><a href="/users/edit/${ idea.id}">Edit</a></button>	
</body>
</html>