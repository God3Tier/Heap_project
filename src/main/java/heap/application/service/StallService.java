package heap.application.service;

import java.util.List;

import heap.application.dto.FilterDTO;
import heap.application.review.Review;
import heap.application.stalls.Stall;

public interface StallService {
    // Getters 
    public List<Stall> getFilteredResult(FilterDTO filterDTO);
    public List<Stall> getAllStalls();
    public List<Review> getAllReviews(Integer stallId);
    
    // Updaters
    public void updateNewReview(Integer stallId, Review review);

    // deleters
    public void deleteStall(Integer stallId);
    public void deleteMeal(Integer mealId);
}