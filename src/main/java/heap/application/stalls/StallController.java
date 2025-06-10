package heap.application.stalls;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import heap.application.stalls.Meal.MealType;
import heap.application.stalls.Meal.MealTypeRepo;

@RestController
@RequestMapping("")
public class StallController {
    
    private final StallRepo stallRepo;
    private final MealTypeRepo mealTypeRepo;
    
    private final Logger log = LoggerFactory.getLogger(StallController.class);
    
    public StallController(StallRepo stallRepo, MealTypeRepo mealTypeRepo) {
        this.stallRepo = stallRepo;
        this.mealTypeRepo = mealTypeRepo;
    }
    
    @GetMapping("/stalls")
    public List<Stall> selectAllStalls() {
        return stallRepo.findAll();
    }
    
    @GetMapping("/meals")
    public List<MealType> selectAllMeals() {
        return mealTypeRepo.findAll();
    }
    
    
    // how to handle input from frontend using springboot? 
    // Holy this can be cut down
    // When we redirect, is it expected for 
    @GetMapping("/{mealType}/{location}/{budget}/{rating}")
    public List<Stall> getValidRestaurants(@PathVariable String mealType, @PathVariable String location, @PathVariable double budget, @PathVariable int rating) {
        List<Stall> meals = stallRepo.findByMealTypes_MealId(mealTypeRepo.findByMealName(mealType).get().getMealId());
        List<Stall> locationList = stallRepo.findByLocation(location);
        List<Stall> budgetList = stallRepo.findByPriceLessThanEqual(budget);
        List<Stall> ratingList = stallRepo.findByRatingGreaterThanEqual(rating);
        
        log.info("{}\n{}\n{}\n{}", meals, locationList, budgetList, ratingList);
        
        return meals.stream()
                    .distinct()
                    .filter(a -> locationList.contains(a) && budgetList.contains(a) && ratingList.contains(a))
                    .collect(Collectors.toCollection(ArrayList::new));

    }
}
