package com.issue.tracker.issuetracker.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.issue.tracker.Model.Issue;

@RestController
@RequestMapping("/issue")
public class IssueController {
    
    @PostMapping("/create")
    public void createIssue(Issue issue){
        
    }
}
