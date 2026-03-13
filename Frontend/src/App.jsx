
import { useEffect, useState } from 'react';
import AddIssue from './components/AddIssue';
import Footer from './components/Footer'
import Header from './components/Header'
import Issues from './components/Issues'
import { getIssues } from './api';


function App(){
   const[data,setData]=useState([]);
  const[loading,setLoading]=useState(true);
  const[addIssue,setAddIssue]=useState(false);
  

  function openSortData(){
  console.log("Before sort:", data);

  const newData = [...data].sort((a,b)=>{
    if(a.status.toUpperCase() === "OPEN" && b.status.toUpperCase() === "RESOLVED"){
      return -1;
    }
    if(a.status.toUpperCase() === "RESOLVED" && b.status.toUpperCase() === "OPEN"){
      return 1;
    }
    return 0;
  });

  console.log("After sort:", newData);

  setData(newData);
}
  function resolveSortData(){
  console.log("Before sort:", data);

  const newData = [...data].sort((a,b)=>{
    if(a.status.toUpperCase() === "OPEN" && b.status.toUpperCase() === "RESOLVED"){
      return 1;
    }
    if(a.status.toUpperCase() === "RESOLVED" && b.status.toUpperCase() === "OPEN"){
      return -1;
    }
    return 0;
  });

  console.log("After sort:", newData);

  setData(newData);
}



  function updateAddIssue(data){
    setAddIssue(data);
    
  }



   async function loadData(){
            const result=await getIssues();
             setData(result);
            setLoading(false);
            return 0;
        }

  useEffect(()=>{
    loadData()
  },[])

  useEffect(() => {
  if (addIssue) {
    document.body.style.overflow = "hidden";
  } else {
    document.body.style.overflow = "auto";
  }
}, [addIssue]);

  
  return <><div className={' min-h-screen  p-2 border-1 m-0 w-full  flex flex-col bg-blue-50'}>
  <Header prompt={updateAddIssue} openSortData={openSortData} resolveSortData={resolveSortData}/>
  <Issues  data={data} loading={loading} loadData={loadData} />
  <Footer />
   {addIssue && (
        <div className="fixed inset-0 bg-black/40 flex justify-center items-center z-50">
          <AddIssue prompt={updateAddIssue} load={loadData} closeModal={() => setAddIssue(false)} />
        </div>
      )}
  </div>
  
  </>
}

     
export default App
