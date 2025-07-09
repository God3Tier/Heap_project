import { useState } from "react"
import { data, Link } from "react-router-dom";
import '../style/Home.css'

export function Home(){
    const [location, setLocation] = useState("all");
    const [budget, setBudget] = useState("all");
    const [mealType, setMeal] = useState("all");
    const [rating, setRating] = useState("all");

    const dataToPass = {
        location, budget, mealType, rating
    };

    return(
        <div className="home-body">
            <h1>Home page</h1>
            
            <div className="topBody">
                <label>Background img here</label>
                <nav className="searchBar">
                    <label>Location:</label>
                    <select value={location}
                            onChange={e => setLocation(e.target.value)}>
                        <option selected value="all">All</option>
                        <option value="Bencoolen MRT">Bencoolen MRT</option>
                        <option value="Bras Basah MRT">Bras Basah MRT</option>
                        <option value="City Hall MRT">City Hall MRT</option>
                        <option value="Bugis MRT">Bugis MRT</option>
                    </select>
                    <label>Budget:</label>
                    <select value={budget}
                            onChange={e => setBudget(e.target.value)}>
                        <option selected value="all">All</option>
                        <option value="1">Less than 1</option>
                        <option value="2">Less than 20</option>
                        <option value="3">$$$</option>
                        <option value="4">$$$$</option>
                        <option value="5">$$$$$</option>
                    </select>
                    <label>Meal Type:</label>
                    <select value={mealType}
                            onChange={e => setMeal(e.target.value)}>
                        <option selected value="all">All</option>
                        <option value="korean">Korean</option>
                        <option value="japanese">Japanese</option>
                        <option value="chinese">Chinese</option>
                        <option value="indian">Indian</option>
                    </select>
                    <label>Min. Rating:</label>
                    <select value={rating}
                            onChange={e => setRating(e.target.value)}>
                        <option selected value="all">All</option>
                        <option value="1">1 Star</option>
                        <option value="2">2 Star</option>
                        <option value="3">3 Star</option>
                        <option value="4">4 Star</option>
                        <option value="5">5 Star</option>
                    </select>
                    <Link to={"/search"} state={dataToPass}><button>Search</button></Link>
                </nav>
            </div>
            <div className="mapBody">
                <div className="map">
                    <p>MAP HERE</p>
                </div>
                <div className="map2">
                    <p>Map here</p>
                </div>
            </div>
        </div>
    )
}