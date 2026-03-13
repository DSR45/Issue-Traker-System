import {  CirclePlus, FunnelPlus } from "lucide-react";
import { useState } from "react";


 

function Header({prompt,openSortData,resolveSortData}){
    const[filter,setFilter]=useState(false);
    function handlerFilter(){
        setFilter(prev=>!prev);
        if(filter){
            openSortData();
        }
        else{
            resolveSortData();
        }
        
        
    }
    

   return <header className=" flex  shadow-sm shadow-gray-300 items-center h-1/8 justify-between p-5 ">
   
        <div className=" flex gap-2   ">
            <h1 className="">Issues</h1>
            <CirclePlus  onClick={()=>{prompt(true)}}/>
        </div>
        <FunnelPlus className="" onClick={handlerFilter} color={filter?"red":"black"}/> 
    </header>
}

export default Header;