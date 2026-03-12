package com.issue.tracker.issuetracker.Repository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import com.issue.tracker.Model.User;







@Repository
public class UserRepository {


    @Value("${app.url}")
    private String url;
    @Value("${app.username}")
    private String username;
    @Value("${app.password}")
    private String password;


@Autowired
IssueRepository issueRepository;




    //add user in database;
    public int save(User user){
        String sql="Insert into users values(?,?)";



        if(user!=null){
            try(Connection conn=DriverManager.getConnection(url, username, password)) {
                PreparedStatement psmt=conn.prepareStatement(sql);
                psmt.setInt(1,user.getId() );
                psmt.setString(2, user.getName());
                int result=psmt.executeUpdate();
                return result;
                
            } catch (SQLException e) {
                // TODO: handle exception
            }    
           
    }
     return 0;
    }

    public User findById(int id){
        String sql="Select * from users where id=?";
        try(Connection conn=DriverManager.getConnection(url, username, password)) {
            
            PreparedStatement psmt=conn.prepareStatement(sql);
            psmt.setInt(1, id);
            ResultSet result=psmt.executeQuery();

           while(result.next()){
            User user=new User(0,"");
                user.setId(result.getInt(1));
                user.setName(result.getString(2));
                 return user;
        }

            
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;

    }




    public List<User> getAll(){
        //Local storage;
        List<User> users=new ArrayList<>();

        String sql="Select * from users";
        try(Connection conn=DriverManager.getConnection(url, username, password)) {
           
            PreparedStatement psmt=conn.prepareStatement(sql);
            ResultSet result=psmt.executeQuery();
            
            while(result.next()){
                User user=new User(result.getInt(1),result.getString(2));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Something Went wrong "+e);
        }
        

        return users;

       
    }

    
}
