package com.amazing.smartLibrary.Library;

import com.amazing.smartLibrary.Models.Book;
import com.amazing.smartLibrary.Models.Library;
import com.amazing.smartLibrary.Models.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.Map;

@RestController
public class LibraryOperations {

    private final MongoOperations mongoOperations;

    public LibraryOperations(@Qualifier("getMongoOperations") MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    @PostMapping("/addLibrary")
    public boolean addLibrary(@RequestParam("libraryName") String libraryName,
                              @RequestParam("address") String address,
                              @RequestParam("city") String city,
                              @RequestParam("zipcode") String zipcode,
                              @RequestParam("maxSeats") String maxSeatsString,
                              @RequestParam("lat") String latitude,
                              @RequestParam("lon") String longitude,
                              @RequestParam("admin") String admin) {
        Integer maxSeats = Integer.parseInt(maxSeatsString);
        Double lat = Double.parseDouble(latitude), lon = Double.parseDouble(longitude);
        try {
            User adminUser = null;
            Query adminQuery = new Query(Criteria.where("userName").is(admin));
            adminUser = mongoOperations.findOne(adminQuery, User.class);
            Library newLibrary = new Library(libraryName, address, city, zipcode, maxSeats, lat, lon, adminUser);
            mongoOperations.insert(newLibrary);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @PostMapping("/addBookToLibrary")
    public boolean addBookToLibrary(@RequestParam("libraryName") String libraryName,
                                    @RequestParam("bookName") String bookName,
                                    @RequestParam("authorName") String authorName,
                                    @RequestParam("price") String priceString,
                                    @RequestParam("pages") String pagesString,
                                    @RequestParam("quantity") String quantityString) {
        try {
            Integer price = Integer.parseInt(priceString), pages = Integer.parseInt(pagesString);
            Integer quantity = 1;
            quantity = Integer.parseInt(quantityString);
            Book newBook = new Book(bookName, authorName, price, pages);
            Query query = new Query(Criteria.where("libraryName").is(libraryName));
            Library library = (Library) mongoOperations.findOne(query, Library.class);
            if (library == null) return false;
            boolean found = false;
            Iterator iterator = library.getBooksInLibrary().entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                Book current = (Book) entry.getKey();
                if (current.getBookName().compareTo(bookName) == 0) {
                    entry.setValue((Integer) entry.getValue() + quantity);
                    found = true;
                    break;
                }
            }
            if (!found) {
                library.addBook(newBook, quantity);
            }
            Update update = new Update().set("booksInLibrary", library.getBooksInLibrary());
            mongoOperations.findAndModify(query, update, Library.class);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
