<%@ page import="javax.servlet.http.HttpServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="models.Book"%>

<!DOCTYPE html>
<html>
<head>
    <title>Add Book Form</title>
</head>
<body>
    <h3>Add Book</h3>
    <form action="InsertBook" method="POST">
        <table>
            <tr>
                <td>ID:</td>
                <td><input type="text" name="id"></td>
            </tr>
            <tr>
                <td>Title:</td>
                <td><input type="text" name="title"></td>
            </tr>
            <tr>
                <td>Author:</td>
                <td><input type="text" name="author"></td>
            </tr>
            <tr>
                <td>Date:</td>
                <td><input type="text" name="date"></td>
            </tr>
            <tr>
                <td>Genres:</td>
                <td><input type="text" name="genres"></td>
            </tr>
            <tr>
                <td>Characters:</td>
                <td><input type="text" name="characters"></td>
            </tr>
            <tr>
                <td>Synopsis:</td>
                <td><input type="text" name="synopsis"></td>
            </tr>
        </table>
        <br>
        <input type="submit" value="Submit">
    </form>
    
   
        <c:forEach items="${books}" var="c">
            <tr>
                <td>${c.id}</td>
                <td>${c.title}</td>
                <td>${c.author}</td>
                <td>${c.date}</td>
                <td>${c.genres}</td>
                <td>${c.characters}</td>
                <td>${c.synopsis}</td>
            </tr>
        </c:forEach>
    
</body>
</html>
	