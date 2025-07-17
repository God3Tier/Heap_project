package heap.application.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import heap.application.dto.CreateUserDTO;
import heap.application.dto.DeleteReviewDTO;
import heap.application.dto.ReviewDTO;
import heap.application.dto.UpdateReviewDTO;
import heap.application.review.Review;
import heap.application.service.UserReviewService;
import heap.application.stalls.Stall;

@Controller
@RequestMapping("/user")
public class UserReviewController {
    
    private final UserReviewService userReviewService;

    private final Logger log = LoggerFactory.getLogger(UserReviewController.class);
    
    public UserReviewController (UserReviewService userReviewService) {
        this.userReviewService = userReviewService;
    }
    
    /*
     * Get
     */
    @GetMapping("/reviews/{id}")
    public List<Review> getReviews(@PathVariable("id") Integer id) {
        return userReviewService.getAllReviews(id);
    }

    @GetMapping("/favourites/{id}")
    public List<Stall> getFavouristes(@PathVariable("id") Integer id) {
        return userReviewService.getFavourites(id);
    }

    /*
     * Post
     */

    @PostMapping("/add_review")
    public ResponseEntity<?> addReview(@RequestBody ReviewDTO reviewDTO) {
        userReviewService.addReview(reviewDTO);
        return new ResponseEntity<>("Review has been added succesfully", HttpStatus.OK);
    }

    @PostMapping("/create_user")
    public ResponseEntity<?> createUser(@RequestBody CreateUserDTO createUserDTO){
        userReviewService.createUser(createUserDTO);
        return new ResponseEntity<>("User successfully created", HttpStatus.OK);
    }

    @PostMapping("/update_review")
    public ResponseEntity<?> updateReview(@RequestBody UpdateReviewDTO updateReviewDTO) {
        userReviewService.updateReview(updateReviewDTO);
        return new ResponseEntity<>("Review updated syccessfully", HttpStatus.OK);
    }

    /*
     * Delete
     */
    @DeleteMapping("delete/{id}") 
    public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id) {
        userReviewService.deleteUser(id);
        return new ResponseEntity<>("User has been deleted succesfully", HttpStatus.OK);
    } 

    @DeleteMapping("delete/review")
    public ResponseEntity<?> deleteReview(@RequestBody DeleteReviewDTO deleteReviewDTO) {
        userReviewService.deleteReview(deleteReviewDTO);
        return new ResponseEntity<>("Review has been deleted succesfully", HttpStatus.OK);
    }

}