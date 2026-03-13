import { CirclePlus } from "lucide-react";


export default function Card({assign,user,show}){
   
    function handlerClick(){
        assign(user)
        console.log(user)
        show(false);
    }
   
    return <div className=" rounded-md flex shadow-md bg-[#efdeb176] justify-between p-2 ">
        <h1>{user.name.toUpperCase()}</h1>
         <CirclePlus color="green" onClick={handlerClick}/>
    </div>
}