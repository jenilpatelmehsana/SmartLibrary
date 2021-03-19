package com.amazing.smartLibrary.Models;

import java.util.HashMap;

public class User {
    private String userName, password, phoneNumber,email;
    private HashMap<Book, Integer> reservedBooks;

    public User() {
    }

    public User(String userName, String password, String phoneNumber, String email) {
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        reservedBooks = new HashMap<>();
    }

    public boolean addBook(Book newBook, Integer quantity) {
        try{
            reservedBooks.put(newBook, quantity);
            return true;
        }catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean removeBook(Book book, Integer quantity) {
        try {
            int available = reservedBooks.get(book);
            if (available >= quantity) {
                reservedBooks.put(book, available - quantity);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public HashMap<Book, Integer> getReservedBooks() {
        return reservedBooks;
    }

    public void setReservedBooks(HashMap<Book, Integer> reservedBooks) {
        this.reservedBooks = reservedBooks;
    }
}
