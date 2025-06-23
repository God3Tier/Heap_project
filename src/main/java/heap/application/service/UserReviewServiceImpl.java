package heap.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import heap.application.review.Review;
import heap.application.review.ReviewRepo;
import heap.application.stalls.Stall;
import heap.application.user.User;
import heap.application.user.UserRepo;



/*
    
*/
@Service("UserReviewService")
public class UserReviewServiceImpl implements UserReviewService {
    private final UserRepo userRepo;
    private final ReviewRepo reviewRepo;
    
    public UserReviewServiceImpl(UserRepo userRepo, ReviewRepo reviewRepo) {
        this.userRepo = userRepo;
        this.reviewRepo = reviewRepo;
    }
    /*
     * Getters
     */

    public List<Review> getAllReviews(Integer id) {
        User user = userRepo.findByUserId(id).orElseThrow(() -> new IllegalArgumentException("No user found"));
        return user.getReviews();
    }

    public List<Stall> getFavourites(Integer id) {
        User user = userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        return user.getFavourites();
    }

    /*
     * Updaters
     */

    @Transactional
    public void addReview(int userId, Review review) {
        User user = userRepo.findByUserId(userId)
                            .orElseThrow(() -> new IllegalArgumentException("user not found"));
                            
        user.getReviews().add(review);     
    }

    /*
     * deleters
     */
    public void deleteUser(Integer id) {
        
    }

}