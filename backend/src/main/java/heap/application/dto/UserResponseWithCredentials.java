package heap.application.dto;

public record UserResponseWithCredentials 
(
    UserResponse userResponse,
    String passwordHash
)
{}
