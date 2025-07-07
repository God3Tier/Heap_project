package heap.application.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import heap.application.dto.CreateUserDTO;
import heap.application.dto.ReviewDTO;
import heap.application.dto.UserResponse;
import heap.application.review.Review;
import heap.application.user.Roles;
import heap.application.user.User;

@Mapper(componentModel = "spring")
public interface MapperModel {
    
    public UserResponse userResponse(User user);

    @Mapping(target = "reviewId", ignore = true)
    @Mapping(source = "userId", target = "user.userId")
    @Mapping(source = "stallId", target = "stall.stallId")
    public Review toReview(ReviewDTO reviewDTO);

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "favourites", ignore = true)
    public User createUser(CreateUserDTO createUSerDTO);


    default List<Roles> mapRoles(List<String> roles) {
        if (roles == null) {
            return new ArrayList<>();
        }

        return roles.stream()
                    .map(Roles::valueOf)
                    .collect(Collectors.toCollection(ArrayList::new));
    }
}
