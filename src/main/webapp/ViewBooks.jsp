<%@ page import="javax.servlet.http.HttpServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="models.Book"%>


<!DOCTYPE html>
<html>
<head><b><br> My Book Store </head></b></br>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Book Database</title>

<body>
<br> <div>
<a href="<%= request.getContextPath() %>/InsertBook">Add Book</a> &nbsp &nbsp 
<a href="<%= request.getContextPath() %>/books">View Books</a>
</div>

	<table BORDER="1">
	<br></br>
	<tr><th>ID</th>
	<th>Title</th>
	<th>Author</th>
	<th>Date</th>
	<th>Genres</th>
	<th>Characters</th>
	<th>Synopsis</th>
	<th>Delete</th>
	<th>Update</th>
	
	</tr>
	<c:forEach items = "${books}" var="c">
	<tr><td><em>${c.id}</em></td> <b> 
	<td>${c.title}</b></td>
	<td>${c.author}</td>
	<td>${c.date}</td>
	<td>${c.genres}</td>
	<td>${c.characters}</td>
	<td>${c.synopsis}</td>
	<td> <a href="<%= request.getContextPath() %>/DeleteBook?Id=${c.id}"> Delete </a> </td>
	<td> <a href="<%= request.getContextPath() %>/UpdateBook?Id=${c.id}"> Update </a> </td>
	
	</tr>
	
	</c:forEach>
	</table>
	
	</body>
	</html>