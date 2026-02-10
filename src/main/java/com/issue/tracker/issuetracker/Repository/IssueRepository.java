package com.issue.tracker.issuetracker.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.issue.tracker.Model.Issue;
import com.issue.tracker.Model.User;

import tools.jackson.databind.ObjectMapper;


@Repository
public class IssueRepository {
    private String url="jdbc:mysql://localhost:3306/mini_issue_tracker";
    private String user="root";
    private String password="divyanshu10@";
    private PreparedStatement psmt;
    
    
    
    //Create connection.
    public Object[] Database(Object... args){
        try{
            Connection conn=DriverManager.getConnection(url, user, password);
            System.out.println("Connection Successfull");
            PreparedStatement psmt=conn.prepareStatement(args[1].toString());
            if(args[0]=="Update"){
                for(int i=2;i<args.length;i++){
                    if(args[i] instanceof Integer){
                        psmt.setInt(i-1,(int)args[i]);
                    }
                    else if(args[i] instanceof String){
                        psmt.setString(i-1,String.valueOf(args[i]));
                    }
                    else{
                         psmt.setString(i-1,String.valueOf(args[i]));
                    }
                    
                    
                }
                int rowEffected=psmt.executeUpdate();
                conn.close();
                return new Object[]{rowEffected};
            }
            else if(args[0]=="Fetch"){
                if(args.length>2){
                    for(int i=2;i<args.length;i++){
                         if(args[i] instanceof String){
                        psmt.setString(i-1,String.valueOf(args[i]));
                    }
                    else if(args[i] instanceof Integer){
                        psmt.setInt(i-1,(int)args[i]);
                    }
                    
                    }

                }
                
                ResultSet result=psmt.executeQuery();
                
                return new Object[]{result};
            }

            return null;
        }
        catch(SQLException e){

            throw new RuntimeException(e);
        }
        
    }

    
//Fetch Result set
Object[] fetch(Object[] obj){
    ResultSet rs=(ResultSet)obj[0];
    try{
        while(rs.next()){
            return new Object[]{rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)};
        }
    }
    catch(SQLException e){
        throw new RuntimeException(e);
    }
return null;
}



    //create a local memory storage.
    private List<Issue> issues=new ArrayList<>();
    
    //save issue to list.
    public Issue save(Issue issue){
            if(issue!=null){
                
                String sql="Insert into issues(id,title,description,status) values(?,?,?,?)";
                Object[] changed=Database(new Object[]{"Update",sql,issue.getId(),issue.getTitle(),issue.getDesc(),issue.getStatus()});
                System.out.println(changed[0]);
                issues.add(issue);
            }
        return issue;
    }

public void remove(int id){
    String sql="Delete from issues where id=?";
    Object[] changed=Database(new Object[]{"Update",sql,id});
    System.out.println("Deleted Row: "+changed[0]);      
}

    //get all issues.
    public List<Issue> getAll(){
         ObjectMapper mapper=new ObjectMapper();

        String sql="Select * from issues";
       Object[] result=Database(new Object[]{"Fetch",sql});
        ResultSet data=(ResultSet)result[0];
       try{
        while (data.next()) {
            System.out.println(data.getString(5));
             Issue issue=new Issue(data.getString(2),data.getString(3));
             if(data.getString(5)!=null){
                 User user=mapper.readValue(data.getString(5),User.class);
                 issue.setAssignTo(user);
             }
            
       issue.setId(data.getInt(1));
       issue.setStatus(data.getString(4));
    
       issues.add(issue);
        }
       }
       catch(SQLException e){
        throw new RuntimeException(e);
       }
       return issues;
    }

    

    public Issue findById(int id){
        
        String sql="Select * from issues where id=?";
        Object[] rs=fetch(Database(new Object[]{"Fetch",sql,id}));
        if(rs.length==0){
            System.out.println("Id not found!");
            return null;
        }
        Issue issue=new Issue(String.valueOf(rs[1]),String.valueOf(rs[2]));
        issue.setId((int)rs[0]);
        issue.setStatus(String.valueOf(rs[3]));
        ObjectMapper mapper=new ObjectMapper();

        issue.setAssignTo(mapper.readValue(String.valueOf(rs[4]), User.class));
        
        return issue;
    }

//Upate 

public void assign(int id,User user){
    String sql="Update issues set user=? where id=?";
   ObjectMapper mapper=new ObjectMapper();
    Object[] changed=Database(new Object[]{"Update",sql,mapper.writeValueAsString(user),id});
    System.out.println(changed[0]);
}

public int setStatus(int id,String status){
    String sql="Update issues set status=? where id=?";
    Object[] changed=Database(new Object[]{"Update",sql,status,id});
    if((int)changed[0]!=0){
        return (int)changed[0];
    }
    return 0;
}

    


    
    
}
