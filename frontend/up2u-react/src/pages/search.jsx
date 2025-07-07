import { useLocation } from "react-router-dom"
import { useEffect, useState } from "react";
import axios from 'axios';

function FetchStuff(){
    axios.get('http://localhost:8080/meals').then(response => {
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

    useEffect(() => {
        const fetchData = async () => {
            try {
                // First, POST to /filter
                console.log(filterDTO);
                const postResponse = await axios.post('http://localhost:8080/filter', filterDTO);
                console.log('POST success:', postResponse.data);

                // Then, GET from /filter
                const getResponse = await axios.get('http://localhost:8080/filter');
                setStalls(getResponse.data);
                console.log('GET success:', getResponse.data);
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

        <div>
            <h2>Stalls:</h2>
            <ul>
                {stalls.map((stall, idx) => (
                <li key={idx}>{stall.name}</li>
                ))}
            </ul>
        </div>
        </>
    )
}

