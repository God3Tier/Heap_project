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
            <table>
                <thead>
                    <tr>
                        <th>Stalls:</th>
                        <th>Address:</th>
                        <th>Average Price:</th>
                        <th>Rating:</th>
                    </tr>
                </thead>
                <tbody>
                    {toPrint.map((item, idx) => (
                        <tr key={idx}>
                            <td>{item.name}</td>
                            <td>{item.address}</td>
                            <td>{item.price}</td>
                            <td>{item.rating}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
        </>
    )
}

