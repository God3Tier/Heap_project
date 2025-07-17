import { useEffect, useState } from "react"
import { data, Link } from "react-router-dom";
import logo from "../assets/logo.png"
import background from "../assets/background.png"
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

    return (
        <div className="home-body">
      
          {/* Text Section */}
          <div className="hero-section">
            <div className="hero-text">
              <h1>
                Hello <span className="username">“{user || 'User'}”</span>!<br />
                Ready to <span className="highlight">satisfy</span><br />
                your hunger and wallet?
              </h1>
            </div>
      
            <div className="hero-image">
              <img src={background} alt="Delicious Food" />
            </div>
          </div>
      
          {/* Meal Search */}
          <div className="topBody">
            <h3 className="background-label">Find Your Meal below: </h3>
      
            <nav className="searchBar">
              <div className="search-section">
                <label>📍 Location:</label>
                <select defaultValue="all" onChange={e => setLocation(e.target.value)}>
                  <option value="all">All</option>
                  <option value="Bencoolen MRT">Bencoolen MRT</option>
                  <option value="Bras Basah MRT">Bras Basah MRT</option>
                  <option value="City Hall MRT">City Hall MRT</option>
                  <option value="Bugis MRT">Bugis MRT</option>
                  <option value="Dhoby Ghaut MRT">Dhoby Ghaut MRT</option>
                  <option value="Somerset MRT">Somerset MRT</option>
                </select>
              </div>
      
              <div className="search-section">
                <label>💸 Budget:</label>
                <select defaultValue="all" onChange={e => setBudget(e.target.value)}>
                  <option value="all">All</option>
                  <option value="10">Less than 10</option>
                  <option value="20">Less than 20</option>
                  <option value="30">Less than 30</option>
                  <option value="40">Less than 40</option>
                  <option value="50">Less than 50</option>
                </select>
              </div>
      
              <div className="search-section">
                <label>🍽️ Meal Type:</label>
                <select defaultValue="all" onChange={e => setMeal(e.target.value)}>
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
              </div>
      
              <div className="search-section">
                <label>⭐ Min. Rating:</label>
                <select defaultValue="all" onChange={e => setRating(e.target.value)}>
                  <option value="all">All</option>
                  <option value="1">1 Star</option>
                  <option value="2">2 Star</option>
                  <option value="3">3 Star</option>
                  <option value="4">4 Star</option>
                  <option value="5">5 Star</option>
                </select>
              </div>
      
              <Link to={"/search"} state={dataToPass}>
                <button className="search-button">Search 🔍 </button>
              </Link>
            </nav>
          </div>
        </div>
      );
    }      