package heap.application.stalls;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StallRepo extends JpaRepository <Stall, Integer> {
    
    // getters
    Optional<Stall> findByStallId(Integer stallId);
    List<Stall> findByLocation(String location);
    List<Stall> findByRatingGreaterThanEqual(Integer rating);
    List<Stall> findByPriceLessThanEqual(Double price);
    List<Stall> findByMeals_MealId(Integer mealId);
}