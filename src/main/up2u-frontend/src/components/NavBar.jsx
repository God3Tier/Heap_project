import { Link } from 'react-router-dom'

export function NavBar(){
    return(
        <div className="navBar">
            <div className="front">
                <Link to="/"><button>Home</button></Link>
                <Link to="/about-us"><button>About Us</button></Link>    
            </div>
            <h1>UP2U</h1>
            <div className="back">
                <Link to="/login"><button>Login</button></Link>
                <Link to="/signup"><button>Create Account</button></Link>
            </div>
        </div>
    )
}