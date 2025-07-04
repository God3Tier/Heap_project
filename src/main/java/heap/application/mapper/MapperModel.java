package heap.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import heap.application.dto.ReviewDTO;
import heap.application.dto.UserResponse;
import heap.application.review.Review;
import heap.application.stalls.Stall;
import heap.application.user.User;

@Mapper(componentModel = "spring")
public interface MapperModel {
    
    public UserResponse userResponse(User user);

    @Mapping(target = "reviewId", ignore = true)
    @Mapping(source = "userId", target = "user.userId")
    @Mapping(source = "stallId", target = "stall.stallId")
    public Review toReview(ReviewDTO reviewDTO);


    // default User mapUser(Integer userId) {
    //     if (userId == null) return null;
    //     User user = new User();
    //     user.setUserId(userId);
    //     return user;
    // }

    // default Stall mapStall(Integer stallId) {
    //     if (stallId == null) return null;
    //     Stall stall = new Stall();
    //     stall.setStallId(stallId);
    //     return stall;
    // }

}
