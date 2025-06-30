package heap.application.service;

import java.util.List;

import heap.application.review.Review;
import heap.application.stalls.Stall;

public interface UserReviewService {

    //Getter
    public List<Review> getAllReviews(Integer id);
    public List<Stall> getFavourites(Integer id);
    public List<Review> getReviews (Integer id);

    // Updater 
    public void addReview(int userId, Review review);

    // Deleter
    public void deleteUser(Integer id);
}