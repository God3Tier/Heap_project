import axios from 'axios'
import '../style/SignUp.css'
import { useState } from 'react';

export function SignUp(){

    const role = ["ROLE_REGULAR"];
    const [username, setUsername] = useState("");
    const [passHash, setPassHash] = useState("");
    const userDTO = {
        username,
        passHash,
        role
    };

    const signupRequest = async() => {
        try {
            const postResponse = await axios.post('http://localhost:8080/user/create_user', userDTO);
                console.log('POST success:', postResponse.data);

        } catch (error) {
            console.error('Error:', error);
        }

    };

    return(
        <div className="signup-body">
            <h1>Signup page</h1>
            <form className="signup-form">
                <input type="text" id="username" name="username" value={username} placeholder="Enter your username" onChange={(e) => setUsername(e.target.value)}></input>
                <input type="password" id="passHash" name="passHash" value={passHash} placeholder="Enter your password" onChange={(e) => setPassHash(e.target.value)}></input>
                {/* <button onClick={signupRequest}>Sign Up</button> */}
                {/* <button onClick={(console.log(userDTO))}>test Up</button> */}
            </form>
            <button onClick={signupRequest}>Sign Up</button>
            {/* <button onClick={(console.log(userDTO))}>test Up</button> */}
        </div>
    )
}