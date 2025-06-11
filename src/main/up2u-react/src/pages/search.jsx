import { useLocation } from "react-router-dom"

export function Search(){
    const data = useLocation();
    const { location, budget, meal, minRating } = data.state || {};
    
    return (
        <>
        <div>location: {location}</div>
        <div>budget: {budget}</div>
        <div>meal: {meal}</div>
        <div>min rating: {minRating}</div>
        </>
    )
}