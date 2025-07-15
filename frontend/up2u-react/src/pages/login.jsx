import '../style/Login.css'
import axios from 'axios';
import { useState } from 'react';

export function Login(){
    const backendUrl = import.meta.env.VITE_BACKEND_URL;
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const userDTO = {
        username,
        password
    };
    const [allow, setAllow] = useState("");

    const loginRequest = async() => {
        try {
            const postResponse = await axios.post(`${backendUrl}/api/auth/login`, userDTO);
            console.log('POST success:', postResponse.data);

            // save token/username/userid to local storage
            localStorage.setItem('token', postResponse.data.token);
            localStorage.setItem('username', postResponse.data.username);
            localStorage.setItem('userId', postResponse.data.userId);
            console.log('Token Saved', postResponse.data.token);
            console.log('Username Saved', postResponse.data.username);
            console.log('UserID Saved', postResponse.data.userId);
            window.location.href = "/";
            

        } catch (error) {
            console.error('Error:', error);
            if(error.status == '401'){
                console.log("Wrong password");
                setAllow("Wrong password");
            }
        }

    };

    return(
        <div className="login-body">
            <h1>Login page</h1>
            <form className="login-form" onSubmit={(e) => e.preventDefault()}>
                <input 
                    type="text"
                    id="username"
                    name="username"
                    value={username}
                    placeholder="Enter your username"
                    onChange={(e) => setUsername(e.target.value)}
                />
                <input
                    type="password"
                    id="passHash"
                    name="passHash"
                    value={password}
                    placeholder="Enter your password"
                    onChange={(e) => setPassword(e.target.value)}
                />
                <button onClick={loginRequest}>Login</button>
            </form>
            <p>{allow}</p>

        </div>
    )
}