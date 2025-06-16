package heap.application.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import heap.application.meal.Meal;
import heap.application.meal.MealRepo;
import heap.application.stalls.Stall;
import heap.application.stalls.StallRepo;

@RestController
@RequestMapping("/request")
public class StallController {
    private final StallRepo stallRepo;
    private final MealRepo mealRepo;
    
    private final Logger log = LoggerFactory.getLogger(StallController.class);
    
    public StallController(StallRepo stallRepo, MealRepo mealRepo) {
        this.stallRepo = stallRepo;
        this.mealRepo = mealRepo;
    }
    
    @GetMapping("/stalls")
    public List<Stall> selectAllStalls() {
        return stallRepo.findAll();
    }
    
    @GetMapping("/meals")
    public List<Meal> selectAllMeals() {
        return mealRepo.findAll();
    } 
    
    
    // how to handle input from frontend using springboot? 
    // Holy this can be cut down
    // When we redirect, is it expected for 
    @GetMapping("/{location}/{budget}/{mealType}/{rating}")
    public List<Stall> getValidRestaurants(@PathVariable String mealType, @PathVariable String location, @PathVariable String budget, @PathVariable String rating) {
        List<Stall> meals;
        if (mealType.toLowerCase().equals("all")) {
            meals = stallRepo.findAll();
        } else {
            meals = stallRepo.findByMealTypes_MealId(mealRepo.findByMealName(mealType).get().mealId());
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


