import { useEffect, useState } from "react"
import { data, Link } from "react-router-dom";
import '../style/Home.css'

export function Home(){
    const [location, setLocation] = useState("all");
    const [budget, setBudget] = useState("all");
    const [mealType, setMeal] = useState("all");
    const [rating, setRating] = useState("all");
    const [user, setUser] = useState("");

    const dataToPass = {
        location, budget, mealType, rating
    };

    // setting user to show on frontend
    useEffect(() => {
        if(localStorage.getItem('username') != null){
            setUser(" " + localStorage.getItem('username'));
        }
    }, []);

    return(
        <div className="home-body">
            <h1>Home page</h1>
            <p>Hello{user}!</p>
            
            <div className="topBody">
                <label>Background img here</label>
                <nav className="searchBar">
                    <label>Location:</label>
                    <select defaultValue="all"
                            onChange={e => setLocation(e.target.value)}>
                        <option value="all">All</option>
                        <option value="Bencoolen MRT">Bencoolen MRT</option>
                        <option value="Bras Basah MRT">Bras Basah MRT</option>
                        <option value="City Hall MRT">City Hall MRT</option>
                        <option value="Bugis MRT">Bugis MRT</option>
                    </select>
                    <label>Budget:</label>
                    <select defaultValue="all"
                            onChange={e => setBudget(e.target.value)}>
                        <option value="all">All</option>
                        <option value="10">Less than 10</option>
                        <option value="20">Less than 20</option>
                        <option value="30">Less than 30</option>
                        <option value="40">Less than 40</option>
                        <option value="50">Less than 50</option>
                    </select>
                    <label>Meal Type:</label>
                    <select defaultValue="all"
                            onChange={e => setMeal(e.target.value)}>
                        <option value="all">All</option> 
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
                    <select defaultValue="all"
                            onChange={e => setRating(e.target.value)}>
                        <option value="all">All</option>
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
                    <Link to="/maps"><p>MAP HERE</p></Link>
                </div>
                <div className="map2">
                    <p>Map here</p>
                </div>
            </div>
        </div>
    )
}