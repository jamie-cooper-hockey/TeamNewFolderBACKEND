package com.jjh.books;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    private static final String url = "jdbc:mysql://localhost:3306/jamie_bookshop";
    private static final String user = "jamie";
    private static final String pwd = "jamie123";

    private Connection conn;

    public void setup() {
        System.out.println("BookDAO.setup()");
        try {
            conn = DriverManager.getConnection(url,user,pwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void shutdown() {
        try {
            conn.close();
        } catch (SQLException exp) {
            exp.printStackTrace();
        }
    }

    public Book getBookByISBN(int isbn) throws SQLException {
        System.out.printf("BookDAO.getBookByISBN(%s)\n", isbn);
        Book book = null;

        // SELECT * FROM books WHERE isbn = 123;
        String sql = String.format("SELECT * FROM books WHERE isbn = %d", isbn);
        System.out.println(sql);
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        if (rs.next()) {
            String title = rs.getString("title");
            String category = rs.getString("category");
            String author = rs.getString("author");
            String imageURL = rs.getString("imageURL");
            book = new Book(isbn, title, category, author, imageURL);
        }
        st.close();
        return book;
    }

    public List<Book> getAllBooks() throws SQLException {

        System.out.println("BookDAO.getAllBooks()");
        List<Book> books = new ArrayList<>();

        String sql = "SELECT * FROM books";
        System.out.println(sql);

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            int isbn = rs.getInt("isbn");
            String title = rs.getString("title");
            String category = rs.getString("category");
            String author = rs.getString("author");
            String imageURL = rs.getString("imageURL");
            books.add(new Book(isbn, title, category, author, imageURL));
        }

        return books;
    }

    public int saveBook(Book book) throws SQLException {
        System.out.printf("BookDAO.saveBook(%s)\n", book);

        String sql = String.format(
                "INSERT INTO books (isbn, title, category, author, imageURL) VALUES('"+ book.getIsbn() +"', '"+ book.getTitle() +"', '"+ book.getCategory() +"', '"+ book.getAuthor() +"', '"+ book.getImageURL() +"')");
                /*book.getIsbn(),
                book.getTitle(),
                book.getCategory(),
                book.getAuthor());
                book.getImageURL();*/

        System.out.println(sql);

        Statement st = conn.createStatement();
        int result = st.executeUpdate(sql);
        st.close();
        return result;
    }

    public int deleteBook(Book book) throws SQLException {
        System.out.printf("BookDAO.deleteBook(%s)\n", book);
        String sql = "DELETE * FROM books WHERE isbn = '" + book.getIsbn() + "'";
        System.out.println(sql);
        Statement st = conn.createStatement();
        int result = st.executeUpdate(sql);
        st.close();
        return result;
    }

    public int deleteBookByTitle(String title) throws SQLException {
        System.out.printf("BookDAO.deleteBookByTitle(%s)\n", title);
        String sql = "DELETE * FROM books WHERE title = '" +title + "'";
        System.out.println(sql);
        Statement st = conn.createStatement();
        int result = st.executeUpdate(sql);
        st.close();
        return result;
    }

    public List<Book> getBookByTitle(String title) throws SQLException {
        System.out.printf("BookDAO.deleteBookByTitle(%s)\n", title);
        String sql = "SELECT * FROM books WHERE title like '%" +title + "%'";
        System.out.println(sql);
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        List<Book> books = new ArrayList<>();

        while (rs.next()) {
            int isbn = rs.getInt("isbn");
            String title2 = rs.getString("title");
            String category = rs.getString("category");
            String author = rs.getString("author");
            String imageURL = rs.getString("imageURL");
            books.add(new Book(isbn, title2, category, author, imageURL));
        }

        return books;
    }

    public List<Book> getAllCatagoryBooks(String genre) throws SQLException {
        System.out.println("BookDAO.getAllBooks()");
        List<Book> books = new ArrayList<>();

        String sql = "SELECT * FROM books WHERE category = '" + genre + "' ";
        System.out.println(sql);

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            int isbn = rs.getInt("isbn");
            String title = rs.getString("title");
            String category = rs.getString("category");
            String author = rs.getString("author");
            String imageURL = rs.getString("imageURL");
            books.add(new Book(isbn, title, category, author, imageURL));
        }

        return books;
    }
}
