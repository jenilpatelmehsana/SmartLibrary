package com.amazing.smartLibrary.Authentication;

import com.amazing.smartLibrary.Models.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class LoginUser {

    private final MongoOperations mongoOperations;

    public LoginUser(@Qualifier("getMongoOperations") MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    @PostMapping("/login")
    public Map<String, Boolean> login(@RequestParam("userName") String userName,
                                      @RequestParam("password") String password) {
        Query userQuery = new Query(Criteria.where("userName").is(userName));
        try {
            User user = (User) mongoOperations.findOne(userQuery, User.class);
            if(user.getPassword().compareTo(password) == 0) {
                return Collections.singletonMap("validation", true);
            }
            else return Collections.singletonMap("validation", false);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Collections.singletonMap("validation", false);
        }
    }

}
