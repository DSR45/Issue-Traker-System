import { useEffect, useState } from "react";
import IssueCard from "./IssueCard"
import { Loader } from "lucide-react";
import Users from "./Users";


function Issues({data,loading,loadData,sortData}){
    const [showUsers,setShowUsers]=useState(false);
    const [id,setId]=useState(null);
    
    
    
    function show(bool){
        setShowUsers(bool);
    }
  
    
    if(loading){

        return <div className=" flex justify-center flex-1 items-center">
            <Loader color="red"/>
        </div> 
    }
    
    else{
        return <>
        
    <div className={"  flex-1  m-3 flex flex-wrap p-3 gap-3 justify-between " }  >
       {/* {sort?data.sort((a,b)=>{if(a.status.toUpperCase()=="OPEN" && b.status.toUpperCase()=="RESOLVED"){return -1}else if(a.status=="RESOLVED"&& b.status=="OPEN"){return 1}else{return 0}}).map(issue=><IssueCard loadData={loadData} user={issue.assignTo} name={issue.title} desc={issue.desc} key={issue.id} id={issue.id} status={issue.status}  showUsers={show} setId={setId}/>):data.sort((a,b)=>{if(a.status.toUpperCase()=="OPEN" && b.status.toUpperCase()=="RESOLVED"){return 1}else if(a.status=="RESOLVED"&& b.status=="OPEN"){return -1}else{return 0}}).map(issue=><IssueCard loadData={loadData} user={issue.assignTo} name={issue.title} desc={issue.desc} key={issue.id} id={issue.id} status={issue.status}  showUsers={show} setId={setId}/>)} */}
       {data.map(issue=><IssueCard loadData={loadData} user={issue.assignTo} name={issue.title} desc={issue.desc} key={issue.id} id={issue.id} status={issue.status}  showUsers={show} setId={setId}/>)}
        {showUsers && <div className="fixed inset-0 bg-black/40 flex justify-center items-center z-50"><Users load={showUsers} loadData={loadData} showDiv={show} id={id}/></div> }
    </div>
    </>
    }
    
}





export default Issues;