package heap.application.stalls;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.List;

@Repository
public interface StallRepo extends JpaRepository <Stall, Integer> {
    Optional<Stall> findByStallId(Integer stallId);
    
    List<Stall> findByLocation(String location);
    
    List<Stall> findByRatingGreaterThanEqual(Integer rating);
    
    List<Stall> findByPriceLessThanEqual(Double price);
    
    List<Stall> findByMealTypes_MealId(Integer mealId);
}