package com.amazing.smartLibrary.Models;

public class BookObject {
    Book newBook;
    Integer Quantity;

    public BookObject() {

    }

    public BookObject(Book newBook, Integer quantity) {
        this.newBook = newBook;
        Quantity = quantity;
    }

    public Book getNewBook() {
        return newBook;
    }

    public void setNewBook(Book newBook) {
        this.newBook = newBook;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }
}
