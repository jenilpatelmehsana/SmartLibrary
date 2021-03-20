package com.amazing.smartLibrary.Authentication;

import com.amazing.smartLibrary.Models.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateUser {

    private final MongoOperations mongoOperations;
//    constructor is used instead of Autowired
    public CreateUser(@Qualifier("getMongoOperations") MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    @PostMapping("/newUser")
    public boolean newUser(@RequestParam("userName") String userName,
                        @RequestParam("password") String password,
                        @RequestParam("phoneNumber") String phoneNumber,
                        @RequestParam("email") String email) {
        User newUser = new User(userName, password, phoneNumber, email);
        try {
            mongoOperations.insert(newUser);
            System.out.println(newUser);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
