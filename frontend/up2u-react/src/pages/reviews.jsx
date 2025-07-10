import '../style/Reviews.css'
import { useEffect, useState } from 'react';
import axios from 'axios';

export function Reviews(){
    const [rating, setRating] = useState(0); //int
    const [stallId, setStallId] = useState(0); //int
    const [reviewDescription, setReviewDescription] = useState(""); 
    const [user, setUser] = useState("Unknown");
    const [userId, setUserId] = useState(0); //int
    const [toPrint, setToPrint] = useState([]);
    const filterDTO = {
        mealType: "all",
        location: "all",
        budget: "all",
        rating: "all"
    };
    const reviewDTO = {
        reviewId: null,
        rating,
        stallId,
        userId,
        reviewDescription
    }

    // on page load
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

        if(localStorage.getItem('username') != null){
            setUser(localStorage.getItem('username'));
            setUserId(localStorage.getItem('userId'));
        }
    },[]);

    // to send
    const postReview = async() =>{
        try{
            console.log(reviewDTO);
            const postResponse = await axios.post('http://localhost:8080/user/add_review', reviewDTO);
            console.log('POST success:', postResponse.data);

        } catch(error){
            console.error('Error', error);
        }
    };

    return(
        <>
        <div className="reviews-body">
            <form>
                <label>User:</label><input value={user} disabled/><br/>
                
                <label>Stall:</label>
                <select defaultValue="0"
                        onChange={e => setStallId(e.target.value)}>
                    <option value="0" disabled>Select Stall</option>
                    {toPrint.map((item, idx) => (
                    <option value={item.stallId} key={idx}>{item.name}</option>
                    ))}
                </select><br/>

                <label>Rating:</label>
                <select defaultValue="0"
                        onChange={e => setRating(e.target.value)}>
                    <option value="0" disabled>Select Rating</option>
                    <option value="1">1 Star</option>
                    <option value="2">2 Star</option>
                    <option value="3">3 Star</option>
                    <option value="4">4 Star</option>
                    <option value="5">5 Star</option>
                </select><br/>

                <label>Review:</label>
                <textarea onChange={e => setReviewDescription(e.target.value)}></textarea><br/>
            </form>
            <button onClick={postReview}>Test</button>
        </div>
        </>
    )
}