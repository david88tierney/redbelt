<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit</title>
</head>
<body>
	<!-- Page to Edit the Idea -->
		<h1>Edit ${ idea.name }</h1>
		
		
		<form:form action="/users/update/${idea.id}" method="POST" modelAttribute="idea">
        <c:if test="${ideaError != null}">
        <p class="error"> ${ideaError}</p> 
        </c:if>
        <p><form:label path="name"> Content: </form:label></p>
		<p><form:errors path="name"></form:errors> </p>
        <p><form:input path="name"></form:input></p>
        

        <p><input type="submit" value="Update" /></p>
    </form:form>
    
   		<form action= "/users/${idea.id}/destroy" method="post">
		<input type="submit" value="delete" />
		</form>
	
</body>
</html>