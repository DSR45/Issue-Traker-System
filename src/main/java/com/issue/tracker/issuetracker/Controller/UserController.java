package com.issue.tracker.issuetracker.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.issue.tracker.Model.User;

import com.issue.tracker.issuetracker.Service.UserService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;





@RestController
@RequestMapping("/user")
@CrossOrigin("http://127.0.0.1:5500")
public class UserController {
    UserService service;

    public UserController(UserService service){
        this.service=service;
    }

    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody User user){
        service.createUser(user);

        return ResponseEntity.ok().body(user);
    }

    @GetMapping("all")
    public ResponseEntity<List<User>> getMethodName() {
        return ResponseEntity.ok().body(service.getAlluser());
    }
    


}
