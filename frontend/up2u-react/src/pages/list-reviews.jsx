import { useEffect, useState } from "react";
import axios from "axios";
import { StallsDropdown } from "../components/StallsDropdown";
import "../style/ListReview.css";

export function ListReviews() {
  const backendUrl = import.meta.env.VITE_BACKEND_URL;
  const [toPrint, setToPrint] = useState([]);
  const [stallId, setStallId] = useState(0); //int
  const [stall, setStall] = useState("");
  const filterDTO = {
    mealType: "all",
    location: "all",
    budget: "all",
    rating: "all",
  };
  const [reviews, setReviews] = useState([]);
  const [selectedId, setSelectedId] = useState(0);

  useEffect(() => {
    if (localStorage.getItem("username") != null) {
      // setUser(localStorage.getItem('username'));
      // setUserId(localStorage.getItem('userId'));
      // setToken(localStorage.getItem('token'));
    }
  }, []);

  // search reviews
  const fetchReview = async () => {
    try {
      const getReview = await axios.get(`${backendUrl}/api/review/${stallId}`);
      console.log("GET Success", getReview.data);
      setReviews(getReview.data);

      const postResponse = await axios.post(
        `${backendUrl}/api/filter`,
        filterDTO
      );
      setToPrint(postResponse.data);

      const selectedStall = toPrint.find((item) => item.stallId === selectedId);
      // setStall(" " + selectedStall.name);
    } catch (error) {
      console.error("Error", error);
    }
  };

  return (
    <>
      <div className="list-reviews-body">
        <StallsDropdown
          onChange={(e) => {
            setStallId(e.target.value);
            setSelectedId(parseInt(e.target.value));
          }}
        />
        <div className="search">
        <button onClick={fetchReview}>Search 🔍</button>
        <br />
        </div>
        <label>Reviews for{stall}:</label>
        <table className="review-table">
          <thead>
            <tr>
              <th>User:</th>
              <th>Rating:</th>
              <th>Review:</th>
            </tr>
          </thead>
          <tbody>
            {reviews.length === 0 ? (
              <tr>
                <td colSpan="3" className="no-reviews">
                  No reviews yet for this stall.
                </td>
              </tr>
            ) : (
              reviews.map((item, idx) => (
                <tr key={idx}>
                  <td>{item.user.username}</td>
                  <td>{item.rating}</td>
                  <td>{item.reviewDescription}</td>
                </tr>
              ))
            )}
          </tbody>
        </table>
      </div>
    </>
  );
}
