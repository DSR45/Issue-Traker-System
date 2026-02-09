package com.issue.tracker.issuetracker.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.issue.tracker.Model.Issue;


@Repository
public class IssueRepository {
    private String url="jdbc:mysql://localhost:3306/mini_issue_tracker";
    private String user="root";
    private String password="divyanshu10@";
    private PreparedStatement psmt;
    
    
    
    //Create connection.
    Object[] Database(Object... args){
        try{
            Connection conn=DriverManager.getConnection(url, user, password);
            System.out.println("Connection Successfull");
            PreparedStatement psmt=conn.prepareStatement(args[1].toString());
            if(args[0]=="Update"){
                for(int i=2;i<args.length;i++){
                    if(args[i] instanceof String){
                        psmt.setString(i-1,String.valueOf(args[i]));
                    }
                    else if(args[i] instanceof Integer){
                        psmt.setInt(i-1,(int)args[i]);
                    }
                    
                }
                int rowEffected=psmt.executeUpdate();
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
Object[] fetch(Object[] obj,int indx){
    ResultSet rs=(ResultSet)obj[0];
    try{
        while(rs.next()){
            return new Object[]{rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)};
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
                
                String sql="Insert into issues values(?,?,?,?)";
                Object[] changed=Database(new Object[]{"Update",sql,issue.getId(),issue.getTitle(),issue.getDesc(),issue.getStatus()});
                System.out.println(changed[0]);
                issues.add(issue);
            }
        return issue;
    }

public void remove(int id){
    issues.remove(findById(id));
   
    
    
}

    //get all issues.
    public List<Issue> getAll(){
        String sql="Select * from issues where id=?";
       int id=(int)fetch(Database(new Object[]{"Fetch",sql,814957}),1)[0];
       System.out.println(id);
       return issues;
    }

    

    public Issue findById(int id){
        String sql="Select * from issues where id=?";
        Object[] rs=Database(new Object[]{"Fetch",sql,id});
        ResultSet resultSet=(ResultSet)rs[0];
        try{
            while(resultSet.next()){
                System.out.println(resultSet.getString(2));
            }
        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }
        for(Issue issue:issues){
            if(issue.getId()==id){
                return issue;
            }
        }

        return null;
    }

    


    
    
}
