package com.issue.tracker.issuetracker.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import com.issue.tracker.Model.Issue;
import com.issue.tracker.Model.User;

import tools.jackson.databind.ObjectMapper;


@Repository
public class IssueRepository {
    ObjectMapper mapper=new ObjectMapper();

    //Database credentials.
    @Value("${app.url}")
    private String url;
    @Value("${app.username}")
    private String username;
    @Value("${app.password}")
    private String password;
   

     //get all issues.
    public List<Issue> getAll(){
        
            List<Issue> issues=new ArrayList<>();
            try(
                Connection conn=DriverManager.getConnection(url, username, password);
                PreparedStatement psmt=conn.prepareStatement("Select * from issues");
                ResultSet result=psmt.executeQuery();
        ){
                
                while(result.next()){
                        Issue issue=new Issue(result.getString(2),result.getString(3));
                        issue.setId(result.getInt(1));
                        issue.setStatus(result.getString(4));
                        if(result.getString(5)!=null)
                         issue.setAssignTo(mapper.readValue(result.getString(5), User.class) );
                        issues.add(issue);
                       
                }
            }
            catch(SQLException e){
                
            }
return issues;

        }


        //function to save issue into database
        public int save(Issue issue){
            try(Connection conn=DriverManager.getConnection(url, username, password)) {
                PreparedStatement psmt=conn.prepareStatement("Insert into issues(id,title,description,status) values(?,?,?,?)");
                psmt.setInt(1, issue.getId());
                psmt.setString(2, issue.getTitle());
                psmt.setString(3, issue.getDesc());
                psmt.setString(4, issue.getStatus());

                return psmt.executeUpdate();

            } catch (SQLException e) {
                // TODO: handle exception
            }
            return 0;
        }
    

        //function to remove issue from database based on id;
    public int remove(int id){
        try(Connection conn=DriverManager.getConnection(url, username, password)){
            PreparedStatement psmt=conn.prepareStatement("Delete from issues where id=?");
            psmt.setInt(1, id);
            return psmt.executeUpdate();
        }
        catch(SQLException e){

        }
        return 0;
    }


    public int setStatus(int id, String status){
        try(Connection conn=DriverManager.getConnection(url, username, password)) {
            PreparedStatement psmt=conn.prepareStatement("Update issues set status=? where id=?");
            psmt.setString(1, status);
            psmt.setInt(2, id);
            return psmt.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return 0;
    }


    public int assign(int id,User user){
        try(
            Connection conn=DriverManager.getConnection(url, username, password);
            PreparedStatement psmt=conn.prepareStatement("Update issues set user=? where id=?");
    ) {
            
            psmt.setString(1, mapper.writeValueAsString(user));
            psmt.setInt(2, id);
            return psmt.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return 0;
    }
    
    public Issue findById(int id){
        
        try (
            Connection conn=DriverManager.getConnection(url, username, password);
            PreparedStatement psmt=conn.prepareStatement("Select * from issues where id=?");
            ResultSet result=psmt.executeQuery();
    ){
            
            psmt.setInt(1, id);
            while(result.next()){
                Issue issue=new Issue(result.getString(2),result.getString(3));
                        issue.setId(result.getInt(1));
                        issue.setStatus(result.getString(4));
                        if(result.getString(5)!=null)
                         issue.setAssignTo(mapper.readValue(result.getString(5), User.class) );
                        return issue;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
}
