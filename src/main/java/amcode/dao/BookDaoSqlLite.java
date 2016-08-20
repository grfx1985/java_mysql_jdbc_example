package amcode.dao;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import amcode.domain.Book;


public class BookDaoSqlLite implements BookDao {
	private Connection connection;
	private Statement statement;
	public BookDaoSqlLite() {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:mylibrary.db");
			statement = connection.createStatement();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		createTable();
	}
	
	private void executeUpdateQuery(String sql){
		try {
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void createTable() {
		String sql = "CREATE TABLE IF NOT EXISTS Books(" + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "title TEXT NOT NULL, " + "author TEXT NOT NULL, " + "pages INTEGER DEFAULT 0" + ")";
		executeUpdateQuery(sql);
	}


	
	
	public void addBook(Book book) {
		String title = book.getTitle();
		String author = book.getAuthor();
		int pages = book.getPages();
		String sql = "INSERT INTO Books(title, author, pages) VALUES(" + "'" + title + "','" + author + "'," + pages
				+ ");";
		executeUpdateQuery(sql);
	}

	public void removeBook(Book book) {
		int id = book.getId();
		String sql = "DELETE FROM Books WHERE id = " + id;
		executeUpdateQuery(sql);
	}

	public List<Book> listBooks() {
		List<Book> books = new ArrayList<Book>();
		try {
			ResultSet result = statement.executeQuery("SELECT * FROM Books");
			String title, author;
			int pages;
			int id;
			/*
			 * Pozytywne Myślenie, 20
			 * Programowanie w Javie, 200
			 */
			while ( result.next()){
				id = result.getInt("id");
				title = result.getString("title");
				author = result.getString("author");
				pages = result.getInt("pages");
				books.add(new Book(id,title,author,pages));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return books;
	}

	public Book find(int id) throws SQLException {
		Book book = null;
		
		String sql = "SELECT id,title,author,pages FROM Books WHERE id=?";
		
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            int pages = resultSet.getInt("pages");
            book = new Book(id, title, author,pages);
        }
        return book;
	}

}