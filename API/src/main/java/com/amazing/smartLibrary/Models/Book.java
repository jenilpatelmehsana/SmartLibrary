package com.amazing.smartLibrary.Models;

public class Book {
    private String bookName, authorName;
    private Integer price, pages;

    public Book() {
    }

    public Book(String bookName, String authorName, Integer price, Integer pages) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.price = price;
        this.pages = pages;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }
}
