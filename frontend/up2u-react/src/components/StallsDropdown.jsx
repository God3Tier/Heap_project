import { useEffect, useState } from "react"
import axios from "axios";


export function StallsDropdown({ onChange }){
    const backendUrl = import.meta.env.VITE_BACKEND_URL;
    const [toPrint, setToPrint] = useState([]);
    const filterDTO = {
        mealType: "all",
        location: "all",
        budget: "all",
        rating: "all"
    };

    useEffect(() => {
        const fetchData = async () => {
            try {
                // POST to /api/filter
                // and get what is passed to us
                const postResponse = await axios.post(`${backendUrl}/api/filter`, filterDTO);
                console.log('POST success:', postResponse.data);
                setToPrint(postResponse.data);

            } catch (error) {
                console.error('Error:', error);
            }
        };

        fetchData();
    },[]);

    return(
        <>
        <label>Stall:</label>
        <select defaultValue="0" onChange={onChange}>
            <option value="0" disabled>Select Stall</option>
            {toPrint.map((item, idx) => (
            <option value={item.stallId} key={idx}>{item.name}</option>
            ))}
        </select>
        </>
    )
}