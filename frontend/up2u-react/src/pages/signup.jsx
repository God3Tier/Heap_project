import axios from 'axios'
import '../style/SignUp.css'
import { useState } from 'react';

export function SignUp(){

    const role = "ROLE_REGULAR";
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const userDTO = {
        username,
        password,
        role
    };

    const signupRequest = async() => {
        try {
            const postResponse = await axios.post('http://localhost:8080/create_user', userDTO);
                console.log('POST success:', postResponse.data);

        } catch (error) {
            console.error('Error:', error);
        }

    };

    return(
        <div className="signup-body">
            <h1>Signup page</h1>
            <form className="signup-form">
                <input type="text" id="username" name="username" placeholder="Enter your username"></input>
                <input type="password" id="password" name="password" placeholder="Enter your password"></input>
                <button onClick={signupRequest}>Sign Up</button>
            </form>
        </div>
    )
}