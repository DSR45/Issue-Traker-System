package com.issue.tracker.Model;
import com.issue.tracker.Model.Status;;
 


public class Issue {
    private int id;
    private String title;
    private String desc;
    private int createdById;
    private int assignedToId;
    private Status status;


    public Issue(int id,String title,String desc,int createdById,int assignedToId,Status status){
        this.id=id;
        this.title=title;
        this.desc=desc;
        this.status=status;
        this.createdById=createdById;
        this.assignedToId=assignedToId;

        
    }


    public int getId(){
        return id;
    }


}
