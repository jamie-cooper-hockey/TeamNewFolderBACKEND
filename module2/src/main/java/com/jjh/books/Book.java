package com.jjh.books;

public class Book {

    private int isbn;
    private String title;
    private String category;
    private String author;
    private String imageURL;

    public Book() {}

    public Book(int isbn, String title, String category, String author, String imageURL) {
        this.isbn = isbn;
        this.title = title;
        this.category = category;
        this.author = author;
        this.imageURL = imageURL;
    }

    public String getImageURL() {return imageURL; }

    public void setImageURL(String imageURL) {this.imageURL = imageURL; }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int id) {
        this.isbn = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", author='" + author + '\'' +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}


