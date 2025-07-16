import '../style/SignUp.css'

export function SignUp(){
    return(
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
    <label for="email">Email</label>
<input type="email" id="email" placeholder="name@email.com" />
    <p className="disclaimer">We will send an email with a confirmation link for registration.</p>
    <button className="continue-btn">Continue</button>
  </div>
</div>

//         <div className="signup-body">
//             <h1>Signup page</h1>
//             <form className="signup-form">
//                 <input type="text" id="email" name="email" placeholder="Enter your email"></input>
//                 <input type="password" id="password" name="password" placeholder="Enter your password"></input>
//                 <button type="submit">Sign Up</button>
//             </form>
//         </div>
    )
}

