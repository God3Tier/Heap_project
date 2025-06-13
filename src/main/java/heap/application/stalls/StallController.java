package heap.application.stalls;

import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    
    // @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/meals")
    public List<MealType> selectAllMeals() {
        return mealTypeRepo.findAll();
    } 
    
    
    // how to handle input from frontend using springboot? 
    // Holy this can be cut down
    // When we redirect, is it expected for 
    @GetMapping("/{mealType}/{location}/{budget}/{rating}")
    public List<Stall> getValidRestaurants(@PathVariable String mealType, @PathVariable String location, @PathVariable String budget, @PathVariable String rating) {
        List<Stall> meals;
        if (mealType.toLowerCase().equals("all")) {
            meals = stallRepo.findAll();
        } else {
            meals = stallRepo.findByMealTypes_MealId(mealTypeRepo.findByMealName(mealType).get().getMealId());
        }
        
        List<Stall> locationList; 
        if (location.toLowerCase().equals("all")) {
            locationList = stallRepo.findAll();
        } else {
            locationList = stallRepo.findByLocation(location);
        }
        
        List<Stall> budgetList;
        if (budget.toLowerCase().equals("all")) {
            budgetList = stallRepo.findAll();
        } else {
            budgetList = stallRepo.findByPriceLessThanEqual(Double.parseDouble(budget));
        }
        
        List<Stall> ratingList;
        if (rating.toLowerCase().equals("all")) {
            ratingList = stallRepo.findAll();
        } else {
            ratingList = stallRepo.findByRatingGreaterThanEqual(Integer.parseInt(rating));
        }
        
        log.info("{}\n{}\n{}\n{}", meals, locationList, budgetList, ratingList);
        
        return meals.stream()
                    .distinct()
                    .filter(a -> locationList.contains(a)&& budgetList.contains(a) && ratingList.contains(a))
                    .collect(Collectors.toCollection(ArrayList::new));

    }
}
