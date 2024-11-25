<%@ page import="javax.servlet.http.HttpServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="models.Book"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Book Form</title>
</head>
<body>
<h3>Update Book</h3>


	<form action="UpdateBook" method="POST">
        <table>
            <tr>
                <td>ID:</td>
                <td><input type="text" name="id" value="${book.id}" ></td>
            </tr>
            <tr>
                <td>Title:</td>
                <td><input type="text" name="title" value="${book.title}" ></td>
            </tr>
            <tr>
                <td>Author:</td>
                <td><input type="text" name="author" value="${book.author}" ></td>
            </tr>
            <tr>
                <td>Date:</td>
                <td><input type="text" name="date" value="${book.date}" ></td>
            </tr>
            <tr>
                <td>Genres:</td>
                <td><input type="text" name="genres" value="${book.genres}" ></td>
            </tr>
            <tr>
                <td>Characters:</td>
                <td><input type="text" name="characters" value="${book.characters}" ></td>
            </tr>
            <tr>
                <td>Synopsis:</td>
                <td><input type="text" name="synopsis" value="${book.synopsis}" ></td>
            </tr>
        </table>
        <br>
        <input type="submit" value="Submit">
    </form>
	
	<c:forEach items="${books}" var="c">
	<tr><td><em>${c.id}</em></td> <b> <td> ${c.title}</b></td>
	<td>${c.author}</td>
	<td>${c.date}</td>
	<td>${c.genres}</td>
	<td>${c.characters}</td>
	<td>${c.synopsis}</td>
	</tr>
</c:forEach>


</body>
</html>
