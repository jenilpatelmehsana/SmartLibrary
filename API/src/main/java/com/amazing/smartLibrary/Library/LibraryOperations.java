package com.amazing.smartLibrary.Library;

import com.amazing.smartLibrary.Models.Book;
import com.amazing.smartLibrary.Models.City;
import com.amazing.smartLibrary.Models.Library;
import com.amazing.smartLibrary.Models.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
            if(adminUser == null) {
                System.out.println("admin does not exists");
                return false;
            }
            Library newLibrary = new Library(libraryName, address, city, zipcode, maxSeats, lat, lon, adminUser);
            mongoOperations.insert(newLibrary);
            addLibraryToCity(newLibrary);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private void addLibraryToCity(Library newLibrary) {
        City currentCity = mongoOperations.findAndRemove(new Query(Criteria.where("cityName").is(newLibrary.getCity())), City.class);
        if(currentCity == null) currentCity = new City(newLibrary.getCity(), "state");
        currentCity.addLibrary(newLibrary);
        mongoOperations.insert(currentCity);

    }

    @PostMapping("/addBookToLibrary")
    public boolean addBookToLibrary(@RequestParam("libraryName") String libraryName,
                                    @RequestParam("bookName") String bookName,
                                    @RequestParam("authorName") String authorName,
                                    @RequestParam("price") String priceString,
                                    @RequestParam("pages") String pagesString,
                                    @RequestParam("quantity") String quantityString,
                                    @RequestParam("admin") String admin) {
        try {
            Integer quantity = Integer.parseInt(quantityString), pages = Integer.parseInt(pagesString),
                    prices = Integer.parseInt(priceString);
            Book newBook = new Book(bookName, authorName, prices, pages);
            Query query = new Query(Criteria.where("libraryName").is(libraryName));
            Library library = mongoOperations.findAndRemove(query, Library.class);
            if (library == null) {
                System.out.println("Library does not exist");
                return false;
            }
            if (library.getAdmin().getUserName().compareTo(admin) != 0) {
                mongoOperations.insert(library);
                System.out.println("only admin can insert book");
                return false;
            }
            library.addBook(newBook, quantity);
            mongoOperations.insert(library);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
