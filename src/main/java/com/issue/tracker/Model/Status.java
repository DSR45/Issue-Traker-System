package com.issue.tracker.Model;

public enum Status{
    OPEN(1),PENDING(2),RESOLVED(3);


    private int statusId;
     Status(int statusId){
        this.statusId=statusId;
    }

    int getStatusId(){
        return statusId;
    }

     void setStatusId(int id){
        this.statusId=id;
    }
}
