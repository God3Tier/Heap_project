package heap.application.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.record.RecordModule;

import heap.application.dto.CreateUserDTO;
import heap.application.dto.ReviewDTO;
import heap.application.dto.UserResponse;
import heap.application.dto.UserResponseWithCredentials;
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

    private ModelMapper modelMapper = new ModelMapper().registerModule(new RecordModule());
    private final PasswordEncoder passwordEncoder;
    
    public UserReviewServiceImpl(UserRepo userRepo, StallRepo stallRepo, ReviewRepo reviewRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.stallRepo = stallRepo;
        this.reviewRepo = reviewRepo;
        this.passwordEncoder = passwordEncoder;
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
            modelMapper.map(user, UserResponse.class), user.getPassHash()
        );
    }

    /*
     * Updaters
     */

    @Transactional
    public void addReview(ReviewDTO reviewDTO) {
        Review review = modelMapper.map(reviewDTO, Review.class);
        
        Stall stall = stallRepo.findById(reviewDTO.getStallId())
        .orElseThrow(() -> new IllegalArgumentException("Stall not found"));
        review.setStall(stall);
        
        User user = userRepo.findByUserId(reviewDTO.getUserId())
                            .orElseThrow(() -> new IllegalArgumentException("user not found"));
        review.setUser(user);

        reviewRepo.save(review);
    }

    @Transactional
    public void createUser(CreateUserDTO createUserDTO) {
        User user = modelMapper.map(createUserDTO, User.class);
        if (userRepo.findByUsername(createUserDTO.username()).isPresent()){
            throw new IllegalArgumentException("Username has already been taken");
        }

        String hashPass = passwordEncoder.encode(createUserDTO.passHash());
        user.setPassHash(hashPass);

        userRepo.save(user);
    }

    /*
     * deleters
     */
    @Transactional
    public void deleteUser(Integer id) {
        User user = userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("No user found"));
        userRepo.delete(user);
    }

}