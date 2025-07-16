import { Link } from 'react-router-dom'

export function NavBar(){
    const token = localStorage.getItem('token');
    const isLoggedIn = !!token;

    const handleLogout = () => {
        localStorage.clear();
        window.location.href = "/";
    };

    return(
        <div className="navBar">
            <div className="front">
                <Link to="/"><button>Home</button></Link>
                <Link to="/about-us"><button>About Us</button></Link>
                <Link to="/list-reviews"><button>List Reviews</button></Link>
            </div>
            <h1>UP2U</h1>
            <div className="back">
                {isLoggedIn ? (
                    <>
                        <Link to="/reviews"><button>Give Reviews</button></Link>
                        <button onClick={handleLogout}>Logout</button>
                    </>
                    ) : (
                    <>
                        <Link to="/login">
                            <button>Login</button>
                        </Link>
                        <Link to="/signup">
                            <button>Create Account</button>
                        </Link>
                    </>
                )}
            </div>
        </div>
    )
}