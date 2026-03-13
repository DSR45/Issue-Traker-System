import { Copyright } from "lucide-react";
import { BsGithub } from "react-icons/bs";
import { FaFacebook, FaInstagram, FaLinkedin } from "react-icons/fa";


function Footer(){
    return <footer className="  flex justify-between p-2 shadow-md shadow-gray-300">
       <p><Copyright/></p>
       
       <span className="flex gap-5">
        <a href="https://github.com/DSR45" target="_blank">
    <BsGithub size={25}/> 
        </a>
        <a href="https://linkedin.com/in/divyanshu-singh-184a03370" target="_blank">
       <FaLinkedin size={25}/> 
       </a>
       
       <FaInstagram size={25} onClick={()=>{}}/>
        </span>
       <span className="flex gap-5">
        <p>terms</p>
       <p>policy</p>
       <p>About</p>
       </span>
      
        
    </footer>
}

export default Footer;