import '../style/Login.css'

export function Login(){
    return(
        <div className="login-body">
            <h1>Login page</h1>
            <form className="login-form">
                <input type="text" id="email" name="email" placeholder="Enter your email"></input>
                <input type="password" id="password" name="password" placeholder="Enter your password"></input>
                <button type="submit">Login</button>
            </form>
        </div>
    )
}