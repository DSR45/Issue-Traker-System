package com.issue.tracker.issuetracker.Repository;

import org.springframework.stereotype.Repository;

import com.issue.tracker.Model.Issue;
import com.issue.tracker.issuetracker.Database.IssueDb;


@Repository
public class IssueRepository {

    //

    public void setIssue(Issue issue){
        IssueDb db=new IssueDb(issue);
    }
    
}
