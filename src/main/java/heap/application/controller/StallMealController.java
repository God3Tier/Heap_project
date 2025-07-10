package heap.application.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import heap.application.dto.FilterDTO;
import heap.application.review.Review;
import heap.application.service.StallService;
import heap.application.stalls.Stall;

@RestController
@RequestMapping("/api")
public class StallMealController {

    private final StallService stallService;

    private final Logger log = LoggerFactory.getLogger(StallMealController.class);
    
    public StallMealController(StallService stallService) {
        this.stallService = stallService;
    }
    
    /*
     * Getters
     */
    @GetMapping("/stalls")
    public List<Stall> selectAllStalls() {
        return stallService.getAllStalls();
    }

    @GetMapping("/review/{id}")
    public List<Review> retrieveAllReviews(@PathVariable("id") Integer stallId) {
        return stallService.getAllReviews(stallId);
    }
    
    
    /*
    * Post
    */
    @PostMapping("/update_stall/{id}")
    public void updateStallRating(@PathVariable("id") Integer stallId) {
        stallService.updateNewReview(stallId);
    }
    
    @PostMapping ("/filter")
    public List<Stall> getValidRestaurants(@RequestBody FilterDTO filterDTO) {
        return stallService.getFilteredResult(filterDTO);
    }
    /*
     * Deleters
     */
    @DeleteMapping("delete/meal/{id}") 
    public ResponseEntity<?> deleteStall(@PathVariable("id") Integer id) {
        stallService.deleteStall(id);
        return new ResponseEntity<>("Successfuly deleted stall", HttpStatus.OK);
    } 

    @DeleteMapping("delete/stall/{id}")
    public ResponseEntity<?> deleteMeal(@PathVariable("id")Integer id) {
        stallService.deleteMeal(id);
        return new ResponseEntity<>("Successfuly deleted stall", HttpStatus.OK);
    }
}


