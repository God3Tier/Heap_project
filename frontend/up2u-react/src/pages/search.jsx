import { useLocation } from "react-router-dom"
import { useEffect, useState } from "react";
import axios from 'axios';
import '../style/Search.css'

function FetchStuff(){
    axios.get('http://localhost:8080/api/stalls').then(response => {
        console.log('works');
    })
}

export function Search(){
    const data = useLocation();
    const { location, budget, mealType, rating } = data.state || {};
    const [stalls, setStalls] = useState([]);
    const filterDTO = {
        mealType,
        location,
        budget,
        rating
    };
    const [toPrint, setToPrint] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            try {
                // POST to /api/filter
                // and get what is passed to us
                console.log(filterDTO);
                const postResponse = await axios.post('http://localhost:8080/api/filter', filterDTO);
                console.log('POST success:', postResponse.data);
                setToPrint(postResponse.data);

            } catch (error) {
                console.error('Error:', error);
            }
        };

        fetchData();
    }, []);

    
    return (
        <>
        <div>location: {location}</div>
        <div>budget: {budget}</div>
        <div>meal: {mealType}</div>
        <div>min rating: {rating}</div>
        <button onClick={FetchStuff}>Stuff</button>

        <div className="stalls-table">
            <ul>
                <h2>Stalls:</h2>
                {toPrint.map((item, idx) => (
                <li key={idx}>{item.name}</li>
                ))}
            </ul>
            <ul>
                <h2>Address:</h2>
                {toPrint.map((item, idx) => (
                <li key={idx}>{item.address}</li>
                ))}
            </ul>
            <ul>
                <h2>Average Price:</h2>
                {toPrint.map((item, idx) => (
                <li key={idx}>{item.price}</li>
                ))}
            </ul>
            <ul>
                <h2>Rating:</h2>
                {toPrint.map((item, idx) => (
                <li key={idx}>{item.rating}</li>
                ))}
            </ul>
        </div>
        </>
    )
}

