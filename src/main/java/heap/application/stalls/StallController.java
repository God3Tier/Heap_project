package heap.application.stalls;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class StallController {
    
    private StallRepo repo;
    
    
    public StallController(StallRepo repo) {
        this.repo = repo;
    }
    
    // how to handle input from frontend using springboot? 
    // Holy this can be cut down
    // When we redirect, is it expected for 
    @GetMapping("/{mealId}{location}{budget}{rating}")
    public List<Stall> getValidRestaurants(@PathVariable int mealId, @PathVariable String location, @PathVariable int budget, @PathVariable int rating) {
        List<Stall> mealList = repo.findByMeal_Id(mealId);
        List<Stall> locationList = repo.findByLocation(location);
        List<Stall> budgetList = repo.findByMeal_Id(budget);
        List<Stall> ratingList = repo.findByMeal_Id(rating);
        
        return mealList.stream()
                       .filter(a -> locationList.contains(a) && budgetList.contains(a) && ratingList.contains(a))
                       .collect(Collectors.toCollection(ArrayList::new));
    }
    
    
}
