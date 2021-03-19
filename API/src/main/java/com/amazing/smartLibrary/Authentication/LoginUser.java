package com.amazing.smartLibrary.Authentication;

import com.amazing.smartLibrary.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginUser {

    private final MongoOperations mongoOperations;

    public LoginUser(@Qualifier("getMongoOperations") MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    @PostMapping("/login")
    public boolean login(@RequestParam("userName") String userName,
                         @RequestParam("password") String password) {
        Query userQuery = new Query(Criteria.where("userName").is(userName));
        try {
            User user = (User) mongoOperations.findOne(userQuery, User.class);
            if(user.getPassword().compareTo(password) == 0)
                return true;
            else return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
