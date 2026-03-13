import {  ArrowBigRight,  X } from "lucide-react";
import { useState } from "react";
import { createIssue} from "../api";


    function AddIssue({prompt,load}){
        const[title,setTitle]=useState("");
        const[disc,setDisc]=useState("");




        async function submitHandler(e){
                e.preventDefault();
                const response =await createIssue({title,desc:disc});
                if(response==0){
                    load();
                }           
                else{
                    alert("Failed to Add");
                }
                prompt(false);
        }

        
       return <div className="shadow-xl rounded-md w-96 max-h-[80vh] border-2 p-4 bg-[#f3ead0]">
       <h1 className=" text-center text-2xl mb-3">Create New Issue</h1>
       <form className="flex flex-col gap-3 h-full" onSubmit={(e)=>submitHandler(e)}>
        <div className=" flex flex-col rounded-md gap-1">
            <label htmlFor="title" >Title :</label>
        <input type="text" value={title} onChange={(value)=>{setTitle(value.target.value)}}  id="title" className="  rounded-md h-10  bg-blue-400"/>
        </div>
       <label htmlFor="desc">Description :</label>
       <textarea value={disc} onChange={(value)=>{setDisc(value.target.value)}} className="border-2 rounded-md flex-1 min-h-[100px] overflow-auto" id="desc" />
        <div className="flex bg-gray-200 justify-between p-2">
            <button className=" " type="button" onClick={()=>prompt(false)}><X color="red"  /></button>
        <button className=" " type="submit"><ArrowBigRight color="green" fill="green" className=" "/> </button>
        </div>
       </form>
    </div>
    }


    export default AddIssue;
