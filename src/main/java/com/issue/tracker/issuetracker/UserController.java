package com.issue.tracker.issuetracker;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {                                                             
    
    @GetMapping("/sayHello")
    public String sayHello(){
        return "TThis is a API call";
    }

    @PostMapping("/user")
    public String setUser(@RequestBody User user){
        System.out.println(user.getName());
        return "Created";
    }
    
}
