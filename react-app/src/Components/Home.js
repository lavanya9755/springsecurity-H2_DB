import React from "react";
const Home = () => {

    const googleLogin = () => {

        window.open("http://localhost:8083/oauth2/authorization/google", "_self")};

    const githubLogin = () => {
        window.open("http://localhost:8083/oauth2/authorization/github", "_self")};
    
    return(
        <div>
            
                <h2>Welcome to OAuth Demo Application</h2>
                <button onClick={googleLogin}>Login with Google</button >
                <button onClick={githubLogin}>Login with GitHub</button >
        </div>
    )
}

export default Home;