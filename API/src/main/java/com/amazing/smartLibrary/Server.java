package com.amazing.smartLibrary;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Server {

    @RequestMapping("/")
    public String homePage() {
        return "home Page";
    }
}
