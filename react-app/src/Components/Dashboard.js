import React, {useEffect, useState} from "react";
import axios from "axios";
const Dashboard =() => {
    const [user, setUser] = useState(null);
    useEffect(() =>{
        axios.get('http://localhost:8083/user-info',{withCredentials:true})//request is beign done with authentication
        .then(response => {
            // console.log(response);
            setUser(response.data);
        })
        .catch(error => {
            console.log('Error occurred:', error);
        });
    }, []);

    return(
    <div>
        
        <h2>Welcome to the Dashboard</h2>
        {user ? (
        <div>
            <p><strong>Name:</strong> {user.name}</p>
            <p><strong>Email:</strong> {user.email}</p>

        </div>
        ) : (<p>Loadign user data....</p>)}
    </div>
        
        
    );

}

export default Dashboard;