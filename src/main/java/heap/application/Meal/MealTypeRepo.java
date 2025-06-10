package heap.application.Meal;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MealTypeRepo extends JpaRepository<MealType, Integer> {
    public Optional<MealType> findById(Integer id);
    @Query("SELECT m FROM MealType m JOIN FETCH m.stall WHERE m.mealName = :mealName")
    public Optional<MealType> findByMealName(@Param("mealName") String mealName);
}c