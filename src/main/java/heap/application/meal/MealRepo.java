package heap.application.meal;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepo extends JpaRepository<Meal, Integer> {
    public Optional<Meal> findById(Integer id);
    public Optional<Meal> findByMealName(@Param("mealName") String mealName);
}