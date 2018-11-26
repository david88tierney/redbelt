<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
</head>
<body> 

    <h2>Welcome, ${ user.name }</h2>
    <p> <a href="/users/" >Logout</a></p>
	
	<h2>Ideas</h2>
	<table border="1">
		<thead>
			<th>Idea</th>
			<th>Created By:</th>
			<th>Likes</th>
            <th>Action</th>
 
		</thead>
		<tbody>
    	<c:forEach var="idea" items="${ ideas }" >
        <tr> 
            <td><a href="ideas/${ idea.id }">${  idea.name } </a></td>
          	<td> ${idea.user.name}</td>
          	<td> </td>
			<td>  </td>
        </tr>
    	</c:forEach>

		</tbody>
	</table>

	<button><a href="/users/add">Share your idea here!</a></button>
</body>
</html>