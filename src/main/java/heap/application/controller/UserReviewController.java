package heap.application.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import heap.application.dto.ReviewDTO;
import heap.application.review.Review;
import heap.application.service.UserReviewService;
import heap.application.stalls.Stall;

@Controller
@RequestMapping("/user")
public class UserReviewController {
    
    private final UserReviewService userReviewService;
    
    public UserReviewController (UserReviewService userReviewService) {
        this.userReviewService = userReviewService;
    }
    
    /*
     * Get
     */
    @GetMapping("/reviews")
    public List<Review> getReviews(Integer id) {
        return userReviewService.getAllReviews(id);
    }

    @GetMapping("/favourites")
    public List<Stall> getFavouristes(Integer id) {
        return userReviewService.getFavourites(id);
    }

    /*
     * Post
     */
    @PostMapping("/update_user")
    public ResponseEntity<?> addReview(@RequestBody ReviewDTO reviewDTO) {
        userReviewService.addReview(reviewDTO);
        return new ResponseEntity<>("Review has been added succesfully", HttpStatus.OK);
    }

    /*
     * Delete
     * TODO: To be functional when spring security is set up
     */
    @DeleteMapping("delete/user/{id}") 
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        userReviewService.deleteUser(id);
        return new ResponseEntity<>("User has been deleted succesfully", HttpStatus.OK);
    } 

}