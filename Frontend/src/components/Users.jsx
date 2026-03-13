import React, { useEffect, useState } from "react";
import Card from "./UserCard";
import { assignUser, getUsers } from "../api";

function Users({load,showDiv,id,loadData}){
    const [users,setUsers]=useState([]);

    async function loadUsers(){
        const data=await getUsers();
        console.log(data);
        setUsers(data);
    }
    
    async function assign(user){
        const response=await assignUser(id,user)
        showDiv(false);
        if(response==0){
            
            loadData();
            
        }
    }

    useEffect(()=>{
if(load){
        loadUsers();
    }
    },[])
    
    return <div className="shadow-xl rounded-md w-96 min-h-[40vh] max-h-[80vh] border-2 p-4 bg-[#f3ead0] absolute flex-col flex gap-3 overflow-y-scroll no-scrollbar ">
        
        {users.map((value)=>{
            return <Card key={value.id} user={value} assign={assign} />
        })}
        <button className="border-2 inline-block bg-[#e8e1e1] " onClick={()=>showDiv(false)}>Close</button>
    </div>



}



export default Users;