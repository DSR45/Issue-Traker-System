async function getIssues(){

    const response= await fetch("http://localhost:8080/issue/all");
    try{
         if(!response.ok){
            throw new Error("Unable to fetch Issues");                
    }
    const result= await response.json();
    console.log(result);
    return result;

    }
   
    catch(e){
        console.error(e);
        return [];
    }

}


async function createIssue(obj){
    const options={
        method:"POST",
        headers:{
            'content-type':'application/json'
        },
        body:JSON.stringify(obj)
    }
    const response=await fetch("http://localhost:8080/issue/create",options);
    if(response.ok){
        return 0
    }
    else{
        return response.status;
    }
}

async function getUsers(){
    const options={
        method:"GET"
    }

    const response=await fetch("http://localhost:8080/user/all");
    const result=await response.json();
    return result;

}

async function assignUser(id,user){
    const options={
        method:'PUT',
        headers:{
            'content-type':'application/json'
        },
        body:JSON.stringify(user)
    }

    const response=await fetch(`http://localhost:8080/issue/assign/${id}`,options);
    if(response.ok){
        
        return 0;
    }
    else{
        alert("Something went wrong");
        throw new Error("Assign operation failed");
    }
}

async function resolve(id){
    const options={
        method:"PUT",
        headers:{
            'content-type':'application/json'
        }
    }

    const response=await fetch(`http://localhost:8080/issue/resolve/${id}`,options);
    
    if(!response.ok){
        alert("Operation failed");
        return -1;
    }
    else{
        return 0;
    }
}

async function remove(id){
    const options={
        method:"PUT",
    }
    const response=await fetch(`http://localhost:8080/issue/delete/${id}`,options);
    if(!response.ok){
        alert("Operation Failed");
        return 1;
    }
    return 0

}

export {createIssue,getIssues,getUsers,assignUser,resolve,remove};