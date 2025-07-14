import '../style/SignUp.css';
import axios from 'axios';
import { useState } from 'react';

export function SignUp() {
    const backendUrl = import.meta.env.VITE_BACKEND_URL;
    const [username, setUsername] = useState("");
    const [passHash, setPassHash] = useState("");
    const role = ["ROLE_REGULAR"];
    const [allow, setAllow] = useState("");

    const userDTO = {
        username,
        passHash,
        role
    };

    const signupRequest = async () => {
        try {
            const postResponse = await axios.post(`${backendUrl}/user/create_user`, userDTO);
            console.log('POST success:', postResponse.data);
            setAllow("Account Created");
        } catch (error) {
            console.error('Error:', error);
            if (error.response?.status === 500) {
                console.log("Username already in use");
                setAllow("Username already in use");
            } else {
                setAllow("Signup failed");
            }
        }
    };

    return (
        <div className="signup-container">
            <div className="left-panel">
                <img src="/assets/food-banner.png" alt="Food" />
            </div>
            <div className="right-panel">
                <img className="logo" src="/assets/logo.png" alt="UP2U Logo" />
                <button className="google-btn">Log in with Google</button>
                <button className="singpass-btn">Sign up with Singpass</button>
                <hr className="divider" />
                <p className="or">OR</p>

                <label htmlFor="username">Email</label>
                <input 
                    type="text" 
                    id="username" 
                    value={username}
                    placeholder="name@email.com"
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

                <p className="disclaimer">
                    We will send an email with a confirmation link for registration.
                </p>
                <button className="continue-btn" onClick={signupRequest}>Continue</button>
                <p>{allow}</p>
            </div>
        </div>
    );
}
