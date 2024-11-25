package Model;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "books")
public class BookList {
	
	@XmlElement(name = "Book")
	private List<Book> bookList;
	public BookList() {}
	public BookList(List<Book> bookList) { //constructor
	this.bookList = bookList;
	}
	public List<Book> getbookList() {  //getter
	return bookList;
	}
	public void setBookList(List<Book> bookList) {  //setter
	this.bookList = bookList;
	}
	public  List<Book> getBooks() {  //method
		return this.bookList;
	}
}
