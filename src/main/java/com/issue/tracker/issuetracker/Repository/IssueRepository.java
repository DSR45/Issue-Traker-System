package com.issue.tracker.issuetracker.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.issue.tracker.Model.Issue;


@Repository
public class IssueRepository {

    //create a local memory storage.
    private List<Issue> issues=new ArrayList<>();
    
    
    
    //save issue to list.
    public Issue save(Issue issue){
            if(issue!=null){
                issues.add(issue);
            }
        return issue;
    }

public void remove(int id){
    issues.remove(findById(id));
   
    
    
}

    //get all issues.
    public List<Issue> getAll(){
       return issues;
    }

    

    public Issue findById(int id){
        for(Issue issue:issues){
            if(issue.getId()==id){
                return issue;
            }
        }

        return null;
    }

    
   
    
}
