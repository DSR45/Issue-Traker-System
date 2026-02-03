package com.issue.tracker.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


 




public class Issue {
    private int id;
    private String title;
    private String desc;
    private String status;
    private User assignTo;
    
    


    
    public Issue(String title, String desc){
        
       
        this.title=title;
        this.desc=desc;
        
       
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public void setAssignTo(User assignTo) {
        this.assignTo = assignTo;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public User getAssignTo() {
        return assignTo;
    }
    public String getDesc() {
        return desc;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

public void setStatus(String status) {
    this.status = status;
}
public String getStatus() {
    return status;
}

}
