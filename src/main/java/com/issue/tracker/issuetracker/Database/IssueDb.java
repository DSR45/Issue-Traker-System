package com.issue.tracker.issuetracker.Database;
import java.util.ArrayList;
import com.issue.tracker.Model.Issue;
import com.issue.tracker.Model.Status;



public class IssueDb {
    private ArrayList<Issue> issueDb=new ArrayList<>();

   public IssueDb(Issue issue){
        this.issueDb.add(issue);
    System.out.println(issue);

    }

    
    public Issue getIssue(int id){
        for(Issue issue:issueDb){
            if(issue.getId()==id){
                return issue;
            }
        }
        return new Issue(0, "Not Exist", "", 0, 0,Status.OPEN);
    }

    public void setIssue(Issue issue){
        this.issueDb.add(issue);
    }
    
}
