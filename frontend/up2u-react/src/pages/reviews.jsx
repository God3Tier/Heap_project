import '../style/Reviews.css'
import { useEffect, useState } from 'react';
import axios from 'axios';
import { StallsDropdown } from '../components/StallsDropdown';
import { RatingDropdown } from '../components/RatingDropdown';

export function Reviews(){
    const backendUrl = import.meta.env.VITE_BACKEND_URL;
    const [rating, setRating] = useState(0); //int
    const [stallId, setStallId] = useState(0); //int
    const [reviewDescription, setReviewDescription] = useState(""); 
    const [user, setUser] = useState("Unknown");
    const [userId, setUserId] = useState(0); //int
    const [token, setToken] = useState("");
    const isLoggedIn = !!token;

    const reviewDTO = {
        reviewId: null,
        rating,
        stallId,
        userId,
        reviewDescription
    };

    // on page load
    useEffect(() => {
        if(localStorage.getItem('username') != null){
            setUser(localStorage.getItem('username'));
            setUserId(localStorage.getItem('userId'));
            setToken(localStorage.getItem('token'));
        }
        // else{
        //     window.location.href = "/";
        // }
    },[]);
    const home = async() => {
        window.location.href = "/";
    };

    // to post reviews
    const postReview = async() =>{
        try{
            // console.log(reviewDTO);
            const postResponse = await axios.post(`${backendUrl}/user/add_review`, reviewDTO, {
            headers: {Authorization: `Bearer ${token}`}});
            const updateStall = await axios.post(`${backendUrl}/api/update_stall/${stallId}`);
            // console.log('POST success:', postResponse.data);
            alert("Review Given");
            window.location.reload();

        } catch(error){
            // console.error('Error', error);
        }
    };

    return (
        <>
          {isLoggedIn ? (
            <div className="reviews-body">
              {/* Heading */}
              <h2 className="page-title">Leave a Review</h2>
      
              {/* Review Form Section */}
              <section className="review-form-section">
                <form>
                  <div className="form-group">
                    <label>User:</label>
                    <input value={user} disabled />
                  </div>
      
                  <div className="form-group">
                    <StallsDropdown onChange={e => setStallId(e.target.value)} />
                  </div>
      
                  <div className="form-group">
                    <RatingDropdown onChange={e => setRating(e.target.value)} />
                  </div>
      
                  <div className="form-group">
                    <label>Review:</label>
                    <textarea onChange={e => setReviewDescription(e.target.value)} rows="4"></textarea>
                  </div>
      
                  <button type="button" onClick={postReview}>Give Review</button>
                </form>
              </section>
          
            </div>
          ) : (
            <>
              You have no access to this page
              <button onClick={home}>Back to Home</button>
            </>
          )}
        </>
      );
}      