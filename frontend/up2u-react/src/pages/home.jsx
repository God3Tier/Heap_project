import { useState } from "react"
import { data, Link } from "react-router-dom";
import '../style/Home.css'

export function Home(){
    const [location, setLocation] = useState();
    const [budget, setBudget] = useState();
    const [meal, setMeal] = useState();
    const [minRating, setMinRating] = useState();

    const dataToPass = {
        location, budget, meal, minRating
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
                        <option selected disabled>Select Location</option>
                        <option value="all">ALL</option>
                        <option value="Bencoolen MRT">Bencoolen MRT</option>
                        <option value="Bras Basah MRT">Bras Basah MRT</option>
                        <option value="City Hall MRT">City Hall MRT</option>
                        <option value="Bugis MRT">Bugis MRT</option>
                    </select>
                    <label>Budget:</label>
                    <select value={budget}
                            onChange={e => setBudget(e.target.value)}>
                        <option selected disabled>Select Budget</option>
                        <option value="1">Less than 1</option>
                        <option value="2">Less than 20</option>
                        <option value="3">$$$</option>
                        <option value="4">$$$$</option>
                        <option value="5">$$$$$</option>
                    </select>
                    <label>Meal Type:</label>
                    <select value={meal}
                            onChange={e => setMeal(e.target.value)}>
                        <option selected disabled>Select Meal Type</option>
                        <optgroup label="Asian">
                        <option value="chinese">Chinese</option>
                        <option value="japanese">Japanese</option>
                        <option value="korean">Korean</option>
                        <option value="thai">Thai</option>
                        <option value="local">Local</option>
                        <option value="malay">Malay</option>
                    </optgroup>

                    <optgroup label="Western">
                        <option value="italian">Italian</option>
                        <option value="french">French</option>
                        <option value="international">International</option>
                        <option value="fast_food">Fast Food</option>
                    </optgroup>

                    <optgroup label="Vegetarian & Healthy">
                        <option value="vegetarian">Vegetarian</option>
                        <option value="light_meal">Light Meal</option>
                        <option value="salad">Salad</option>
                    </optgroup>

                    <optgroup label="Desserts & Snacks">
                        <option value="dessert">Dessert</option>
                        <option value="snack">Snacks</option>
                        <option value="beverage">Beverages</option>
                        <option value="coffee">Coffee</option>
                    </optgroup>
                    </select>
                    <label>Min. Rating:</label>
                    <select value={minRating}
                            onChange={e => setMinRating(e.target.value)}>
                        <option selected disabled>Select Rating</option>
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