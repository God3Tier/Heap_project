import { useLocation } from "react-router-dom"
import axios from 'axios';

export function Search(){
    const data = useLocation();
    const { location, budget, meal, minRating } = data.state || {};
    
    return (
        <>
        <div>location: {location}</div>
        <div>budget: {budget}</div>
        <div>meal: {meal}</div>
        <div>min rating: {minRating}</div>
        <button onClick={FetchStuff}>Stuff</button>
        </>
    )
}

function FetchStuff(){
    axios.get('http://localhost:8080/meals').then(response => {
        console.log('works');
    })
}