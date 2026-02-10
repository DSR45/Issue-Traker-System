package com.issue.tracker.issuetracker.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.issue.tracker.Model.User;


@Repository
public class UserRepository {
//Local storage;
List<User> users=new ArrayList<>();

@Autowired
IssueRepository issueRepository;


    //add user in database;
    public User save(User user){
        String sql="Insert into users values(?,?)";

        
        
        if(user!=null){
            Object[] changed=issueRepository.Database(new Object[]{"Update",sql,user.getId(),user.getName()});
            if(!String.valueOf((int)changed[0]).isEmpty()) {
                System.out.println("User added");
                return user;}
        }
        return null;
    }


    public User findById(int id){
        String sql="Select * from users where id=?";
        Object[] result=issueRepository.Database(new Object[]{"Fetch",sql});
        ResultSet data=(ResultSet)result[0];
        User user=new User(0,"");
        try{
            while(data.next()){
                user.setId(data.getInt(1));
                user.setName(data.getString(2));
                 return user;
        }
        return null;
       
    }
    catch(SQLException e){
        throw new RuntimeException(e);
    }
        
    }

    public List<User> getAll(){
        String sql="Select * from users";
        Object[] result=issueRepository.Database(new Object[]{"Fetch",sql});
        ResultSet data=(ResultSet)result[0];
        try{
            while(data.next()){
                User user=new User(data.getInt(1),data.getString(2));
                users.add(user);
            }
        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }
        return users;
    }

    
}
