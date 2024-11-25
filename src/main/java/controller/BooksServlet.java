package controller;

import java.io.IOException;

import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import com.google.gson.Gson;

import Model.Book;
import Model.BookList;
import db.BookDAO;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

/**
 * The BooksServlet class handles requests related to books in the book store.
 * It provides methods to retrieve, insert, update, and delete book information in various formats.
 */

@WebServlet("/books")
public class BooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Handles the HTTP GET request to retrieve books information.
	 * Retrieves the list of books from the database and sends the response in the requested format (XML, JSON, or plain text).
	 *
	 * @param request  the HttpServletRequest object containing the request parameters and attributes
	 * @param response the HttpServletResponse object for sending the response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String format = request.getHeader("Accept");
		System.out.println(format);
		PrintWriter out = response.getWriter();

		BookDAO dao = new BookDAO();
		ArrayList<Book> allbooks = dao.getAllBooks();
		BookList bookList = new BookList(allbooks);

		switch (format.toLowerCase()) {
		case "application/xml":
			BookList bl = new BookList(allbooks);
			StringWriter sw = new StringWriter();
			JAXBContext context;
			try {
				context = JAXBContext.newInstance(BookList.class);
				Marshaller m = context.createMarshaller();
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
				m.marshal(bl, sw);
			} catch (JAXBException e) {
				e.printStackTrace();
			}

			response.setContentType("application/xml");
			out.write(sw.toString());
			break;

		case "application/json":
			Gson gson = new Gson();
			String json = gson.toJson(bookList);
			response.setContentType("application/json");
			out.write(json);
			break;

		case "text/plain":
			ArrayList<Book> books = dao.getAllBooks();
			StringBuilder sb = new StringBuilder();
			for (Book book : books) {
				sb.append(book.toString());
				sb.append("\n");
			}
			response.setContentType("text/plain");
			out.write(sb.toString());
			break;

		default:
			response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
			break;
		}

		out.close();
	}

	/**
	 * Handles the HTTP POST request to insert a new book.
	 * Retrieves the book information from the request parameters, inserts the book into the database,
	 * and sends the response indicating the success or failure of the insertion.
	 *
	 * @param request  the HttpServletRequest object containing the request parameters and attributes
	 * @param response the HttpServletResponse object for sending the response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//System.out.println(request.getParameter("title"));
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String date = request.getParameter("date");
		String genre = request.getParameter("genres");
		String character = request.getParameter("characters");
		String synopsis = request.getParameter("synopsis");

		BookDAO dao = new BookDAO();
		Book b = new Book(id, title, author, date, genre, character, synopsis);

		//dao.getBookByID(id);
		PrintWriter out = response.getWriter();

		try {
			dao.insertBook(b);
			out.write("Book inserted successfully");
		} catch (SQLException e) {
			out.write("Error inserting the book");
			e.printStackTrace();
		}

		out.close();
	}

	/**
	 * Handles the HTTP PUT request to update a book.
	 * Retrieves the book information from the request parameters, updates the book in the database,
	 * and sends the response indicating the success or failure of the update.
	 *
	 * @param request  the HttpServletRequest object containing the request parameters and attributes
	 * @param response the HttpServletResponse object for sending the response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String date = request.getParameter("date");
		String genre = request.getParameter("genres");
		String character = request.getParameter("characters");
		String synopsis = request.getParameter("synopsis");

		BookDAO dao = new BookDAO();
		Book b = new Book(id, title, author, date, genre, character, synopsis);
		PrintWriter out = response.getWriter();
		request.setAttribute("book", b);

		// forward the request to the JSP that displays the form
		RequestDispatcher dispatcher = request.getRequestDispatcher("Form2.html");
		dispatcher.include(request, response);

		try {
			dao.updateBook(b);
			out.write("Book updated successfully");
		} catch (SQLException e) {
			out.write("Error updating the book");
			e.printStackTrace();
		}

		out.close();
	}

	/**
	 * Handles the HTTP DELETE request to delete a book.
	 * Retrieves the book ID from the request parameters, deletes the book from the database,
	 * and sends the response indicating the success or failure of the deletion.
	 *
	 * @param request  the HttpServletRequest object containing the request parameters and attributes
	 * @param response the HttpServletResponse object for sending the response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		Book b = new Book();
		b.setId(id);
		BookDAO dao = new BookDAO();
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");


		try {
			dao.deleteBook(id);
			out.write("Book deleted successfully");
		} catch (SQLException e) {
			out.write("Error deleting the book");
			e.printStackTrace();
		}

		out.close();
	}
}
