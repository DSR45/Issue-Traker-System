package com.issue.tracker.issuetracker.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.issue.tracker.Model.User;


@Repository
public class UserRepository {
//Local storage;
    private List<User> users=new ArrayList<>();



    //add user in database;
    public User save(User user){
        if(user!=null){
            users.add(user);
            return user;
        }
        return null;
    }


    public User findById(int id){
        for(User user:users){
            if(user.getId()==id){
                return user;
            }
        }
        return null;
    }

    public List<User> getAll(){
        return users;
    }

    
}
