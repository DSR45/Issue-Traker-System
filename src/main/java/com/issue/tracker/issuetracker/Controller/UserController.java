package com.issue.tracker.issuetracker.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.issue.tracker.Model.User;

import com.issue.tracker.issuetracker.Service.UserService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;





@RestController
@RequestMapping("/user")
public class UserController {
    UserService service;

    public UserController(UserService service){
        this.service=service;
    }

    @PostMapping("/create")
    public User create(@RequestBody User user){
        service.createUser(user);
        return user;
    }

    @GetMapping("all")
    public List<User> getMethodName() {
        return service.getAlluser();
    }
    


}
