package heap.application.service;

import java.util.List;

import heap.application.review.Review;

public interface UserReviewService {
    //Getter
    public List<Review> getAllReviews(Integer id)   ;
    // Updater
    public void addReview(int userId, Review review);
    // public void createUser();
}