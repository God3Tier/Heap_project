package heap.application.service;

import java.util.List;

import heap.application.dto.CreateUserDTO;
import heap.application.dto.ReviewDTO;
import heap.application.dto.UserResponseWithCredentials;
import heap.application.review.Review;
import heap.application.stalls.Stall;

public interface UserReviewService {

    //Getter
    public List<Review> getAllReviews(Integer id);
    public List<Stall> getFavourites(Integer id);
    public List<Review> getReviews (Integer id);
    public UserResponseWithCredentials getUserCredentialsByUsername(String username);

    // Updater 
    public void addReview(ReviewDTO reviewDTO);
    public void createUser(CreateUserDTO createUserDTO);

    // Deleter
    public void deleteUser(Integer id);
}