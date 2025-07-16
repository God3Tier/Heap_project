import { useLocation } from "react-router-dom"
import { useEffect, useState } from "react";
import axios from 'axios';
import { Link } from "react-router-dom";
import '../style/Search.css'
import { GoogleMap, useJsApiLoader, Marker, InfoWindow } from "@react-google-maps/api";
import React from "react";

export function Search(){
    const backendUrl = import.meta.env.VITE_BACKEND_URL;
    const data = useLocation();
    const { location, budget, mealType, rating } = data.state || {};
    const [stalls, setStalls] = useState([]);
    const [selectedStall, setSelectedStall] = useState([]);
    const filterDTO = {
        mealType,
        location,
        budget,
        rating
    };
    const [toPrint, setToPrint] = useState([]);
    const [markers, setMarkers] = useState([]);
    const [activeMarker, setActiveMarker] = useState(null);


    // onload, access backend to get the list of items based off filterDTO
    useEffect(() => {
        const fetchData = async () => {
            try {
                // POST to /api/filter
                // and get what is passed to us
                console.log(filterDTO);
                const postResponse = await axios.post(`${backendUrl}/api/filter`, filterDTO);
                console.log('POST success:', postResponse.data);
                setToPrint(postResponse.data);

            } catch (error) {
                console.error('Error:', error);
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
            console.error("Geocode error:", response.data.status);
            return null;
            }
        } catch (error) {
            console.error("Geocode fetch error:", error);
            return null;
        }
    };

    const handleSearchSelected = async () => {
        const geocodedMarkers = await Promise.all(
            selectedStall.map(async (address) => {
            const coords = await geocodeAddress(address);
            return coords ? { address, lat: coords.lat, lng: coords.lng } : null;
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

        <div className="search-body">
            <div className="stalls-table">
                <table>
                    <thead>
                        <tr>
                            <th>Select:</th>
                            <th>Stalls:</th>
                            <th>Average Price:</th>
                            <th>Rating:</th>
                        </tr>
                    </thead>
                    <tbody>
                        {toPrint.map((item, idx) => (
                            <tr key={idx}>
                                <td>
                                    <input 
                                    type="checkbox" 
                                    value={item.address} 
                                    checked={selectedStall.includes(item.address)}
                                    onChange={handleCheckboxChange}
                                    disabled={!selectedStall.includes(item.address) && selectedStall.length >= 5}
                                    />
                                </td>
                                <td>{item.name}</td>
                                <td>{item.price}</td>
                                <td>{item.rating}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
            <div className="search-map">
                <div>map here :D</div>
                <button onClick={handleSearchSelected}>Search Selected</button>
                {isLoaded ? (
                    <GoogleMap
                        mapContainerStyle={containerStyle}
                        center={center}
                        zoom={15}
                        onLoad={onLoad}
                        onUnmount={onUnmount}
                        >
                            <>
                                {markers.map((marker, idx) => (
                                <Marker
                                    key={idx}
                                    position={{ lat: marker.lat, lng: marker.lng }}
                                    label={(idx + 1).toString()}
                                    onClick={() => setActiveMarker(idx)}
                                >
                                    {activeMarker === idx && (
                                    <InfoWindow onCloseClick={() => setActiveMarker(null)}>
                                        <div>
                                            {/* <p>{marker.toPrint}</p> */ /* need set as stall name*/}
                                            <p>{marker.address}</p>
                                        </div>
                                    </InfoWindow>
                                    )}
                                </Marker>
                                ))}
                            </>
                    </GoogleMap>
                    ) : (
                        <></>
                    )
                }
            </div>
        </div>
        
        </>
    )
}