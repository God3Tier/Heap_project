import { useLocation } from "react-router-dom"
import { useEffect, useState } from "react";
import axios from 'axios';
import { Link } from "react-router-dom";
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
                // console.log(filterDTO);
                const postResponse = await axios.post(`${backendUrl}/api/filter`, filterDTO);
                // console.log('POST success:', postResponse.data);
                setToPrint(postResponse.data);

            } catch (error) {
                // console.error('Error:', error);
            }
        };

        fetchData();
    }, []);

    // checking checkbox, if more than 5 is ticked, disable the rest
    const handleCheckboxChange = (e) => {
        const address = e.target.value;
        const checked = e.target.checked;

        if (checked) {
            if (selectedStall.length >= 5) {
                alert("You can only select up to 5 items.");
                return;
            }
            setSelectedStall([...selectedStall, address]);
        } else {
            setSelectedStall(selectedStall.filter((item) => item !== address));
        }
    };

    // maps stuff
    const containerStyle = {
        width: 'auto',
        height: '500px',
    }

    const center = {
        lat: 1.2970400194243092, 
        lng: 103.8495403289578,
    }

    const map_api_key = import.meta.env.VITE_MAPS_API;
    const { isLoaded } = useJsApiLoader({
        id: 'google-map-script',
        googleMapsApiKey: map_api_key,
    })

    const [map, setMap] = React.useState(null)

    const onLoad = React.useCallback(function callback(map) {
        // const bounds = new window.google.maps.LatLngBounds(center)
        // map.fitBounds(bounds)

        setMap(map)
    }, [])

    const onUnmount = React.useCallback(function callback(map) {
        setMap(null)
    }, [])

    const geocodeAddress = async (address) => {
        const url = `https://maps.googleapis.com/maps/api/geocode/json?address=${encodeURIComponent(address)}&key=${map_api_key}`;
        try {
            const response = await axios.get(url);
            if (response.data.status === "OK") {
            const { lat, lng } = response.data.results[0].geometry.location;
            return { lat, lng };
            } else {
            // console.error("Geocode error:", response.data.status);
            return null;
            }
        } catch (error) {
            // console.error("Geocode fetch error:", error);
            return null;
        }
    };

    const handleSearchSelected = async () => {
        const geocodedMarkers = await Promise.all(
            selectedStall.map(async (address) => {
            const matched = toPrint.find((stall) => stall.address === address);
            const coords = await geocodeAddress(address);
            return coords && matched
            ? { name: matched.name, address, lat: coords.lat, lng: coords.lng }
            : null;
            })
        );
        // Remove nulls (failed geocodes)
        setMarkers(geocodedMarkers.filter((m) => m !== null));
    };
    
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
                        <th>View Review:</th>
                    </tr>
                </thead>
                <tbody>
                    {toPrint.map((item, idx) => (
                        <tr key={idx}>
                            <td>{item.name}</td>
                            <td>{item.address}</td>
                            <td>{item.price}</td>
                            <td>{item.rating}</td>
                            <td><Link to="/list-reviews"><button/></Link></td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
        </>
    )
}

