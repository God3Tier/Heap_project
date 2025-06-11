import '../style/SignUp.css'

export function SignUp(){
    return(
        <div className="signup-body">
            <h1>Signup page</h1>
            <form className="signup-form">
                <input type="text" id="email" name="email" placeholder="Enter your email"></input>
                <input type="password" id="password" name="password" placeholder="Enter your password"></input>
                <button type="submit">Sign Up</button>
            </form>
        </div>
    )
}