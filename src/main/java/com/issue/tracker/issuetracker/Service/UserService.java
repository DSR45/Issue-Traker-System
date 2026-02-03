package com.issue.tracker.issuetracker.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.issue.tracker.Model.User;
import com.issue.tracker.issuetracker.Repository.UserRepository;


@Service
public class UserService {
    

UserRepository repository;


public UserService(UserRepository repository){
    this.repository=repository;
}


public User createUser(User user){
    System.out.println("hello");
    if(user!=null){
        repository.save(user);
        return user;
    }
    return null;
}   


public User findUserById(int id){
    return repository.findById(id);
}

public List<User> getAlluser(){
return repository.getAll();
}










}
