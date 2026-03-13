import {  CircleCheckBig, CircleDot,Trash, UserCheck, UserPlus } from "lucide-react";
import { remove, resolve } from "../api";
function IssueCard({name,desc,status,showUsers,id,setId,user,loadData}){
    
     async function handlerClick(){
       const result=await resolve(id);
       console.log(result);
       if(result==0){
        loadData();
       }

     }

     async function handlerRemove(){
        const result=await remove(id);
        if(result==0){
            loadData();
        }
     }

    
    
    return <div className="  shadow-xl rounded-md w-100 h-100 p-3 flex flex-col ">
        <div className=" bg-blue-400 h-15 rounded-sm flex justify-between items-center p-3">
             {status=="OPEN"?<CircleDot fill="green" color="gre<en" onClick={handlerClick}/>:<CircleCheckBig color="red"/>}
            <Trash  color="red" fill="white" onClick={handlerRemove}/>
        </div>
        <div className=" flex-1 flex flex-col items-center p-2 overflow-y-scroll no-scrollbar">
            <h1 className=" text-xl font-">{name}</h1>
            <p>{desc}</p>
        </div>
        <div className=" flex bg-gray-200 justify-between p-2">
           
           {!user?<UserPlus onClick={()=>{setId(id);showUsers(true)}}/>:<div className=" flex justify-between flex-1"><h1 className=" font-medium">{user.name}</h1><UserCheck color="red" onClick={()=>{alert("Already Assigned")}}/></div>} 
        </div>
        
    </div>
}

export default IssueCard;