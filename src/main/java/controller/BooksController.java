package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.BookDAO;
import models.Book;

/**
 * The BooksController class handles requests related to books in the book store.
 * It provides methods to retrieve and update books information.
 */
@WebServlet("/books")
public class BooksController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Handles the HTTP GET request to retrieve all books.
	 * Retrieves the list of books from the database and forwards the request to the ViewBooks.jsp page.
	 *
	 * @param request  the HttpServletRequest object containing the request parameters and attributes
	 * @param response the HttpServletResponse object for sending the response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		BookDAO dao = new BookDAO();
		ArrayList<Book> allbooks = dao.getAllBooks();
		request.setAttribute("books", allbooks);
		RequestDispatcher rd = request.getRequestDispatcher("ViewBooks.jsp");
		rd.include(request, response);
	}

	/**
	 * Handles the HTTP POST request to update a book.
	 * Retrieves the book information from the request parameters, updates the book in the database,
	 * retrieves the updated list of books, and forwards the request to the ViewBooks.jsp page.
	 *
	 * @param request  the HttpServletRequest object containing the request parameters and attributes
	 * @param response the HttpServletResponse object for sending the response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		String title  = request.getParameter("title");
		String author  = request.getParameter("author");
		String date  = request.getParameter("date");
		String genres  = request.getParameter("genres");
		String characters  = request.getParameter("characters");
		String synopsis  = request.getParameter("synopsis");

		BookDAO dao = new BookDAO();
		Book b = new Book(id,title,author,date,genres,characters,synopsis);

		
		try {
			dao.updateBook(b);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ArrayList<Book> allbooks = dao.getAllBooks();
		request.setAttribute("books", allbooks);

		RequestDispatcher rd = request.getRequestDispatcher("ViewBooks.jsp");
		rd.forward(request, response);

	}

}


