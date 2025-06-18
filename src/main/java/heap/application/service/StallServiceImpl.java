package heap.application.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import heap.application.dto.FilterDTO;
import heap.application.meal.MealRepo;
import heap.application.review.Review;
import heap.application.meal.Meal;
import heap.application.stalls.Stall;
import heap.application.stalls.StallRepo;

@Service("StallService")
public class StallServiceImpl implements StallService {
    
    private final StallRepo stallRepo;
    private final MealRepo mealRepo;
    
    
    public StallServiceImpl(StallRepo stallRepo, MealRepo mealRepo) {
        this.stallRepo = stallRepo;
        this.mealRepo = mealRepo;
    }
    
    @Transactional
    public void updateNewReview(Integer stallId, Review review) {
        Stall stall = stallRepo.findByStallId(stallId).orElseThrow(() -> new IllegalArgumentException("Stall not found"));
        stall.getReviews().add(review);
    }
    
    public List<Stall> getAllStalls() {
        return stallRepo.findAll();
    }
    
    public List<Review> getAllReviews(Integer stallId) {
        Stall stall = stallRepo.findByStallId(stallId).orElseThrow(() -> new IllegalArgumentException("Stall not found"));
        return stall.getReviews();
    }
    
    public List<Stall> getFilteredResult(FilterDTO filterDTO) {
        List<Stall> meals;
        if (filterDTO.mealType().toLowerCase().equals("all")) {
            meals = stallRepo.findAll();
        } else {
            Meal meal = mealRepo.findByMealName(filterDTO.mealType()).orElseThrow(() -> new IllegalArgumentException("no meal type found"));
            meals = stallRepo.findByMeals_MealId(meal.getMealId());
        }
        
        List<Stall> locationList; 
        if (filterDTO.location().toLowerCase().equals("all")) {
            locationList = stallRepo.findAll();
        } else {
            locationList = stallRepo.findByLocation(filterDTO.location());
        }
        
        List<Stall> budgetList;
        if (filterDTO.budget().toLowerCase().equals("all")) {
            budgetList = stallRepo.findAll();
        } else {
            budgetList = stallRepo.findByPriceLessThanEqual(Double.parseDouble(filterDTO.budget()));
        }
        
        List<Stall> ratingList;
        if (filterDTO.rating().toLowerCase().equals("all")) {
            ratingList = stallRepo.findAll();
        } else {
            ratingList = stallRepo.findByRatingGreaterThanEqual(Integer.parseInt(filterDTO.rating()));
        }
        
        return meals.stream()
                    .distinct()
                    .filter(a -> locationList.contains(a)&& budgetList.contains(a) && ratingList.contains(a))
                    .collect(Collectors.toCollection(ArrayList::new));
    }
}