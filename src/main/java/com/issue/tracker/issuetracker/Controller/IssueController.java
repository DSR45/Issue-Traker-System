package com.issue.tracker.issuetracker.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.issue.tracker.Model.Issue;
import com.issue.tracker.Model.User;
import com.issue.tracker.issuetracker.Service.IssueService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/issue")
@CrossOrigin("http://127.0.0.1:5500")
public class IssueController {

   IssueService service;

public IssueController(IssueService service){
    this.service=service;
}



    @PostMapping("/create")
    public ResponseEntity<String> createIssue(@RequestBody Issue issue){
        if(issue!=null){
            service.createIssue(issue);
            return ResponseEntity.ok().body("Done");
        }
        return null;
    }

    @GetMapping("/all")
    
    public ResponseEntity<List<Issue>> getAll() {
        return ResponseEntity.ok().body(service.listAll());

    }

    @PutMapping("/assign/{id}")
    public Issue assignUser(@PathVariable int id,@RequestBody User user){
        if(user!=null){
            return service.assignTo(id, user);
        }
        
        return null;
    }
    


   
}
