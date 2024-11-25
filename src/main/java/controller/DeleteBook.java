package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.BookDAO;
import models.Book;


@WebServlet("/DeleteBook")
public class DeleteBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("ViewBooks.jsp");
        rd.include(request, response);
		BookDAO dao = new BookDAO();
		int id = Integer.parseInt(request.getParameter("Id"));
		Book b = dao.getBookByID(id);
		
		try {
			dao.deleteBook(id);
		}
		catch(SQLException se) {
			System.out.println(se.getMessage());
		}
		response.sendRedirect("./books");
	}

	}




