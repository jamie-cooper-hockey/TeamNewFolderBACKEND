package com.jjh.service;

import com.jjh.books.Book;
import com.jjh.books.BookException;
import com.jjh.books.Bookshop;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600) // see https://www.baeldung.com/spring-cors
@RestController
@RequestMapping("bookshop")
public class BookshopController {

    private Bookshop bookshop = new Bookshop();

    @CrossOrigin
    @GetMapping("{title}")
    public List<Book> getBook(@PathVariable String title) throws SQLException {
        System.out.println("BookshopController.getBook(" + title + ")");
        return this.bookshop.getBookByTitle(title);
    }

    @CrossOrigin
    @GetMapping("list")
    public List<Book> getAllBooks() throws SQLException {
        System.out.println("BookshopController.getAllBooks()");
        return bookshop.getBooks();
    }

    @CrossOrigin
    @GetMapping("genre/Drama")
    public List<Book> getAllDramaBooks() throws SQLException {
        System.out.println("BookshopController.getAllBooks()");
        return bookshop.getCatagoryBooks("Drama");
    }

    @CrossOrigin
    @GetMapping("genre/Food")
    public List<Book> getAllFoodBooks() throws SQLException {
        System.out.println("BookshopController.getAllBooks()");
        return bookshop.getCatagoryBooks("Food");
    }

    @CrossOrigin
    @GetMapping("genre/Children")
    public List<Book> getAllChildrenBooks() throws SQLException {
        System.out.println("BookshopController.getAllBooks()");
        return bookshop.getCatagoryBooks("Children");
    }

    @CrossOrigin
    @GetMapping("genre/Romance")
    public List<Book> getAllRomanceBooks() throws SQLException {
        System.out.println("BookshopController.getAllBooks()");
        return bookshop.getCatagoryBooks("Romance");
    }

    @CrossOrigin
    @GetMapping("genre/Technical")
    public List<Book> getAllTechnicalBooks() throws SQLException {
        System.out.println("BookshopController.getAllBooks()");
        return bookshop.getCatagoryBooks("Technical");
    }

    @CrossOrigin
    @GetMapping("genre/Christmas")
    public List<Book> getAllChristmasBooks() throws SQLException {
        System.out.println("BookshopController.getAllBooks()");
        return bookshop.getCatagoryBooks("Christmas");
    }

    @CrossOrigin
    @GetMapping("genre/Historical")
    public List<Book> getAllHistoricalBooks() throws SQLException {
        System.out.println("BookshopController.getAllBooks()");
        return bookshop.getCatagoryBooks("Historical");
    }

    @CrossOrigin
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void addBook(@RequestBody String book) throws SQLException {
        String book1[] = book.split("%2C");
        book1[4] = book1[4].substring(0,book1[4].length()-1);
        for (int z = 0; z < 5; z++){
            book1[z] = book1[z].replace("+", " ");
            book1[z] = book1[z].replace("%3A", ":");
            book1[z] = book1[z].replace("%2F", "/");
            book1[z] = book1[z].replace("%3F", "?");
        }
        System.out.println(book1);
        Book book2 = new Book(Integer.parseInt(book1[0]), book1[1], book1[2], book1[3], book1[4]);
        System.out.println("BookshopController.addBook(" + book2 + ")");
        this.bookshop.saveBook(book2);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateBook(@RequestBody Book book) {
        System.out.println("BookshopController.updateBook(" + book + ")");
        //this.bookshop.updateBook(book);
    }

    @DeleteMapping("{title}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBook(@PathVariable String title) throws SQLException {
        System.out.println("BookshopController.deleteBook(" + title + ")");
        bookshop.deleteBookByTitle(title);
    }

    @ExceptionHandler(BookException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Book not found")
    public void updateFailure() { }

}
