import '../style/Login.css'
import axios from 'axios';
import { useState } from 'react';

export function Login(){
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const userDTO = {
        username,
        password
    };

    const loginRequest = async() => {
        try {
            const postResponse = await axios.post('http://localhost:8080/api/auth/login', userDTO);
                console.log('POST success:', postResponse.data);

        } catch (error) {
            console.error('Error:', error);
        }

    };

    return(
        <div className="login-body">
            <h1>Login page</h1>
            <form className="login-form">
                <input type="text" id="username" name="username" value={username} placeholder="Enter your username" onChange={(e) => setUsername(e.target.value)}></input>
                <input type="password" id="passHash" name="passHash" value={password} placeholder="Enter your password" onChange={(e) => setPassword(e.target.value)}></input>
                {/* <button onClick={loginRequest}>Sign Up</button> */}
                {/* <button onClick={(console.log(userDTO))}>test Up</button> */}
            </form>
            <button onClick={loginRequest}>Login</button>
            {/* <button onClick={(console.log(userDTO))}>test Up</button> */}
        </div>
    )
}