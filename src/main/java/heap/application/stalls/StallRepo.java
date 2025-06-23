package heap.application.stalls;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import heap.application.review.Review;

@Repository
public interface StallRepo extends JpaRepository <Stall, Integer> {
    // getters
    Optional<Stall> findByStallId(Integer stallId);
    List<Stall> findByLocation(String location);
    List<Stall> findByRatingGreaterThanEqual(Integer rating);
    List<Stall> findByPriceLessThanEqual(Double price);
    List<Stall> findByMeals_MealId(Integer mealId);
    
    // @Query("Select r.review_id, r.stall_id, r.user_id, r.review from review r, stall s where r.sid = :stallId")
    // List<Review> getAllReviews(@Param("stallId") Integer stallId);
}