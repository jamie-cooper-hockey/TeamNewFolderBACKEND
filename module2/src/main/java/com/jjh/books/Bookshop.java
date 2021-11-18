package com.jjh.books;

import java.sql.SQLException;
import java.util.List;

public class Bookshop {

    private BookDAO dao = new BookDAO();

    public Bookshop() {
        dao.setup();
    }

    public void shutdown() {
        dao.shutdown();
    }

    public List<Book> getBooks() throws SQLException {
        System.out.println("Bookshop.getBooks");
        return dao.getAllBooks();
    }

    public int saveBook(Book book) throws SQLException {
        System.out.println("Bookshop.saveBook(" + book + ")");
        return dao.saveBook(book);
    }

    public void deleteBook(Book book) throws SQLException {
        System.out.println("Bookshop.deleteBook()");
        dao.deleteBook(book);
    }

    public Book getBookByISBN(int isbn) throws SQLException {
        System.out.println("Bookshop.getBookByISBN()");
        return dao.getBookByISBN(isbn);
    }

    public int deleteBookByTitle(String title) throws SQLException {
        System.out.println("Bookshop.getBookByISBN()");
        return dao.deleteBookByTitle(title);
    }

    public List<Book>  getBookByTitle(String title) throws SQLException {
        System.out.println("Bookshop.getBookByISBN()");
        return dao.getBookByTitle(title);
    }

    public List<Book> getCatagoryBooks(String genre) throws SQLException {
        System.out.println("Bookshop.getDramaBooks");
        return dao.getAllCatagoryBooks(genre);
    }
}
