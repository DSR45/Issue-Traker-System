package com.issue.tracker.issuetracker.Service;


import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.issue.tracker.Model.Issue;
import com.issue.tracker.Model.User;
import com.issue.tracker.issuetracker.Repository.IssueRepository;


@Service
public class IssueService {
    
   IssueRepository repository;
    
    
public IssueService(IssueRepository repository){
    this.repository=repository;
}



//create a new issue;
   public Issue createIssue(Issue issue){
    if(issue!=null){
        LocalTime time=LocalTime.now();
        
        String ms=String.valueOf(time.getNano()).substring(0,2) +Math.round(Math.random()*100)+time.getMinute();
        issue.setId(Integer.parseInt(ms));
        issue.setStatus("OPEN");
        return repository.save(issue);
    }
    return null;

   }



   //update the issue status to resolved if issue exists;
   public Issue Resolve(int id){
        
        if(!String.valueOf(id).isEmpty()&& repository.findById(id)!=null){

            repository.setStatus(id, "Resolved");
            Issue issue=repository.findById(id);
            return issue;
        }

        return null;
   }



//assign issue to a particular user;
   public Issue assignTo(int id, User user){
        
        if(user!=null && !String.valueOf(id).isEmpty()){
         if(repository.findById(id)!=null){
            repository.assign(id, user);
         }
           
        }
        return null;
   }



   //return all the issues;
   public List<Issue> listAll(){
        return repository.getAll();
   }
    



    
}
