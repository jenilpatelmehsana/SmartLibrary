package com.amazing.smartLibrary.Models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import java.util.HashMap;

public class Library {
    private String libraryName, address, city, zipcode;
    private Integer maxSeats, reservedSeats, availableSeats;
    private Double lat, lon;
    private HashMap<String, BookObject> booksInLibrary;
    private String libraryUID;
    private User admin;

    @Autowired
    private MongoOperations mongoOperations;

    public Library() {
    }

    public Library(String libraryName, String address, String city, String zipcode, Integer maxSeats, Double lat, Double lon, User admin) {
        this.libraryName = libraryName;
        this.address = address;
        this.city = city;
        this.zipcode = zipcode;
        this.maxSeats = maxSeats;
        this.lat = lat;
        this.lon = lon;
        this.admin = admin;
        this.booksInLibrary = new HashMap<>();
    }

    public boolean addBook(Book newBook, Integer quantity) {
        try {
            if(!this.booksInLibrary.containsKey(newBook.getBookName())) {
                this.booksInLibrary.put(newBook.getBookName(), new BookObject(newBook, quantity));
            } else {
                BookObject current = this.booksInLibrary.get(newBook.getBookName());
                current.setQuantity(current.getQuantity() + quantity);
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Integer getMaxSeats() {
        return maxSeats;
    }

    public void setMaxSeats(Integer maxSeats) {
        this.maxSeats = maxSeats;
    }

    public Integer getReservedSeats() {
        return reservedSeats;
    }

    public void setReservedSeats(Integer reservedSeats) {
        this.reservedSeats = reservedSeats;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public HashMap<String, BookObject> getBooksInLibrary() {
        return booksInLibrary;
    }

    public void setBooksInLibrary(HashMap<String, BookObject> booksInLibrary) {
        this.booksInLibrary = booksInLibrary;
    }

    public String getLibraryUID() {
        return libraryUID;
    }

    public void setLibraryUID(String libraryUID) {
        this.libraryUID = libraryUID;
    }
}
