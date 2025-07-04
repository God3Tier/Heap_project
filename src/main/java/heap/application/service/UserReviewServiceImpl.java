package heap.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import heap.application.dto.ReviewDTO;
import heap.application.dto.UserResponseWithCredentials;
import heap.application.mapper.MapperModel;
import heap.application.review.Review;
import heap.application.review.ReviewRepo;
import heap.application.stalls.Stall;
import heap.application.stalls.StallRepo;
import heap.application.user.User;
import heap.application.user.UserRepo;



/*
    
*/
@Service("UserReviewService")
public class UserReviewServiceImpl implements UserReviewService {
    
    private final UserRepo userRepo;
    private final StallRepo stallRepo;
    private final ReviewRepo reviewRepo;
    private final MapperModel mapperModel;
    
    public UserReviewServiceImpl(UserRepo userRepo, StallRepo stallRepo, ReviewRepo reviewRepo, MapperModel mapperModel) {
        this.userRepo = userRepo;
        this.stallRepo = stallRepo;
        this.reviewRepo = reviewRepo;
        this.mapperModel = mapperModel;
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

    public List<Review> getReviews (Integer id) {
        User user = userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("No user found"));
        return user.getReviews();
    }

    public UserResponseWithCredentials getUserCredentialsByUsername(String username) {
        User user = userRepo.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("No user found"));

        return new UserResponseWithCredentials(
            mapperModel.userResponse(user), user.getPassHash()
        );
    }

    /*
     * Updaters
     */

    @Transactional
    public void addReview(ReviewDTO reviewDTO) {
        Review review = mapperModel.toReview(reviewDTO);
        
        Stall stall = stallRepo.findById(reviewDTO.stallId())
        .orElseThrow(() -> new IllegalArgumentException("Stall not found"));
        review.setStall(stall);
        
        User user = userRepo.findByUserId(reviewDTO.userId())
                            .orElseThrow(() -> new IllegalArgumentException("user not found"));
        review.setUser(user);

        reviewRepo.save(review);
    }

    /*
     * deleters
     */
    public void deleteUser(Integer id) {

    }

}