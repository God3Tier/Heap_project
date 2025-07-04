package heap.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import heap.application.dto.ReviewDTO;
import heap.application.dto.UserResponse;
import heap.application.review.Review;
import heap.application.user.User;

@Mapper(componentModel = "spring")
public interface MapperModel {
    
    public UserResponse userResponse(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "userId", target = "user.userId")
    @Mapping(source = "stallId", target = "stall.stallId")
    public Review toReview(ReviewDTO reviewDTO);

}
