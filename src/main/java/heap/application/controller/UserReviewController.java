package heap.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import heap.application.review.Review;
import heap.application.service.UserReviewService;

@Controller
@RequestMapping("/user")
public class UserReviewController {
    
    private final UserReviewService userReviewService;
    
    public UserReviewController (UserReviewService userReviewService) {
        this.userReviewService = userReviewService;
    }
    
    @PostMapping("/{id}")
    public ResponseEntity<?> addReview(@PathVariable int id, Review review) {
        userReviewService.addReview(id, review);
        return new ResponseEntity<>("Review has been added succesfully", HttpStatus.OK);
    }
    

}