package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Book;


public class BookDAO {

	Book oneBook = null;
	Connection conn = null;
	Statement stmt = null;
	String user = "unnisawa";
	String password = "zonkeRan6";
	String url = "jdbc:mysql://mudfoot.doc.stu.mmu.ac.uk:6306/" + user;

	public BookDAO() {
	}

	private void openConnection() {
		// loading jdbc driver for mysql
		try {
			Class.forName("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			System.out.println(e);
		}

		// connecting to database
		try {
			// connection string for demos database, username demos, password demos
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
		} catch (SQLException se) {
			System.out.println(se);
		}
	}

	private void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Book getNextBook(ResultSet rs) {
		Book thisBook = null;
		try {

			thisBook = new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getString("date"),
					rs.getString("genres"), rs.getString("characters"), rs.getString("synopsis"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return thisBook;
	}

	//retrieving all books
	
	public ArrayList<Book> getAllBooks() {

		ArrayList<Book> allBooks = new ArrayList<Book>();
		openConnection();

		// Create select statement and execute it
		try {
			String selectSQL = "select * from books";
			ResultSet rs1 = stmt.executeQuery(selectSQL);
			// Retrieve the results
			while (rs1.next()) {
				oneBook = getNextBook(rs1);
				allBooks.add(oneBook);
			}

			stmt.close();
			closeConnection();
		} catch (SQLException se) {
			System.out.println(se);
		}

		return allBooks;
	}
	
	//searching a book by id

	public Book getBookByID(int id) {

		openConnection();
		oneBook = null;
		// Create select statement and execute it
		try {
			String searchSQL = "select * from books where id=" + id;
			ResultSet rs1 = stmt.executeQuery(searchSQL);
			// Retrieve the results
			while (rs1.next()) {
				oneBook = getNextBook(rs1);
			}

			stmt.close();
			closeConnection();
		} catch (SQLException se) {
			System.out.println(se);
		}

		return oneBook;
	}

	// inserting book
	
	public boolean insertBook(Book b) throws SQLException {
		openConnection();
		boolean bl = false;
		try {
			String insertSQL = "insert into books (id, title, author, date, genres, characters, synopsis) values ('"
					+ b.getId() + "','" + b.getTitle() + "','" + b.getAuthor() + "','" + b.getDate() + "','"
					+ b.getGenres() + "','" + b.getCharacters() + "','" + b.getSynopsis() + "');";
			System.out.println(insertSQL);
			bl = stmt.execute(insertSQL);
			closeConnection();
			bl = true;
		} catch (SQLException s) {
			throw new SQLException("Book Not Added");
		}
		return bl;

	}
	
	
	//updating a book
	
	public boolean updateBook(Book b) throws SQLException {
		openConnection();
		boolean bl = false;
		try{
			    String updateSQL = "UPDATE books SET title = '" + b.getTitle() + "', author = '" + b.getAuthor() 
			                + "', date = '" + b.getDate() + "', genres = '" + b.getGenres() + "', characters = '" 
			                + b.getCharacters() + "', synopsis = '" + b.getSynopsis() + "' WHERE id = " + b.getId();
			System.out.println(updateSQL);
			bl = stmt.execute(updateSQL);
			closeConnection();
			bl = true;
		} catch (SQLException s) {
			throw new SQLException("Book Not updated");
		}
		return bl;
	}
	
	
		//deleting a book
	
	public boolean deleteBook(int id) throws SQLException {
		openConnection();
		boolean bl = false;
		try {
			String deleteSQL = "delete from books where id = " + id + ";";

			System.out.println(deleteSQL);
			bl = stmt.execute(deleteSQL);
			
		} catch (SQLException s) {
			throw new SQLException("Book Not deleted");
		}
		finally 
		{
		closeConnection();
		bl = true;
		}
		return bl;
	}
}
