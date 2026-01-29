package com.issue.tracker.issuetracker;



public class User {
    private String name;
    private int id;
    private String spec;

    public User(String name,int id,String spec){
        this.name=name;
        this.id=id;
        this.spec=spec;
    }

    public String getName(){
        return name;
    }
    public int getId(){
        return id;
    }
    public String getSpec(){
        return spec;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setId(int id){
        this.id=id;
    }
    public void setSpec(String spec){
        this.spec=spec;
    }

    
}
