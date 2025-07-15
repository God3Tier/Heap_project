import axios from 'axios'
import '../style/SignUp.css'
import { useState } from 'react';

export function SignUp(){
    const backendUrl = import.meta.env.VITE_BACKEND_URL;
    const role = ["ROLE_REGULAR"];
    const [username, setUsername] = useState("");
    const [passHash, setPassHash] = useState("");
    const [allow, setAllow] = useState("");
    const userDTO = {
        username,
        passHash,
        role
    };

    const signupRequest = async() => {
        try {
            const postResponse = await axios.post(`${backendUrl}/user/create_user`, userDTO);
            console.log('POST success:', postResponse.data);
            setAllow("Account Created");
        } catch (error) {
            console.error('Error:', error);
            if(error.status == '500'){
                console.log("Username already in use");
                setAllow("Username already in use");
            }
        }
    };

    return(
        <div className="signup-container">
            <div className="left-panel">
                <img src="/assets/food-banner.png" alt="Food" />
            </div>
            <div className="right-panel">
                <img className="logo" src="/assets/logo.png" alt="UP2U Logo" />

                <label htmlFor="username">Username</label>
                <input 
                    type="text" 
                    id="username" 
                    value={username}
                    placeholder="Enter your Username"
                    onChange={(e) => setUsername(e.target.value)}
                />

                <label htmlFor="passHash">Password</label>
                <input 
                    type="password" 
                    id="passHash" 
                    value={passHash}
                    placeholder="Enter your password"
                    onChange={(e) => setPassHash(e.target.value)}
                />

                <button className="continue-btn" onClick={signupRequest}>Continue</button>
                <p>{allow}</p>
            </div>
        </div>
    )
}