package amcode.mylibrary;

import java.util.Scanner;

import amcode.dao.BookDao;
import amcode.dao.BookDaoSqlLite;
import amcode.domain.Book;

public class Main {

	private static Scanner scanner;

	public static void main(String[] args) {
		BookDao bookDao;
		bookDao = new BookDaoSqlLite();
		scanner = new Scanner(System.in);
		int number = 0;
		String title,author;
		int pages;
		do {

			System.out.println("1: dodaj książkę");
			System.out.println("2: usuń książkę");
			System.out.println("3: wyświetl wszystkie książki");
			System.out.println("4: koniec programu");
			number = scanner.nextInt();
			switch (number) {
			case 1:
				System.out.println("Podaj autora");
				title = scanner.next();
				System.out.println("Podaj tytuł");
				author = scanner.next();
				System.out.println("Podaj liczbę stron");
				pages = scanner.nextInt();
				bookDao.addBook(new Book(title,author,pages));
				break;
			case 2:
				// praca do domu:)
				break;
			case 3:
				for (Book book:bookDao.listBooks() ){
					System.out.println(book);
				}
				break;
			}

		} while (number != 4);

	}

}