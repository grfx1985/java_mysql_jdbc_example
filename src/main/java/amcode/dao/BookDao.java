package amcode.dao;

import java.sql.SQLException;
import java.util.List;

import amcode.domain.Book;

public interface BookDao {
	public void addBook(Book book);
	public void  removeBook(Book book);
	public List<Book> listBooks();
	public Book find(int id) throws SQLException;

}
