import { useEffect, useState } from "react";
import axios from "axios";


export function ListReviews(){
    const [toPrint, setToPrint] = useState([]);
    const [stallId, setStallId] = useState(0); //int
    const [stall, setStall] = useState("");
    const filterDTO = {
        mealType: "all",
        location: "all",
        budget: "all",
        rating: "all"
    };
    const [reviews, setReviews] = useState([]);
    const [selectedId, setSelectedId] = useState(0);

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
                console.error('Error', error);
            }
        };

        fetchData();

        if(localStorage.getItem('username') != null){
            // setUser(localStorage.getItem('username'));
            // setUserId(localStorage.getItem('userId'));
            // setToken(localStorage.getItem('token'));
        }
    },[]);

    // search reviews
    const fetchReview = async () =>{
        try{
            const getReview = await axios.get(`http://localhost:8080/api/review/${stallId}`);
            console.log('GET Success', getReview.data);
            setReviews(getReview.data);

            const selectedStall = toPrint.find(item => item.stallId === selectedId);
            setStall(" " + selectedStall.name);
        } catch(error) {
            console.error('Error', error);
        }
    };


    return(
        <>
        <div className="list-reviews-body">
            <label>Stall:</label>
            <select defaultValue="0"
                    onChange={e => {
                        setStallId(e.target.value);
                        setSelectedId(parseInt(e.target.value));
                        
                    }}>
                <option value="0" disabled>Select Stall</option>
                {toPrint.map((item, idx) => (
                <option value={item.stallId} key={idx}>{item.name}</option>
                ))}
            </select>
            <button onClick={fetchReview}>Search</button><br/>

            <label>Reviews for{stall}:</label>
            <table>
                <thead>
                    <tr>
                        <th>User:</th>
                        <th>Rating:</th>
                        <th>Review:</th>
                        <th>Action:</th>
                    </tr>
                </thead>
                <tbody>
                    {reviews.map((item, idx) => (
                        <tr key={idx}>
                            <td>{item.user.username}</td>
                            <td>{item.rating}</td>
                            <td>{item.reviewDescription}</td>
                            <td>Dropdown</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
        </>
    )
}